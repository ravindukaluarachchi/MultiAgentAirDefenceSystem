/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.agents;

import com.rav.environment.Positioned;
import com.rav.util.Global;
import com.rav.util.Position;
import madkit.kernel.Message;
import madkit.message.StringMessage;
import multiagentairdefence.MultiagentAirDefence;

/**
 *
 * @author ravindu kaluarachchi
 */
public class SirenAgent extends Agent {

    private boolean onAlert = false;

    @Override
    protected void activate() {
        position = new Position(100, 100);
        MultiagentAirDefence.uiObjects.add(this);
        createGroupIfAbsent(Global.COMMUNITY, Global.GROUP);
        requestRole(Global.COMMUNITY, Global.GROUP, Global.ROLE_DEFENCE);
    }

    @Override
    protected void live() {

        //waitNextMessage();
        while (true) {
            StringMessage m;
            m = (StringMessage) nextMessage();
            /*while ((m = (StringMessage) nextMessage()) == null) {
            }*/

            if (m != null) {
                if (m.getContent().equals("alert-on")) {
                    onAlert = true;
                } else if (m.getContent().equals("alert-off")) {
                    onAlert = false;
                }
            }
            if (onAlert) {
                getLogger().info("siren on %%%%%%%%%%%");
            }
            pause(1000);
        }
    }

}
