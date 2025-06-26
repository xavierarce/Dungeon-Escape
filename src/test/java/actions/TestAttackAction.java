package actions;

import characters.Fighter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AttackActionTest {

    static class TestFighter implements Fighter {
        private final String name;
        private final int attack;
        private int health;

        TestFighter(String name, int health, int attack) {
            this.name = name;
            this.health = health;
            this.attack = attack;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getHealth() {
            return health;
        }

        @Override
        public int getAttack() {
            return attack;
        }

        public int getDefense() {
            return 0; // ou une valeur que tu veux simuler dans tes tests
        }

        @Override
        public boolean isAlive() {
            return health > 0;
        }

        @Override
        public void takeDamage(int amount) {
            health = Math.max(0, health - amount);
        }
    }

    @Test
    void attackShouldReduceEnemyHealth() {
        TestFighter player = new TestFighter("Hero", 100, 20);
        TestFighter enemy = new TestFighter("Goblin", 50, 10);

        AttackAction attack = new AttackAction();
        String result = attack.execute(player, enemy);

        assertEquals(30, enemy.getHealth(), "Enemy HP should be reduced by 20");
        assertTrue(result.contains("Hero attacks Goblin for 20"), "Action message should be correct");
    }

    @Test
    void attackShouldNotReduceHealthBelowZero() {
        TestFighter player = new TestFighter("Hero", 100, 80);
        TestFighter enemy = new TestFighter("Slime", 50, 10);

        AttackAction attack = new AttackAction();
        attack.execute(player, enemy);

        assertEquals(0, enemy.getHealth(), "Enemy HP should not go below 0");
        assertFalse(enemy.isAlive(), "Enemy should be dead");
    }
}
