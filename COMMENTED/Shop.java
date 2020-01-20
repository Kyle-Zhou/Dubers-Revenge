import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.MouseInfo;

/**
* [Shop.java]
* contains loadSprite, update, trackX and trackY methods
* Displays graphical interface of shop which adjusts accordingly based on 
* interactions and which items are purchased.
* @author Kyle Zhou
**/

class Shop {
  
  private Camera scroll;
  private Human duber;
  private Spawner spawner;
  private HeadUpDisplay hud;
  private BufferedImage[] sprites;
  private Rectangle itemButtonHitbox, weaponButtonHitbox;
  private Rectangle buyingButtonHitbox, buyingButtonHitbox2, buyingButtonHitbox3, buyingButtonHitbox4;
  private Rectangle lockHitbox, lockHitbox2, lockHitbox3, lockHitbox4;
  private Rectangle exitButtonHitbox;
  private int maxX, maxY;
  private int shopEWidth, shopEHeight, buttonshopEWidth, buttonshopEHeight, buyingButtonshopEWidth, buyingButtonshopEHeight, 
              lockshopEWidth, lockshopEHeight; 
  private int currentShop = 1;
  private boolean addedShop;
  private boolean shotgunBought, automaticBought, rocketLauncherBought, flameThrowerBought;
    
  /**
  * Shop constructor 
  * inherits maxX, maxY, scroll (Camera), hud (HeadUpDisplay)
  * intializes hitboxes and sprites
  */
  Shop(int maxX, int maxY, Camera scroll, HeadUpDisplay hud){

    this.maxX = maxX;
    this.maxY = maxY;
    this.scroll = scroll;
    this.hud = hud;
    sprites = new BufferedImage[9];
    itemButtonHitbox = new Rectangle(0,0,0,0);
    weaponButtonHitbox = new Rectangle(0,0,0,0);
    buyingButtonHitbox = new Rectangle(0,0,0,0);
    lockHitbox = new Rectangle(0,0,0,0);
    buyingButtonHitbox2 = new Rectangle(0,0,0,0);
    lockHitbox2 = new Rectangle(0,0,0,0);
    buyingButtonHitbox3 = new Rectangle(0,0,0,0);
    lockHitbox3 = new Rectangle(0,0,0,0);
    buyingButtonHitbox4 = new Rectangle(0,0,0,0);
    lockHitbox4 = new Rectangle(0,0,0,0);
    exitButtonHitbox = new Rectangle(0,0,0,0);
    loadSprites();
  }
  
  /**
  * loadSprite 
  * attempts to open the necessary image(s)
  * catches error if not possible
  */
  private void loadSprites() {
    try {
      sprites[0] = ImageIO.read(new File("weaponShop.png"));
      sprites[1] = ImageIO.read(new File("ITEMbutton.png"));
      sprites[2] = ImageIO.read(new File("WEAPONSbuttonSelected.png"));
      sprites[3] = ImageIO.read(new File("itemShop.png"));
      sprites[4] = ImageIO.read(new File("WeaponButton.png"));
      sprites[5] = ImageIO.read(new File("ITEMbuttonSelected.png"));
      sprites[6] = ImageIO.read(new File("buyingButton1.png"));
      sprites[7] = ImageIO.read(new File("iconLock2.png"));
      sprites[8] = ImageIO.read(new File("exitButton.png"));
    } catch(Exception e) { System.out.println("Error Loading Shop Sprites...");}
  }
  
  /**
  * update 
  * determines which version of shop to display based on given conditions
  * determines what to display based on which items have been purchased
  * displays buying and lock buttons
  * checks interactions with buttons/locks
  * @param g, Graphics
  */
  public void update(Graphics g) {
    
    //intializing width and height of the shop
    shopEWidth = sprites[0].getWidth();
    shopEHeight = sprites[0].getHeight();   
    
    //intializing width and height of the side buttons
    buttonshopEWidth = sprites[1].getWidth();
    buttonshopEHeight = sprites[1].getWidth();
    
    //intializing width and height of the buying buttons
    buyingButtonshopEWidth = sprites[6].getWidth();
    buyingButtonshopEHeight = sprites[6].getHeight();
    
    //intializing width and height of the locks
    lockshopEWidth = sprites[7].getWidth();
    lockshopEHeight = sprites[7].getHeight();
    
    //positions
    int sideButtonX = (int)-scroll.getXCamera() + maxX/2 - shopEWidth/2 + 15;
    int selfButtonY = (int)-scroll.getYCamera() + maxY/2 - shopEHeight/2 + 110;
    int weaponButtonY = (int)-scroll.getYCamera() + maxY/2 - shopEHeight/2 + 160;
        
    //common hitboxes amongst both shops
    itemButtonHitbox = new Rectangle(sideButtonX, selfButtonY-10, buttonshopEWidth, buttonshopEHeight-30);
    weaponButtonHitbox = new Rectangle(sideButtonX, weaponButtonY+10, buttonshopEWidth, buttonshopEHeight);
    exitButtonHitbox = new Rectangle(sideButtonX+890, weaponButtonY-80, 50, 50);
    
    if (currentShop == 1){ //if weapons shop is selected
      
      buyingButtonHitbox = new Rectangle(sideButtonX+365, weaponButtonY+195, 
                                         buyingButtonshopEWidth+80, buyingButtonshopEHeight+30);
      buyingButtonHitbox2 = new Rectangle(sideButtonX+810, weaponButtonY+195, 
                                          buyingButtonshopEWidth+10, buyingButtonshopEHeight+30);
      buyingButtonHitbox3 = new Rectangle(sideButtonX+810, weaponButtonY+495, 
                                          buyingButtonshopEWidth+20, buyingButtonshopEHeight+30);
      buyingButtonHitbox4 = new Rectangle(sideButtonX+405, weaponButtonY+495, 
                                          buyingButtonshopEWidth+30, buyingButtonshopEHeight+30);
      
      lockHitbox = new Rectangle(sideButtonX+300, weaponButtonY+30, lockshopEWidth+60, lockshopEHeight+30);
      lockHitbox2 = new Rectangle(sideButtonX+720, weaponButtonY+30, lockshopEWidth+50, lockshopEHeight+30);
      lockHitbox3 = new Rectangle(sideButtonX+710, weaponButtonY+325, lockshopEWidth+60, lockshopEHeight+30);
      lockHitbox4 = new Rectangle(sideButtonX+310, weaponButtonY+325, lockshopEWidth+60, lockshopEHeight+30);
      
      //weapon shop 
      g.drawImage(sprites[0], (int)-scroll.getXCamera() + maxX/2 - shopEWidth/2 - 10, 
                  (int)-scroll.getYCamera() + maxY/2 - shopEHeight/2 - 20, null);
      //self button 
      g.drawImage(sprites[1], sideButtonX, selfButtonY, null);
      //weapon button SELECTED
      g.drawImage(sprites[2], sideButtonX, weaponButtonY, null);
      
      if (shotgunBought == false){  //if shotgun has not been purchased yet
        if (buyingButtonHitbox.contains(trackX(), trackY())){  //if mouse hovers over the buyingButton hitbox
          g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+170, 62, 39, null);  //draw expanded image
        } else {
          g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+170, null);
        }
        if (lockHitbox.contains(trackX(), trackY())){  //if mouse hovers over the lock
          g.drawImage(sprites[7], sideButtonX+350, weaponButtonY, 30, 41, null); //draw expanded image
        } else {
          g.drawImage(sprites[7], sideButtonX+350, weaponButtonY, null);
        }
      }
      
      if (automaticBought == false){  //Repeated process
        if (buyingButtonHitbox2.contains(trackX(), trackY())){
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
      
      if (flameThrowerBought == false){ //Repeated process
        if (buyingButtonHitbox3.contains(trackX(), trackY())){
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
      
     if (rocketLauncherBought == false){ //Repeated process
        if (buyingButtonHitbox4.contains(trackX(), trackY())){
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
      
      if (itemButtonHitbox.contains(trackX(), trackY())){  //if mouse hovers over items button
        g.drawImage(sprites[5], sideButtonX, selfButtonY, null); //draw the selected items button img
        g.drawImage(sprites[4], sideButtonX, weaponButtonY, null); //draw the regular weapons button img
      }
      
    } else if (currentShop == 2) { //if items shop is selected 
      
      buyingButtonHitbox = new Rectangle(sideButtonX+365, weaponButtonY+195, 
                                         buyingButtonshopEWidth+80, buyingButtonshopEHeight+30);
      buyingButtonHitbox2 = new Rectangle(sideButtonX+810, weaponButtonY+195, 
                                          buyingButtonshopEWidth+10, buyingButtonshopEHeight+30);
      buyingButtonHitbox4 = new Rectangle(sideButtonX+405, weaponButtonY+495, 
                                          buyingButtonshopEWidth+30, buyingButtonshopEHeight+30);

      //items shop
      g.drawImage(sprites[3], (int)-scroll.getXCamera() + maxX/2 - shopEWidth/2 - 10, 
                 (int)-scroll.getYCamera() + maxY/2 - shopEHeight/2 - 20, null);
      //self button selected
      g.drawImage(sprites[5], sideButtonX, selfButtonY, null);
      //weapom button
      g.drawImage(sprites[4], sideButtonX, weaponButtonY, null);
      
      if (buyingButtonHitbox.contains(trackX(), trackY())){ //if mouse hovers over buying button
        g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+170, 62, 39, null); //draw expanded img
      } else {
        g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+170, null); // if mouse is anywhere else draw regular img
      }
      
      if (buyingButtonHitbox2.contains(trackX(), trackY())){  // repeat process
        g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+170, 62, 39, null);
      } else {
        g.drawImage(sprites[6], sideButtonX+830, weaponButtonY+170, null);
      }
      
      if (buyingButtonHitbox4.contains(trackX(), trackY())){ // repeat process
        g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+485, 62, 39, null);
      } else {
        g.drawImage(sprites[6], sideButtonX+435, weaponButtonY+485, null);
      }      
      
      if (weaponButtonHitbox.contains(trackX(), trackY())){ //if mouse hovers over weapons button
        g.drawImage(sprites[2], sideButtonX, selfButtonY, null);  //draw the selected weapons button
        g.drawImage(sprites[1], sideButtonX, weaponButtonY, null); //draw the regular items button
      }
      
    } 
    
    if (exitButtonHitbox.contains(trackX(), trackY())){  //if mouse hovers over exit button
      g.drawImage(sprites[8], sideButtonX+900, weaponButtonY-110, 60, 30, null); // draw expanded exit button
    } else {
      g.drawImage(sprites[8], sideButtonX+910, weaponButtonY-100, null); //draw regular exit button
    }
    
  }
  
  /**
  * trackX 
  * @return int value of current cursor X position adjusted to the camera
  */    
  public int trackX() {
    return (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)scroll.getXCamera();
  }
  
  /**
  * trackY
  * @return int value of current cursor Y position adjusted to the camera
  */    
  public int trackY() {
    return (int)MouseInfo.getPointerInfo().getLocation().getY() - (int)scroll.getYCamera();
  }
  
  /**
  * getShopEWidth 
  * @return int value of shopEWidth
  */    
  public int getshopEWidth(){
    return shopEWidth;
  }
  
  /**
  * getShopEHeight 
  * @return int value of shopEHeight
  */    
  public int getshopEHeight(){
    return shopEHeight;
  }
    
  /**
  * getAddedShop 
  * @return boolean value of addedShop
  */    
  public boolean getAddedShop(){
    return addedShop;
  }
  
  /**
  * setAddedShop 
  * @param addedShop, boolean value of addedShop
  */    
  public void setAddedShop(boolean addedShop){
    this.addedShop = addedShop; 
  }
  
  /**
  * getCurrentShop 
  * @return int value of currentShop
  */    
  public int getCurrentShop(){
    return currentShop;
  }
  
  /**
  * setCurrentShop 
  * @param currentShop, int value of currentShop
  */    
  public void setCurrentShop(int currentShop){
    this.currentShop = currentShop;
  }
  
  /**
  * getItemButtonHitbox 
  * @return Rectangle value of itemButtonHitbox
  */      
  public Rectangle getitemButtonHitbox() {
    return itemButtonHitbox;
  }

  /**
  * getWeaponButtonHitbox 
  * @return Rectangle value of weaponButtonHitbox
  */      
  public Rectangle getWeaponButtonHitbox() {
    return weaponButtonHitbox;
  }
  
  /**
  * getbuyingButtonHitbox 
  * @return Rectangle value of buyingButtonHitbox
  */      
  public Rectangle getbuyingButtonHitbox() {
    return buyingButtonHitbox;
  }
  
  /**
  * getbuyingButtonHitbox2
  * @return Rectangle value of buyingButtonHitbox2
  */      
  public Rectangle getbuyingButtonHitbox2() {
    return buyingButtonHitbox2;
  }
  
  /**
  * getbuyingButtonHitbox3
  * @return Rectangle value of buyingButtonHitbox3
  */      
  public Rectangle getbuyingButtonHitbox3() {
    return buyingButtonHitbox3;
  }
  
  /**
  * getbuyingButtonHitbox4
  * @return Rectangle value of buyingButtonHitbox4
  */      
  public Rectangle getbuyingButtonHitbox4() {
    return buyingButtonHitbox4;
  }
  
  /**
  * getExitButtonHitbox 
  * @return Rectangle value of exitButtonHitbox
  */      
  public Rectangle getExitButtonHitbox(){
    return exitButtonHitbox;
  }
    
  /**
  *  setShotgunBought
  * @param shotgunBought, boolean value of shotgunBought
  */    
  public void setShotgunBought(boolean shotgunBought){
    this.shotgunBought = shotgunBought;
  }
  
  /**
  * getShotgunBought 
  * @return boolean value of shotgunBought
  */     
  public boolean getShotgunBought(){
    return shotgunBought;
  }

  /**
  *  setAutomaticBought
  * @param automaticBought, boolean value of automaticBought
  */    
  public void setAutomaticBought(boolean automaticBought){
    this.automaticBought = automaticBought;
  }

  /**
  * getAutomaticBought 
  * @return boolean value of automaticBought
  */     
  public boolean getAutomaticBought(){
    return automaticBought;
  }
  
  /**
  *  setRocketLauncherBought
  * @param rocketLauncherBought, boolean value of rocketLauncherBought
  */   
  public void setRocketLauncherBought(boolean rocketLauncherBought){
    this.rocketLauncherBought = rocketLauncherBought;
  }
  
  /**
  * getRocketLauncherBought 
  * @return boolean value of rocketLauncherBought
  */     
  public boolean getRocketLauncherBought(){
    return rocketLauncherBought;
  }
  
  /**
  *  setFlameThrowerBought
  * @param flameThrowerBought, boolean value of flameThrowerBought
  */   
  public void setFlameThrowerBought(boolean flameThrowerBought) {
    this.flameThrowerBought = flameThrowerBought;
  }
  
  /**
  * getFlameThrowerBought 
  * @return boolean value of flameThrowerBought
  */     
  public boolean getFlameThrowerBought() {
    return flameThrowerBought;
  }
  
}