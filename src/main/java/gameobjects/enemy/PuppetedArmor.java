package gameobjects.enemy;

import gameobjects.Player;

public class PuppetedArmor extends Enemy {

    public PuppetedArmor() {
        super("Puppeted Armor", 20,9,10);
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }
}
