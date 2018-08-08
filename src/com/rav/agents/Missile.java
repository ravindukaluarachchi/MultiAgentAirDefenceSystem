/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.util.Position;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ravindu
 */
public class Missile {
    private Position position;
    private Position destination;
    private int speed = 50;

    public Missile() {
    }

    public Missile(Position position, Position destination) {
        this.position = position;
        this.destination = destination;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public void fire() {
        new Thread(() -> {
            int unitDistance = 10;
                System.out.println("Missile Fired ");
            while (!position.inRange(destination, unitDistance)) {
                int x1 = destination.getX();
                int x2 = position.getX();
                int x3 = -1;
                int hx1 = position.distanceFromPoint(destination);
                int hx2 = hx1 - unitDistance;
                x3 = (hx2 * (x2 - x1) / hx1) + x1;
                int y1 = destination.getY();
                int y2 = position.getY();
                int y3 = -1;
                int hy1 = position.distanceFromPoint(destination);
                int hy2 = hy1 - unitDistance;
                y3 = (hy2 * (y2 - y1) / hy1) + y1;
                position.setX(x3);
                position.setY(y3);
                System.out.println("Missile: " + position);
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Aircraft.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}
