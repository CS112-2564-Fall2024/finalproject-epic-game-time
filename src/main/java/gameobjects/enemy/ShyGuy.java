package gameobjects.enemy;

import gameobjects.Player;
import javafx.scene.image.Image;

public class ShyGuy extends Enemy {

    private final Image shyguyImage;

    public ShyGuy() {
        super("Shy Guy", 12,6,0);
        this.shyguyImage = new Image(getClass().getResourceAsStream("/images/ShyGuyUpdate.png"));
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
