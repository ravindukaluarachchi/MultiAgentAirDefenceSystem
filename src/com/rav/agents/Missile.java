/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.environment.Positioned;
import com.rav.util.Global;
import com.rav.util.Position;
import java.util.logging.Level;
import java.util.logging.Logger;
import multiagentairdefence.MultiagentAirDefence;

/**
 *
 * @author ravindu
 */
public class Missile implements Positioned{
    private Position position;
    private Position destination;
    private int speed = 1500;
    private int id;

    public Missile() {
        this.id = Global.missileId++;
        
    }

    public Missile(Position position, Position destination) {
        this();
        this.position = position;
        this.destination = destination;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        MultiagentAirDefence.uiObjects.add(this);
        System.out.println("added******************************" + MultiagentAirDefence.uiObjects.size());
        new Thread(() -> {
            int unitDistance = 10;
                System.out.println("Missile Fired " + id);
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
            MultiagentAirDefence.uiObjects.remove(this);
            System.out.println("removed******************************" + MultiagentAirDefence.uiObjects.size());
        }).start();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Missile other = (Missile) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
