package gameobjects;

import java.util.Optional;

import static gameobjects.LootRandomizer.randomArmorDrop;
import static gameobjects.LootRandomizer.randomWeaponDrop;

public class Test {
    public static void main(String[] args) {

        Weapon weapon = new Weapon ("dagger",2, Optional.of(0.0), "white");
        Armor armor =  new Armor ("diamond", 5, Optional.of(0.0), "white");

        Player Eboss = new Player(10, weapon, armor);
        System.out.println(Eboss);
        Eboss.displayHealthPoints();
        Eboss.takeDamage(10);
        Eboss.displayHealthPoints();
        Eboss.heal(10);
        Eboss.displayHealthPoints();

    }
}
