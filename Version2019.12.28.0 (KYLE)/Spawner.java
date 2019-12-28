class Spawner {
  private static boolean availability;
  private static int position;
  private static Entity[] entities;
  
  Spawner(Entity[] entities) {
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
  
  public static void spawnWalker(int xCord, int yCord, int health, int damage, int speed) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Walker(xCord, yCord, health, damage, speed);
      } else {
        position++;
      }
    }
  }
  
  public static void fireBullet(Human duber) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Bullet(duber.getxCentre(), duber.getyCentre(), 10);
      } else {
        position++;
      }
    }
  }
  
  public static void spawnCash(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Cash(xCord, yCord);
      } else {
        position++;
      }
    }
  }
    
  public static void spawnNorth(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new North(xCord, yCord);
      } else {
        position++;
      }
    }
  }
  
  public static void spawnSouth(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new South(xCord, yCord);
      } else {
        position++;
      }
    }
  }
  
  
  
  public static Entity[] getEntities() {
    return entities;
  }
  
}