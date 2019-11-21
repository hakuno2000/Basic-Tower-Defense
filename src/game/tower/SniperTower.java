package game.tower;

import game.soldier.SniperSoldier;

public class SniperTower extends Tower {

    public SniperTower(int posX, int posY) {
        super(posX, posY, "sniper",300,25,200,200);
        setRange(250);
        setShoot(false);
        setActive(true);
        setSpeed(1.25);
        setPath("magebolt");
        setSoldier(new SniperSoldier(posX + 38, posY - 17));
    }
}
