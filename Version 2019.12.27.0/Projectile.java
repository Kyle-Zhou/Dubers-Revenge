import java.awt.Rectangle;
import java.awt.Graphics;

abstract class Projectile extends Entity {
  private int speed;
  private float angle, xDirection, yDirection;
  private boolean limit = false;

  Projectile(int xCord, int yCord, int speed){
    super(xCord, yCord);
    this.speed = speed;
  }
  
  abstract void travel(Human duber, int xCursor, int yCursor);

  public int getSpeed() {
    return speed;
  }

  public Rectangle getHitbox() {
    return super.getHitbox();
  }

  public float getxDirection() {
    return xDirection;
  }

  public float getyDirection() {
    return yDirection;
  }

  public float getAngle() {
    return angle;
  }

  public boolean isLimit() {
    return limit;
  }

  public void setLimit(boolean limit) {
    this.limit = limit;
  }

  public void setAngle(float angle) {
    this.angle = angle;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public void setHitbox(Rectangle hitbox) {
    super.setHitbox(hitbox);
  }

  public void setxDirection(float xDirection) {
    this.xDirection = xDirection;
  }

  public void setyDirection(float yDirection) {
    this.yDirection = yDirection;
  }
}