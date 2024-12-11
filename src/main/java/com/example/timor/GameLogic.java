package com.example.timor;

import gameobjects.Weapon;
import gameobjects.enemy.AngrySkeleton;
import gameobjects.enemy.BigRat;
import gameobjects.enemy.Enemy;
import gameobjects.enemy.LittleTweaker;

import java.util.Random;

//ended up storing all the logic within the Game class, this is just the old game loop that was ditched

public class GameLogic {

//    public void startGame() {
//
//        while (player.isAlive() && currentEnemy.enemyIsAlive()) {
//
//            controller.updateHealthUI();
//
//            if (playerTurn) { // handle player action
//                //debugging help
//                System.out.println("Player Turn: " + playerTurn);
////                controller.getAttackButton().setOnAction(handlePlayerTurn());
//                //only switch turn if player action complete
//                if(isActionComplete) {
//                    System.out.println("Action Complete, Switching Turn");
//                    playerTurn = !playerTurn;
//                    System.out.println("After switching, Player Turn: " + playerTurn);// Debugging output
////                    System.out.println("Player is still performing action");
//                }
//            } else {
//                System.out.println("Enemy Turn: " + playerTurn);
//                handleEnemyTurn();
//                playerTurn = !playerTurn;
//                System.out.println("After switching, Player Turn: " + playerTurn);  // Debugging output
//            }
//
////            playerTurn = !playerTurn;
//
//
//            if (!checkGame()) {
//                break;
//            }
//        }
//    }
//
//
//
//    public boolean checkGame () {
//        if (currentEnemy.enemyIsAlive()) {
////            showStatsPage();
//            return false;
//        } else if (player.isAlive()) {
////            generateLoot();
////            advanceToNextRoom();
//            return false;
//        }
//        return true;
//    }
//
//    public void handlePlayerTurn() {
//        //reset action flag at beginning of turn
//        isActionComplete = false;
//
//        if (playerTurn) {
//            System.out.println("player turn to act");  // Debugging message
//
//            // Handle player's actions (Attack, Block, etc.)
//            if (playerInput == 1) {
//                handleAttack();
//                isActionComplete = true;
//                System.out.println("Player Attacked");
//                System.out.println(currentEnemy.getName() + " current health " + currentEnemy.getEnemyHealth() + " / " + currentEnemy.getEnemyMaxHealth());
//            } else if (playerInput == 2) {
//                handleBlock();
//            }
//
//            playerInput = 0;  // Reset player action for next turn
//
//
//            controller.updateHealthUI();
//        }
//    }

//    public void handleAttack() {
//
//        Weapon equippedWeapon = player.getEquippedWeapon();
//
//        if (equippedWeapon != null) {
//            // Get damage from weapon
//            System.out.println("Attacking " + currentEnemy.getName() + " for " + player.getEquippedWeapon().getAttackDamage() + " damage.");
//
//            // Apply damage to the enemy
//            player.playerAttack(currentEnemy);
//
//            System.out.println("Dealt " + player.getEquippedWeapon().getAttackDamage() + " damage to " + currentEnemy.getName());
//            // Update UI with new health values
//            controller.updateHealthUI();
//        } else {
//            System.out.println("No weapon equipped! Cannot attack.");
//        }
//    }


//    }
}