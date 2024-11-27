package gameobjects.enemy;

import gameobjects.Player;
import javafx.scene.image.Image;

public class PuppetedArmor extends Enemy {

    private final Image armorImage;

    public PuppetedArmor() {
        super("Puppeted Armor", 20,9,10);
        this.armorImage = new Image(getClass().getResourceAsStream("/images/armor.png"));
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }

    @Override
    public Image getImage() {
        return armorImage;
    }
}
