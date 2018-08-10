/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.util.Global;
import com.rav.util.Position;
import madkit.kernel.Message;
import madkit.message.StringMessage;
import multiagentairdefence.MultiagentAirDefence;

/**
 *
 * @author ravindu kaluarachchi
 */
public class PowerGridAgent extends Agent {

    private boolean onAlert = false;

    @Override
    protected void activate() {
        MultiagentAirDefence.uiObjects.add(this);
        createGroupIfAbsent(Global.COMMUNITY, Global.GROUP);
        requestRole(Global.COMMUNITY, Global.GROUP, Global.ROLE_DEFENCE);
    }

    @Override
    protected void live() {
        position = new Position(300, 300);
        //waitNextMessage();
        while (true) {
            StringMessage m;
            m = (StringMessage) nextMessage();
            /*while ((m = (StringMessage) nextMessage()) == null) {
            }*/

            if (m != null) {
                if (m.getContent().equals("alert-on")) {
                    onAlert = true;
                    getLogger().info("power-grid off");
                } else if (m.getContent().equals("alert-off")) {
                    onAlert = false;
                    getLogger().info("power-grid on");
                }
            }
            pause(1000);
        }
    }
}
