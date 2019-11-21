package game.tower;

import game.soldier.NormalSoldier;

public class NormalTower extends Tower {

    public NormalTower(int posX, int posY) {
        super(posX, posY, "normal",200,5,100,150);
        setRange(200);
        setShoot(false);
        setActive(true);
        setSpeed(1);
        setPath("arrow");
        setSoldier(new NormalSoldier(posX + 36, posY - 10));
    }
}
