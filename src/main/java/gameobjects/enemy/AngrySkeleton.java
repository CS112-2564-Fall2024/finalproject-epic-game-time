package gameobjects.enemy;

import gameobjects.Player;
import javafx.scene.image.Image;

public class AngrySkeleton extends Enemy {

    private final Image skeletonImage;

    public AngrySkeleton() {
        super("Angry Skeleton", 8, 4,0);
        enemyIsAlive = true;
        this.skeletonImage = new Image(getClass().getResourceAsStream("/images/skeleton.png"));

    }

    @Override
    public boolean enemyIsAlive() {
        // Define your logic here
        return super.enemyIsAlive; // use inherited isAlive from Enemy
    }

    @Override
    public Image getImage() {
        return skeletonImage;
    }

    //potential method in game to set image
//    public void updateEnemyImage(Enemy enemy) {
//        Image enemyImage = enemy.getImage();
//        // Set the image to a GUI component (e.g., an image view)
//        imageView.setImage(enemyImage);

    @Override
    public void enemyAttack(Player player) {
        player.playerTakeDamage(getAttackDamage());
    }
    //TODO add special move type
}
