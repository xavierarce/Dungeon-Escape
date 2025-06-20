package map;

public class Room {
  private boolean visited = false;
  private boolean hasEnemy = Math.random() < 0.7;
  private boolean isExit = false;

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public boolean hasEnemy() {
    return hasEnemy;
  }

  public boolean isExit() {
    return isExit;
  }

  public void setExit(boolean exit) {
    isExit = exit;
  }
}
