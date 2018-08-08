/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiagentairdefence;

import com.rav.agents.Aircraft;
import com.rav.agents.AntiAircraftAgent;
import com.rav.agents.PowerGridAgent;
import com.rav.agents.RadarAgent;
import com.rav.agents.SirenAgent;
import com.rav.util.Position;
import madkit.kernel.Madkit;

/**
 *
 * @author ravklk
 */
public class MultiagentAirDefence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Aircraft a = new Aircraft();
        a.setPosition(new Position(100, 100));
        a.setDestination(new Position(0, 0));
        a.setSpeed(500);
        a.fly();
        System.out.println("put a on sky");
        args = new String[]{"--launchAgents",
              RadarAgent.class.getName() + ",false,1;"
            + SirenAgent.class.getName() + ",false,1;"
            +PowerGridAgent.class.getName() + ",false,1;"};
        //Madkit.main(args);
    }

}
