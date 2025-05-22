package gui;

import javax.swing.*;
import java.awt.*;

public class TextIcon implements Icon {
    private final String text;
    private final Color color;
    private final int size;

    public TextIcon(String text, Color color, int size) {
        this.text = text;
        this.color = color;
        this.size = size;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("Arial", Font.BOLD, size));
        g.drawString(text, x, y + size);
    }

    @Override
    public int getIconWidth() { return size; }
    @Override
    public int getIconHeight() { return size; }
}
