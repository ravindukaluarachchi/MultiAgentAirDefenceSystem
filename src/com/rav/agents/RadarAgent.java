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
public class RadarAgent extends Agent {

    @Override
    protected void activate() {
        createGroupIfAbsent(Global.COMMUNITY, Global.GROUP);
        requestRole(Global.COMMUNITY, Global.GROUP, Global.ROLE);
    }

    @Override
    protected void live() {
        Message m = new StringMessage("ABCD");
        broadcastMessage(Global.COMMUNITY, Global.GROUP, Global.ROLE, m);
        getLogger().info("message sent");
        pause(15000);
         m = new StringMessage("EFGH");
        broadcastMessage(Global.COMMUNITY, Global.GROUP, Global.ROLE, m);
    }

}
