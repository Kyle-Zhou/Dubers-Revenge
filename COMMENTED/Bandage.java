import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;

/**
* [Bandage.java]
* subclass to Entity
* contains loadSprite method
* contains overriden draw method
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
      sprite = ImageIO.read(new File("bandage.png"));
    } catch(Exception e) { System.out.println("Error Loading 'bandage.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }  
}
