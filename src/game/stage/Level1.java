package game.stage;

import game.enemy.BossEnemy;
import game.enemy.NormalEnemy;
import game.enemy.SmallerEnemy;
import game.enemy.TankerEnemy;
import game.object.GameObject;
import game.tower.EmptyTower;
import javafx.scene.image.Image;

public class Level1 extends GameStage {
    private Image hammerTower, overWall;

    public Level1() {
        super(1);
        hammerTower = new Image("file:resources/stage1/Stage1_Hammer.png");
        overWall = new Image("file:resources/stage1/Stage1_overWall.png");


        enemies.add(new SmallerEnemy(4800, 370, 1));
        enemies.add(new SmallerEnemy(4800, 410, -1));
        enemies.add(new NormalEnemy(4800, 420, -1));
        enemies.add(new NormalEnemy(4850, 420, 1));
        enemies.add(new NormalEnemy(4900, 380, 1));
        enemies.add(new NormalEnemy(4950, 380, -1));
        enemies.add(new TankerEnemy(3400, 400, -1));
        enemies.add(new TankerEnemy(3400, 390, 1));
        enemies.add(new BossEnemy(2950, 370, -1));

        enemies.add(new SmallerEnemy(3500, 370, 1));
        enemies.add(new SmallerEnemy(3550, 440, -1));
        enemies.add(new SmallerEnemy(3600, 380, 1));
        enemies.add(new SmallerEnemy(3650, 400, -1));
        enemies.add(new SmallerEnemy(3700, 390, 1));
        enemies.add(new SmallerEnemy(3750, 390, -1));

        enemies.add(new NormalEnemy(2250, 420, -1));
        enemies.add(new TankerEnemy(2050, 410, -1));
        enemies.add(new NormalEnemy(2300, 380, 1));
        enemies.add(new NormalEnemy(2350, 430, -1));

        enemies.add(new NormalEnemy(1250, 380, -1));
        enemies.add(new NormalEnemy(1300, 380, 1));
        enemies.add(new NormalEnemy(1350, 410, -1));
        enemies.add(new NormalEnemy(1400, 410, 1));
        enemies.add(new NormalEnemy(1450, 380, 1));
        enemies.add(new NormalEnemy(1500, 380, -1));
    }

    @Override
    public void addOrnament() {
        ornament.add(new GameObject(543, 337, hammerTower));
        ornament.add(new GameObject(-49, 220, overWall));

        // Vẽ 10 cái tháp trống
        towers.add(new EmptyTower(540, 640));
        towers.add(new EmptyTower(250, 550));
        towers.add(new EmptyTower(460, 470));
        towers.add(new EmptyTower(615, 470));
        towers.add(new EmptyTower(415, 380));
        towers.add(new EmptyTower(665, 380));
        towers.add(new EmptyTower(460, 297));
        towers.add(new EmptyTower(610, 297));
        towers.add(new EmptyTower(250, 200));
        towers.add(new EmptyTower(540, 130));
    }

    @Override
    public void update() { super.update(); }
}
