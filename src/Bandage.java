import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
* [Bandage.java]
* a pick-up that will heal duber after being picked up and pressing "h"
* subclass to Entity
* contains overridden draw method
* @author Kyle Zhou
**/

class Bandage extends Entity {
  
  private BufferedImage sprite;
  
  /**
  * Bandage constructor 
  * inherits xCoordinate, yCoordinate from Entity
  * sets hitbox
  */
  Bandage (int xCord, int yCord){
    super(xCord, yCord);
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
      sprite = ImageIO.read(new File("bandage.png"));
    } catch(Exception e) { System.out.println("Error Loading 'bandage.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }  
}
