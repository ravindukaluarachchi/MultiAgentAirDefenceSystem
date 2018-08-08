/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.environment.Airborne;
import com.rav.environment.Sky;
import com.rav.util.Global;
import com.rav.util.Position;
import madkit.kernel.Agent;
import madkit.kernel.Message;
import madkit.message.StringMessage;

/**
 *
 * @author ravindu kaluarachchi
 */
public class RadarAgent extends Agent {

    private Position position;
    private int range = 300;
    private int alertCount;

    public RadarAgent() {
        position = new Position(500, 500);
    }

    @Override
    protected void activate() {
        createGroupIfAbsent(Global.COMMUNITY, Global.GROUP);
        requestRole(Global.COMMUNITY, Global.GROUP, Global.ROLE);
    }

    @Override
    protected void live() {
        while (true) {
            alertCount = 0;
            for (Airborne object : Sky.getObjects()) {
                if (object.getPosition().inRange(position, range)) {
                    alertCount++;
                }
            }
            if (alertCount > 0) {
                Message m = new StringMessage("alert-on");
                broadcastMessage(Global.COMMUNITY, Global.GROUP, Global.ROLE, m);                
                pause(1000);                                
            }
        }
    }

}
