package game.soldier;

import javafx.scene.image.Image;

public class SniperSoldier extends Soldier {
    public SniperSoldier(int posX, int posY) {
        super(posX, posY, "sniper");
        setDirection("front");
        setFrameAmount(8);
        setImage(new Image("file:resources/soldier/sniper_front_idle.png"));
    }
}
