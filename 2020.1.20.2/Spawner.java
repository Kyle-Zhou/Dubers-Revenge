/**
* [Spawner.java]
* 
* adds all objects to the game
* 
* contains:
* spawnHuman, spawnWalker, spawnRunner, spawnSpitter, spawnBloated, 
* fireSpit, fireBullet, fireShell, fireFlames, fireRocket,
* spawnCash, spawnBandage, spawnMedKit, spawnCanister
* spawnNorth, spawnSouth, 
* explode, implode,
* addPistol, addShotgun, addMachineGun, addRocketLauncher, addFlamethrower 
* 
* methods
* 
* @author Ethan Zhang, Kyle Zhou, Eric Miao
* Ethan Zhang - wrote the spawning/firing/explode and implode methods
* Kyle Zhou - Added the addWeapon methods and added specifics to firing methods
* Eric Miao - General Edits, fixed values for realism
**/

class Spawner {
  
  private static boolean availability;
  private static int position, currentWeapon, currentBandage, currentMedKit;
  private static Entity[] entities;
  private static Weapon[] weapons;
  private static Shop shop;
  
  /**
  * Spawner constructor 
  * inherits entities[], weapons[], shop
  */
  Spawner(Entity[] entities, Weapon[] weapons, Shop shop) {
    this.entities = entities;
    this.weapons = weapons;
    this.shop = shop;
    this.currentWeapon = 0;
  }
  
  /**
  * spawnHuman 
  * checks for availability before adding a human to the lowest value possible in the entities array
  * @param xCord, int value for xCoordinate
  * @param yCord, int value for yCoordinate
  * @param health, int value for health
  * @param damage, int value for damage
  * @param speed, int value for speed
  */
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
  
  public static void spawnSpitter(int xCord, int yCord, int health, int damage, int speed) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Spitter(xCord, yCord, health, damage, speed);
      } else {
        position++;
      }
    }
  }
  
  public static void spawnBloated(int xCord, int yCord, int health, int damage, int speed) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Bloated(xCord, yCord, health, damage, speed);
      } else {
        position++;
      }
    }
  }
  
  public static void fireSpit(Spitter spitter){
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Spit(spitter.getxCentre(), spitter.getyCentre(), 5);
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
  
  public static void fireRocket(Human duber) {
    if ((weapons[currentWeapon].getShootingLock() == false) && (weapons[currentWeapon].getClip() > 0)){
      weapons[currentWeapon].setClip(weapons[currentWeapon].getClip()-1);
      position = 0;
      availability = false;
      while (availability == false) {
        if (entities[position] == null) {
          availability = true;
          entities[position] = new Rocket(duber.getxCentre(), duber.getyCentre(), 20);
        } else {
          position++;
        }
      }
    }
  }
  
  public static void fireFlames(Human duber) {
    if ((weapons[currentWeapon].getShootingLock() == false) && (weapons[currentWeapon].getClip() > 0)) {
      weapons[currentWeapon].setClip(weapons[currentWeapon].getClip() - 1);
      position = 0;
      availability = false;
      while (availability == false) {
        if ((entities[position] == null) && (entities[position + 1] == null) && (entities[position + 2] == null) &&
            (entities[position + 3] == null) && (entities[position + 4] == null)) {
          availability = true;
          entities[position] = new Flame(duber.getxCentre(), duber.getyCentre(), 10);
          entities[position + 1] = new Flame(duber.getxCentre(), duber.getyCentre(), 10);
          entities[position + 2] = new Flame(duber.getxCentre(), duber.getyCentre(), 10);
          entities[position + 3] = new Flame(duber.getxCentre(), duber.getyCentre(), 10);
          entities[position + 4] = new Flame(duber.getxCentre(), duber.getyCentre(), 10);
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
  
  public static void spawnMedKit(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new MedKit(xCord, yCord);
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
  
  public static void explode(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Explosion(xCord, yCord);
      } else {
        position++;
      }
    }
  }
  
  public static void implode(int xCord, int yCord) {
    position = 0;
    availability = false;
    while (availability == false) {
      if (entities[position] == null) {
        availability = true;
        entities[position] = new Implosion(xCord, yCord);
      } else {
        position++;
      }
    }
  }
  
  public static void addPistol(Human duber) {
    weapons[0] = new Pistol(duber.getxCentre(), duber.getyCentre() - 10, 15, 30, 15, 2000);
  }
  
  public static void addShotgun(Human duber) {
    weapons[1] = new Shotgun(duber.getxCentre(), duber.getyCentre() - 9, 20, 20, 10, 3000);
  }
  
  public static void addMachinegun(Human duber) {
    weapons[2] = new Machinegun(duber.getxCentre(), duber.getyCentre() - 11, 15, 200, 50, 4000);
  }
  
  public static void addRocketLauncher(Human duber) {
    weapons[3] = new RocketLauncher(duber.getxCentre() - 40, duber.getyCentre() - 15, 70, 9, 1, 5000);
  }
  
  public static void addFlamethrower(Human duber) {
    weapons[4] = new Flamethrower(duber.getxCentre(), duber.getyCentre() - 8, 10, 9, 1, 1500);
  }
  
  public void setCurrentBandage(int currentBandage){
    this.currentBandage = currentBandage;
  }
  
  public static int getCurrentBandage(){
    return currentBandage;
  }
    
  public void setCurrentMedKit(int currentMedKit){
    this.currentMedKit = currentMedKit;
  }
  
  public static int getCurrentMedKit(){
    return currentMedKit;
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
