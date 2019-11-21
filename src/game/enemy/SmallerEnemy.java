package game.enemy;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy(int posX, int posY, int type) {
        super(posX, posY, "smaller", type,80,30);
        setFrameAmount(9);
        setSpeed(1);
    }
}