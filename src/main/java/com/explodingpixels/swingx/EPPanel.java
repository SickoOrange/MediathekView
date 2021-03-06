package com.explodingpixels.swingx;

import com.explodingpixels.painter.MacWidgetsPainter;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class EPPanel extends JPanel {
    private MacWidgetsPainter<Component> fBackgroundPainter;

    public EPPanel() {
        super();
        init();
    }

    private void init() {
        setOpaque(false);
    }

    public void setBackgroundPainter(MacWidgetsPainter<Component> painter) {
        fBackgroundPainter = painter;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (fBackgroundPainter != null) {
            Graphics2D graphics2D = (Graphics2D) g.create();
            fBackgroundPainter.paint(graphics2D, this, getWidth(), getHeight());
            graphics2D.dispose();
        }

        // TODO see if we can get rid of this call to super.paintComponent.
        super.paintComponent(g);
    }


}
