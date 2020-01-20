import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
* [Bloated.java]
* a massive zombie that explodes on death
* subclass to Zombie
* contains overridden draw and attack methods
* @author Ethan Zhang
**/
class Bloated extends Zombie {
  private BufferedImage sheet, spriteRight, spriteLeft;
  
  /**
  * Bloated constructor 
  * inherits xCoordinate, yCoordinate, health, damage, and speed from Zombie
  * sets hitbox
  */
  Bloated(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprites();
    seteWidth(spriteRight.getWidth());
    seteHeight(spriteRight.getHeight());
    setxCentre(xCord + (geteWidth() / 2));
    setyCentre(yCord + (geteHeight() / 2));
    setHitbox(new Rectangle(xCord + 10, yCord + 5, geteWidth() - 20, geteHeight() - 10));
  }
  
  /**
  * loadSprites
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
    try {
      sheet = ImageIO.read(new File("bloatedSheet.png"));
      spriteRight = sheet.getSubimage(0, 0, 106, 152);
      spriteLeft = sheet.getSubimage(106, 0, 106, 152);
    } catch(Exception e) { System.out.println("Error Loading 'bloatedSheet.png'...");};
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
    setCooldown(150);
    duber.setHealth(duber.getHealth() - getDamage());
  }
  
}