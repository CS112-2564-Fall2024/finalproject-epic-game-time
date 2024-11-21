package gameobjects.enemy;

import gameobjects.Player;

public class BigRat extends Enemy {

    public BigRat() {
        super("Big Rat", 20,5,5);
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }

    //TODO add special attack, Rat Plague will poison player
}
