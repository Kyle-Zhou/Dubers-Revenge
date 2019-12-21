class Spawner {
  private static boolean availability;
  private static int position;
  private static Mammal[] entities;
  //private static Projectile[] bullets = new Projectile[255];
  
  Spawner(Mammal[] entities) {
    this.entities = entities;
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
  
  public static void shootProjectile() {
  }
}
