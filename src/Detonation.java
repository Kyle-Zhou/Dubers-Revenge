import java.awt.Graphics;

/**
* [Detonation.java]
* a rapid increase in volume and release of energy in an extreme manner that proves harmful
* subclass to Entity
* superclass to Explosion, Implosion
* contains abstract getFrames method
* @author Ethan Zhang
**/

abstract class Detonation extends Entity {
  private int sprite;
  
  /**
  * Detonation constructor 
  * inherits xCoordinate, yCoordinate from Entity
  * defaults sprite to zero
  */
  Detonation(int xCord, int yCord) {
    super(xCord, yCord);
    sprite = 0;
  }
  
  /**
  * update
  * increases sprite by one, shifting the sprite used in BufferedImage array to simulate animation
  */
  public void update() {
    sprite++;
  }
  
  /**
  * getSprite
  * @return current value of sprite
  */
  public int getSprite() {
    return sprite;
  }
  
  /**
  * getFrames
  * @return the maximum number of individual sprites in the sprite sheet
  */
  abstract int getFrames();
}