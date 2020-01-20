import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
* [North.java]
* the north, east, and west walls of the laboratory
* subclass to Wall
* contains overridden draw method
* @author Ethan Zhang
**/
class North extends Wall {
  private BufferedImage sprite;
  private Rectangle eastHitbox, westHitbox;
  
  /**
  * North constructor 
  * inherits xCoordinate, yCoordinate from Entity
  * sets hitboxes
  */
  North(int xCord, int yCord) {
    super(xCord, yCord);
    loadSprites();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), 150));
    eastHitbox = new Rectangle(geteWidth() - 64, yCord, 64, geteHeight());
    westHitbox = new Rectangle(xCord, yCord, 64, geteHeight());
  }
  
  /**
  * loadSprites
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
    try {
      sprite = ImageIO.read(new File("north.png"));
    } catch(Exception e) { System.out.println("Error Loading 'north.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
  }
  
  /**
  * getEastHitbox
  * @return the hitbox of the east wall
  */
  public Rectangle getEastHitbox() {
    return eastHitbox;
  }
  
  /**
  * getWestHitbox
  * @return the hitbox of the west wall
  */
  public Rectangle getWestHitbox() {
    return westHitbox;
  }
}
