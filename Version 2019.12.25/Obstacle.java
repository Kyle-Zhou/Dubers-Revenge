import java.awt.Rectangle;
import java.awt.Graphics;

abstract class Obstacle {
  public int xCord, yCord, eWidth, eHeight, adjust;
  public Rectangle hitbox;

  //constructor
  Obstacle(int xCord, int yCord) {
    this.xCord = xCord;
    this.yCord = yCord;
  }
  
  Obstacle(int xCord, int yCord, int eWidth, int eHeight) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.eWidth = eWidth;
    this.eHeight = eHeight;
  }
  
  Obstacle(int xCord, int yCord, int eWidth, int eHeight, int adjust) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.eWidth = eWidth;
    this.eHeight = eHeight;
    this.adjust = adjust;
  }
  
  abstract void draw(Graphics g);
}