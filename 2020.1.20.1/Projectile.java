import java.awt.Graphics;

/**
* [Projectile.java]
* an object thrown into space by the exertion of a force
* subclass to Entity
* superclass to Bullet, Shell, Spit, Rocket, Flame
* contains abstract travel method
* @author Kyle Zhou
**/

abstract class Projectile extends Entity {
  
  private double speed;
  private double angle, xDirection, yDirection;
  private boolean limit = false;
  
  /**
  * Projectile constructor 
  * inherits xCoordinate, yCoordinate from Entity
  * sets speed as parameter
  */
  Projectile(int xCord, int yCord, int speed){
    super(xCord, yCord);
    this.speed = speed;
  }
  
  /**
  * travel
  * Calculates angle and position of mouse and fires bullets to it's position
  * @param duber, Human
  * @param xCursor, current X location of the cursor
  * @param yCursor, current Y location of the cursor
  */
  abstract void travel(Human duber, int xCursor, int yCursor);
  
  /**
  * getSpeed 
  * @return double value of speed
  */
  public double getSpeed() {
    return speed;
  }
  
  /**
  * getxDirection 
  * @return double value of xDirection
  */
  public double getxDirection() {
    return xDirection;
  }

  /**
  * getyCord
  * @return double value of yDirection
  */
  public double getyDirection() {
    return yDirection;
  }

  /**
  * getAngle
  * @return double value of angle
  */
  public double getAngle() {
    return angle;
  }

  /**
  * getIsLimit
  * @return boolean value of limit
  */
  public boolean getLimit() {
    return limit;
  }

  /**
  * setLimit
  * @param limit, boolean value of limit
  */
  public void setLimit(boolean limit) {
    this.limit = limit;
  }

  /**
  * setAngle 
  * @param angle, double value of angle
  */
  public void setAngle(double angle) {
    this.angle = angle;
  }

  /**
  * setSpeed 
  * @param speed, double value of speed
  */
  public void setSpeed(double speed) {
    this.speed = speed;
  }

  /**
  * setxDirection
  * @param xDirection, double value of xDirection
  */
  public void setxDirection(double xDirection) {
    this.xDirection = xDirection;
  }

  /**
  * setyDirection 
  * @param yDirection, double value of yDirection
  */
  public void setyDirection(double yDirection) {
    this.yDirection = yDirection;
  }
}