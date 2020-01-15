class Spawner {
  private static boolean availability;
  private static int position, currentWeapon, currentBandage;
  private static Entity[] entities;
  private static Weapon[] weapons;
  private static Shop shop;
  
  Spawner(Entity[] entities, Weapon[] weapons, Shop shop) {
    this.entities = entities;
    this.weapons = weapons;
    this.shop = shop;
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
  
  public static void spawnRunner(int xCord, int yCord, int health, int damage, int speed) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Runner(xCord, yCord, health, damage, speed);
      } else {
        position++;
      }
    }
  }
  
  public static void fireBullet(Human duber) {
    if ((weapons[currentWeapon].getShootingLock() == false) && (weapons[currentWeapon].getClip() > 0)){
      weapons[currentWeapon].setClip(weapons[currentWeapon].getClip()-1);
      position = 0;
      availability = false;
      while (availability == false) {
        if (entities[position] == null) {
          availability = true;
          entities[position] = new Bullet(duber.getxCentre(), duber.getyCentre(), 15);
        } else {
          position++;
        }
      }
    }
  }
  
  public static void fireShell(Human duber) {
    if ((weapons[currentWeapon].getShootingLock() == false) && (weapons[currentWeapon].getClip() > 0)){
      weapons[currentWeapon].setClip(weapons[currentWeapon].getClip()-1);
      position = 0;
      availability = false;
      while (availability == false) {
        if ((entities[position] == null) && (entities[position + 1] == null) && (entities[position + 2] == null) &&
            (entities[position + 3] == null) && (entities[position + 4] == null)) {
          availability = true;
          entities[position] = new Shell(duber.getxCentre(), duber.getyCentre(), 15);
          entities[position + 1] = new Shell(duber.getxCentre(), duber.getyCentre(), 15);
          entities[position + 2] = new Shell(duber.getxCentre(), duber.getyCentre(), 15);
          entities[position + 3] = new Shell(duber.getxCentre(), duber.getyCentre(), 15);
          entities[position + 4] = new Shell(duber.getxCentre(), duber.getyCentre(), 15);
        } else {
          position++;
        }
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
    
  public static void spawnBandage(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Bandage(xCord, yCord);
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
  
  public static void addPistol(Human duber) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (weapons[position] == null) {
        availability = true;
        weapons[position] = new Pistol(duber.getxCentre(), duber.getyCentre() - 10, 15, 30, 15);
      } else {
        position++;
      }
    }
  }
    
  public static void addShotgun(Human duber) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (weapons[position] == null) {
        availability = true;
        weapons[position] = new Shotgun(duber.getxCentre(), duber.getyCentre() - 9, 20, 15, 15);
      } else {
        position++;
      }
    }
  }
  
  public static void spawnCanister(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Canister(xCord, yCord);
      } else {
        position++;
      }
    }
  }
    
  public void setCurrentBandage(int currentBandage){
    this.currentBandage = currentBandage;
  }
  
  public static int getCurrentBandage(){
    return currentBandage;
  }
  
  public static int getCurrentWeapon(){
    return currentWeapon;
  }
  
  public void setCurrentWeapon(int currentWeapon){
    this.currentWeapon = currentWeapon;
  }
  
  
  public static Entity[] getEntities() {
    return entities;
  }
  
}
