import java.awt.Rectangle;
import java.awt.Graphics;

/**
* [Entity.java]
* contains abstract method draw
* Superclass to all in game objects
* @author Kyle Zhou, Ethan Zhang
**/

abstract class Entity {
  
  private int xCord, yCord, eWidth, eHeight;
  private Rectangle hitbox;
  
  /**
  * Entity constructor 
  * takes in xCoordinate and yCoordinate as parameters
  */
  Entity (int x, int y) {
    this.xCord = x;
    this.yCord = y;
  }

  /**
  * draw 
  * draws the image to the screen
  * @param g, Graphics
  */
  abstract void draw(Graphics g);
  
  /**
  * setxCord
  * @param xCord, int value of xCord
  */   
  public void setxCord(int xCord) {
    this.xCord = xCord;
  }

  /**
  * setyCord
  * @param yCord, int value of yCord
  */   
  public void setyCord(int yCord) {
    this.yCord = yCord;
  }

  /**
  * seteWidth
  * @param eWidth, int value of eWidth
  */   
  public void seteWidth(int eWidth) {
    this.eWidth = eWidth;
  }

  /**
  * seteHeight
  * @param eHeight, int value of eHeight
  */   
  public void seteHeight(int eHeight) {
    this.eHeight = eHeight;
  }

  /**
  * getHitbox 
  * @return Rectangle value of hitbox
  */
  public Rectangle getHitbox() {
    return hitbox;
  }

  /**
  * setHitbox
  * @param hitbox, Rectangle value of hitbox
  */   
  public void setHitbox(Rectangle hitbox) {
    this.hitbox = hitbox;
  }

  /**
  * getxCord 
  * @return int value of xCord
  */
  public int getxCord() {
    return xCord;
  }

  /**
  * getyCord 
  * @return int value of yCord
  */
  public int getyCord() {
    return yCord;
  }

  /**
  * geteWidth 
  * @return int value of eWidth
  */
  public int geteWidth() {
    return eWidth;
  }

  /**
  * geteHeight 
  * @return int value of eHeight
  */
  public int geteHeight() {
    return eHeight;
  }
  
}
