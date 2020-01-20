import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.MouseInfo;

class Shop {
  
  private int maxX, maxY;
  private int eWidth, eHeight, buttonEWidth, buttonEHeight, buyingButtonEWidth, buyingButtonEHeight, 
              lockEWidth, lockEHeight;
  private int currentShop = 1;
  
  private Camera scroll;
  private Human duber;
  private Spawner spawner;
  
  private HeadUpDisplay hud;
  
  private BufferedImage[] sprites;
  private Rectangle selfButtonHitbox, weaponButtonHitbox, fiftyButtonHitbox, lockHitbox, fiftyButtonHitbox2, 
                    lockHitbox2, fiftyButtonHitbox3, lockHitbox3, fiftyButtonHitbox4, lockHitbox4;
  
  private boolean addedShop;
  
  private boolean shotgunBought, automaticBought, rocketLauncherBought, flameThrowerBought;
    
  Shop(int maxX, int maxY, Camera scroll, HeadUpDisplay hud){

    this.maxX = maxX;
    this.maxY = maxY;
    this.scroll = scroll;
    this.hud = hud;
    sprites = new BufferedImage[9];
    selfButtonHitbox = new Rectangle(0,0,0,0);
    weaponButtonHitbox = new Rectangle(0,0,0,0);
    fiftyButtonHitbox = new Rectangle(0,0,0,0);
    lockHitbox = new Rectangle(0,0,0,0);
    fiftyButtonHitbox2 = new Rectangle(0,0,0,0);
    lockHitbox2 = new Rectangle(0,0,0,0);
    fiftyButtonHitbox3 = new Rectangle(0,0,0,0);
    lockHitbox3 = new Rectangle(0,0,0,0);
    fiftyButtonHitbox4 = new Rectangle(0,0,0,0);
    lockHitbox4 = new Rectangle(0,0,0,0);
    loadSprites();
  }
  
  private void loadSprites() {
    try {
      sprites[0] = ImageIO.read(new File("weaponShop.png"));
      sprites[1] = ImageIO.read(new File("ITEMbutton.png"));
      sprites[2] = ImageIO.read(new File("WEAPONSbuttonSelected.png"));
      sprites[3] = ImageIO.read(new File("itemShop.png"));
      sprites[4] = ImageIO.read(new File("WeaponButton.png"));
      sprites[5] = ImageIO.read(new File("ITEMbuttonSelected.png"));
      sprites[6] = ImageIO.read(new File("fiftyButton.png"));
      sprites[7] = ImageIO.read(new File("iconLock2.png"));
      sprites[8] = ImageIO.read(new File("exitButton.png"));
    } catch(Exception e) { System.out.println("Error Loading Shop Sprites...");}
  }
  
  public void update(Graphics g) {
    eWidth = sprites[0].getWidth();
    eHeight = sprites[0].getHeight();   
    buttonEWidth = sprites[1].getWidth();
    buttonEHeight = sprites[1].getWidth();
    buyingButtonEWidth = sprites[6].getWidth();
    buyingButtonEHeight = sprites[6].getHeight();
    lockEWidth = sprites[7].getWidth();
    lockEHeight = sprites[7].getHeight();
    
    int sideButtonX = (int)-scroll.getXCamera() + maxX/2 - eWidth/2 + 15;
    int selfButtonY = (int)-scroll.getYCamera() + maxY/2 - eHeight/2 + 110;
    int weaponButtonY = (int)-scroll.getYCamera() + maxY/2 - eHeight/2 + 160;
        
    selfButtonHitbox = new Rectangle(sideButtonX, selfButtonY-10, buttonEWidth, buttonEHeight-30);
    weaponButtonHitbox = new Rectangle(sideButtonX, weaponButtonY+10, buttonEWidth, buttonEHeight);
    
    if (currentShop == 1){
      
      fiftyButtonHitbox = new Rectangle(sideButtonX+365, weaponButtonY+195, buyingButtonEWidth+80, buyingButtonEHeight+30);
      lockHitbox = new Rectangle(sideButtonX+300, weaponButtonY+30, lockEWidth+60, lockEHeight+30);
      fiftyButtonHitbox2 = new Rectangle(sideButtonX+810, weaponButtonY+195, buyingButtonEWidth+10, buyingButtonEHeight+30);
      lockHitbox2 = new Rectangle(sideButtonX+720, weaponButtonY+30, lockEWidth+50, lockEHeight+30);
      fiftyButtonHitbox3 = new Rectangle(sideButtonX+810, weaponButtonY+495, buyingButtonEWidth+20, buyingButtonEHeight+30);
      lockHitbox3 = new Rectangle(sideButtonX+710, weaponButtonY+325, lockEWidth+60, lockEHeight+30);
      fiftyButtonHitbox4 = new Rectangle(sideButtonX+405, weaponButtonY+495, buyingButtonEWidth+30, buyingButtonEHeight+30);
      lockHitbox4 = new Rectangle(sideButtonX+310, weaponButtonY+325, lockEWidth+60, lockEHeight+30);
      
      //weapon shop
      g.drawImage(sprites[0], (int)-scroll.getXCamera() + maxX/2 - eWidth/2 - 10, (int)-scroll.getYCamera() + maxY/2 - eHeight/2 - 20, null);
      //self button
      g.drawImage(sprites[1], sideButtonX, selfButtonY, null);
      //weapon button SELECTED
      g.drawImage(sprites[2], sideButtonX, weaponButtonY, null);
      //Exit button
      g.drawImage(sprites[8], sideButtonX+800, weaponButtonY-80, null);    
      
      if (shotgunBought == false){

        if (fiftyButtonHitbox.contains(trackX(), trackY())){
          g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+170, 62, 39, null);
        } else {
          g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+170, null);
        }
        if (lockHitbox.contains(trackX(), trackY())){
          g.drawImage(sprites[7], sideButtonX+350, weaponButtonY, 30, 41, null);
        } else {
          g.drawImage(sprites[7], sideButtonX+350, weaponButtonY, null);
        }
      }
      
      if (automaticBought == false){
        
        if (fiftyButtonHitbox2.contains(trackX(), trackY())){
          g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+170, 62, 39, null);
        } else {
          g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+170, null);
        }
        if (lockHitbox2.contains(trackX(), trackY())){
          g.drawImage(sprites[7], sideButtonX+730, weaponButtonY, 30, 41, null);
        } else {
          g.drawImage(sprites[7], sideButtonX+730, weaponButtonY, null);
        }
      }
      
      if (flameThrowerBought == false){
       
        if (fiftyButtonHitbox3.contains(trackX(), trackY())){
          g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+485, 62, 39, null);
        } else {
          g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+485, null);
        }
        if (lockHitbox3.contains(trackX(), trackY())){
          g.drawImage(sprites[7], sideButtonX+730, weaponButtonY+305, 30, 41, null);
        } else {
          g.drawImage(sprites[7], sideButtonX+730, weaponButtonY+305, null);
        }
      }
      
     if (rocketLauncherBought == false){

        if (fiftyButtonHitbox4.contains(trackX(), trackY())){
          g.drawImage(sprites[6], sideButtonX+430, weaponButtonY+485, 62, 39, null);
        } else {
          g.drawImage(sprites[6], sideButtonX+430, weaponButtonY+485, null);
        }
        if (lockHitbox4.contains(trackX(), trackY())){
          g.drawImage(sprites[7], sideButtonX+350, weaponButtonY+305, 30, 41, null);
        } else {
          g.drawImage(sprites[7], sideButtonX+350, weaponButtonY+305, null);
        }
      }
      
      if (selfButtonHitbox.contains(trackX(), trackY())){
        g.drawImage(sprites[5], sideButtonX, selfButtonY, null);
        g.drawImage(sprites[4], sideButtonX, weaponButtonY, null);
      }
      
    } else if (currentShop == 2){ 
      
      fiftyButtonHitbox = new Rectangle(sideButtonX+365, weaponButtonY+195, buyingButtonEWidth+80, buyingButtonEHeight+30);
      fiftyButtonHitbox2 = new Rectangle(sideButtonX+810, weaponButtonY+195, buyingButtonEWidth+10, buyingButtonEHeight+30);
      fiftyButtonHitbox3 = new Rectangle(sideButtonX+810, weaponButtonY+495, buyingButtonEWidth+20, buyingButtonEHeight+30);
      fiftyButtonHitbox4 = new Rectangle(sideButtonX+405, weaponButtonY+495, buyingButtonEWidth+30, buyingButtonEHeight+30);

      g.drawImage(sprites[3], (int)-scroll.getXCamera() + maxX/2 - eWidth/2 - 10, (int)-scroll.getYCamera() + maxY/2 - eHeight/2 - 20, null);
      //self button selected
      g.drawImage(sprites[5], sideButtonX, selfButtonY, null);
      //weapom button
      g.drawImage(sprites[4], sideButtonX, weaponButtonY, null);
      
      
      if (fiftyButtonHitbox.contains(trackX(), trackY())){
        g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+170, 62, 39, null);
      } else {
        g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+170, null);
      }
      if (fiftyButtonHitbox2.contains(trackX(), trackY())){
        g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+170, 62, 39, null);
      } else {
        g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+170, null);
      }
      if (fiftyButtonHitbox3.contains(trackX(), trackY())){
        g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+485, 62, 39, null);
      } else {
        g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+485, null);
      }     
      if (fiftyButtonHitbox4.contains(trackX(), trackY())){
        g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+485, 62, 39, null);
      } else {
        g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+485, null);
      }      
      
      if (weaponButtonHitbox.contains(trackX(), trackY())){
        g.drawImage(sprites[2], sideButtonX, selfButtonY, null);
        g.drawImage(sprites[1], sideButtonX, weaponButtonY, null);
      }
    } 

    
  }
  
  
  public int trackX() {
    return (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)scroll.getXCamera();
  }
  
  public int trackY() {
    return (int)MouseInfo.getPointerInfo().getLocation().getY() - (int)scroll.getYCamera();
  }
  
  public int geteWidth(){
    return eWidth;
  }
  
  public int geteHeight(){
    return eHeight;
  }
    
  public boolean getAddedShop(){
    return addedShop;
  }
  
  public void setAddedShop(boolean addedShop){
    this.addedShop = addedShop; 
  }
  
  public int getCurrentShop(){
    return currentShop;
  }
  
  public void setCurrentShop(int currentShop){
    this.currentShop = currentShop;
  }
  
  public Rectangle getSelfButtonHitbox() {
    return selfButtonHitbox;
  }

  public Rectangle getWeaponButtonHitbox() {
    return weaponButtonHitbox;
  }
  
  public Rectangle getFiftyButtonHitbox() {
    return fiftyButtonHitbox;
  }
  
  public Rectangle getFiftyButtonHitbox2() {
    return fiftyButtonHitbox2;
  }
  
  public Rectangle getFiftyButtonHitbox3() {
    return fiftyButtonHitbox3;
  }
  
  public Rectangle getFiftyButtonHitbox4() {
    return fiftyButtonHitbox4;
  }
  
  public void setShotgunBought(boolean shotgunBought){
    this.shotgunBought = shotgunBought;
  }
  
  public boolean getShotgunBought(){
    return shotgunBought;
  }
  
  public void setAutomaticBought(boolean automaticBought){
    this.automaticBought = automaticBought;
  }

  public boolean getAutomaticBought(){
    return automaticBought;
  }
  
  public void setRocketLauncherBought(boolean rocketLauncherBought){
    this.rocketLauncherBought = rocketLauncherBought;
  }
  
  public boolean getRocketLauncherBought(){
    return rocketLauncherBought;
  }
  
  public void setFlameThrowerBought(boolean flameThrowerBought) {
    this.flameThrowerBought = flameThrowerBought;
  }
  
  public boolean getFlameThrowerBought() {
    return flameThrowerBought;
  }
}