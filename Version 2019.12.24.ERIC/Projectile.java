import java.awt.Rectangle;
import java.awt.Graphics;

abstract class Projectile {
  public int xCord, yCord, speed, eWidth, eHeight;
  public Rectangle hitbox;
  public float angle, xDirection, yDirection;
  public boolean limit = false;

  Projectile(int xCord, int yCord, int speed){
    this.xCord = xCord;
    this.yCord = yCord;
    this.speed = speed;
  }

  abstract void draw(Graphics g);
  
  abstract void travel(Human duber, int xCursor, int yCursor);
}