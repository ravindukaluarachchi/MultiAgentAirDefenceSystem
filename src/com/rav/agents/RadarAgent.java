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
import madkit.message.ACLMessage;
import madkit.message.ObjectMessage;
import madkit.message.StringMessage;

/**
 *
 * @author ravindu kaluarachchi
 */
public class RadarAgent extends Agent {

    private Position position;
    private int range = 100;
    private int alertCount; 
    private boolean onAlert = false;
    
    public RadarAgent() {
        position = new Position(300, 300);
    }

    @Override
    protected void activate() {
        createGroupIfAbsent(Global.COMMUNITY, Global.GROUP);
        requestRole(Global.COMMUNITY, Global.GROUP, Global.ROLE_DEFENCE);
    }

    @Override
    protected void live() {
        while (true) {
            alertCount = 0;
            for (Airborne object : Sky.getObjects()) {
                if (object.getPosition().inRange(position, range)) {
                    Message m = new ObjectMessage(object.getPosition());
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
