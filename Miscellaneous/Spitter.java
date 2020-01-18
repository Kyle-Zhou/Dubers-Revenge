import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class Spitter extends Zombie {

  BufferedImage sprite;
  private int cooldown;
  private boolean inRange;
  
  Spitter(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    loadSprite();
    seteWidth(sprite.getWidth());
    seteHeight(sprite.getHeight());
    setxCentre(xCord + (geteWidth() / 2));
    setyCentre(yCord + (geteHeight() / 2));
    setHitbox(new Rectangle(xCord, yCord, geteWidth(), geteHeight()));
    setStartingHealth(health);
    cooldown = getCooldown();
  }
  
  public void loadSprite() {
    try {
      sprite = ImageIO.read(new File("spitter.png"));
    } catch(Exception e) { System.out.println("Error Loading Sprite... walker.png");};
  }
  
  public void draw(Graphics g) {
    g.drawImage(sprite, getxCord(), getyCord(), null);
    g.setColor(Color.RED);
    g.drawRect(getxCord(), getyCord(), geteWidth(), geteHeight());
    
    g.setColor(Color.RED);
    g.drawRect(getxCord()-200, getyCord()-200, 400, 400);
    
    //g.setColor(Color.red);
    //g.fillRect(getxCord()+7, getyCord()-10, (getStartingHealth()) / 2, 7);
    g.setColor(Color.green);
    g.fillRect(getxCord()+7, getyCord()-10, (getHealth()) / 2, 7);
  }
  
  public void attack(Human duber) {
    setCooldown(50);
    duber.setHealth(duber.getHealth() - getDamage());
  }
  
  public void move(Human duber, Entity[] entity) {
    int xDir = 0;
    int yDir = 0;
    //get a direction of travel
    if ((getxCentre() >= duber.getxCentre()) && (getyCentre() >= duber.getyCentre())) {
      xDir = -(getSpeed());
      yDir = -(getSpeed());
    } else if ((getxCentre() <= duber.getxCentre()) && (getyCentre() >= duber.getyCentre())) {
      xDir = getSpeed();
      yDir = -(getSpeed());
    } else if ((getxCentre() <= duber.getxCentre()) && (getyCentre() <= duber.getyCentre())) {
      xDir = getSpeed();
      yDir = getSpeed();
    } else if ((getxCentre() >= duber.getxCentre()) && (getyCentre() <= duber.getyCentre())) {
      xDir = -(getSpeed());
      yDir = getSpeed();
    } 

    if (getxCentre() == duber.getxCentre()+400) {
      xDir = 0;
      inRange = true;
    }
    if (getxCentre() == duber.getxCentre()-400) {
      xDir = 0;
      inRange = true;
    }
    if (getyCentre() == duber.getyCentre()+300) {
      yDir = 0;
      inRange = true;
    }
    if (getyCentre() == duber.getyCentre()-300) {
      yDir = 0;
      inRange = true;
    }    
    
    //path adjusting
    //Adjust the path to Human direction in angles
    double angle = Math.atan2(getyCentre() - duber.getyCentre(),getxCentre() - duber.getxCentre());
    double x = (double)xDir * Math.abs(Math.cos(angle));
    double y = (double)yDir * Math.abs(Math.sin(angle));

    if (x >= 0)
      xDir = (int) Math.ceil(x);
    else
      xDir = (int) Math.floor(x);

    if (y >= 0)
      yDir = (int)Math.ceil(y);
    else
      yDir = (int) Math.floor(y);

    //check if zombies are overlapping each other
    for (int i = 0; i < entity.length; i++) {
      if (entity[i] instanceof Zombie) {
        Zombie zombie = (Zombie) entity[i];
        if (getHitbox().intersects(zombie.getHitbox())) {
          if ((getxCentre() < zombie.getxCentre())) {//super is left of the specified
            if(xDir > 0) xDir--;//slows down, moving right
            else if (xDir < 0) xDir++;//slows down, moving left
          }
          if ((getyCentre() < zombie.getyCentre())) {//super is above the specified
            if(yDir > 0) yDir--;//slows down, moving up
            else if (yDir < 0) yDir++;//slows down, moving down
          }
        }
      }
    }

    //Set the movement directions
    setxDirection(xDir);
    setyDirection(yDir);

    //Move, zombies ;D
    getHitbox().setLocation((int) getHitbox().getX() + getxDirection(),
          (int) getHitbox().getY() + getyDirection());
    setxCord(getxCord() + getxDirection());
    setyCord(getyCord() + getyDirection());
    setxCentre(getxCentre() + getxDirection());
    setyCentre(getyCentre() + getyDirection());//*/

  }
  
  public boolean getInRange(){
    return inRange;
  }
  
}