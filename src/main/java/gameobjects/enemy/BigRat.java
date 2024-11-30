package gameobjects.enemy;

import gameobjects.Player;
import javafx.scene.image.Image;

public class BigRat extends Enemy {

    private final Image bigratImage;

    public BigRat() {
        super("Big Rat", 12,5,0);
        this.bigratImage = new Image(getClass().getResourceAsStream("/images/rat.png"));
    }

    @Override
    public Image getImage() {
        return bigratImage;
    }

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }

    //TODO add special attack, Rat Plague will poison player
}
