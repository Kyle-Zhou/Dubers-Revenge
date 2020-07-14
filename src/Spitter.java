import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;


/**
* [Spitter.java]
* a mutated zombie that is able to attack at a range
* subclass to Zombie
* contains overridden draw and attack methods
* @author Ethan Zhang
**/
class Spitter extends Zombie {
  private BufferedImage sheet, spriteRight, spriteLeft;
  private Rectangle range;
  
  /**
  * Spitter constructor 
  * inherits xCoordinate, yCoordinate, health, damage, and speed from Zombie
  * sets hitbox
  */
  Spitter(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprites();
    seteWidth(spriteRight.getWidth());
    seteHeight(spriteRight.getHeight());
    setxCentre(xCord + (geteWidth() / 2));
    setyCentre(yCord + (geteHeight() / 2));
    setHitbox(new Rectangle(xCord + 10, yCord, geteWidth() - 20, geteHeight()));
    range = new Rectangle(getxCord() - 200, getyCord() - 200, 400 + geteWidth(), 400 + geteWidth());
  }
  
  /**
  * loadSprites 
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
    try {
      sheet = ImageIO.read(new File("spitterSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 54, 106);
      spriteLeft = sheet.getSubimage(54, 0, 54, 106);
    } catch(Exception e) { System.out.println("Error Loading 'spitterSheet.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    if (getDirection() == true) {
      g.drawImage(spriteRight, getxCord(), getyCord(), null);
    } else {
      g.drawImage(spriteLeft,getxCord(), getyCord(), null);
    }
    g.setColor(Color.red);
    g.fillRect(getxCord()+7, getyCord()-10, (getStartingHealth()) / 2, 7);
    g.setColor(Color.green);
    g.fillRect(getxCord()+7, getyCord()-10, (getHealth()) / 2, 7);
    
  }
  
  @Override
  public void attack(Human duber) {
    setCooldown(50);
    duber.setHealth(duber.getHealth() - getDamage());
  } 
  
  /**
  * adjustRange
  * adjusts the hitbox of the spitter's range based on its coordinates
  */
  public void adjustRange() {
    range.setLocation(getxCord() - 200, getyCord() - 200);
  }
  
  /**
  * getRange
  * @return the hitbox of the range of the spitter
  */
  public Rectangle getRange() {
    return range;
  }
}