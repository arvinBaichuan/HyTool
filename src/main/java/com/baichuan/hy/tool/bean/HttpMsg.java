package com.baichuan.hy.tool.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * HttpMsg
 * </pre>
 *
 * @author <a href="https://github.com/arvinBaichuan">arvin</a>
 * @since 2019/7/18.
 */
@Getter
@Setter
@ToString
public class HttpMsg {

    private String url;

    private String body;

    private Map<String, Object> paramMap;

    private Map<String, Object> headerMap;

    private List<HttpCookie> cookies;

}
