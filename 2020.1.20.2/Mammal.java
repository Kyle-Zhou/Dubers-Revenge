/**[Mammal.java]
 * An abstract Mammal class from Entity that declares all living organisms
 * @author Ethan Zhang, Eric Miao, Kyle Zhou
 */
abstract class Mammal extends Entity {
  private int health, speed;
  private int xDirection, yDirection, damage, xCentre, yCentre;

  /**Mammal constructor
   * The constructor that inherits x coordinate,  y coordinate, health, damage, and speed
   * @param xCord, an integer value that represents the x coordinate
   * @param yCord, an integer value that represents the y coordinate
   * @param health, an integer value that represents the max/spawn health
   * @param damage, an integer value that represents the damage the organism can do
   * @param speed, an integer value that represents the speed the organism can travel
   */
  Mammal(int xCord, int yCord, int health, int damage, int speed) {
    super(xCord, yCord);
    this.xDirection = 0;
    this.yDirection = 0;
    this.health = health;
    this.damage = damage;
    this.speed = speed;
  }

  /**setHealth
   * A method that sets the health
   * @param health, an integer value that holds the data of the max health
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**getHealth
   * A method that gets the current health of the organism
   * @return an integer value of the health
   */
  public int getHealth() {
    return this.health;
  }

  /**setSpeed
   * A method that sets the speed of the organism
   * @param speed, an integer value that holds the speed
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**getSpeed
   * A method that gets the speed of the organism
   * @return an integer value of the speed
   */
  public int getSpeed() {
    return this.speed;
  }

  /**getxDirection
   * A method that gets the horizontal direction of travel
   * @return an integer value, which -1 represents left, 0 stays, 1 towards the right
   */
  public int getxDirection() {
    return xDirection;
  }

  /**getyDirection
   * A method that gets the verticle direction of travel
   * @return an integer value, which -1 represents up, 0 stays, 1 towards the bottom
   */
  public int getyDirection() {
    return yDirection;
  }

  /**getDamage
   * A method that gets the damage deal from the organism
   * @return an integer value that holds the damage one can do
   */
  public int getDamage() {
    return damage;
  }

  /**getxCentre
   * A method that gets the centre x coordinate of the organism
   * @return an integer value that stores the x centre coordinate
   */
  public int getxCentre() {
    return xCentre;
  }

  /**getyCentre
   * A method that gets the centre y coordinate of the organism
   * @return an integer value that stores the y centre coordinate
   */
  public int getyCentre() {
    return yCentre;
  }

  /**setxDirection
   * A method that sets the horizontal direction of travel
   * @param xDirection, an integer value that holds the x direction of travel
   */
  public void setxDirection(int xDirection) {
    this.xDirection = xDirection;
  }

  /**setyDirection
   * A method that sets the vertical direction of travel
   * @param yDirection, an integer alue that holds the y direction of travel
   */
  public void setyDirection(int yDirection) {
    this.yDirection = yDirection;
  }

  /**setDamage
   * A method that sets the damage the organism can deal
   * @param damage, an integer value that holds the data of the damage one can do
   */
  public void setDamage(int damage) {
    this.damage = damage;
  }

  /**setxCentre
   * A method that sets the centre x coordinate of the sprite
   * @param xCentre, an integer value that holds the data of the centre x coordinate
   */
  public void setxCentre(int xCentre) {
    this.xCentre = xCentre;
  }

  /**setyCentre
   * A method that sets the centre y coordinate of the sprite
   * @param yCentre, an integer value that holds the data of the centre y coordinate
   */
  public void setyCentre(int yCentre) {
    this.yCentre = yCentre;
  }
}
