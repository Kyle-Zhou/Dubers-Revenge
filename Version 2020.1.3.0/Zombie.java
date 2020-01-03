import java.util.Random;

abstract class Zombie extends Mammal {
  
  private int cooldown, startingHealth, movementcooldown;

  Zombie(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    this.startingHealth = health;
    this.cooldown = cooldown;
    Random random = new Random();
    this.movementcooldown = random.nextInt(100) + 20;
  }
    
  public void move(Human duber, Entity[] entity) {
    if (movementcooldown < 0) {
      if ((super.getxCentre() >= duber.getxCentre()) && (super.getyCentre() >= duber.getyCentre())) {
        super.setxDirection(-(getSpeed()));
        super.setyDirection(-(getSpeed()));
      } else if ((super.getxCentre() <= duber.getxCentre()) && (super.getyCentre() >= duber.getyCentre())) {
        super.setxDirection(getSpeed());
        super.setyDirection(-(getSpeed()));
      } else if ((super.getxCentre() <= duber.getxCentre()) && (super.getyCentre() <= duber.getyCentre())) {
        super.setxDirection(getSpeed());
        super.setyDirection(getSpeed());
      } else if ((super.getxCentre() >= duber.getxCentre()) && (super.getyCentre() <= duber.getyCentre())) {
        super.setxDirection(-(getSpeed()));
        super.setyDirection(getSpeed());
      }

      for (int i = 0; i < entity.length; i++) {
        if (entity[i] instanceof Zombie) {
          Zombie zombie = (Zombie) entity[i];
          if (super.getHitbox().intersects(zombie.getHitbox())) {
            if ((super.getxCentre() < zombie.getxCentre())) {
              super.setxDirection(-(getSpeed()));
            }
            if ((super.getyCentre() < zombie.getyCentre())) {
              super.setyDirection(-(getSpeed()));
            }
          }
        }
      }
    } else movementcooldown--;

    super.getHitbox().setLocation((int) super.getHitbox().getX() + super.getxDirection(),
          (int) super.getHitbox().getY() + super.getyDirection());
    super.setxCord(super.getxCord() + super.getxDirection());
    super.setyCord(super.getyCord() + super.getyDirection());
    super.setxCentre(super.getxCentre() + super.getxDirection());
    super.setyCentre(super.getyCentre() + super.getyDirection());

  }
    
  abstract void attack(Human duber);
  
  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }
  
  public int getCooldown() {
    return cooldown;
  }
  
  public int getStartingHealth(){
    return startingHealth;
  }
  
  public void setStartingHealth(int startingHeatlh){
    this.startingHealth = startingHealth;
  }

}