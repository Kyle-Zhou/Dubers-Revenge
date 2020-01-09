import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

public class Keys implements KeyListener{

  private Human duber;
  private Weapon[] weapons;
  private Entity[] entities;
  private Spawner spawner;
  private HeadUpDisplay hud;
  private Graphics g;
  private static boolean left, down, right, up;
  
  Keys(Human duber, Weapon[] weapons, Spawner spawner, HeadUpDisplay hud, Entity entities[]){
    this.duber = duber;
    this.weapons = weapons;
    this.spawner = spawner;
    this.hud = hud;
    this.entities = entities;
    left = false;
    down = false;
    right = false;
    up = false;
  }
  
  public void keyTyped(KeyEvent e) {
  }

  public void keyPressed(KeyEvent e) {
    if ((e.getKeyChar() == 'a')) {  //If 'A' is pressed
      left = true;
      duber.setxDirection(-duber.getSpeed());
    } else if (e.getKeyChar() == 's' ){  //If 'S' is pressed
      down = true;
      duber.setyDirection(duber.getSpeed());
    } else if (e.getKeyChar() == 'd' ){  //If 'D' is pressed
      right = true;
      duber.setxDirection(duber.getSpeed());
    } else if ((e.getKeyChar() == 'w')) { //If 'W' is pressed
      up = true;
      duber.setyDirection(-duber.getSpeed());
    }
    
    if ((e.getKeyChar() == '1')) {
      spawner.setCurrentWeapon(0);
    } else if ((e.getKeyChar() == '2')) {
      spawner.setCurrentWeapon(1);
      //System.out.println(currentWeapon);
    }
    
    if ((e.getKeyChar() == 'e')) { 
      if (entities[spawner.getCurrentBandage()] instanceof Bandage){
      if (entities[spawner.getCurrentBandage()].getHitbox().intersects(duber.getHitbox())){
        hud.setBandageCount(hud.getBandageCount()+1);   
        entities[spawner.getCurrentBandage()] = null;
      }
      }
    }
    
    if ((e.getKeyChar() == 'h')) { 
      if (hud.getBandageCount() > 0){
        hud.setBandageCount(hud.getBandageCount()-1);  
        if (duber.getHealth() + 20 > 100){
          duber.setHealth(100);
        } else {
          duber.setHealth(duber.getHealth() + 20);
        }
      }
    }
    
  }
  
  public void keyReleased(KeyEvent e) {
    if(e.getKeyChar() == 'a' ){    //Good time to use a Switch statement
      duber.setxDirection(0);
      left = false;
    } else if(e.getKeyChar() == 's' ){
      duber.setyDirection(0);
      down = false;
    } else if(e.getKeyChar() == 'd' ){
      duber.setxDirection(0);
      right = false;
    } else if(e.getKeyChar() == 'w' ){
      duber.setyDirection(0);
      up = false;
    }  //note - would be better to make player class and pass in map, test movement in there
  }
  
  public boolean movement() {
    if (left || down || right || up) {
      return true;
    } else {
      return false;
    }
  }
}
