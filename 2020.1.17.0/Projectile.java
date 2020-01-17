abstract class Projectile extends Entity {
  
  private double speed;
  private double angle, xDirection, yDirection;
  private boolean limit = false;
  
  Projectile(int xCord, int yCord, int speed){
    super(xCord, yCord);
    this.speed = speed;
  }
  
  abstract void travel(Human duber, int xCursor, int yCursor);

  public double getSpeed() {
    return speed;
  }

  public double getxDirection() {
    return xDirection;
  }

  public double getyDirection() {
    return yDirection;
  }

  public double getAngle() {
    return angle;
  }

  public boolean isLimit() {
    return limit;
  }

  public void setLimit(boolean limit) {
    this.limit = limit;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public void setxDirection(double xDirection) {
    this.xDirection = xDirection;
  }

  public void setyDirection(double yDirection) {
    this.yDirection = yDirection;
  }
  
  
}