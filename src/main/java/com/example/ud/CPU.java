package com.example.ud;

public class CPU extends Player{
    /**
     * CPU Class:
     * - The class inherits the Player class
     * - The checkBlackJack class check if the card value is 'A' in that case,
     * the method should check if the sum of the card values plus the maximum 'A' value is less than 17, so it return 11.
     * if it's more than 17 it returns 1.
     * Also, if the card value is not 'A' the program calls the player method getPointsValues.
     *
     * @author Martin Velasquez
     * @version 1.1
     */

    /*
     * UML CLASS DIAGRAM:
     * ------------------------------------------------
     * CPU
     * ------------------------------------------------
     * + CPU(playerHand : String[])
     * + checkBlackJack(player : Player, index : int, sumOfValues : int) : int
     */

    /** COSTRUCTOR METHODS **/

    //Full Constructor
    public CPU(String[] playerHand){
        super(playerHand);
    }

    /** OTHER REQUIRED METHODS **/
    @Override
    public int checkBlackJack(Player player,int index,int sumOfValues){
        int ans = 0;
        if(!player.getPlayerHand(index).equals("") && !player.getPlayerHand(index).contains("A")) {
            ans = player.getPointsValue(index);
        }
        else if(player.getPlayerHand(index).contains("A")){
            if(sumOfValues + 11 < 17){
                ans = 11;
            }
            else {
                ans = 1;
            }
        }
        return ans;
    }
}
