/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.util;

/**
 *
 * @author ravindu kaluarachchi
 */
public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean inRange(Position center, int radius) {
        double distPositionToCenter = Math.sqrt(Math.pow(center.getX() - x, 2) + Math.pow(center.getY() - y, 2));
        if (radius - distPositionToCenter > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int distanceFromPoint(Position point) {
        return  new Double(Math.sqrt(Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2))).intValue();
        
    }

    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }
    
    
}
