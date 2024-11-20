package gameobjects.enemy;

import gameobjects.Player;

public abstract class LittleTweaker extends Enemy{

    public LittleTweaker(){
        super("Little Tweaker",10,6,5);
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }
}
