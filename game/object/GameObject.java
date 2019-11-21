package game.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
    public double scale;
    protected int posX, posY;
    private Image image;
    private int height, width;

    public GameObject() {
    }

    public GameObject(int posX, int posY, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
        height = (int) image.getHeight();
        width = (int) image.getWidth();
    }

    public GameObject(Image image) {
        this.image = image;
        height = (int) image.getHeight();
        width = (int) image.getWidth();
        this.posX = 0;
        this.posY = 0;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(getImage(), getPosX(), getPosY());
    }

    protected boolean hover(int mouseX, int mouseY) {
        return (getPosX() <= mouseX && mouseX <= getPosX() + getImage().getWidth()
                && getPosY() <= mouseY && mouseY <= getPosY() + getImage().getHeight());
    }

    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public int getXcenter() {
        return getPosX() + getWidth() / 2;
    }
    protected void setXcenter(int x) {
        setPosX(x - (int) getImage().getWidth() / 2);
    }

    public int getYcenter() {
        return getPosY() + getHeight() / 2;
    }
    protected void setYcenter(int y) {
        setPosY(y - (int) getImage().getHeight() / 2);
    }
}
