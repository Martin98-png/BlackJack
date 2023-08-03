package com.example.ud;

public abstract class Player {
    /**
     * Player Abstract Class:
     * - Creates the abstract method checkBlackJack that check the sum of values and check if a card has the A value.
     * - Generates a hand deck of five cards for the object class.
     * - return a card in the hand with the index.
     * - check The values of the card while the card has not a value of A.
     *
     * @author Martin Velasquez
     * @version 1.1
     */

    /*
     * UML CLASS DIAGRAM:
     * --------------------------------------------------
     * Player
     * --------------------------------------------------
     * - playerHand : String[]
     * + valueOfA : int
     * -------------------------------------------------
     * + checkBlackJack(player : player, index : int, sumOfValues : int) : int
     * + Player()
     * + Player(playerHand : String[])
     * + Player(original : Player)
     * + setPlayerHand(PlayerHand : String[]) : void
     * + getPlayerHand(index : int) : String
     * + toString() : String
     * + getPointsValues(index : int) : int
     */

    /** INSTANCE VARIABLES **/
    private String[] playerHand;
    public int valueOfA;

    /** ABSTRACT METHOD **/
    public abstract int checkBlackJack(Player player,int index,int sumOfValues);

    /** CONSTRUCTOR METHODS **/

    //Default Constructor
    public Player(){
        this.playerHand = new String[5];
    }

    //Full Constructor
    public Player(String[] playerHand){
        this.playerHand = playerHand;
    }

    //Copy Constructor
    public Player(Player original){
        this.playerHand = original.playerHand;
    }

    /** MUTATOR METHODS(SETTERS) **/

    public void setPlayerHand(String[] playerHand) {
        this.playerHand = playerHand;
    }

    /** ACCESSOR METHODS (GETTERS) **/
    public String getPlayerHand(int index){
        return this.playerHand[index];
    }

    /** OTHER REQUIRED METHODS **/

    public String toString(){
        return this.playerHand[0];
    }

    public int getPointsValue(int index) {
        if(this.playerHand[index].contains("A")) { return valueOfA; }
        else if((this.playerHand[index].contains("10")) || (this.playerHand[index].contains("J")) || (this.playerHand[index].contains("Q")) || (this.playerHand[index].contains("K"))) { return 10; }
        else if(this.playerHand[index].contains("2")){return 2;}
        else if(this.playerHand[index].contains("3")){return 3;}
        else if(this.playerHand[index].contains("4")){return 4;}
        else if(this.playerHand[index].contains("5")){return 5;}
        else if(this.playerHand[index].contains("6")){return 6;}
        else if(this.playerHand[index].contains("7")){return 7;}
        else if(this.playerHand[index].contains("8")){return 8;}
        else if(this.playerHand[index].contains("9")){return 9;}
        return 0;
    }
}
