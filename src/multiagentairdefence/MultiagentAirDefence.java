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
import com.rav.environment.Positioned;
import com.rav.environment.Sky;
import com.rav.util.Position;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
    public static List<Positioned> uiObjects = new ArrayList<>();
    private static final int UI_WIDTH = 800;
    private static final int UI_HEIGHT = 600;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        sargs = args;
        launch(args);

    }

    static void runMAS() {
        Aircraft a = new Aircraft();
        a.setPosition(new Position(0, 0));
        a.setDestination(new Position(1000, 1000));
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
        Screen screen3 = Screen.getScreens().get(1);
        Rectangle2D bounds = screen3.getBounds();

        primaryStage.setX(bounds.getMinX() + 100);
        primaryStage.setY(bounds.getMinY() + 100);
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

    public static void movePlane(Position position) {
        gc.drawImage(imgPlane, position.getX(), position.getY(), 100, 80);
    }

    static int y = 0;

    public static void drawUI() {
        new Thread(() -> {
            while (true) {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, UI_WIDTH, UI_HEIGHT); 
                y = 0;
                uiObjects.forEach(o -> {
                    y += 20;
                    gc.strokeText(o.getClass().getName() + " : " + 0 +o.getPosition().toString(),0, y);
                   /* gc.strokeText(o.getClass().getName() + " : ", 0, y);
                    y += 10;
                    gc.strokeText(o.getPosition().toString(), 200, y);*/
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MultiagentAirDefence.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

}
