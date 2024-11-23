package gameobjects.enemy;

import gameobjects.Player;

public class CannonCrew extends Enemy{

    public CannonCrew() {
        super("Cannon Crew", 30,10,10);
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }
}
