/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.environment.Positioned;
import com.rav.util.Position;

/**
 *
 * @author ravindu kaluarachchi
 */
public class Agent extends madkit.kernel.Agent implements Positioned {

    protected Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(int x,int y) {
        if (position == null) {
            position = new Position(x, y);
        }else{
            position.setX(x);
            position.setY(y);
        }
    }
}
