import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
* [Bullet.java]
* subclass to Projectile
* contains overridden travel and draw methods
* contains loadSprite method
* @author Kyle Zhou, Ethan Zhang
* Kyle Zhou - firing bullets (travel)
* Ethan Zhang - drawing
**/

class Bullet extends Projectile {
  private BufferedImage sprite;
  
  /**
  * Bullet constructor 
  * inherits xCoordinate, yCoordinate, speed from Projectile
  * sets hitbox
  */
  Bullet(int xCord, int yCord, int speed) {
    super(xCord, yCord, speed);
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
  }
  
  /**
  * loadSprite 
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("bullet.png"));
    } catch(Exception e) { System.out.println("Error Loading 'bullet.png'...");};
  }

  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }
  
  @Override
  public void travel(Human duber, int xCursor, int yCursor) {
    if (getIsLimit() == false){
      setAngle((double)Math.atan2(yCursor - duber.getyCentre(), xCursor - duber.getxCentre()));
      setxDirection((double)((getSpeed()) * Math.cos(getAngle())));
      setyDirection((double)((getSpeed()) * Math.sin(getAngle())));
      setLimit(true);
    } 
    getHitbox().setLocation((int)Math.round(getHitbox().getX()) + (int)Math.round(getxDirection()),
          (int)Math.round(getHitbox().getY()) + (int)Math.round(getyDirection()));
    setxCord(getxCord() + (int)Math.round(getxDirection()));
    setyCord(getyCord() + (int)Math.round(getyDirection()));
  }
}
