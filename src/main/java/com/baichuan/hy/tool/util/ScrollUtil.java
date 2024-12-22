package com.baichuan.hy.tool.util;

import javax.swing.*;

/**
 * some functions about scroll
 *
 * @author <a href="https://github.com/arvinBaichuan">arvinBaichuan</a>
 * @since 2021/11/23.
 */
public class ScrollUtil {

    public static void smoothPane(JScrollPane scrollPane) {
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(14);
        scrollPane.getVerticalScrollBar().setDoubleBuffered(true);
        scrollPane.getHorizontalScrollBar().setDoubleBuffered(true);
    }
}
