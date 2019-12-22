import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;

class Projectile {

  private BufferedImage sprite;
  private int xCord, yCord, xDirection, yDirection, speed, eWidth, eHeight;
  public Rectangle hitbox;
  private boolean trajectory = false;

//  public int targetX, targetY, bulletX, bulletY, bulletSpeedX, bulletSpeedY;

  Projectile(int xCord, int yCord, int speed){
    this.xCord = xCord;
    this.yCord = yCord;
    this.speed = speed;
    loadSprite();
    eWidth = sprite.getWidth();
    eHeight = sprite.getHeight();
    hitbox = new Rectangle(xCord, yCord, eWidth, eHeight);
  }

  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("bullet.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite...");};
  }

  public void draw(Graphics g) {
    g.drawImage(sprite, xCord, yCord, null);
    g.setColor(Color.RED);
    g.drawRect(xCord, yCord, eWidth, eHeight);
  }
  
  public void travel(Human duber, int xCursor, int yCursor) {
    if (trajectory == false) {
      xDirection = xCursor - duber.xCord;
      yDirection = yCursor - duber.yCord;
      trajectory = true;
    }
    hitbox.setLocation((int)hitbox.getX() + this.xDirection, (int)hitbox.getY() + this.yDirection);
    this.xCord += this.xDirection;
    this.yCord += this.yDirection;
  }
}
