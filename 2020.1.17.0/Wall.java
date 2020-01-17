import java.awt.Graphics;

abstract class Wall extends Obstacle {
  
  Wall(int xCord, int yCord) {
    super(xCord, yCord);
  }
  
  abstract void draw(Graphics g);
}