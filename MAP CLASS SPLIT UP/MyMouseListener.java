//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MyMouseListener implements MouseListener{

  private Human duber;
  MyMouseListener(Human duber){
    this.duber=duber;
  }  
  
  public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse Clicked");
    System.out.println("X:"+e.getX() + " y:"+e.getY());
  }

  public void mousePressed(MouseEvent e) {
    int mouseX = e.getX();
    int mouseY = e.getY();
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }
} 
