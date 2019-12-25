import java.awt.Rectangle;
import java.awt.Graphics;

abstract class Wall extends Obstacle {
  
  Wall(int xCord, int yCord, int eWidth, int eHeight) {
    super(xCord, yCord, eWidth, eHeight);
  }
  
  Wall(int xCord, int yCord, int eWidth, int eHeight, int adjust) {
    super(xCord, yCord, eWidth, eHeight, adjust);
  }
  
  abstract void draw(Graphics g);
}