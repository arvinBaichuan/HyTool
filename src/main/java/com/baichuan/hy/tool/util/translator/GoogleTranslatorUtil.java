package com.baichuan.hy.tool.util.translator;

import cn.hutool.json.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.SSLHandshakeException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;

/**
 * 翻译工具类
 */
@Slf4j
public class GoogleTranslatorUtil implements Translator {

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
            String url = "https://translate.googleapis.com/translate_a/single?" +
                    "client=gtx&" +
                    "sl=" + sourceLanguage +
                    "&tl=" + targetLanguage +
                    "&dt=t&q=" + URLEncoder.encode(word, StandardCharsets.UTF_8);

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // 超时时间
            con.setConnectTimeout(5000);
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return parseResult(response.toString());
        } catch (SSLHandshakeException e) {
            log.error("SSLHandshakeException", e);
            return "访问Google翻译接口网络异常：" + e.getMessage();
        } catch (HttpTimeoutException e) {
            log.error("HttpTimeoutException", e);
            return "访问Google翻译接口超时：" + e.getMessage();
        } catch (Exception e) {
            log.error("访问Google翻译异常", e);
            return "访问Google翻译接口异常：" + e.getMessage();
        }
    }

    private static String parseResult(String inputJson) {
        JSONArray jsonArray2 = (JSONArray) new JSONArray(inputJson).get(0);
        StringBuilder result = new StringBuilder();
        for (Object o : jsonArray2) {
            result.append(((JSONArray) o).get(0).toString());
        }
        return result.toString();
    }
}
