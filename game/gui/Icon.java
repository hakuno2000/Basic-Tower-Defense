package game.gui;

import game.object.GameObject;
import game.object.UpdatableObject;
import game.tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Icon extends GameObject implements UpdatableObject {
    private int tag;
    private double range;
    private boolean isOn;
    private String imageOn, imageOff;
    Text text = new Text();

    public Icon(int posX, int posY, int tag) {
        super(posX, posY, new Image("file:resources/icon/icon_" + tag + "_off.png"));
        this.tag = tag;
        range = 0;
        scale = 0;
        isOn = false;
        imageOn = "file:resources/icon/icon_" + tag + ".png";
        imageOff = "file:resources/icon/icon_" + tag + "_off.png";
    }
    public int onClick(int mouseX, int mouseY, Object caller) {
        if (hover(mouseX, mouseY)) return tag;
        return -1;
    }

    public void onHover(int mouseX, int mouseY, Object caller) {
        if (caller instanceof Tower) {
            Tower tower = (Tower) caller;
            if (hover(mouseX, mouseY)) {
                tower.setRangeCircle(new RangeCircle(tower.getXcenter(), tower.getYcenter(), getRange()));
            }
        }

        if (hover(mouseX, mouseY)) {
            if (!isOn) setImage(new Image(imageOn, getWidth() * scale, getHeight() * scale, false, true));
            isOn = true;
        } else {
            setImage(new Image(imageOff, getWidth() * scale, getHeight() * scale, false, true));
            isOn = false;
        }
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void update() {
        if (scale < 0.65) {
            scale += 0.05;
            setImage(new Image(imageOff, getWidth() * scale, getHeight() * scale, false, true));
        }
    }


    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        Font myFont = Font.loadFont("file:resources/fast_99/fast99.ttf", 13);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText(text.getText(),posX+20,posY+65);

    }

    public void setText(String text) {
        this.text.setText(text);
    }
}
