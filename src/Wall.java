import java.awt.Graphics;

/**
* [Wall.java]
* walls of the laboratory
* subclass to Obstacle
* @author Ethan Zhang
**/
abstract class Wall extends Obstacle {
  /**
  * Explosion constructor 
  * inherits xCoordinate, yCoordinate from Entity
  * sets hitbox
  */
  Wall(int xCord, int yCord) {
    super(xCord, yCord);
  }
}