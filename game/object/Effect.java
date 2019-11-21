package game.object;

import javafx.scene.image.Image;

public class Effect extends GameObject implements UpdatableObject, AnimateObject {
    private boolean destroyed;
    private int FPS;
    private int currentFrame, frameAmount;
    private Image[] frame;
    private long lastAniTime;

    public Effect(int posX, int posY, String url, int frameAmount, int FPS) {
        super(posX, posY, new Image(url + "0.png"));
        destroyed = false;
        currentFrame = 0;
        this.FPS = FPS;
        this.frameAmount = frameAmount;
        frame = new Image[frameAmount];
        for (int i = 0; i < frameAmount; i++) frame[i] = new Image(url + i + ".png");
        lastAniTime = System.currentTimeMillis();
    }

    @Override
    public void animateUpdate(int FPS) {
        if (lastAniTime + 1000 / FPS < System.currentTimeMillis()) {
            lastAniTime = System.currentTimeMillis();
            if (++currentFrame >= frameAmount) {
                destroyed = true;
                return;
            }
            setImage(frame[currentFrame]);
        }
    }

    @Override
    public void update() {
        animateUpdate(FPS);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
