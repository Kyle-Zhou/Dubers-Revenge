import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;

class Bullet extends Projectile {
  private BufferedImage sprite;
  
  Bullet(int xCord, int yCord, int speed){
    super(xCord, yCord, speed);
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
    angle = (float)Math.atan2(yCursor - duber.yCentre, xCursor - duber.xCentre);
    if (limit == false){
      xDirection = (float)((speed) * Math.cos(angle));
      yDirection = (float)((speed) * Math.sin(angle));
      limit = true;
    } 
    hitbox.setLocation((int)hitbox.getX() + (int)this.xDirection, (int)hitbox.getY() + (int)this.yDirection);
    xCord += xDirection;
    yCord += yDirection;
  }
}
