import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;


public class Menu extends JFrame{
  private Image pic;
  Menu() {
    super("Duber\'s Revenge - Menu");

    //configure the window
    this.setSize(1024,768);
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable (false);

    //this.setUndecorated(true);
    //setBackground(new Color(0,0,0));

    //Create a Panel for stuff
    //JPanel decPanel = new DecoratedPanel();
    //decPanel.setBorder(new EmptyBorder(768-240*2, 68, 68, 68));
    try{
      pic = ImageIO.read(new File("a1.png"));
    }catch(Exception e) {}

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(95, 82, 111));
    mainPanel.setPreferredSize(new Dimension(1024,768));


    //Create a JButton for the centerPanel
    ImageIcon sb =new ImageIcon("start.png");


    JButton startButton = new JButton(sb);
    startButton.setBackground(new Color(0, 0, 0, 0));
    startButton.setRolloverIcon(new ImageIcon("start2.png"));
    startButton.setBorder(BorderFactory.createEmptyBorder());
    startButton.setMaximumSize(new Dimension(10, 10));
    startButton.setFocusPainted(false);
    startButton.addActionListener(new StartButtonListener());

    JPanel bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(0, 0, 0, 0));
    bottomPanel.setPreferredSize(new Dimension(340, 54));
    bottomPanel.add(startButton);

    //Create a JButton for the centerPanel
    //JLabel startLabel = new JLabel("<HTML><H1><font color='white'>Welome to Duber's Revenge, press Start to play!</H1></HTML>");

    JPanel background = new DecoratedPanel();

    //Add all panels to the mainPanel according to border layout
    mainPanel.add(background);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    //mainPanel.add(startLabel, BorderLayout.CENTER);

    //decPanel.add(mainPanel);
    //add the main panel to the frame
    this.add(mainPanel);//decPanel);

    //Start the app
    this.setVisible(true);
  }

  public void refresh() {
    this.repaint();
  }

  //INNER CLASS - Overide Paint Component for JPANEL
  private class DecoratedPanel extends JPanel {

    DecoratedPanel() {
      //Image pic = Toolkit.getDefaultToolkit().createImage("a1.png");

      //this.setBackground(new Color(0,0,0,0));
      //System.out.println("1");
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      //System.out.println("Hello");
      //Image pic = Toolkit.getDefaultToolkit().createImage("a1.png");//.getImage();
      g.drawImage(pic,0,0,null);
    }


  }
  //This is an inner class that is used to detect a button press
  class StartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {
      Main.game = new Game("Duber\'s Revenge"); //create a new FunkyFrame (another file that extends JFrame)
      Main.menu = false;
      Main.running = true;
      dispose();
      //System.out.println("Starting new Game");
    }

  }
}
