import java.awt.Graphics;

abstract class Detonation extends Entity {
  private int sprite;
  
  Detonation(int xCord, int yCord) {
    super(xCord, yCord);
    sprite = 0;
  }
  
  abstract void draw(Graphics g);
  
  public void update() {
    sprite++;
  }
  
  public int getSprite() {
    return sprite;
  }
  
  abstract int getFrames();
}