package com.baichuan.hy.tool.ui.component;

import com.baichuan.hy.tool.util.ColorUtil;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * <pre>
 * 自定义颜色块单元格渲染器
 * </pre>
 *
 * @author <a href="https://github.com/arvinBaichuan">arvin</a>
 * @since 2019/3/26.
 */
public class TableInCellColorBlockRenderer extends JPanel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        String colorHex = (String) value;
        setBackground(ColorUtil.fromHex(colorHex));
        return this;
    }
}