import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Toolkit;

/**
* [Flame.java]
* a projectile used by the flamethrower
* subclass to Projectile
* contains overridden travel and draw methods
* contains loadSprite method
* @author Kyle Zhou, Ethan Zhang
* Kyle Zhou - Foundation, Projectile Trajectory
* Ethan Zhang - Projectile Displacement, Spritework
**/

class Flame extends Projectile {
  private BufferedImage sprite;
  private int displaceX, displaceY, maxX, maxY;
  private double multiplierX, multiplierY;
  private Random random;
  
  /**
  * Flame constructor 
  * inherits xCoordinate, yCoordinate, speed from Projectile
  * sets hitbox
  */
  Flame(int xCord, int yCord, int speed){
    super(xCord, yCord, speed);
    random = new Random();
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
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
      sprite = ImageIO.read(new File("flame.png"));
    } catch(Exception e) { System.out.println("Error Loading 'flame.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }
  
  /**
  * travel 
  * based on original travel method from bullet
  * adds spread and varying trajectory 
  */
  @Override
  public void travel(Human duber, int xCursor, int yCursor) {
    if (getLimit() == false){
      multiplierX = Math.round(((double)(xCursor - duber.getxCentre()) / (double)maxX) * 15);
      multiplierY = Math.round(((double)(yCursor - duber.getyCentre()) / (double)maxY) * 15);
      if (multiplierX == 0) {
        multiplierX = 1;
      } else if (multiplierY == 0) {
        multiplierY = 1;
      }
      displaceX = (int)(Math.round((random.nextInt(25 + 25) - 25) * multiplierX));
      displaceY = (int)(Math.round((random.nextInt(25 + 25) - 25) * multiplierY));
      setAngle((float)Math.atan2(yCursor - duber.getyCentre() + displaceX, xCursor - duber.getxCentre() + displaceY));
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
