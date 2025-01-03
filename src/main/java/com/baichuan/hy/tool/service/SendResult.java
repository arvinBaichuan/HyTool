package com.baichuan.hy.tool.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 发送结果
 * </pre>
 *
 * @author <a href="https://github.com/arvinBaichuan">arvinBaichuan</a>
 * @since 2019/6/15.
 */
@Getter
@Setter
@ToString
public class SendResult {
    private boolean success = false;

    private String info;
}
