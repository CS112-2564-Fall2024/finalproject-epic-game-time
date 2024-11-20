package gameobjects.enemy;

import gameobjects.Player;

public abstract class CursedAutomation extends Enemy {

    public CursedAutomation() {
        super("Cursed Automation", 30,10,10);
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }

    //TODO add special move for fire DOT
}
