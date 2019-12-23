import java.awt.Rectangle;

abstract class Obstacle {
  public int xCord, yCord, eWidth, eHeight;
  public Rectangle hitbox;

  //constructor
  Obstacle(int xCord, int yCord) {
    this.xCord = xCord;
    this.yCord = yCord;
  }
}