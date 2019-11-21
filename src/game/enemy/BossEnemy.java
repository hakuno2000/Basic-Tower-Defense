package game.enemy;

public class BossEnemy extends Enemy {
    public BossEnemy(int posX, int posY, int type) {
        super(posX, posY, "boss", type,2000, 100);
        setFrameAmount(12);
        setSpeed(0.125);
    }
}