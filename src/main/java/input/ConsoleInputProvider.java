package input;

import actions.IActionProvider;
import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {
    private final Scanner scanner = new Scanner(System.in);
    private final IActionProvider actionProvider = new ConsoleActionProvider();

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public IActionProvider getActionProvider() {
        return actionProvider;
    }
}
