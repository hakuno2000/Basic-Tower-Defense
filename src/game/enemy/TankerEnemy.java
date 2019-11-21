package game.enemy;

public class TankerEnemy extends Enemy {
    public TankerEnemy(int posX, int posY, int type) {
        super(posX, posY, "tanker", type,200,70);
        setFrameAmount(12);
        setSpeed(0.25);
    }
}
