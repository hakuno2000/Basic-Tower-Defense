package game.gui;

import game.object.GameObject;
import javafx.scene.image.Image;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;

public class UpdateBar extends GameObject implements UpdatableObject {
    private GameObject updateBar;
    private int rate;
    private boolean done;

    public UpdateBar(int posX, int posY) {
        super(posX, posY, new Image("file:resources/buildBar_bg.png"));
        updateBar = new GameObject(posX + 2, posY + 2, new Image("file:resources/buildBar.png", 0, 8, false, true));
    }

    @Override
    public void update() {
        if (rate++ <= 100) {
            updateBar = new GameObject(posX + 2, posY + 2, new Image("file:resources/buildBar.png", (int) (rate * 0.54), 8, false, true));
        } else done = true;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        updateBar.draw(gc);
    }

    public boolean isDone() {
        return done;
    }
}
