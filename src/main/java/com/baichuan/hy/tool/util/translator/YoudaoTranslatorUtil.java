package com.baichuan.hy.tool.util.translator;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 翻译工具类
 */
@Slf4j
public class YoudaoTranslatorUtil implements Translator {

    private static final String APP_KEY = "2ddff67aec7486be";     // 您的应用ID
    private static final String APP_SECRET = "QzzVBnraFlkWzD9LPTkAqfOlo3ESFYbV";  // 您的应用密钥
    private static OkHttpClient httpClient = new OkHttpClient.Builder().build();
    /**
     * @param word
     * @param sourceLanguage 源语言 默认auto 英文为 en
     * @param targetLanguage 目标语言 默认zh-CN
     * @return
     */
    public String translate(String word, String sourceLanguage, String targetLanguage) {
        try {
            if (StringUtils.isEmpty(sourceLanguage)) {
                sourceLanguage = "auto";
            }
            if (StringUtils.isEmpty(targetLanguage)) {
                targetLanguage = "zh-CN";
            }
            // 添加请求参数
            Map<String, String[]> params = createRequestParams(word, sourceLanguage, targetLanguage);
            // 添加鉴权相关参数
            addAuthParams(APP_KEY, APP_SECRET, params);
            // 请求api服务
            byte[] result = doPost("https://openapi.youdao.com/api", null, params, "application/json");
            // 打印返回结果
            StringBuilder response = new StringBuilder();
            if (result != null) {
                response.append(new String(result, StandardCharsets.UTF_8));
            }

            return parseResult(response.toString());
        } catch (Exception e) {
            log.error("访问有道翻译异常", e);
            return "访问有道翻译接口异常：" + e.getMessage();
        }
    }

    private static String parseResult(String inputJson) {
        JSONObject styeNode = JSONUtil.parseObj(inputJson);
        Iterator<Map.Entry<String, Object>> it = styeNode.entrySet().iterator();
        String re = "";
        while (it.hasNext()) {
            Map.Entry<String, Object> map = it.next();
            String k = map.getKey();
            Object v = map.getValue();
            if ("translation".equals(k) && !ObjectUtil.isEmpty(v)) {
                JSONArray arr = JSONUtil.parseArray(v.toString());
                re = arr.get(0).toString();
                return re;
            }
        }
        return re;
    }

    private static Map<String, String[]> createRequestParams(String q, String from, String to) {
        /*
         * note: 将下列变量替换为需要请求的参数
         * 取值参考文档: https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
         */
        String vocabId = "out_id";

        return new HashMap<String, String[]>() {{
            put("q", new String[]{q});
            put("from", new String[]{from});
            put("to", new String[]{to});
            put("vocabId", new String[]{vocabId});
        }};
    }

    /**
     * 添加鉴权相关参数 -
     * appKey : 应用ID
     * salt : 随机值
     * curtime : 当前时间戳(秒)
     * signType : 签名版本
     * sign : 请求签名
     *
     * @param appKey    您的应用ID
     * @param appSecret 您的应用密钥
     * @param paramsMap 请求参数表
     */
    public static void addAuthParams(String appKey, String appSecret, Map<String, String[]> paramsMap)
            throws NoSuchAlgorithmException {
        String[] qArray = paramsMap.get("q");
        if (qArray == null) {
            qArray = paramsMap.get("img");
        }
        StringBuilder q = new StringBuilder();
        for (String item : qArray) {
            q.append(item);
        }
        String salt = UUID.randomUUID().toString();
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        String sign = calculateSign(appKey, appSecret, q.toString(), salt, curtime);
        paramsMap.put("appKey", new String[]{appKey});
        paramsMap.put("salt", new String[]{salt});
        paramsMap.put("curtime", new String[]{curtime});
        paramsMap.put("signType", new String[]{"v3"});
        paramsMap.put("sign", new String[]{sign});
    }

    /**
     * 计算鉴权签名 -
     * 计算方式 : sign = sha256(appKey + input(q) + salt + curtime + appSecret)
     *
     * @param appKey    您的应用ID
     * @param appSecret 您的应用密钥
     * @param q         请求内容
     * @param salt      随机值
     * @param curtime   当前时间戳(秒)
     * @return 鉴权签名sign
     */
    public static String calculateSign(String appKey, String appSecret, String q, String salt, String curtime)
            throws NoSuchAlgorithmException {
        String strSrc = appKey + getInput(q) + salt + curtime + appSecret;
        return encrypt(strSrc);
    }

    private static String encrypt(String strSrc) throws NoSuchAlgorithmException {
        byte[] bt = strSrc.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bt);
        byte[] bts = md.digest();
        StringBuilder des = new StringBuilder();
        for (byte b : bts) {
            String tmp = (Integer.toHexString(b & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

    private static String getInput(String input) {
        if (input == null) {
            return null;
        }
        String result;
        int len = input.length();
        if (len <= 20) {
            result = input;
        } else {
            String startStr = input.substring(0, 10);
            String endStr = input.substring(len - 10, len);
            result = startStr + len + endStr;
        }
        return result;
    }

    public static byte[] doPost(String url, Map<String, String[]> header, Map<String, String[]> body, String expectContentType) {
        Request.Builder builder = new Request.Builder().url(url);
        addHeader(builder, header);
        addBodyParam(builder, body, "POST");
        return requestExec(builder.build(), expectContentType);
    }

    private static void addHeader(Request.Builder builder, Map<String, String[]> header) {
        if (header == null) {
            return;
        }
        for (String key : header.keySet()) {
            String[] values = header.get(key);
            if (values != null) {
                for (String value : values) {
                    builder.addHeader(key, value);
                }
            }
        }
    }

    private static void addBodyParam(Request.Builder builder, Map<String, String[]> body, String method) {
        if (body == null) {
            return;
        }
        FormBody.Builder formBodyBuilder = new FormBody.Builder(StandardCharsets.UTF_8);
        for (String key : body.keySet()) {
            String[] values = body.get(key);
            if (values != null) {
                for (String value : values) {
                    formBodyBuilder.add(key, value);
                }
            }
        }
        builder.method(method, formBodyBuilder.build());
    }

    private static byte[] requestExec(Request request, String expectContentType) {
        Objects.requireNonNull(request, "okHttp request is null");

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == 200) {
                ResponseBody body = response.body();
                if (body != null) {
                    String contentType = response.header("Content-Type");
                    if (contentType != null && !contentType.contains(expectContentType)) {
                        String res = new String(body.bytes(), StandardCharsets.UTF_8);
                        System.out.println(res);
                        return null;
                    }
                    return body.bytes();
                }
                System.out.println("response body is null");
            } else {
                System.out.println("request failed, http code: " + response.code());
            }
        } catch (IOException ioException) {
            System.out.println("request exec error: " + ioException.getMessage());
        }
        return null;
    }
}
