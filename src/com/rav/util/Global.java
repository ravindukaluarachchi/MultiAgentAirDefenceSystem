/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.util;

/**
 *
 * @author ravindu kaluarachchi
 */
public class Global {
    public static final String COMMUNITY = "community1";
    public static final String GROUP = "g1";
    public static final String ROLE_DEFENCE = "DEFENCE";
    public static final String ROLE_OFFENCE = "OFFENCE";
    public static int missileId = -1;
    public static  int antiAircraftIndex= 0;
    public static final int[] xs = {100,500,300};
    public static final int[] ys = {600,500,700};
    
    public static int antiAircraftStartX(){        
        return xs[antiAircraftIndex];
    }
    
    public static int antiAircraftStartY(){        
        return ys[antiAircraftIndex++];
    }
}
