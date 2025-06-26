package input;

import actions.IActionProvider;

public interface InputProvider {
  String nextLine();

  IActionProvider getActionProvider();
}