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
import com.rav.environment.Sky;
import com.rav.util.Position;
import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import madkit.kernel.Madkit;

/**
 *
 * @author ravindu
 */
public class MultiagentAirDefence extends Application {

    static GraphicsContext gc;
    static String[] sargs;
    static Image imgPlane;

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
        a.fly();
        System.out.println("put a on sky");
        sargs = new String[]{"--launchAgents",
            RadarAgent.class.getName() + ",false,1;"
            + SirenAgent.class.getName() + ",false,1;"
            + PowerGridAgent.class.getName() + ",false,1;"
            + AntiAircraftAgent.class.getName() + ",false,1;"};
        Madkit.main(sargs);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        File imageFile = new File("images/plane1.jpg");
        Canvas canvas = new Canvas(800, 600);

        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Multi Agent Air Defence");
        primaryStage.setScene(scene);

        imgPlane = new Image(new FileInputStream(imageFile));

        primaryStage.show();

        runMAS();
    }

    public static void movePlane(Position position) {
        gc.drawImage(imgPlane, position.getX(), position.getY(), 100, 80);
    }

}
