import game.GameManager;
import input.ConsoleInputProvider;
import input.InputProvider;
import combat.DefaultFightManagerFactory;
import combat.FightManagerFactory;

public class App {
  public static void main(String[] args) {
    InputProvider inputProvider = new ConsoleInputProvider();
    FightManagerFactory fightFactory = new DefaultFightManagerFactory();
    GameManager gameManager = new GameManager(inputProvider, fightFactory);
    gameManager.start();
  }
}
