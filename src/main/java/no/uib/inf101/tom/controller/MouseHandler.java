package no.uib.inf101.tom.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import no.uib.inf101.tom.view.TomView;

public class MouseHandler implements MouseListener, MouseMotionListener{
    private ControllableModel model;
    private TomView view;

    public MouseHandler(ControllableModel model, TomView view) {
        this.model = model;
        this.view = view;
        this.view.addMouseListener(this);
        this.view.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseDragged(MouseEvent e) {}


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
     
}
