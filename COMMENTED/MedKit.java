import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
* [MedKit.java]
* subclass to Entity
* contains overridden draw method
* contains loadSprite method
* @author Kyle Zhou
**/

class MedKit extends Entity {
  
  private BufferedImage sprite;
  
  /**
  * MedKit constructor 
  * inherits xCoordinate, yCoordinate from Entity
  * sets hitbox
  */
  MedKit (int xCord, int yCord){
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
      sprite = ImageIO.read(new File("medkit.png"));
    } catch(Exception e) { System.out.println("Error Loading 'medkit.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }
    
}
