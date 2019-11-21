package game.soldier;

import javafx.scene.image.Image;

public class NormalSoldier extends Soldier {
    public NormalSoldier(int posX, int posY) {
        super(posX, posY, "normal");
        setDirection("IV");
        setFrameAmount(4);
        setImage(new Image("file:resources/soldier/normal_IV_idle.png"));
    }
}
