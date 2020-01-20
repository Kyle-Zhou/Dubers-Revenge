import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
* [Spit.java]
* corrosive acid spewed by a spitter
* subclass to Projectile
* contains overridden travel and draw methods
* contains loadSprite method
* @author Kyle Zhou, Ethan Zhang
* Kyle Zhou - Foundation
* Ethan Zhang - Projectile Trajectory, Spritework
**/
class Spit extends Projectile {
  private BufferedImage sprite;
  Spit(int xCord, int yCord, int speed) {
    super(xCord, yCord, speed);
    loadSprites();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
  }

  /**
  * loadSprites 
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
    try {
      sprite = ImageIO.read(new File("spit.png"));
    } catch(Exception e) { System.out.println("Error Loading 'spit.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }
  
  /**
  * travel 
  * based on original travel method from bullet
  * directions are opposite in order to travel towards duber 
  */
  @Override
  public void travel(Human duber, int xCursor, int yCursor) {
    if (getLimit() == false){
       setAngle((double)Math.atan2(getyCord() - duber.getyCentre(), getxCord() - duber.getxCentre()));
      setxDirection(-(double)((getSpeed()) * Math.cos(getAngle())));
      setyDirection(-(double)((getSpeed()) * Math.sin(getAngle())));
      setLimit(true);
    } 
    getHitbox().setLocation((int)Math.round(getHitbox().getX()) + (int)Math.round(getxDirection()),
          (int)Math.round(getHitbox().getY()) + (int)Math.round(getyDirection()));
    setxCord(getxCord() + (int)Math.round(getxDirection()));
    setyCord(getyCord() + (int)Math.round(getyDirection()));
  }
}