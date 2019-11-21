package game.stage;

import game.enemy.Enemy;
import game.gui.HUD;
import game.gui.Icon;
import game.object.GameObject;
import game.tower.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class GameStage {
    public ArrayList<GameObject> ornament = new ArrayList<>();
    public ArrayList<Tower> towers = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();
    private Image map;
    private double brightness;
    private ColorAdjust colorAdjust;

    private Icon pause;
    private boolean isPause;
    private long pauseTime;

    private HUD hud;

    // Load map của level tương ứng
    public GameStage(int k) {
        map = new Image("file:resources/Stage_" + k + ".png");
        brightness = -1;
        colorAdjust = new ColorAdjust();
        isPause = false;
        pause = new Icon(1118, 30, 9);
        hud = new HUD(30,30);
    }

    // Thêm vật trang trí
    public abstract void addOrnament();

    /**
     * Vẽ màn chơi
     * Vẽ kẻ địch
     * Vẽ các tháp
     * Vẽ vật trang trí
     * Vẽ các layout
     */
    public void draw(GraphicsContext gc) {
        gc.setEffect(colorAdjust);
        gc.drawImage(map, 0, -50);
        gc.drawImage(map, 0, -50);
        pause.draw(gc);
        hud.draw(gc);
        for (Tower tower : towers) tower.draw(gc);
        sortEnemies(false); // Thằng nào thấp hơn thì vẽ sau, tránh bị đè lên nhau
        for (Enemy enemy : enemies) enemy.draw(gc);
        for (GameObject i : ornament) i.draw(gc);
        for (Tower tower: towers) tower.drawLayout(gc);
    }

    /**
     * Cập nhật màn chơi
     */
    public void update() {
        if (!isPause && brightness < 0) brightness += 0.05;
        colorAdjust.setBrightness(brightness);
        if (isPause) return;

        for (int i = 0; i < towers.size(); i++) {
            Tower tower = towers.get(i);
            // Update tháp
            tower.update();
            // Nêu tháp được nâng cấp thì set 1 tháp mới thay vào
            if (tower.getUpgrade() == 0) {
                towers.set(i, new NormalTower(towers.get(i).getPosX(), towers.get(i).getPosY()));
                hud.setCoins(hud.getCoins() - (towers.get(i)).getPrice());
            }
            else if (tower.getUpgrade() == 1) {
                towers.set(i, new SniperTower(towers.get(i).getPosX(), towers.get(i).getPosY()));
                hud.setCoins(hud.getCoins() - (towers.get(i)).getPrice());
            }
            else if (tower.getUpgrade() == 2) {
                towers.set(i, new MachineGunTower(towers.get(i).getPosX(), towers.get(i).getPosY()));
                hud.setCoins(hud.getCoins() - (towers.get(i)).getPrice());
            }
            else if (tower.getUpgrade() == 4) {
                tower.upgrade(new Image("file:resources/tower/" + tower.getTag() + "_tower_" + tower.increaseRank() + ".png"));
                tower.setRange(tower.getRange() + 50);
                tower.setUpgrade(-1);
                if (tower.getRank() < 3){
                    hud.setCoins(hud.getCoins() - tower.getUpgradeprice());
                    tower.setUpgradeprice(tower.getUpgradeprice()+50);
                    tower.setSellprice(tower.getSellprice()+50);
                }
            } else if (tower.getUpgrade() == 5) {
                towers.set(i, new EmptyTower(tower.getPosX(), tower.getPosY()));
                hud.setCoins(hud.getCoins()+tower.getSellprice());
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).getPosX() < -30) {
                enemies.remove(i);
                hud.DecreaseLife();
            }
            else if (enemies.get(i).getCurrentHp() == 0) {
                hud.setCoins(hud.getCoins() + enemies.get(i).getReward());
                enemies.remove(i);
            }
        }

        // Xoá bớt mấy thằng hết máu
        for (int i = 0; i < enemies.size(); i++)
            while (i < enemies.size() && enemies.get(i).getHealthBar().getHealth() <= 0) enemies.remove(i);

        sortEnemies(true);  // Bắn thằng đang đi nhanh nhất
        for (Tower i : towers) i.attack(enemies);

        //if (hud.getLife() <= 0) System.exit(0);
    }

    /** Đọc các sự kiện bàn phím chuột
     *  key = 0: Mouse Position When Clicking
     *  key = 1: Mouse Position
     */
    public void input(int key, double mouseX, double mouseY) {
        if (key == 0) {
            if (isPause) {
                isPause = false;
                brightness = 0;
                pauseTime = System.currentTimeMillis() - pauseTime;
                for (Enemy enemy: enemies) enemy.setLastAniTime(enemy.getLastAniTime() + pauseTime);
                for (Enemy enemy: enemies) enemy.setLastMoveTime(enemy.getLastMoveTime() + pauseTime);
                for (Tower tower: towers) tower.setLastShootTime(tower.getLastShootTime() + pauseTime);
            }
            if (pause.onClick((int) mouseX, (int) mouseY, null) > 0) {
                isPause = true;
                brightness = -0.5;
                pauseTime = System.currentTimeMillis();
            }
            for (Tower tower : towers) {
                pause.onHover((int) mouseX, (int) mouseY, null);
                tower.onClick((int) mouseX, (int) mouseY, this);
            }
        }
        if (key == 1) for (Tower tower : towers) tower.onHover((int) mouseX, (int) mouseY, this);
    }

    private boolean cmp(int i, int j, boolean X) {
        if (X && enemies.get(i).getPosX() + enemies.get(i).getWidth() <= enemies.get(j).getPosX() + enemies.get(j).getWidth()) return true;
        if (!X && enemies.get(i).getPosY() + enemies.get(i).getHeight() <= enemies.get(j).getPosY() + enemies.get(j).getHeight()) return true;
        return false;
    }

    private void sortEnemies(boolean X) {
        for (int  i = 1; i < enemies.size(); i++)
            for (int j = 0; j < i; j++) if (cmp(i, j, X)) {
                Enemy tempEnemy = enemies.get(i);
                enemies.set(i, enemies.get(j));
                enemies.set(j, tempEnemy);
            }
    }

    public HUD getHud() {
        return hud;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }
}
