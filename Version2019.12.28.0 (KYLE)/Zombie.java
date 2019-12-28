import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;

abstract class Zombie extends Mammal {
 
  private int cooldown;
  private int startingHealth;

  Zombie(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    this.startingHealth = health;
    this.cooldown = cooldown;
  }
    
  public void move(Human duber) {
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
    super.getHitbox().setLocation((int)super.getHitbox().getX() + super.getxDirection(),
          (int)super.getHitbox().getY() + super.getyDirection());
    super.setxCord(super.getxCord() + super.getxDirection());
    super.setyCord(super.getyCord() + super.getyDirection());
    super.setxCentre(super.getxCentre() + super.getxDirection());
    super.setyCentre(super.getyCentre() + super.getyDirection());
  }
    
  public void attack(Human duber) {
    duber.setHealth(duber.getHealth() - super.getDamage());
  }
  
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