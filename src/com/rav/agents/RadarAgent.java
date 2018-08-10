/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.environment.Sky;
import com.rav.util.Global;
import com.rav.util.Position;
import madkit.kernel.Message;
import madkit.message.ACLMessage;
import madkit.message.ObjectMessage;
import madkit.message.StringMessage;
import com.rav.environment.Positioned;
import multiagentairdefence.MultiagentAirDefence;

/**
 *
 * @author ravindu kaluarachchi
 */
public class RadarAgent extends Agent {
    
    private int range = 200;
    private int alertCount; 
    private boolean onAlert = false;
    
    public RadarAgent() {
        
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isOnAlert() {
        return onAlert;
    }

    public void setOnAlert(boolean onAlert) {
        this.onAlert = onAlert;
    }

    @Override
    protected void activate() {
        position = new Position(300, 300);
        MultiagentAirDefence.uiObjects.add(this);
        createGroupIfAbsent(Global.COMMUNITY, Global.GROUP);
        requestRole(Global.COMMUNITY, Global.GROUP, Global.ROLE_DEFENCE);
    }

    @Override
    protected void live() {
        while (true) {
            alertCount = 0;
            for (Positioned object : Sky.getObjects()) {
                if (object.getPosition().inRange(position, range)) {
                    Message m = new ObjectMessage(new Position(object.getPosition().getX(),object.getPosition().getY()));
                    broadcastMessage(Global.COMMUNITY, Global.GROUP, Global.ROLE_OFFENCE, m); 
                    alertCount++;
                }
            }
            if (alertCount > 0) {
                onAlert = true;
                Message m = new StringMessage("alert-on");
                broadcastMessage(Global.COMMUNITY, Global.GROUP, Global.ROLE_DEFENCE, m);                
                pause(1000);                                
            }else if(onAlert){
                onAlert = false;
                Message m = new StringMessage("alert-off");
                broadcastMessage(Global.COMMUNITY, Global.GROUP, Global.ROLE_DEFENCE, m);                
                pause(1000);                                
            }
            
        }
    }

}
