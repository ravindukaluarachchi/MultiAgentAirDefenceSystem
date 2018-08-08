/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rav.environment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ravindu kaluarachchi
 */
public class Sky {
    private static List<Airborne> objects = new ArrayList<>();

    public static List<Airborne> getObjects() {
        return objects;
    }

    public static void setObjects(List<Airborne> objects) {
        Sky.objects = objects;
    }
    
}
