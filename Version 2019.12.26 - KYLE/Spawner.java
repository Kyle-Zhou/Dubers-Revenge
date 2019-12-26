import java.awt.event.MouseEvent;

class Spawner {
  private static boolean availability;
  private static int position;
  private static Mammal[] entities;
  private static Projectile[] projectiles;
  private static Obstacle[] obstacles;
  private static Cash[] cash;
  
  Spawner(Mammal[] entities, Projectile[] projectiles, Obstacle[] obstacles, Cash[] cash) {
    this.entities = entities;
    this.projectiles = projectiles;
    this.obstacles = obstacles;
    this.cash = cash;
  }
  
  public static void spawnHuman(int xCord, int yCord, int health, int damage, int speed) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Human(xCord, yCord, health, damage, speed);
      } else {
        position++;
      }
    }
  }
  
  public static void spawnZombie(int xCord, int yCord, int health, int damage, int speed) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Zombie(xCord, yCord, health, damage, speed);
      } else {
        position++;
      }
    }
  }
  
  public static Mammal[] getEntities() {
    return entities;
  }
  
  public static void fireBullet(Human duber) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (projectiles[position] == null) {
        availability = true;
        projectiles[position] = new Bullet(duber.getxCentre(), duber.getyCentre(), 10);
      } else {
        position++;
      }
    }
  }
  
  public static Projectile[] getProjectiles() {
    return projectiles;
  }
  
  public static void spawnNorth (int xCord, int yCord, int eWidth, int eHeight, int adjust) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (obstacles[position] == null) {
        availability = true;
        obstacles[position] = new North(xCord, yCord, eWidth, eHeight, adjust);
      } else {
        position++;
      }
    }
  }
  
  public static Obstacle[] getObstacles() {
    return obstacles;
  }
  
  public static void spawnCash(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        cash[position] = new Cash(xCord, yCord);
      } else {
        position++;
      }
    }
  }
  
  public static Cash[] getCash() {
    return cash;
  }
  
  
}