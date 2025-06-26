package map;

public class Room {
  private boolean visited = false;
  private boolean hasEnemy = false;
  private boolean isExit = false;
  private boolean bossRoom = false;

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public boolean hasEnemy() {
    return hasEnemy;
  }

  public void setHasEnemy(boolean hasEnemy) {
    this.hasEnemy = hasEnemy;
  }

  public boolean isExit() {
    return isExit;
  }

  public void setExit(boolean exit) {
    isExit = exit;
  }

  public boolean isBossRoom() {
    return bossRoom;
  }

  public void setBossRoom(boolean bossRoom) {
    this.bossRoom = bossRoom;
  }

}
