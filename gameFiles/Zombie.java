abstract class Zombie extends Mammal {
  private int cooldown, startingHealth;
  private boolean direction;

  /**
   * Zombie constructor
   * This constructor inherits xCoordinate, yCoordinate, health, damage, and speed from Mammal
   * @param damage, an integer that represents damage zombies do
   * @param health, an integer that represents the max or spawned health
   * @param speed, an integer that holds the speed of zombies
   * @param xCord, an integer that holds the x Coordinate of the zombie
   * @param yCord, an integer that holds the y Coordinate of the zombie
   */
  Zombie(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord, health, damage, speed);
    startingHealth = health;
    cooldown = 0;
  }

  /**
   * move
   * this method gives the zombie a direction of travel towards Duber
   * @param duber, a Human object that represents Duber
   * @param entity, a 1D array that holds data representing all the objects
   */
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

  /**
   * attack
   * An abstract method that allows to set Duber's health and cooldown
   * @param duber, a Human object that represents Duber
   */
  abstract void attack(Human duber);

  /**
   * setCooldown
   * A method that sets the cooldown time after each attack
   * @param cooldown, an integer that sends in the cooldown timer
   */
  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }

  /**
   * getCooldown
   * A method that returns the cooldown value
   * @return an integer that represents cooldown
   */
  public int getCooldown() {
    return cooldown;
  }

  /**
   * getStartingHealth
   * A method that returns the starting health of the zombie
   * @return an integer that represents this zombie's health
   */
  public int getStartingHealth(){
    return startingHealth;
  }

  /**
   * setStartingHealth
   * A method that sets the starting health of the zombie
   * @param startingHealth, an integer value that represents the health value to set to
   */
  public void setStartingHealth(int startingHealth){
    this.startingHealth = startingHealth;
  }

  /**
   * collision
   * A method that checks if the zombie is colliding with the walls
   * @param north, a North object that represents the North, West, and East wall
   * @param south, a South object that represents the South wall
   */
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

  /**
   * collision
   * A method that checks if the zombie is colliding with the obstacles
   * @param obstacle, an Obstacle object that represents one obstacle
   */
  public void collision(Obstacle obstacle) {
    if (getHitbox().intersects(obstacle.getHitbox())) {
      if (getxCord() < obstacle.getxCord()) {
        setxDirection(0);
        setxCord(getxCord() - 1);
        setxCentre(getxCord() + (geteWidth() / 2));
        getHitbox().setLocation((int)getxCord(), (int)getyCord());
      } else {
        setxDirection(0);
        setxCord(getxCord() + 1);
        setxCentre(getxCord() + (geteWidth() / 2));
        getHitbox().setLocation((int)getxCord(), (int)getyCord());
      }

      if (getyCord() < obstacle.getyCord()) {
        setyDirection(0);
        setyCord(getyCord() - 1);
        setyCentre(getyCord() + (geteHeight() / 2));
        getHitbox().setLocation((int)getxCord(), (int)getyCord());
      } else {
        setyDirection(0);
        setyCord(getyCord() + 1);
        setyCentre(getyCord() + (geteHeight() / 2));
        getHitbox().setLocation((int)getxCord(), (int)getyCord());
      }
    }
  }

  /**
   * setDirection
   * A method that sets the boolean value of direction
   * @param direction, a boolean value of direction
   */
  public void setDirection(boolean direction) {
    this.direction = direction;
  }

  /**
   * getDirection
   * A method that gets the boolean value of direction, right or left
   * @return true if direction of travel is right, false if direction of travel is left
   */
  public boolean getDirection() {
    return direction;
  }
}