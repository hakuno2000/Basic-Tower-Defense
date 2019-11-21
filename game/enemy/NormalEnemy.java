package game.enemy;

public class NormalEnemy extends Enemy {
    public NormalEnemy(int posX, int posY, int type) {
        super(posX, posY, "normal", type,100,50);
        setFrameAmount(12);
        setSpeed(0.75);
    }
}
