import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;

class Zombie extends Mammal {
  private int cooldown;
  private int startingHealth;
  BufferedImage sprite;
  
  Zombie(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprite();
    eWidth = sprite.getWidth();
    eHeight = sprite.getHeight();
    xCentre = xCord + (eWidth / 2);
    yCentre = yCord + (eHeight / 2);
    hitbox = new Rectangle(xCord, yCord, eWidth, eHeight);
    this.startingHealth = health;
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("undead.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite...");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, xCord, yCord, null);
    g.setColor(Color.RED);
    g.drawRect(xCord, yCord, eWidth, eHeight);
    
    int zombieHealth = getHealth();
    int ratio = Toolkit.getDefaultToolkit().getScreenSize().height / 5 / 100;
    g.setColor(Color.red);
    g.fillRect(xCord+7, yCord-10, (startingHealth*ratio) / 2, 7);
    g.setColor(Color.green);
    g.fillRect(xCord+7, yCord-10, (zombieHealth*ratio) / 2, 7);
    
  }
    
  public void move(Human duber) {
    if ((xCentre >= duber.xCentre) && (yCentre >= duber.yCentre)) {
      xDirection = -(getSpeed());
      yDirection = -(getSpeed());
    } else if ((xCentre <= duber.xCentre) && (yCentre >= duber.yCentre)) {
      xDirection = getSpeed();
      yDirection = -(getSpeed());
    } else if ((xCentre <= duber.xCentre) && (yCentre <= duber.yCentre)) {
      xDirection = getSpeed();
      yDirection = getSpeed();
    } else if ((xCentre >= duber.xCentre) && (yCentre <= duber.yCentre)) {
      xDirection = -(getSpeed());
      yDirection = getSpeed();
    }
    hitbox.setLocation((int)hitbox.getX() + this.xDirection, (int)hitbox.getY() + this.yDirection);
    this.xCord += this.xDirection;
    this.yCord += this.yDirection;
    this.xCentre += this.xDirection;
    this.yCentre += this.yDirection;
  }
  
  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }
  
  public int getCooldown() {
    return cooldown;
  }
  
  public void attack(Human duber) {
    cooldown = 100;
    duber.setHealth(duber.getHealth() - damage);
  }
}
