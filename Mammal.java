import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;

abstract class Mammal {

  public BufferedImage sprite;
  public int xCord, yCord, xDirection, yDirection, eWidth, eHeight, health, damage, speed;
  public Rectangle hitbox;

  //constructor 
  Mammal(int xCord, int yCord, int health, int damage, int speed) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.xDirection = 0;
    this.yDirection = 0;
    this.health = health;
    this.damage = damage;
    this.speed = speed;
  }
  
  //move method
  public abstract void loadSprite();
  
  public abstract void draw(Graphics g);
  
  public abstract void move();
  
  public abstract void attack();
}
