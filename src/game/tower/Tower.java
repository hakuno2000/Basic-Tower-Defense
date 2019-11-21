package game.tower;

import game.enemy.Enemy;
import game.gui.RangeCircle;
import game.gui.Ring;
import game.gui.UpdateBar;
import game.object.*;
import game.soldier.Soldier;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Tower extends GameObject implements UpdatableObject, ClickableObject {
    private RangeCircle rangeCircle;
    private Ring ring;
    private String tag, path;
    private long lastShootTime;
    private double range, speed;
    private boolean shoot, active;
    private int upgrade, tempUpgrade;
    private ArrayList<Bullet> bullets;
    private Soldier soldier;
    private UpdateBar updateBar, tempUpdateBar;
    private Effect buildSmoke;
    private int price;
    private int strength;
    private int upgradeprice;
    private int sellprice;

    private int rank = 1;

    Tower(int posX, int posY, String tag, int price, int strength, int upgradeprice, int sellprice) {
        super(posX, posY, new Image("file:resources/tower/" + tag + "_tower_" + 1 + ".png"));
        upgrade = -1;
        active = false;
        this.tag = tag;
        bullets = new ArrayList<Bullet>();
        lastShootTime = System.currentTimeMillis();
        tempUpdateBar = new UpdateBar(posX + 30, posY - 22);
        buildSmoke = new Effect(posX + 25, posY + 30, "file:resources/Effect/effect_buildSmoke_", 35, 60);
        this.price = price;
        this.strength = strength;
        this.upgradeprice = upgradeprice;
        this.sellprice = sellprice;
    }

    @Override
    public void onHover(int mouseX, int mouseY, Object caller) {
        if (ring != null) ring.onHover(mouseX, mouseY, this);
    }

    @Override
    public void onClick(int mouseX, int mouseY, Object caller) {
        if (ring != null) ring.onClick(mouseX, mouseY, this);
        rangeCircle = new RangeCircle(getXcenter(), getYcenter() - getHeight() / 2 + 19, getRange());

        if (hover(mouseX, mouseY)) {
            if (active) ring = new Ring(getXcenter(), getYcenter(), 1);
            else ring = new Ring(getXcenter(), getYcenter(), 0);
        } else {
            if (ring != null && ring.getUpgrade() >= 0) {
                updateBar = tempUpdateBar;
                tempUpgrade = ring.getUpgrade();
            }
            ring = null;
            rangeCircle = null;
        }
    }

    @Override
    public void update() {
        if (ring != null) ring.update();

        if (soldier != null) soldier.update();

        if (updateBar != null && updateBar.isDone()) {
            updateBar = null;
            upgrade = tempUpgrade;
        }
        if (updateBar != null) updateBar.update();

        for (Bullet bullet : bullets) bullet.update();

        if (buildSmoke != null) {
            buildSmoke.update();
            if (buildSmoke.isDestroyed()) buildSmoke = null;
        }
    }

    public void attack(ArrayList<Enemy> enemies) {
        if (range == 0) return;
        shoot = false;
        while (!bullets.isEmpty() && bullets.get(0).isDestroyed()) bullets.remove(0);
        if (lastShootTime + 1000 * speed < System.currentTimeMillis()) {
            lastShootTime = System.currentTimeMillis();
            for (Enemy enemy : enemies) {
                double dis = Math.pow(getXcenter() - enemy.getPosX(), 2) + Math.pow(getYcenter() - enemy.getPosY(), 2);
                if (dis <= range * range) {
                    bullets.add(new Bullet(posX + getWidth() / 2, posY - 10, enemy,path,strength));
                    soldier.setShooting(true);
                    if (soldier.getPosY() <= enemy.getPosY()) {
                        if (tag.equals("normal")) if (soldier.getPosX() <= enemy.getPosX()) soldier.setDirection("IV"); else soldier.setDirection("III");
                        if (tag.equals("sniper")) soldier.setDirection("front");
                    } else {
                        if (tag.equals("normal")) if (soldier.getPosX() <= enemy.getPosX()) soldier.setDirection("I"); else soldier.setDirection("II");
                        if (tag.equals("sniper")) soldier.setDirection("behind");
                    }
                    shoot = true;
                    break;
                }
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (soldier != null) soldier.draw(gc);
        if (buildSmoke != null) buildSmoke.draw(gc);
        if (updateBar != null) updateBar.draw(gc);
        if (rangeCircle != null) rangeCircle.draw(gc);
    }

    public void drawLayout(GraphicsContext gc) {
        if (ring != null) ring.draw(gc);
        for (Bullet bullet : bullets) bullet.draw(gc);
    }

    public int increaseRank() {
        if (rank >= 3) return 3;
        else rank++;
        return rank;
    }

    public void upgrade(Image image) {
        super.setImage(image);
        buildSmoke = new Effect(posX + 25, posY + 30, "file:resources/Effect/effect_buildSmoke_", 35, 60);
    }

    public Ring getRing() { return ring; }
    public RangeCircle getRangeCircle() { return rangeCircle; }
    public void setRangeCircle(RangeCircle rangeCircle) { this.rangeCircle = rangeCircle; }
    public boolean isShoot() { return shoot; }
    public void setShoot(boolean shoot) { this.shoot = shoot; }
    public String getTag() { return tag; }
    public double getRange() { return range; }
    public void setRange(double range) { this.range = range; }
    public int getUpgrade() { return upgrade; }
    public void setUpgrade(int upgrade) { this.upgrade = upgrade; }
    public void setActive(boolean active) { this.active = active; }
    public void setSpeed(double speed) { this.speed = speed; }
    public void setSoldier(Soldier soldier) { this.soldier = soldier; }
    public long getLastShootTime() { return lastShootTime; }
    public void setLastShootTime(long lastShootTime) { this.lastShootTime = lastShootTime; }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getUpgradeprice() {
        return upgradeprice;
    }

    public void setUpgradeprice(int upgradeprice) {
        this.upgradeprice = upgradeprice;
    }

    public int getRank() {
        return rank;
    }

    public int getSellprice() {
        return sellprice;
    }

    public void setSellprice(int sellprice) {
        this.sellprice = sellprice;
    }
}