package no.uib.inf101.tom.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import no.uib.inf101.tom.view.TomView;

public class MouseHandler implements MouseListener, MouseMotionListener{
    private ControllableModel model;
    private TomView view;

    private boolean rightClicking;

    public MouseHandler(ControllableModel model, TomView view) {
        this.model = model;
        this.view = view;
        this.view.addMouseListener(this);
        this.view.addMouseMotionListener(this);

        this.rightClicking = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightClicking = false;
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        //mousedragging should only work with walking.
        Point2D point = e.getPoint();
        if (this.rightClicking) {
            this.model.walk(point);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point2D point = e.getPoint();
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightClicking = true;
            this.model.walk(point);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
     
}
