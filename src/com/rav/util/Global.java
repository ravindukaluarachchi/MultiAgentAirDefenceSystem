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
    public static int missileId = 1;
    public static  int antiAircraftStartX = 0;
    public static  int antiAircraftStartY = 500;
    public static final int ANTI_AIRCRAFT_DIST_X = 100;
    
    public static int antiAircraftStartX(){
        antiAircraftStartX += ANTI_AIRCRAFT_DIST_X;
        return antiAircraftStartX;
    }
    
    public static int antiAircraftStartY(){        
        return antiAircraftStartY;
    }
}
