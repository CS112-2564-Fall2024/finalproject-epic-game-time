package gameobjects.enemy;

import gameobjects.Player;

public abstract class AngrySkeleton extends Enemy {

    public AngrySkeleton() {
        super("Angry Skeleton", 15, 7,5);
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }
    //TODO add special move type
}
