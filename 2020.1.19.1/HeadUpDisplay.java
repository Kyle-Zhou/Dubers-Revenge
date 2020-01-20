import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

class HeadUpDisplay {
  private BufferedImage[] sprites;
  private Camera scroll;
  private final int ratio = 3;
  private Rectangle gunHitbox, shopHitbox;
  private Spawner spawner;
  private Weapon[] weapons;
  private Shop shop;
  private Counter counter;
  private int maxX, maxY, xAdjust, yAdjust, xHalf, yHalf, xLock, yLock, vitality, currency, bandageCount, medKitCount, 
    waveNum, zombieCounter, xCorrection, yCorrection;
  private int eWidth, eHeight;
  
  HeadUpDisplay(Camera scroll, int maxX, int maxY, Spawner spawner, Shop shop, Weapon[] weapons, Counter counter) {
    this.scroll = scroll;
    this.maxX = maxX;
    this.maxY = maxY;
    this.shop = shop;
    this.weapons = weapons;
    this.counter = counter;
    sprites = new BufferedImage[15];
    xHalf = maxX / 2;
    yHalf = maxY / 2;
    loadSprites();
    vitality = 100;
    currency = 9999999;
    waveNum = 0;
    zombieCounter = 0;
  }
  
  private void loadSprites() {
    try {
      sprites[0] = ImageIO.read(new File("minimap.png"));
      sprites[1] = ImageIO.read(new File("player.png"));
      sprites[2] = ImageIO.read(new File("enemy.png"));
      sprites[3] = ImageIO.read(new File("fog.png"));
      sprites[4] = ImageIO.read(new File("vision.png"));
      sprites[5] = ImageIO.read(new File("selectedGun.png"));
      sprites[6] = ImageIO.read(new File("pistolSilhouette.png"));
      sprites[7] = ImageIO.read(new File("shotgunSilhouette.png"));
      sprites[8] = ImageIO.read(new File("shopButton.png"));
      sprites[9] = ImageIO.read(new File("shopButton2.png"));
      sprites[10] = ImageIO.read(new File("iconLock.png"));
      sprites[11] = ImageIO.read(new File("machinegunSilhouette.png"));
      sprites[12] = ImageIO.read(new File("rocketLauncherSilhouette.png"));
      sprites[13] = ImageIO.read(new File("flamethrowerSilhouette.png"));
      sprites[14] = ImageIO.read(new File("Rbutton.png"));
    } catch(Exception e) { System.out.println("Error Loading HUD Sprites...");}
  }
  
  public void update(Camera scroll, Human duber) {
    if (duber.getMoveLock() == false){
      this.scroll = scroll;
      xAdjust = -duber.getxCentre() + xHalf;
      yAdjust = -duber.getyCentre() + yHalf;
      
      if (xAdjust > 0) {
        xLock = 0;
      } else if (xAdjust < -4500 + maxX) {
        xLock = 0;
      } else {
        xLock = duber.getxDirection();
      }
      
      if (yAdjust > 0) {
        yLock = 0;
      } else if (yAdjust < -4500 + maxY) {
        yLock = 0;
      } else {
        yLock = duber.getyDirection();
      }
    }
  }
  
  public void draw(Graphics g, Human duber, Entity[] entities, Shop shop) { //make things less lengthy
    if (vitality > duber.getHealth()) {
      vitality--;
    } else if (vitality < duber.getHealth()) {
      vitality++;
    }
    
    xCorrection = (int)-scroll.getXCamera() + 50 - xLock;
    yCorrection = (int)-scroll.getYCamera() + 30 - yLock;
    g.setColor(Color.RED);
    g.fillRect(xCorrection, yCorrection, vitality * ratio, 25);
    g.setColor(Color.BLACK);
    g.drawRect(xCorrection, yCorrection, 100 * ratio, 25);
        
    Font font = new Font("Calibri", Font.PLAIN, 30);  
    g.setColor(Color.white);
    g.setFont(font);
    g.drawString("$" + Integer.toString(currency), (int)-scroll.getXCamera() + 1000 - xLock, (int)-scroll.getYCamera() + 60 -yLock);
    g.drawString("Wave Number: " + Integer.toString(waveNum), (int)-scroll.getXCamera() + 500 - xLock, (int)-scroll.getYCamera() + 60 - yLock);  
    g.drawString("Zombies Remaining: " + Integer.toString(zombieCounter), (int)-scroll.getXCamera() + 1000 - xLock, (int)-scroll.getYCamera() + 120 - yLock);
    g.drawString(Integer.toString(weapons[spawner.getCurrentWeapon()].getClip()) + "/" + Integer.toString(weapons[spawner.getCurrentWeapon()].getTotalAmmo()), 
                 (int)-scroll.getXCamera() + 470 - xLock, (int)-scroll.getYCamera() + (maxY-120) - yLock);
    
    if (weapons[spawner.getCurrentWeapon()].getReloading() == true) {
      if (counter.getReloadTime()+weapons[spawner.getCurrentWeapon()].getReloadSpeed() >= System.currentTimeMillis()) {
      long intervals = (counter.getReloadTime()+weapons[spawner.getCurrentWeapon()].getReloadSpeed() - System.currentTimeMillis()) / 50;
      g.fillRect((int)-scroll.getXCamera() + 420 - xLock, (int)-scroll.getYCamera() + (maxY-190) - yLock, (int)intervals*5, 25);
      }
    }
    
    if (weapons[spawner.getCurrentWeapon()].getClip() <= 0){
      g.drawImage(sprites[14], duber.getxCentre()-10, duber.getyCord()-10, null);
    }
    if ((counter.getGracePeriodTime() != 0) && ((counter.getGracePeriodTime()+10000 - System.currentTimeMillis()) / 1000 > 0)){
      g.drawString("Grace Period: " + Long.toString((counter.getGracePeriodTime()+10000 - System.currentTimeMillis()) / 1000), 
                   (int)-scroll.getXCamera() + 500 - xLock, (int)-scroll.getYCamera() + (maxY-600) - yLock);
    }
//renovate later
    
    xCorrection = (int)-scroll.getXCamera() + (maxX - 350) - xLock;
    yCorrection = (int)-scroll.getYCamera() + (maxY - 300) - yLock;
    g.drawImage(sprites[0], xCorrection, yCorrection, null);
    g.drawImage(sprites[3], xCorrection, yCorrection, null);
    xCorrection = ((int)-scroll.getXCamera() / 15) + (maxX - 350) - xLock + (int)-scroll.getXCamera();
    yCorrection = ((int)-scroll.getYCamera() / 22) + (maxY - 300) - yLock + (int)-scroll.getYCamera();
    g.drawImage(sprites[4], xCorrection, yCorrection, maxX / 15, maxY / 22, null);
    xCorrection = (duber.getxCord() / 15) + (maxX - 350) - xLock + (int)-scroll.getXCamera();
    yCorrection = (duber.getyCord() / 22) + (maxY - 300) - yLock + (int)-scroll.getYCamera();
    g.drawImage(sprites[1], xCorrection, yCorrection, null);
    
    for (int i = 0; i < entities.length; i++) {
      if (entities[i] instanceof Zombie) {
        xCorrection = (entities[i].getxCord() / 15) + (maxX - 350) - xLock + (int)-scroll.getXCamera();
        yCorrection = (entities[i].getyCord() / 22) + (maxY - 300) - yLock + (int)-scroll.getYCamera();
        g.drawImage(sprites[2], xCorrection, yCorrection, null);
      }
    }    
    
    ///SHOP BUTTON
    eWidth = sprites[8].getWidth();
    eHeight = sprites[8].getHeight();
    g.drawImage(sprites[8], (int)-scroll.getXCamera() + (maxX - 250) - xLock, (int)-scroll.getYCamera() + (maxY - 400) - yLock, null);  //SHOP BUTTON
    shopHitbox = new Rectangle((int)-scroll.getXCamera() + (maxX - 250) - xLock, (int)-scroll.getYCamera() + (maxY - 360) - yLock, eWidth, eHeight);
    
    if (spawner.getCurrentWeapon() == 0){
      xCorrection = (int)-scroll.getXCamera() + 30 - xLock;
      yCorrection = (int)-scroll.getYCamera() + (maxY - 350) - yLock;
      g.drawImage(sprites[5], xCorrection, yCorrection, null); 
    } else if (spawner.getCurrentWeapon() == 1){
      xCorrection = (int)-scroll.getXCamera() + 30 - xLock;
      yCorrection = (int)-scroll.getYCamera() + (maxY - 300) - yLock;
      g.drawImage(sprites[5], xCorrection, yCorrection, null); 
    } else if (spawner.getCurrentWeapon() == 2) {
      xCorrection = (int)-scroll.getXCamera() + 30 - xLock;
      yCorrection = (int)-scroll.getYCamera() + (maxY - 250) - yLock;
      g.drawImage(sprites[5], xCorrection, yCorrection, null);
    } else if (spawner.getCurrentWeapon() == 3) {
      xCorrection = (int)-scroll.getXCamera() + 30 - xLock;
      yCorrection = (int)-scroll.getYCamera() + (maxY - 200) - yLock;
      g.drawImage(sprites[5], xCorrection, yCorrection, null);
    } else if (spawner.getCurrentWeapon() == 4) {
      xCorrection = (int)-scroll.getXCamera() + 30 - xLock;
      yCorrection = (int)-scroll.getYCamera() + (maxY - 150) - yLock;
      g.drawImage(sprites[5], xCorrection, yCorrection, null);
    }
    
    xCorrection = (int)-scroll.getXCamera() + 70 - xLock;
    yCorrection = (int)-scroll.getYCamera() + (maxY - 330) - yLock;
    g.drawImage(sprites[6], xCorrection, yCorrection, null);
    xCorrection = (int)-scroll.getXCamera() + 55 - xLock;
    yCorrection = (int)-scroll.getYCamera() + (maxY - 275) - yLock;
    g.drawImage(sprites[7], xCorrection, yCorrection, null);
    if (shop.getShotgunBought() == false){
      g.drawImage(sprites[10], xCorrection+70, yCorrection-7, null);
    }
    xCorrection = (int)-scroll.getXCamera() + 55 - xLock;
    yCorrection = (int)-scroll.getYCamera() + (maxY - 225) - yLock;
    g.drawImage(sprites[11], xCorrection, yCorrection, null);
    if (shop.getAutomaticBought() == false) {
      g.drawImage(sprites[10], xCorrection + 70, yCorrection - 7, null);
    }
    xCorrection = (int)-scroll.getXCamera() + 55 - xLock;
    yCorrection = (int)-scroll.getYCamera() + (maxY - 180) - yLock;
    g.drawImage(sprites[12], xCorrection, yCorrection, null);
    if (shop.getRocketLauncherBought() == false) {
      g.drawImage(sprites[10], xCorrection + 70, yCorrection - 7, null);
    }
    xCorrection = (int)-scroll.getXCamera() + 55 - xLock;
    yCorrection = (int)-scroll.getYCamera() + (maxY - 130) - yLock;
    g.drawImage(sprites[13], xCorrection, yCorrection, null);
    if (shop.getFlameThrowerBought() == false) {
      g.drawImage(sprites[10], xCorrection + 70, yCorrection - 7, null);
    }
    
    g.drawString("Bandages: " + Integer.toString(bandageCount), (int)-scroll.getXCamera() + 70 - xLock, (int)-scroll.getYCamera() + 120 - yLock);
    g.drawString("Medkits: " + Integer.toString(medKitCount), (int)-scroll.getXCamera() + 70 - xLock, (int)-scroll.getYCamera() + 160 - yLock);
  }
  
  public boolean displayBandagePrompt(Graphics g, Human duber){
    g.drawString("Press 'E' to pick up", duber.getxCentre(), duber.getyCentre() + 30);
    return true;
  }
  
  public void hoverShopButton(Graphics g){
    g.drawImage(sprites[9], (int)-scroll.getXCamera() + (maxX - 250) - xLock, (int)-scroll.getYCamera() + (maxY - 400) - yLock, null);  //SHOP BUTTON      
  }
  
  public void setCurrency(int currency) {
    this.currency = currency;
  }
  
  public int getCurrency() {
    return currency;
  }
  
  public void setBandageCount(int bandageCount) {
    this.bandageCount = bandageCount;
  }
  
  public int getBandageCount() {
    return bandageCount;
  }
  
  public void setMedKitCount(int medKitCount) {
    this.medKitCount = medKitCount;
  }
  
  public int getMedKitCount() {
    return medKitCount;
  }
  
  
  public void setWaveNum(int waveNum){
    this.waveNum = waveNum;
  }
  
  public int getWaveNum() {
    return waveNum;
  }
  
  public void setZombieCounter(int zombieCounter){
    this.zombieCounter = zombieCounter;
  }
  
  public int getZombieCounter() {
    return zombieCounter;
  }
  
  public Rectangle getShopHitbox(){
    return shopHitbox;
  }
}