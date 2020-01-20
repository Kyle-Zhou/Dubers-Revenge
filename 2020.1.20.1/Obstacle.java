import java.awt.Graphics;

/**
* [Obstacle.java]
* objects that obstruct movement for mammals
* subclass to Entity
* @author Ethan Zhang
**/
abstract class Obstacle extends Entity {
  /**
  * Obstacle constructor 
  * inherits xCoordinate, yCoordinate from Entity
  */
  Obstacle(int xCord, int yCord) {
    super(xCord, yCord);
  }
}