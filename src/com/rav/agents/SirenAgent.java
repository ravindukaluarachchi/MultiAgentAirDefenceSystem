/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.util.Global;
import madkit.kernel.Agent;
import madkit.kernel.Message;
import madkit.message.StringMessage;

/**
 *
 * @author ravindu kaluarachchi
 */
public class SirenAgent extends Agent {

    @Override
    protected void activate() {
        createGroupIfAbsent(Global.COMMUNITY, Global.GROUP);
        requestRole(Global.COMMUNITY, Global.GROUP, Global.ROLE);
    }

    @Override
    protected void live() {

        //waitNextMessage();
        while (true) {
            Message m;
            while ((m = nextMessage()) == null) {
            }

            System.out.println("m:" + m);
            if (m != null) {
                getLogger().info("@S>" + m.getSender() + " > " + m.toString() + "\n");

            }
            pause(5000);
        }
    }
}
