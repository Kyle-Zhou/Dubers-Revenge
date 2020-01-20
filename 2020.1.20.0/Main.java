/*
* Main.java
* Editor: Eric Miao
* January 20, 2020
 */

public class Main {

  public static boolean menu = true, running = false, end = false;
  public static Game game;

  public static void main(String[] args) {

    while (true) {
      Menu mainmenu;
      if(menu) { // when game repeats itself there's a possibility that it may not redirect to menu
        mainmenu = new Menu(); // creates menu Frame
        while (menu) { // while loop for menu Frame
          mainmenu.refresh(); // awaiting commands...
        }
      }

      if(running) { // check if game is running
        start(); //create a new game
        while (running) { //Main Loop
          game.refresh();
        }
      }

      //Oh no you died
      //System.out.println("Died");

      while(end) game.refresh(); //a different boolean variable assists the KeyListener
    }
  }

  private static void start() { // start a new game
    game = new Game("Duber's Revenge");
  }
}