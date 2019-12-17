abstract class Mammal{

  public int xCord;
  public int yCord;
  public int health;
  public int damage;
  public int speed;

  //constructor 
  Mammal(int xCord, int yCord, int health, int damage, int speed){
    this.xCord = xCord;
    this.yCord = yCord;
    this.health = health;
    this.damage = damage;
    this.speed = speed;
  }
  
  //move method
  public abstract void move();
  
  public abstract void attack();
  
}