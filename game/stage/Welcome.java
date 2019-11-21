package game.stage;

import game.gui.Icon;
import game.object.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Welcome extends GameStage {
    private Image mainMenuImage;
    private Icon startIcon;
    private int k = 0, stage;
    private long lastBlinkTime, waitTime;

    public Welcome() {
        super(0);
        stage = 0;
        waitTime = 50;
        startIcon = new Icon(436, 330, 8);
        lastBlinkTime = System.currentTimeMillis();
        mainMenuImage = new Image("file:resources/Stage_0.png", 1580, 900, false, true);
    }

    @Override
    public void addOrnament() {
        ornament.add(new GameObject(-190, 0, mainMenuImage));
        ornament.add(new GameObject(360, 10, new Image("file:resources/welcome/logo.png")));
    }

    @Override
    public void input(int key, double mouseX, double mouseY) {
        if (key == 1) startIcon.onHover((int) mouseX, (int) mouseY, null);
        if (key == 0) if (startIcon.onClick((int) mouseX, (int) mouseY, this) > 0) stage = 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        startIcon.draw(gc);
        if (lastBlinkTime + waitTime < System.currentTimeMillis()) {
            k = (k + 1) % 19;
            waitTime -= 5;
            if (k == 0) waitTime = 45;
            if (k == 18) waitTime = 2000;
            lastBlinkTime = System.currentTimeMillis();
        }
    }

    public int getStage() {
        return stage;
    }
}
