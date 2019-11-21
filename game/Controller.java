package game;

import game.stage.GameStage;
import game.stage.Level1;
import game.stage.Welcome;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class Controller {
    private int stage;
    private GameStage level;

    public Controller() {
        stage = 0;
        level = new Welcome();
        level.addOrnament();
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, 1200, 900);
        level.draw(gc);
    }

    public void update() {
        level.update();
        if (stage == 0 && ((Welcome) level).getStage() > 0) {
            stage = 1;
            level = new Level1();
            level.addOrnament();
        }
        if ((level.getHud()).getLife() == 0) {
            stage = 0;
            level = new Welcome();
            level.addOrnament();
        }
    }

    void mouseController(Scene theScene) {
        theScene.setOnMouseClicked(e -> level.input(0, e.getX(), e.getY()));
        theScene.setOnMouseMoved(event -> level.input(1, event.getX(), event.getY()));
    }

    public GameStage getLevel() {
        return level;
    }

    public void setLevel(GameStage level) {
        this.level = level;
    }
}
