package com.example.ud;

public class User extends Player{
    /**
     * User Class:
     * - The class inherits the Player class
     * - Contains the variable coins to check the coins that the User have.
     * - The checkBlackJack class check if the card value is 'A' in that case,
     * the method should check if the sum of the card values plus the maximum 'A' value is less than 21, so it return 11.
     * if it's more than 21 it returns 1.
     * Also, if the card value is not 'A' the program calls the player method getPointsValues.
     *
     * @author Martin Velasquez
     * @version 1.1
     */

    /*
     * UML CLASS DIAGRAM:
     * ------------------------------------------------
     * User
     * ------------------------------------------------
     * - coins : int
     * ------------------------------------------------
     * + User(coins : int, playerHand : String[])
     * + setCoins(coins : int) : boolean
     * + getCoins() : int
     * + checkBlackJack(player : Player, index : int, sumOfValues : int) : int
     */

    /** INSTANCE VARIABLES **/
    private int coins;

    /** CONSTRUCTORS METHODS **/

    //Full Constructor
    public User(int coins, String[] playerHand){
        super(playerHand);
        setCoins(coins);
    }

    /** MUTATOR METHODS(SETTERS) **/
    public boolean setCoins(int coins){
        if(coins >= 10){
            this.coins = coins;
            return true;
        }
        else{
            return false;
        }
    }

    /** ACCESSOR METHODS (GETTERS) **/
    public int getCoins(){
        return this.coins;
    }

    /** OTHER REQUIRED METHODS **/
    @Override
    public int checkBlackJack(Player player,int index,int sumOfValues){
        int ans = 0;
        if(!player.getPlayerHand(index).equals("") && !player.getPlayerHand(index).contains("A")) {
            ans = player.getPointsValue(index);
        }
        else if(player.getPlayerHand(index).contains("A")){
            if(sumOfValues + 11 <= 21){
                ans = 11;
            }
            else {
                ans = 1;
            }
        }
        return ans;
    }

    public boolean checkDouble(Player player, int index){

        if(player.getPlayerHand(index).contains("A")){
            return true;
        }
        else{
            return false;
        }
    }
}
