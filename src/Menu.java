import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

/**
 * [Menu.java]
 * The Main Menu page of the project. Consists of Play, Instructions/Tutorial, and Quit
 * @author Eric Miao
 Jan 20, 2020
 **/
class Menu extends JFrame{ //Frame for Menu Screen

  JFrame frame;

  /**Menu constructor
   * The constructor for Menu, consists Play, Instructions, and Quit button with a beautiful background
   */
  public Menu(){
    frame = this;
    setBack(); //Get image (set background)

    Container c = getContentPane(); //Get JFrame Pane

    //Start Button
    JPanel startPanel = new JPanel(); //Create JPanel
    startPanel.setPreferredSize(new Dimension(340, 54)); //set Size
    startPanel.setOpaque(false); //Set to transparent background
    ImageIcon sb = new ImageIcon("start.png"); //Load Image
    JButton startButton = new JButton(sb); //create JButton
    startButton.setBorder(BorderFactory.createEmptyBorder()); //No border
    startButton.setBackground(new Color(0, 0, 0, 0)); //transparent button
    startButton.setRolloverIcon(new ImageIcon("start2.png")); //Image when cursor is on the button
    startButton.addActionListener(new StartButtonListener()); //Button Listener
    startPanel.add(startButton);

    //Instructions Button
    JPanel insPanel = new JPanel(); //Create JPanel
    insPanel.setPreferredSize(new Dimension(340, 54)); //set Size
    insPanel.setOpaque(false); //Set to transparent background
    ImageIcon ib = new ImageIcon("inst.png"); //Load Image
    JButton insButton = new JButton(ib); //create JButton
    insButton.setBorder(BorderFactory.createEmptyBorder()); //No border
    insButton.setBackground(new Color(0, 0, 0, 0)); //transparent button
    insButton.setRolloverIcon(new ImageIcon("inst2.png")); //Image when cursor is on the button
    insButton.addActionListener(new InstButtonListener()); //Button Listener
    insPanel.add(insButton);

    //Quit Button
    JPanel qPanel = new JPanel(); //Create JPanel
    qPanel.setPreferredSize(new Dimension(300, 54)); //set Size
    qPanel.setOpaque(false); //Set to transparent background
    ImageIcon qb = new ImageIcon("quit.png"); //Load Image
    JButton qButton = new JButton(qb); //create JButton
    qButton.setBorder(BorderFactory.createEmptyBorder()); //No border
    qButton.setBackground(new Color(0, 0, 0, 0)); //transparent button
    qButton.setRolloverIcon(new ImageIcon("quit2.png")); //Image when cursor is on the button
    qButton.addActionListener(new QButtonListener()); //Button Listener
    qPanel.add(qButton);

    //blank panel to support button positioning
    JPanel blank = new JPanel();
    blank.setOpaque(false);
    blank.setPreferredSize(new Dimension(20, 200));
    c.add(blank, BorderLayout.NORTH);
    c.add(startPanel, BorderLayout.WEST);
    c.add(insPanel, BorderLayout.CENTER);
    c.add(qPanel, BorderLayout.EAST);
    setSize(1024, 768);
    setVisible(true);
  }

  /**refresh
   * A method that repaints the current JFrame
   */
  public void refresh(){
    this.repaint();
  }

  /**setBack
   * A method that sets the background of the Panel, used in class constructor
   */
  public void setBack(){
    ((JPanel) this.getContentPane()).setOpaque(false);
    ImageIcon img = new ImageIcon("menu.png");
    JLabel background = new JLabel(img);
    this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
    background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
  }

  /**StartButtonListener inner Class
   * Necessary inner class for the Start Button Action Listener
   */
  class StartButtonListener implements ActionListener{ // necessary START button Listener
    public void actionPerformed(ActionEvent event){
      Main.menu = false;
      Main.end = false;
      Main.running = true;
      dispose();
      System.out.println("Starting new Game");
    }
  }

  /**InstButtonListener inner Class
   * Necessary inner class for the Instructions Button Action Listener
   */
  class InstButtonListener implements ActionListener{ // necessary Instructions button Listener
    public void actionPerformed(ActionEvent event){
      System.out.println("Displays Instructions");
      JLabel jlabel = new JLabel(); // create JLabel for the image
      ImageIcon img = new ImageIcon("instructions.png");
      img.setImage(img.getImage().getScaledInstance(550, 430, Image.SCALE_DEFAULT)); //resize the image
      jlabel.setOpaque(true);
      jlabel.setIcon(img);
      jlabel.setBounds(240, 280, 550, 430); //set location
      jlabel.setSize(550,430);

      frame.add(jlabel); //add the JLabel onto the Frame
    }
  }

  /**QButtonListener inner Class
   * Necessary inner class for the Quit Button Action Listener
   */
  class QButtonListener implements ActionListener{ // necessary QUIT button Listener
    public void actionPerformed(ActionEvent event){
      System.exit(0); //exits program
    }
  }
}