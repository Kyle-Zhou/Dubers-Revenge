import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.awt.Toolkit;

/**
* [Shell.java]
* subclass to Projectile
* contains overridden travel and draw methods
* contains loadSprite method
* @author Kyle Zhou, Ethan Zhang
* Kyle Zhou - foundation for firing bullets (travel)
* Ethan Zhang - added spread and trajectory (travel)
**/

class Shell extends Projectile {
  private BufferedImage sprite;
  private int displaceX, displaceY, maxX, maxY;
  private double multiplierX, multiplierY;
  private Random random;
  
  /**
  * Shell constructor 
  * inherits xCoordinate, yCoordinate, speed from Projectile
  * sets hitbox
  */
  Shell(int xCord, int yCord, int speed){
    super(xCord, yCord, speed);
    random = new Random();
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
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
      sprite = ImageIO.read(new File("pellet.png"));
    } catch(Exception e) { System.out.println("Error Loading 'pellet.png'...");};
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
    if (getIsLimit() == false){
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