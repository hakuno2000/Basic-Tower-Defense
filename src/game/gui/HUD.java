package game.gui;

import game.enemy.Enemy;
import game.object.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class HUD extends GameObject {
    private int life;
    private int coins;
    private int wave;
    Text text1 = new Text();
    Text text2 = new Text();
    Text text3 = new Text();

    public HUD(int posX,int posY){
        super(posX,posY,new Image("file:resources/hud_background.png"));
        this.life = 5;
        this.coins = 600;
        this.wave = 1;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void IncreaseCoins(Enemy enemy){
        coins += enemy.getReward();

    }

    public void DecreaseLife(){
        life--;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        Font myFont = Font.loadFont("file:resources/fast_99/fast99.ttf", 25);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        text1.setText(String.valueOf(life));
        text2.setText(String.valueOf(coins));
        text3.setText(String.valueOf(wave));

        gc.clearRect(45,25,10,10);
        gc.fillText(text1.getText(),75,55);
        gc.clearRect(125,25,40,10);
        gc.fillText(text2.getText(),180,55);
        gc.clearRect(60,60,5,10);
        gc.fillText("WAVE "+ text3.getText(),75,95);


    }
}
