import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Zombie extends Mammal {
  BufferedImage sprite;
  
  Zombie(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprite();
    eWidth = sprite.getWidth();
    eHeight = sprite.getHeight();
    hitbox = new Rectangle(xCord, yCord, eWidth, eHeight);
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("undead.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite...");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, xCord, yCord, null);
    g.setColor(Color.RED);
    g.drawRect(xCord, yCord, eWidth, eHeight);
  }
  
  public void move() {
    hitbox.setLocation((int)hitbox.getX() + this.xDirection, (int)hitbox.getY() + this.yDirection);
    this.xCord += this.xDirection;
    this.yCord += this.yDirection;
  }
  
  public void attack() {
  }
}