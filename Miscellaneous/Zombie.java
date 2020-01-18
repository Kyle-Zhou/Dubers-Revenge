abstract class Zombie extends Mammal {

  private int cooldown, startingHealth;//, movementcooldown;

  Zombie(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    this.startingHealth = health;
    this.cooldown = cooldown;
    //this.movementcooldown = random.nextInt(100) + 20;
  }

  public void move(Human duber, Entity[] entity) {
    int xDir = 0, yDir = 0;//initiate xDirection and yDirection variable for later use

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

    if(getxCentre() == duber.getxCentre()) xDir = 0;
    if(getyCentre() == duber.getyCentre()) yDir = 0;

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
          if ((getxCentre() < zombie.getxCentre())) { //super is left of the specified
            if(xDir > 0) xDir--;//slows down, moving right
            else if (xDir < 0) xDir++;//slows down, moving left
          }
          if ((getyCentre() < zombie.getyCentre())) { //super is above the specified
            if(yDir > 0) yDir--;//slows down, moving up
            else if (yDir < 0) yDir++;//slows down, moving down
          }
        }
      }
    }

    //Set the movement directions
    setxDirection(xDir);
    setyDirection(yDir);

    //Move, zombies 
    getHitbox().setLocation((int) getHitbox().getX() + getxDirection(),
          (int) getHitbox().getY() + getyDirection());
    setxCord(getxCord() + getxDirection());
    setyCord(getyCord() + getyDirection());
    setxCentre(getxCentre() + getxDirection());
    setyCentre(getyCentre() + getyDirection());//*/

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

  public void setStartingHealth(int startingHealth){
    this.startingHealth = startingHealth;
  }

  public void collision(North north, South south) { //if hitbox is changed later on, make edits
    if (getHitbox().intersects(north.getHitbox())) {
      setyDirection(0);
      setyCord(150);
      setyCentre(getyCord() + (geteHeight() / 2));
      getHitbox().setLocation((int)getxCord() + getxDirection(),
            (int)getyCord() + getyDirection());
    } else if (getHitbox().intersects(north.getEastHitbox())) {
      setxDirection(0);
      setxCord(4500 - 64 - geteWidth());
      setxCentre(getxCord() + (geteWidth() / 2));
      getHitbox().setLocation((int)getxCord() + getxDirection(),
            (int)getyCord() + getyDirection());
    } else if (getHitbox().intersects(south.getHitbox())) { // 81
      setyDirection(0);
      setyCord(4367 - geteHeight());
      setyCentre(getyCord() + (geteHeight() / 2));
      getHitbox().setLocation((int)getxCord() + getxDirection(),
            (int)getyCord() + getyDirection());
    } else if (getHitbox().intersects(north.getWestHitbox())) {
      setxDirection(0);
      setxCord(64);
      setxCentre(getxCord() + (geteWidth() / 2));
      getHitbox().setLocation((int)getxCord() + getxDirection(),
            (int)getyCord() + getyDirection());
    }
  }
  
}