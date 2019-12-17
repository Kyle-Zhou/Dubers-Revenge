class Human extends Mammal{

  Human(int xCord, int yCord, int health, int damage, int speed){
    super(xCord, yCord, health, damage, speed);
  }

  @Override
  public void move(){
    System.out.print("pass");
  }
  
  public void attack(){
    
  }
  
  public int swapGun(){
    return 0;
  }
  
}