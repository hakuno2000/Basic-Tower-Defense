package game.gui;

import game.object.GameObject;
import javafx.scene.image.Image;

public class RangeCircle extends GameObject {
    private static final int baseRange = 150;
    private static final int width = 289;
    private static final int height = 289;

    public RangeCircle(int x, int y, double range) {
        super(new Image("file:resources/range_circle.png"));
        setImage(new Image("file:resources/range_circle.png", height * range / baseRange + 1, width * range / baseRange + 1, false, false));
        setXcenter(x);
        setYcenter(y);
    }
}
