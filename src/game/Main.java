package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {
    public static Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Basic Tower Defense");
        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        Canvas canvas = new Canvas(1200, 900);

        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        controller = new Controller();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                controller.update();
                controller.draw(gc);
            }
        }.start();

        controller.mouseController(theScene);

        primaryStage.show();
    }
}
