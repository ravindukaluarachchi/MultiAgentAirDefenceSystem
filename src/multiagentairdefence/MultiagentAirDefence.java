/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiagentairdefence;

import com.rav.agents.Aircraft;
import com.rav.agents.AntiAircraftAgent;
import com.rav.agents.Missile;
import com.rav.agents.PowerGridAgent;
import com.rav.agents.RadarAgent;
import com.rav.agents.SirenAgent;
import com.rav.environment.Positioned;
import com.rav.environment.Sky;
import com.rav.util.Position;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import madkit.kernel.Madkit;

/**
 *
 * @author ravindu
 */
public class MultiagentAirDefence extends Application {

    private static GraphicsContext gc;
    private static String[] sargs;
    static Image imgPlane;
    static Image imgMissile;
    static Image imgTank;
    static Image imgBackgroundOn;
    static Image imgBackgroundOff;
    static Image imgSiren;
    public static List<Positioned> uiObjects = new ArrayList<>();
    private static final int UI_WIDTH = 800;
    private static final int UI_HEIGHT = 800;
    private static boolean alertOn;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        sargs = args;
        launch(args);

    }

    static void runMAS() {
        Aircraft a = new Aircraft();
        a.setPosition(new Position(600, 0));
        a.setDestination(new Position(0, 600));
        a.setSpeed(500);
        Sky.getObjects().add(a);
        uiObjects.add(a);
        a.fly();
        System.out.println("put a on sky");
        sargs = new String[]{"--launchAgents",
            RadarAgent.class.getName() + ",false,1;"
            + SirenAgent.class.getName() + ",false,1;"
            + PowerGridAgent.class.getName() + ",false,1;"
            + AntiAircraftAgent.class.getName() + ",false,2;"};
        Madkit.main(sargs);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Screen screen3 = Screen.getScreens().get(1);
        // Rectangle2D bounds = screen3.getBounds();

        // primaryStage.setX(bounds.getMinX() + 100);
        //  primaryStage.setY(bounds.getMinY() + 100);
        //  File imageFile = new File("images/plane1.jpg");
        Canvas canvas = new Canvas(UI_WIDTH, UI_HEIGHT);

        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Multi Agent Air Defence");
        primaryStage.setScene(scene);
        primaryStage.show();
        drawUI();
        runMAS();
    }
 

    static int y = 0;

    public static void drawUI() {
        final File imagePlaneFile = new File("images/plane.png");
        final File imageTankFile = new File("images/tank.png");
        final File imageMissileFile = new File("images/fire.png");
        final File imageBackgroundOnFile = new File("images/1.jpg");
        final File imageBackgroundOffFile = new File("images/2.jpg");
        final File imageSirenFile = new File("images/red.png");

        try {
            imgPlane = new Image(new FileInputStream(imagePlaneFile));
            imgMissile = new Image(new FileInputStream(imageMissileFile));
            imgTank = new Image(new FileInputStream(imageTankFile));
            imgBackgroundOn = new Image(new FileInputStream(imageBackgroundOnFile));
            imgBackgroundOff = new Image(new FileInputStream(imageBackgroundOffFile));
            imgSiren = new Image(new FileInputStream(imageSirenFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MultiagentAirDefence.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {

                    //gc.setFill(Color.WHITE);
                    //gc.fillRect(0, 0, UI_WIDTH, UI_HEIGHT);
                    if (alertOn) {
                        gc.drawImage(imgBackgroundOff, 0, 0);
                    } else {
                        gc.drawImage(imgBackgroundOn, 0, 0);
                    }
                    y = 0;
                    uiObjects.forEach(o -> {
                      //  y += 20;
                       // gc.strokeText(o.getClass().getName() + " : " + 0 + o.getPosition().toString(), 0, y);
                        if (o instanceof Aircraft) {
                            gc.drawImage(imgPlane, o.getPosition().getX(), o.getPosition().getY(), 50, 60);
                        } else if (o instanceof AntiAircraftAgent) {
                            gc.drawImage(imgTank, o.getPosition().getX(), o.getPosition().getY(), 50, 60);
                        } else if (o instanceof Missile) {
                            gc.drawImage(imgMissile, o.getPosition().getX(), o.getPosition().getY(), 20, 20);
                        }  else if (o instanceof SirenAgent) {
                            if (((SirenAgent)o).isOnAlert()) {
                                gc.drawImage(imgSiren, 700,100,100,100);                                
                            }
                        } else if (o instanceof RadarAgent) {
                            RadarAgent r = (RadarAgent) o;
                            if (r.isOnAlert()) {
                                alertOn = true;
                                gc.setStroke(Color.RED);
                            } else {
                                alertOn = false;
                                gc.setStroke(Color.GREEN);
                            }
                            gc.strokeOval(o.getPosition().getX() - r.getRange(), o.getPosition().getY() - r.getRange(), r.getRange() * 2, r.getRange() * 2);
                        }
                        /* gc.strokeText(o.getClass().getName() + " : ", 0, y);
                         y += 10;
                         gc.strokeText(o.getPosition().toString(), 200, y);*/
                    });
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MultiagentAirDefence.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

}
