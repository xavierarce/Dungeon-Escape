package test.actions;

import characters.Fighter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAttackAction {

    @Test
    public void testExecuteAttackReducesEnemyHp() {
        // Given
        Fighter attacker = new Fighter("Hero", 10, 100);
        Fighter enemy = new Fighter("Goblin", 5, 50);
        AttackAction attackAction = new AttackAction();

        // When
        String result = attackAction.execute(attacker, enemy);

        // Then
        assertEquals(40, enemy.getHp(), "Enemy HP should be reduced by attack damage");
        assertEquals("Hero attacks Goblin for 10 damage!", result);
    }

    @Test
    public void testExecuteDoesNotMakeHpNegative() {
        // Given
        Fighter attacker = new Fighter("Hero", 60, 100);
        Fighter enemy = new Fighter("Goblin", 5, 50);
        AttackAction attackAction = new AttackAction();

        // When
        attackAction.execute(attacker, enemy);

        // Then
        assertEquals(0, enemy.getHp(), "Enemy HP should not be negative");
    }
}
