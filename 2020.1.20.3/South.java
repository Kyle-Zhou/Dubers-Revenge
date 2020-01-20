import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

/**
* [South.java]
* subclass to Detonation
* contains overridden draw method
* @author Ethan Zhang
**/
class South extends Wall {
  BufferedImage sprite;
  
  /**
  * South constructor 
  * inherits xCoordinate, yCoordinate from Entity
  * sets hitbox
  */
  South(int xCord, int yCord) {
    super(xCord, yCord);
    loadSprites();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setHitbox(new Rectangle(xCord, yCord + 30, geteWidth(), geteHeight() - 30));
  }
  
  /**
  * loadSprites 
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
    try {
      sprite = ImageIO.read(new File("south.png"));
    } catch(Exception e) { System.out.println("Error Loading 'south.png'...");};
  }
  
  @Override
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
  }
}