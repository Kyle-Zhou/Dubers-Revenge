/**
 * [Main.java]
 * The Main class of the project
 * @author Eric Miao
 **/
public class Main {

  public static boolean menu = true, running = false, end = false;
  public static Game game;

  /**main
   * the main method is required in all projects
   * @param args, String array arguments
   */
  public static void main(String[] args){

    while (true){
      Menu mainmenu;
      if(menu){ // when game repeats itself there's a possibility that it may not redirect to menu
        mainmenu = new Menu(); // creates menu Frame
        while (menu){ // while loop for menu Frame
          mainmenu.refresh(); // awaiting commands...
        }
      }

      if(running){ // check if game is running
        start(); //create a new game
        while (running){ //Main Loop
          game.refresh();
        }
      }

      while(end) game.refresh(); //a different boolean variable assists the KeyListener
    }
  }

  /**start
   * A static method that creates a new Game Frame
   * Method is used before a new Game starts
   */
  private static void start(){ // start a new game
    game = new Game("Duber's Revenge");
  }
}