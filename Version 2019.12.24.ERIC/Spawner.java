class Spawner {
  private static boolean availability;
  private static int position;
  private static Mammal[] entities;
  private static Projectile[] projectiles;
  
  Spawner(Mammal[] entities, Projectile[] projectiles) {
    this.entities = entities;
    this.projectiles = projectiles;
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
        projectiles[position] = new Bullet(duber.xCentre, duber.yCentre, 10);
      } else {
        position++;
      }
    }
  }
  
  public static Projectile[] getProjectiles() {
    return projectiles;
  }
}