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
  
  public static void spawnNorth(int xCord, int yCord, int eWidth, int eHeight, int adjust) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new North(xCord, yCord, eWidth, eHeight, adjust);
      } else {
        position++;
      }
    }
  }
  
  public static void spawnEast(int xCord, int yCord, int eWidth, int eHeight) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new East(xCord, yCord, eWidth, eHeight);
      } else {
        position++;
      }
    }
  }
  
  public static void spawnSouth(int xCord, int yCord, int eWidth, int eHeight, int adjust) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new South(xCord, yCord, eWidth, eHeight, adjust);
      } else {
        position++;
      }
    }
  }
  
  public static void spawnWest(int xCord, int yCord, int eWidth, int eHeight) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new West(xCord, yCord, eWidth, eHeight);
      } else {
        position++;
      }
    }
  }
  
  public static Entity[] getEntities() {
    return entities;
  }
}