import java.awt.Rectangle;

abstract class Mammal extends Entity {
  private int health, speed;
  private int xDirection, yDirection, damage, xCentre, yCentre;

  //constructor
  Mammal(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord);
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

  public int getxCord() {
    return super.getxCord();
  }

  public int getyCord() {
    return super.getyCord();
  }

  public int getxDirection() {
    return xDirection;
  }

  public int getyDirection() {
    return yDirection;
  }

  public int geteWidth() {
    return super.geteWidth();
  }

  public int geteHeight() {
    return super.geteHeight();
  }

  public int getDamage() {
    return damage;
  }

  public int getxCentre() {
    return xCentre;
  }

  public int getyCentre() {
    return yCentre;
  }

  public Rectangle getHitbox() {
    return super.getHitbox();
  }

  public void setHitbox(Rectangle hitbox) {
    super.setHitbox(hitbox);
  }

  public void setxCord(int xCord) {
    super.setxCord(xCord);
  }

  public void setyCord(int yCord) {
    super.setyCord(yCord);
  }

  public void setxDirection(int xDirection) {
    this.xDirection = xDirection;
  }

  public void setyDirection(int yDirection) {
    this.yDirection = yDirection;
  }

  public void seteWidth(int eWidth) {
    super.seteWidth(eWidth);
  }

  public void seteHeight(int eHeight) {
    super.seteHeight(eHeight);
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public void setxCentre(int xCentre) {
    this.xCentre = xCentre;
  }

  public void setyCentre(int yCentre) {
    this.yCentre = yCentre;
  }
}
