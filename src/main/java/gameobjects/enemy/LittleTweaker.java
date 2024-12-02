package gameobjects.enemy;

import gameobjects.Player;
import javafx.scene.image.Image;

public class LittleTweaker extends Enemy{

    private final Image littleTweakerImage;

    public LittleTweaker(){
        super("Little Tweaker",10,4,0);
        this.littleTweakerImage = new Image(getClass().getResourceAsStream("/images/little witch.png"));
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }

    @Override
    public Image getImage() {
        return littleTweakerImage;
    }
}
