import javax.swing.JPanel; 
import java.awt.Toolkit; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Image; 
import java.util.Random; 

class GamePanel extends JPanel { 
  
  private static int maxX, maxY; 
  private Human duber; 
  private North north; 
  private South south; 
  private Spawner spawner; 
  private Camera scroll; 
  private HeadUpDisplay hud; 
  private Shop shop; 
  private Entity[] entities; 
  private Weapon[] weapons; 
  private Mouse mouse; 
  private Keys keys; 
  private boolean zombiesAlive, gracePeriod, bumped;
  private Random random; 
  private Image floor; 
  private Graphics2D g2d;
  private Counter counter;
  
  GamePanel(Human duber, Spawner spawner, Camera scroll, HeadUpDisplay hud, Entity[] entities, Weapon[] weapons, 
            Shop shop, Counter counter) { 
    this.duber = duber; 
    this.spawner = spawner; 
    this.scroll = scroll; 
    this.hud = hud; 
    this.shop = shop; 
    this.entities = entities; 
    this.weapons = weapons; 
    this.counter = counter;
    north = (North)entities[1]; 
    south = (South)entities[2]; 
    
    mouse = new Mouse(duber, spawner, scroll, hud, shop, weapons); 
    keys = new Keys(duber, weapons, spawner, hud, entities, shop, counter); 
    
    random = new Random(); 
    
    floor = Toolkit.getDefaultToolkit().getImage("map.png");
  } 
  
  public void paintComponent(Graphics g) { 
    super.paintComponent(g);
    setDoubleBuffered(true); 
    g2d = (Graphics2D)g; 
    
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize()); 
    this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width; 
    this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height; 
    
    g2d.translate(scroll.getXCamera(), scroll.getYCamera()); 
    g.drawImage(floor, 0, 0, this); 
    north.draw(g); 
    zombieWave(g); 
    counterReloading();
    hud.setZombieCounter(0); 
    
    if (duber != null) { 
    for (int i = 0; i < entities.length; i++) { 
      if (entities[i] instanceof Zombie) { 
        hud.setZombieCounter(hud.getZombieCounter()+1); 
        if (((Zombie)entities[i]).getxCentre() > duber.getxCentre()) {
          ((Zombie)entities[i]).setDirection(false);
        } else {
          ((Zombie)entities[i]).setDirection(true);
        }
        ((Zombie)entities[i]).draw(g); 
        if (((Zombie)entities[i]).getCooldown() > 0) { 
          ((Zombie)entities[i]).setCooldown(((Zombie)entities[i]).getCooldown() - 1); 
        } 
      } else if (entities[i] instanceof Projectile) { 
        entities[i].draw(g); 
      } else if (entities[i] instanceof Obstacle) {
        entities[i].draw(g);
      }
    } 
    
      if (mouse.trackX() > duber.getxCentre()) { 
        if ((keys.movement()) && !((duber.getSprite() >= 16) && (duber.getSprite() < 24))) { 
          duber.setSprite(16); 
        } else if (!(keys.movement()) && !((duber.getSprite() >= 0) && (duber.getSprite() < 8))) { 
          duber.setSprite(0); 
        } 
      } else { 
        if ((keys.movement()) && !((duber.getSprite() >= 24) && (duber.getSprite() < 32))) { 
          duber.setSprite(24); 
        } else if (!(keys.movement()) && !((duber.getSprite() >= 8) && (duber.getSprite() < 16))){ 
          duber.setSprite(8); 
        } 
      } 
      duber.update(); 
      duber.draw(g); 
      duber.move(); 
      duber.collision(north, south);
      for (int i = 0; i < entities.length; i++) {
        if (entities[i] instanceof Obstacle) {
          duber.collision((Obstacle)entities[i]);
        }
      }
      
      if ((spawner.getCurrentWeapon() == 2) && (mouse.getHolding() == true) && (duber.getFrame() % 6 == 0)) {
        spawner.fireBullet(duber);
      } else if ((spawner.getCurrentWeapon() == 4) && (mouse.getHolding() == true) && (duber.getFrame() % 6 == 0)) {
        spawner.fireFlames(duber);
      }
      
      g2d.rotate((double)Math.atan2(mouse.trackY() - duber.getyCentre(), mouse.trackX() - duber.getxCentre()), 
                 duber.getxCentre(), duber.getyCentre());
      if (spawner.getCurrentWeapon() == 0) {
        if (mouse.trackX() > duber.getxCentre()) {
          weapons[0].setDirection(true);
        } else {
          weapons[0].setDirection(false);
        }
        weapons[0].draw(g);
      } else if (spawner.getCurrentWeapon() == 1) {
        if (mouse.trackX() > duber.getxCentre()) {
          weapons[1].setDirection(true);
        } else {
          weapons[1].setDirection(false);
        }
        weapons[1].draw(g);
      } else if (spawner.getCurrentWeapon() == 2) {
        if (mouse.trackX() > duber.getxCentre()) {
          weapons[2].setDirection(true);
        } else {
          weapons[2].setDirection(false);
        }
        weapons[2].draw(g);
      } else if (spawner.getCurrentWeapon() == 3) {
        if (mouse.trackX() > duber.getxCentre()) {
          weapons[3].setDirection(true);
        } else {
          weapons[3].setDirection(false);
        }
        weapons[3].draw(g);
      } else {
        if (mouse.trackX() > duber.getxCentre()) {
          weapons[4].setDirection(true);
        } else {
          weapons[4].setDirection(false);
        }
        weapons[4].draw(g);
      }
      g2d.rotate(-((double)Math.atan2(mouse.trackY() - duber.getyCentre(), mouse.trackX() - duber.getxCentre())), 
                 duber.getxCentre(), duber.getyCentre());
      
      for (int i = 0; i < weapons.length; i++) {
        if (weapons[i] != null) {
          weapons[i].move(duber);
        }
      }
      
      for (int i = 0; i < entities.length; i++) { 
        if (entities[i] instanceof Zombie) { 
          ((Zombie)entities[i]).collision(north, south); 
          if (!(entities[i] instanceof Spitter)) {
            if (entities[i].getHitbox().intersects(duber.getHitbox())) { 
              ((Zombie)entities[i]).setxDirection(0); 
              ((Zombie)entities[i]).setyDirection(0); 
              if (((Zombie)entities[i]).getCooldown() == 0) { 
                ((Zombie)entities[i]).attack(duber); 
              }
            } else { 
              ((Zombie)entities[i]).move(duber, entities); 
              for (int j = 0; j < entities.length; j++) {
                if (entities[j] instanceof Obstacle) {
                  ((Zombie)entities[i]).collision((Obstacle)entities[j]);
                }
              }
            }
          } else {
            if (((Spitter)entities[i]).getRange().intersects(duber.getHitbox())) {
              ((Zombie)entities[i]).setxDirection(0); 
              ((Zombie)entities[i]).setyDirection(0); 
              if (((Zombie)entities[i]).getCooldown() == 0) { 
                spawner.fireSpit((Spitter)entities[i]);
                ((Zombie)entities[i]).setCooldown(100);
              }
            } else {
              ((Zombie)entities[i]).move(duber, entities); 
              ((Spitter)entities[i]).adjustRange();
              for (int j = 0; j < entities.length; j++) {
                if (entities[j] instanceof Obstacle) {
                  ((Zombie)entities[i]).collision((Obstacle)entities[j]);
                }
              }
            }
          }
        }
        if (entities[i] instanceof Projectile) { 
          if (!(entities[i] instanceof Spit)) {
            ((Projectile)entities[i]).travel(duber, mouse.trackX(), mouse.trackY()); 
            for (int j = 0; j < entities.length; j++) { 
              if ((entities[j] instanceof Zombie) && (entities[i].getHitbox().intersects(entities[j].getHitbox()))) { 
                ((Zombie)entities[j]).setHealth(((Zombie)entities[j]).getHealth() - 
                                                weapons[spawner.getCurrentWeapon()].getDamage()); 
                if (entities[i] instanceof Rocket) {
                  spawner.explode(entities[i].getxCord() - 235, entities[i].getyCord() - 235);
                  entities[i] = null;
                } else if (!(entities[i] instanceof Flame)) {
                  entities[i] = null;
                }
                j += entities.length; 
              } 
            } 
          } else {
            ((Projectile)entities[i]).travel(duber, mouse.trackX(), mouse.trackY()); 
            if (entities[i].getHitbox().intersects(duber.getHitbox())) {
              duber.setHealth(duber.getHealth() - 15);
              entities[i] = null;
            }
          }
          
          if (entities[i] instanceof Shell){ 
            if ((((Shell)entities[i]).getxCord() > duber.getxCentre() + 400) || 
                (((Shell)entities[i]).getxCord() < duber.getxCentre() - 400) || 
                (((Shell)entities[i]).getyCord() > duber.getyCentre() + 400) || 
                (((Shell)entities[i]).getyCord() < duber.getyCentre() - 400)) { 
              entities[i] = null; 
            } 
          } else if (entities[i] instanceof Flame) {
            if ((((Flame)entities[i]).getxCord() > duber.getxCentre() + 400) || 
                (((Flame)entities[i]).getxCord() < duber.getxCentre() - 400) || 
                (((Flame)entities[i]).getyCord() > duber.getyCentre() + 400) || 
                (((Flame)entities[i]).getyCord() < duber.getyCentre() - 400)) { 
              entities[i] = null; 
            }
          } 
          
          if ((entities[i] != null) && ((entities[i].getHitbox().intersects(north.getHitbox()) || 
                                         (entities[i].getHitbox().intersects(north.getEastHitbox())) || 
                                         (entities[i].getHitbox().intersects(south.getHitbox())) || 
                                         (entities[i].getHitbox().intersects(north.getWestHitbox()))))) { 
            if (entities[i] instanceof Rocket) {
              spawner.explode(entities[i].getxCord() - 235, entities[i].getyCord() - 235);
            }
            entities[i] = null; 
          } 
        } 
        
        if (entities[i] instanceof Detonation) {
          entities[i].draw(g);
          ((Detonation)entities[i]).update();
          for (int j = 0; j < entities.length; j++) { 
            if ((entities[j] instanceof Zombie) && (entities[i].getHitbox().intersects(entities[j].getHitbox()))) { 
              ((Zombie)entities[j]).setHealth(((Zombie)entities[j]).getHealth() - 3); 
            }
          }
          if (entities[i].getHitbox().intersects(duber.getHitbox())) {
            duber.setHealth(duber.getHealth() - 3);
          }
          if (((Detonation)entities[i]).getSprite() > (((Detonation)entities[i]).getFrames() - 1)) {
            entities[i] = null;
          }
        }
        
        if (entities[i] instanceof Cash){ 
          (entities[i]).draw(g); 
          if (entities[i].getHitbox().intersects(duber.getHitbox())){ 
            if (hud.getCurrency() >= 1000){
              hud.setCurrency(1000);
            } else {
              hud.setCurrency(hud.getCurrency() + 1); 
              entities[i] = null;             
            }
          } 
        }  
        
        if (entities[i] instanceof Bandage){ 
          (entities[i]).draw(g); 
          if (entities[i].getHitbox().intersects(duber.getHitbox())){ 
            hud.displayBandagePrompt(g, duber); 
            spawner.setCurrentBandage(i); 
          } 
        } 
        
        if (entities[i] instanceof MedKit){
          (entities[i]).draw(g); 
          if (entities[i].getHitbox().intersects(duber.getHitbox())){ 
            hud.displayBandagePrompt(g, duber); 
            spawner.setCurrentMedKit(i); 
          }
        }
      } 
      
      south.draw(g); 
      
      hud.draw(g, duber, entities, shop); 
      
      if (hud.getShopHitbox().contains(mouse.trackX(), mouse.trackY())){
        hud.hoverShopButton(g);
      }
      if (shop.getAddedShop()){ 
        shop.update(g);   
        duber.setMoveLock(true); 
        weapons[0].setShootingLock(true);
      } 
      g2d.translate(-scroll.getXCamera(), -scroll.getYCamera()); 
      
      if (duber.getHealth() <= 0) {
        duber = null;
        entities[0] = null;
        Main.running = false;
      }
      
      for (int i = 0; i < entities.length; i++) {
        if ((entities[i] instanceof Zombie) && (((Zombie)entities[i]).getHealth() <= 0)) { 
          int randInt = random.nextInt(2)+1; 
          if (randInt == 1){ 
            spawner.spawnCash(((Zombie)entities[i]).getxCentre(), ((Zombie)entities[i]).getyCentre()); 
          } else if (randInt == 2){ 
            spawner.spawnBandage(((Zombie)entities[i]).getxCentre(), ((Zombie)entities[i]).getyCentre()); 
          }
          if (entities[i] instanceof Bloated) {
            spawner.implode(((Zombie)entities[i]).getxCentre() - 250, ((Zombie)entities[i]).getyCentre() - 250);
          }
          entities[i] = null;
        }
      }
    }
    
  } 
  
  public void zombieWave(Graphics g){ 
    zombiesAlive = false; 
    
    if (hud.getWaveNum() == 0) {
      int num = random.nextInt(10)+1; 
      nextWave(num);
    }
    
    for (int i = 0; i < entities.length; i++){ 
      if (entities[i] instanceof Zombie) { 
        zombiesAlive = true; 
      } 
    } 
    
    if (zombiesAlive == false) { 
      if (gracePeriod == false){
        counter.setGracePeriodTime(System.currentTimeMillis());
        System.out.println(counter.getGracePeriodTime());
        gracePeriod = true;
      }
      long time = 10000;
      if (counter.getGracePeriodTime() != 0){
        if (counter.getGracePeriodTime()+10000 <= System.currentTimeMillis()) {
          System.out.println("works");
          gracePeriod = false;
          int num = random.nextInt(hud.getWaveNum()*8+1)+10; 
          nextWave(num); 
        }
      }
    }
  }
  
  public void nextWave(int number) { 
    hud.setWaveNum(hud.getWaveNum()+1); 
    for (int i = 0; i < number; i++) { 
      int xRange = random.nextInt(4372)+64; 
      int yRange = random.nextInt(4302)+100; 
      int randInt = random.nextInt(2)+1; 
      
      if (((xRange < (int)-scroll.getXCamera()) && (yRange > (int)-scroll.getYCamera())) || 
          ((xRange < (int)-scroll.getXCamera()) && (yRange < (int)-scroll.getYCamera())) || 
          ((xRange > (int)-scroll.getXCamera() + maxX) && (yRange > (int)-scroll.getYCamera())) || 
          ((xRange > (int)-scroll.getXCamera() + maxX) && (yRange < (int)-scroll.getYCamera())) || 
          (yRange > (int)-scroll.getYCamera()+maxY)) { 
        int randomZombie = random.nextInt(4); 
        if (randomZombie == 0){
          spawner.spawnWalker(xRange, yRange, 100, 10, 1); 
        } else if (randomZombie == 1){
          spawner.spawnRunner(xRange, yRange, 70, 5, 3); 
        } else if (randomZombie == 2){
          spawner.spawnSpitter(xRange, yRange, 100, 10, 1); 
        } else {
          spawner.spawnBloated(xRange, yRange, 200, 20, 1);
        }
      } else { 
        i--; 
      } 
    } 
  }
  
  public void counterReloading() {
    if (weapons[spawner.getCurrentWeapon()].getReloading() == true){
      if (System.currentTimeMillis() >= weapons[spawner.getCurrentWeapon()].getReloadSpeed()+counter.getReloadTime()) {
        weapons[spawner.getCurrentWeapon()].reload();
      }
    }
  }
  
}
