package game.tower;

import game.soldier.NormalSoldier;

public class NormalTower extends Tower {

    public NormalTower(int posX, int posY) {
        super(posX, posY, "normal",200,10,100,150);
        setRange(175);
        setShoot(false);
        setActive(true);
        setSpeed(0.8);
        setPath("arrow");
        setSoldier(new NormalSoldier(posX + 36, posY - 10));
    }
}
