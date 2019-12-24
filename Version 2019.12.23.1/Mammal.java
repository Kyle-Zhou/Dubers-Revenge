import java.awt.Rectangle;

abstract class Mammal {
  private int health, speed;
  public int xCord, yCord, xDirection, yDirection, eWidth, eHeight, damage, xCentre, yCentre;
  public Rectangle hitbox;

  //constructor
  Mammal(int xCord, int yCord, int health, int damage, int speed) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.xDirection = 0;
    this.yDirection = 0;
    this.health = health;
    this.damage = damage;
    this.speed = speed;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getHealth() {
    return this.health;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getSpeed() {
    return this.speed;
  }
}
