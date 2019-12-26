abstract class Weapon {
  private int xCord, yCord, damage;
  
  Weapon(int xCord, int yCord, int damage) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.damage = damage;
  }

  public int getxCord() {
    return xCord;
  }

  public int getyCord() {
    return yCord;
  }

  public int getDamage() {
    return damage;
  }

  public void setxCord(int xCord) {
    this.xCord = xCord;
  }

  public void setyCord(int yCord) {
    this.yCord = yCord;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }
}