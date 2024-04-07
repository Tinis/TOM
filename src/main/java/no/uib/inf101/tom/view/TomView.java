package no.uib.inf101.tom.view;

import javax.swing.JPanel;

import no.uib.inf101.tom.Config;

import java.awt.Graphics;

public class TomView extends JPanel {

    ViewableModel model;

    public TomView(ViewableModel model) {
        this.model = model;

        this.setBackground(Config.BACKGROUND_COLOR);
        this.setFocusable(true);
        this.setPreferredSize(Config.WINDOW_DIMENSION);
    }

    @Override
    public void paintComponent(Graphics g) {

    }
}
