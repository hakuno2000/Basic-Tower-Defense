package game.gui;

import game.object.GameObject;
import game.object.UpdatableObject;
import game.tower.MachineGunTower;
import game.tower.Tower;
import game.tower.NormalTower;
import game.tower.SniperTower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.crypto.Mac;


public class Ring extends GameObject implements UpdatableObject {
    private Icon[] icons;
    private int upgrade, iconAmount;

    public Ring(int xCenter, int yCenter, int tag) {
        super(new Image("file:resources/gui_ring.png"));
        setXcenter(xCenter);
        setYcenter(yCenter);
        scale = 0;
        upgrade = -1;
        if (tag == 0) iconAmount = 4;
        else iconAmount = 2;
        icons = new Icon[iconAmount];
        if (iconAmount == 4) {
            for (int i = 0; i < iconAmount; i++) icons[i] = new Icon(-100, -100, i);
//            trụ bắn cung có tầm bắn 150
            icons[0].setRange(200);
//            trụ phép có tầm bắn 200
            icons[1].setRange(250);
//            trụ ??? có tầm bắn ngắn
            icons[2].setRange(150);
        } else {
            for (int i = 0; i < iconAmount; i++) icons[i] = new Icon(-100, -100, i + 4);
        }
    }

    public void onClick(int mouseX, int mouseY, Object caller) {
        for (Icon icon : icons) {
            upgrade = icon.onClick(mouseX, mouseY, caller);
            if (upgrade >= 0) break;
        }
    }

    public void onHover(int mouseX, int mouseY, Object caller) {
        if (iconAmount == 2) {
            icons[0].setRange((int) ((Tower) caller).getRange() + 50);
            if (caller instanceof NormalTower){
                icons[0].setText(String.valueOf(((NormalTower) caller).getUpgradeprice()));
                icons[1].setText(String.valueOf(((NormalTower) caller).getSellprice()));
            } else if (caller instanceof SniperTower){
                icons[0].setText(String.valueOf(((SniperTower) caller).getUpgradeprice()));
                icons[1].setText(String.valueOf(((SniperTower) caller).getSellprice()));
            } else if (caller instanceof MachineGunTower){
                icons[0].setText(String.valueOf(((MachineGunTower) caller).getUpgradeprice()));
                icons[1].setText(String.valueOf(((MachineGunTower) caller).getUpgradeprice()));
            }
        }
        for (Icon icon : icons) icon.onHover(mouseX, mouseY, caller);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        for (Icon icon : icons) icon.draw(gc);
    }

    // Zoom vào cái vòng tròn chọn Icon
    public void update() {
        if (scale < 0.7) {
            scale += 0.1;
            setPosX(getPosX() + (int) getImage().getWidth() / 2);
            setPosY(getPosY() + (int) getImage().getHeight() / 2);
            setImage(new Image("file:resources/gui_ring.png", getWidth() * scale, getHeight() * scale, false, true));
            setPosX(getPosX() - (int) getImage().getWidth() / 2);
            setPosY(getPosY() - (int) getImage().getHeight() / 2);
        }

        for (Icon icon : icons) icon.update();

        if (iconAmount == 2) {
            int x_l = getPosX() - (int) icons[0].getImage().getWidth() / 2 + 10;
            int y = getPosY() + (int) getImage().getWidth() / 2 - (int) icons[0].getImage().getHeight() / 2;
            int x_r = getPosX() + (int) getImage().getWidth() - (int) icons[0].getImage().getWidth() / 2 - 10;
            icons[0].setPosX(x_r);
            icons[0].setPosY(y);
            icons[1].setPosX(x_l);
            icons[1].setPosY(y);

        } else {
            int x_left = getPosX();
            int x_right = getPosX() + (int) getImage().getWidth() - (int) icons[1].getImage().getWidth();
            int y_up = getPosY();
            int y_down = getPosY() + (int) getImage().getHeight() - (int) icons[2].getImage().getHeight();
            icons[0].setPosX(x_left);
            icons[0].setPosY(y_up);
            icons[0].setText(Integer.toString(new NormalTower(0, 0).getPrice()));

            icons[1].setPosX(x_right);
            icons[1].setPosY(y_up);
            icons[1].setText(Integer.toString(new SniperTower(0, 0).getPrice()));

            icons[2].setPosX(x_left);
            icons[2].setPosY(y_down);
            icons[2].setText(Integer.toString(new MachineGunTower(0, 0).getPrice()));

            icons[3].setPosX(x_right);
            icons[3].setPosY(y_down);
        }
    }

    public int getUpgrade() {
        return upgrade;
    }
}
