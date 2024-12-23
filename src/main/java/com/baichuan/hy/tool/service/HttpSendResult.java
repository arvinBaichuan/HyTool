package com.baichuan.hy.tool.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * HttpSendResult
 * </pre>
 *
 * @author <a href="https://github.com/arvinBaichuan">arvin</a>
 * @since 2019/7/19.
 */
@Getter
@Setter
@ToString
public class HttpSendResult extends SendResult {
    private String headers;

    private String body;

    private String cookies;
}
