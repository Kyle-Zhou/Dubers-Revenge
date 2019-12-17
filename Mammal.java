import java.awt.Rectangle;
import java.awt.Graphics;

abstract class Mammal {

public int xCord, yCord, xDirection, yDirection, eWidth, eHeight, health, damage, speed;
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

  //move method
  abstract void move();

  public void attack() {

  }
}
