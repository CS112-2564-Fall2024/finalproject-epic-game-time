package gameobjects.enemy;

import gameobjects.Player;
import javafx.scene.image.Image;

public class RollingGolem extends Enemy {

    private final Image rollingGolemImage;

    public RollingGolem() {
        super("Rolling Golem", 15, 10, 10);
        this.rollingGolemImage = new Image(getClass().getResourceAsStream("/images/RollingGolem.png"));
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }

    @Override
    public Image getImage() {
        return rollingGolemImage;
    }
}
