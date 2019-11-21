package game.tower;

import game.soldier.NormalSoldier;

public class MachineGunTower extends Tower {

    public MachineGunTower(int posX, int posY) {
        super(posX, posY, "machine_gun",400,15,250,275);
        setRange(150);
        setShoot(false);
        setActive(true);
        setSpeed(0.5);
        setPath("magearrow");
        setSoldier(new NormalSoldier(posX + 36, posY - 10));
    }
}
