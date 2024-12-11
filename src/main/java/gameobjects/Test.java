package gameobjects;

import gameobjects.enemy.AngrySkeleton;
import gameobjects.enemy.Enemy;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {

        Weapon weapon = new Weapon ("dagger",10, Optional.of(0.0), "white");
        Armor armor =  new Armor ("diamond", 4, Optional.of(0.0), "white");
        Enemy skeleton = new AngrySkeleton();
        //need to fix

        Player Eboss = new Player(10, weapon, armor);
        System.out.println(Eboss);
        Eboss.displayHealthPoints();
        skeleton.displayHealthEnemy();
        skeleton.enemyAttack(Eboss);
        Eboss.displayHealthPoints();
        Eboss.playerAttack(skeleton);
        skeleton.displayHealthEnemy();

    }
}
