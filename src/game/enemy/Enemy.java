package game.enemy;

import game.gui.HealthBar;
import game.object.AnimateObject;
import game.object.GameObject;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class Enemy extends GameObject implements UpdatableObject, AnimateObject {
    private String tag;
    private Image frame[] = new Image[12];
    private long lastAniTime, lastMoveTime;
    private double speed, doublePosX, doublePosY;
    private int currentFrame, hp, path, frameAmount, currentHp;
    private HealthBar healthBar;
    private int reward;
    private boolean died;


    public Enemy(int posX, int posY, String tag, int path, int hp, int reward) {
        super(posX, posY, new Image("file:resources/enemy/" + tag + "_0.png"));
        this.tag = tag;
        this.path = path;
        this.died = false;
        this.hp = hp;
        this.currentHp = hp;
        this.healthBar = new HealthBar(posX + getWidth() / 2 - 15, posY - 10);
        currentFrame = 0;
        doublePosX = getPosX();
        doublePosY = getPosY();
        lastAniTime = lastMoveTime = System.currentTimeMillis();
        for (int i = 0; i < 12; i++) frame[i] = new Image("file:resources/enemy/" + tag + "_" + i + ".png");
        this.reward = reward;
    }

    private void move(double x, double y) {
        doublePosX += x;
        doublePosY += y;
        setPosX((int) doublePosX);
        setPosY((int) doublePosY);
    }

    @Override
    public void update() {
        animateUpdate(16);
        if (lastMoveTime + 10 < System.currentTimeMillis()) {
            if (getPosX() >= 850) move(-0.9 * speed, 0);
            if (790 <= getPosX() && getPosX() <= 850) move(-0.5 * speed, -0.75 * speed * path);
            if (750 <= getPosX() && getPosX() <= 790) move(-0.6364 * speed, -0.6364 * speed * path);
            if (670 <= getPosX() && getPosX() <= 750) move(-0.75 * speed, -0.5 * speed * path);
            if (480 <= getPosX() && getPosX() <= 670) move(-0.9 * speed, 0);
            if (440 <= getPosX() && getPosX() <= 480) move(-0.75 * speed, 0.5 * speed * path);
            if (400 <= getPosX() && getPosX() <= 440) move(-0.6364 * speed, 0.6364 * speed * path);
            if (320 <= getPosX() && getPosX() <= 400) move(-0.5 * speed, 0.75 * speed * path);
            if (getPosX() <= 320) move(-0.9 * speed, 0);
            healthBar.setPosX(getPosX() + getWidth() / 2 - 15);
            healthBar.setPosY(getPosY() - 10);
            setHealthBar(getHealthBar());
            lastMoveTime = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (healthBar !=null)
            healthBar.draw(gc);
    }

    @Override
    public void animateUpdate(int FPS) {
        if (lastAniTime + 1000 / FPS < System.currentTimeMillis()) {
            lastAniTime = System.currentTimeMillis();
            currentFrame = (currentFrame + 1) % frameAmount;
            setImage(frame[currentFrame]);
        }
    }

    public void setSpeed(double speed) { this.speed = speed; }
    public void setFrameAmount(int frameAmount) { this.frameAmount = frameAmount; }
    public long getLastAniTime() { return lastAniTime; }
    public long getLastMoveTime() { return lastMoveTime; }
    public void setLastAniTime(long lastAniTime) { this.lastAniTime = lastAniTime; }
    public void setLastMoveTime(long lastMoveTime) { this.lastMoveTime = lastMoveTime; }
    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }
    public HealthBar getHealthBar() {
        return healthBar;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }
    public int getReward() {
        return reward;
    }
    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setDied(boolean died) {
        this.died = died;
    }

    public boolean isDied() {
        return died;
    }
}
