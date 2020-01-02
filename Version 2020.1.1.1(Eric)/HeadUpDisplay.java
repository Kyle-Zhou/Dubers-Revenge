import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

class HeadUpDisplay {
  private BufferedImage[] sprites;
  private Camera scroll;
  private int RATIO;
  private Rectangle gunHitbox;
  private Spawner spawner;
  private int maxX, maxY, xAdjust, yAdjust, xHalf, yHalf, xLock, yLock, vitality, currency, waveNum, zombieCounter,
    xCorrection, yCorrection;
  
  HeadUpDisplay(Camera scroll, int maxX, int maxY, Spawner spawner) {
    this.scroll = scroll;
    this.maxX = maxX;
    this.maxY = maxY;
    sprites = new BufferedImage[8];
    xHalf = maxX / 2;
    yHalf = maxY / 2;
    loadSprites();
    RATIO = 3;
    vitality = 100;
    currency = 0;
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
      sprites[6] = ImageIO.read(new File("pistol.png"));
      sprites[7] = ImageIO.read(new File("shotgun.png"));
      
    } catch(Exception e) { System.out.println("Error Loading Sprites...");}
  }
  
  public void update(Camera scroll, Human duber) {
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
  
  public void draw(Graphics g, Human duber, Entity[] entities) { //make things less lengthy
    RATIO = 3;
    
    if (vitality > duber.getHealth()) {
      vitality--;
    } else if (vitality < duber.getHealth()) {
      vitality++;
    }
    
    xCorrection = (int)-scroll.getXCamera() + 50 - xLock;
    yCorrection = (int)-scroll.getYCamera() + 30 - yLock;
    g.setColor(Color.RED);
    g.fillRect(xCorrection, yCorrection, vitality * RATIO, 25);
    g.setColor(Color.BLACK);
    g.drawRect(xCorrection, yCorrection, 100 * RATIO, 25);
    
    Font font = new Font("Calibri", Font.PLAIN, 30);  
    g.setColor(Color.white);
    g.setFont(font);
    //Currency
    g.drawString("$" + Integer.toString(currency), (int)-scroll.getXCamera() + 1000 - xLock, (int)-scroll.getYCamera() + 60 -yLock);
    //Wave num
    g.drawString("Wave Number: " + Integer.toString(waveNum), (int)-scroll.getXCamera() + 500 - xLock, (int)-scroll.getYCamera() + 60 - yLock);  
    //Zombies left
    g.drawString("Zombies Remaining: " + Integer.toString(zombieCounter), (int)-scroll.getXCamera() + 1000 - xLock, (int)-scroll.getYCamera() + 120 - yLock);
    
    g.drawImage(sprites[0], (int)-scroll.getXCamera() + (maxX - 350) - xLock, (int)-scroll.getYCamera() + (maxY - 300) - yLock, null);
    g.drawImage(sprites[3], (int)-scroll.getXCamera() + (maxX - 350) - xLock, (int)-scroll.getYCamera() + (maxY - 300) - yLock, null);
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
    
    if (spawner.getCurrentWeapon() == 0){
      g.drawImage(sprites[5], (int)-scroll.getXCamera() + (maxX - 1250) - xLock, (int)-scroll.getYCamera() + (maxY - 250) - yLock, null); 
    } else if (spawner.getCurrentWeapon() == 1){
      g.drawImage(sprites[5], (int)-scroll.getXCamera() + (maxX - 1250) - xLock, (int)-scroll.getYCamera() + (maxY - 200) - yLock, null); 
    }
    
    g.drawImage(sprites[6], (int)-scroll.getXCamera() + (maxX - 1250) - xLock, (int)-scroll.getYCamera() + (maxY - 250) - yLock, null); 
    g.drawImage(sprites[7], (int)-scroll.getXCamera() + (maxX - 1250) - xLock, (int)-scroll.getYCamera() + (maxY - 200) - yLock, null); 
    
  }
  
  
  
  public void setCurrency(int currency) {
    this.currency = currency;
  }
  
  public int getCurrency() {
    return currency;
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
}