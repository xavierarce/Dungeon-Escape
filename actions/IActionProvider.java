package actions;

public interface IActionProvider {
  ICombatAction getAction();

  IActionProvider getActionProvider();

}
