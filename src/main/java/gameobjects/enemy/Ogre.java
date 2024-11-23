package gameobjects.enemy;

import gameobjects.Player;

public class Ogre extends Enemy {

    public Ogre() {
        super("Ogre", 50,20,5);
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }
}
