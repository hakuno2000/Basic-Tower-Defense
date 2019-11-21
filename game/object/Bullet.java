package game.object;

import game.enemy.Enemy;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.SnapshotParameters;

public class Bullet extends GameObject implements UpdatableObject {
    private Enemy target;
    private boolean destroyed;
    private String path;
    private int dame;

    public Bullet(int posX, int posY, Enemy target, String path, int dame) {
        super(posX, posY, new Image("file:resources/"+path+".png"));
        this.path = path;
        this.target = target;
        destroyed = false;
        this.dame = dame;
    }

    @Override
    public void update() {
        int targetX = target.getPosX() + target.getWidth() / 2 - getWidth() / 2;
        int targetY = target.getPosY() + target.getHeight() / 2 - getHeight() / 2;
        int distance = (int) Math.sqrt(Math.pow(targetX - posX, 2) + Math.pow(targetY - posY, 2));
        distance /= 8;

        if (distance != 0) {
            double moveX = 0, moveY = 0;
            moveX = (double) (targetX - posX) / distance;
            moveY = (double) (targetY - posY) / distance;

            setPosX(posX + (int) moveX);
            setPosY(posY + (int) moveY);

            ImageView iv = new ImageView(new Image("file:resources/" + path + ".png"));
            double cc = 0;
            if (posX >= targetX) cc = 180;
            if (moveX != 0) iv.setRotate(Math.toDegrees(Math.atan(moveY / moveX)) + cc);
            else iv.setRotate(90 + cc);

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            Image rotatedImage = iv.snapshot(params, null);
            setImage(rotatedImage);
        } else {
            destroyed = true;
            target.getHealthBar().setHealth(target.getHealthBar().getHealth() - dame*target.getHealthBar().getTempHealth()/target.getHp());
            if (target.getCurrentHp() <= dame)
                target.setCurrentHp(0);
            else target.setCurrentHp(target.getCurrentHp() - dame);
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setPath(String path) {
        this.path = path;
    }
}