/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.util.Global;
import com.rav.util.Position;
import madkit.kernel.Agent;
import madkit.message.ObjectMessage;
import madkit.message.StringMessage;

/**
 *
 * @author ravindu kaluarachchi
 */
public class AntiAircraftAgent extends Agent {

    private boolean onAlert = false;
    private Position position = new Position(400, 300);

    @Override
    protected void activate() {
        createGroupIfAbsent(Global.COMMUNITY, Global.GROUP);
        requestRole(Global.COMMUNITY, Global.GROUP, Global.ROLE_OFFENCE);
       // System.out.println("AA active-------------------------------");
    }

    @Override
    protected void live() {

        //waitNextMessage();
        while (true) {
            ObjectMessage m;
            m = (ObjectMessage) nextMessage();
           // System.out.println("AA live-------------------------------" + m);
            /*while ((m = (StringMessage) nextMessage()) == null) {
             }*/

            if (m != null) {
                Position target = (Position)m.getContent();
                new Missile(position, target).fire();
            }
            
            pause(1000);
        }
    }
}
