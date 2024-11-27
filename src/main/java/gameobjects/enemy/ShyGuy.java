package gameobjects.enemy;

import gameobjects.Player;
import javafx.scene.image.Image;

public class ShyGuy extends Enemy {

    private final Image shyguyImage;

    public ShyGuy() {
        super("Shy Guy", 15,10,5);
        this.shyguyImage = new Image(getClass().getResourceAsStream("/images/shyguyu.png"));
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }

    @Override
    public Image getImage() {
        return shyguyImage;
    }
}
