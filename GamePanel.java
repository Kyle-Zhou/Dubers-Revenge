import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

class GamePanel extends JPanel {

  private static int maxX, maxY, GridToScreenRatio;
  private Human duber;
  private North north;
  private South south;
  private Spawner spawner;
  private Camera scroll;
  private HeadUpDisplay hud;
  private Entity[] entities;
  private Weapon[] weapons;
  private Mouse listener;
  private boolean zombiesAlive;
  private Random random = new Random();
  private Image[] tiles;

  Map map = new Map();

  GamePanel(Human duber, Spawner spawner, Camera scroll, HeadUpDisplay hud, Entity[] entities, Weapon[] weapons) {
    this.duber = duber;
    this.spawner = spawner;
    this.scroll = scroll;
    this.hud = hud;
    this.entities = entities;
    this.weapons = weapons;

    north = (North)entities[1];
    south = (South)entities[2];

    tiles = new Image[3];
    tiles[0] = Toolkit.getDefaultToolkit().getImage("tile.png");
    tiles[1] = Toolkit.getDefaultToolkit().getImage("crackedTile.png");
    tiles[2] = Toolkit.getDefaultToolkit().getImage("brokenTile.png");

    listener = new Mouse(duber, spawner, scroll);
    addKeyListener(new Keys(duber, weapons, spawner, hud, entities));
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g); //required
    setDoubleBuffered(true);
    Graphics2D g2d = (Graphics2D)g;

    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = (maxY) / (map.getMap().length + 1);

    g2d.translate(scroll.getXCamera(), scroll.getYCamera());
    for(int i = 0; i < 125; i++) {
      for(int j = 0; j < 125; j++) {
        if(map.getMap()[i][j] == 1) {
          g.drawImage(tiles[0], j * 36, i * 36, 36, 36, this);
        } else if (map.getMap()[i][j] == 2){
          g.drawImage(tiles[1], j * 36, i * 36, 36, 36, this);
        } else if (map.getMap()[i][j] == 3) {
          g.drawImage(tiles[2], j * 36, i * 36, 36, 36, this);
        }
      }
    }

    north.draw(g);
    zombieWave();
    hud.setZombieCounter(0);

    for (int i = 0; i < entities.length; i++) {
      if (entities[i] instanceof Zombie) {
        hud.setZombieCounter(hud.getZombieCounter()+1);
        ((Zombie)entities[i]).draw(g);
        if (((Zombie)entities[i]).getCooldown() > 0) {
          ((Zombie)entities[i]).setCooldown(((Zombie)entities[i]).getCooldown() - 1);
        }
      } else if (entities[i] instanceof Projectile) {
        entities[i].draw(g);
      }
    }

    if (duber != null) {
      duber.draw(g);
      duber.move();
      duber.collision(north, south);

      for (int i = 0; i < entities.length; i++) {
        if (entities[i] instanceof Zombie) {

          if (entities[i].getHitbox().intersects(duber.getHitbox())) {
            ((Zombie)entities[i]).setxDirection(0);
            ((Zombie)entities[i]).setyDirection(0);
            if (((Zombie)entities[i]).getCooldown() == 0) {
              ((Zombie)entities[i]).attack(duber);
            }
          } else {
            ((Zombie)entities[i]).move(duber, entities);
          }
        }
        if (entities[i] instanceof Projectile) {
          ((Projectile)entities[i]).travel(duber, listener.getXCursor(), listener.getYCursor());

          for (int j = 0; j < entities.length; j++) {
            if ((entities[j] instanceof Zombie) && (entities[i].getHitbox().intersects(entities[j].getHitbox()))) {
              ((Zombie)entities[j]).setHealth(((Zombie)entities[j]).getHealth() - weapons[spawner.getCurrentWeapon()].getDamage());
              entities[i] = null;
              if (((Zombie)entities[j]).getHealth() <= 0) {

                int randInt = random.nextInt(2)+1;
                if (randInt == 1){
                  spawner.spawnCash(((Zombie)entities[j]).getxCentre(), ((Zombie)entities[j]).getyCentre());
                } else if (randInt == 2){
                  spawner.spawnBandage(((Zombie)entities[j]).getxCentre(), ((Zombie)entities[j]).getyCentre());
                }

                entities[j] = null;
              }
              j += entities.length;
            }
          }

          if (entities[i] instanceof Shell){
            if ((((Shell)entities[i]).getxCord() > duber.getxCentre() + 400) ||
                  (((Shell)entities[i]).getxCord() < duber.getxCentre() - 400) ||
                  (((Shell)entities[i]).getyCord() > duber.getyCentre() + 400) ||
                  (((Shell)entities[i]).getyCord() > duber.getyCentre() + 400)) {
              entities[i] = null;
            }
          }

          if ((entities[i] != null) && ((entities[i].getHitbox().intersects(north.getHitbox()) ||
                (entities[i].getHitbox().intersects(north.getEastHitbox())) ||
                (entities[i].getHitbox().intersects(south.getHitbox())) ||
                (entities[i].getHitbox().intersects(north.getWestHitbox()))))) {
            entities[i] = null;
          }
        }

        if (entities[i] instanceof Cash){
          (entities[i]).draw(g);
          if (entities[i].getHitbox().intersects(duber.getHitbox())){
            hud.setCurrency(hud.getCurrency() + 1);
            entities[i] = null;
          }
        }

        if (entities[i] instanceof Bandage){
          (entities[i]).draw(g);
          if (entities[i].getHitbox().intersects(duber.getHitbox())){
            hud.displayBandagePrompt(g, duber);
            spawner.setCurrentBandage(i);
          }
        }

      }

      south.draw(g);

      hud.draw(g, duber, entities);
      g2d.translate(-scroll.getXCamera(), -scroll.getYCamera());

      if (duber.getHealth() <= 0) {
        duber = null;
        entities[0] = null;
      }

    }

  }

  public void zombieWave(){
    zombiesAlive = false;

    for (int i = 0; i < entities.length; i++){
      if (entities[i] instanceof Zombie) {
        zombiesAlive = true;
      }
    }

    if (zombiesAlive == false){
      int num;
      if(hud.getWaveNum() == 0) num = random.nextInt(10)+1;
      else num = random.nextInt(hud.getWaveNum()*8+1)+10;
      nextWave(num);
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

        if (hud.getWaveNum() < 2){
          spawner.spawnWalker(xRange, yRange, 100, 10, randInt);
        } else if (hud.getWaveNum() < 6) {
          int randomZombie = random.nextInt(2)+1;
          if (randomZombie == 1){
            spawner.spawnRunner(xRange, yRange, 70, 5, 3);
          } else if (randomZombie == 2){
            spawner.spawnWalker(xRange, yRange, 100, 10, 1);
          }
        } else {
          spawner.spawnWalker(xRange, yRange, 100, 10, 1);
        }
      } else {
        i--;
      }
    }
  }
}
