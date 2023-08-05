package com.example.ud;

import java.util.Random;

public class Deck {
    public static class Card{

        /**
         * Inner Class:
         * - Card value represents the number/letter printed on the card,
         * usually in the corners (A, 2, 3, ..., 9, 10, J, Q, K)
         * - Card value is stored as an integer to make error checking/validation easier,
         * but must be outputted appropriately (1 is A, 11 is J, 12 is Q, 13 is K) for user
         * - Card suit represents one of 4 suits (heart, diamond, club, spade)
         * - Card suit is stored as the unicode char representing the suit,
         * constant variables will be used throughout code for consistency
         * - Whenever value/suit is changed, it must be within the valid values
         *
         * @author Martin Velasquez
         * @version 1.1         // version 1.0 is on UD1
         */

        /*
         * UML CLASS DIAGRAM:
         * -------------------------------------------------------
         *   Card
         * -------------------------------------------------------
         * - cardValue : int
         * - cardSuit : char
         * + HEART : char			//static constant with value ♥
         * + DIAMOND : char			//static constant with value ♦
         * + CLUB : char			//static constant with value ♣
         * + SPADE : char			//static constant with value ♠
         * -------------------------------------------------------
         * + Card()
         * + Card(value : int, suit : char)
         * + Card(original : Card)
         * + setValue(value : int) : boolean
         * + setSuit(suit : char) : boolean
         * + setAll(value : int, suit : char) : boolean
         * + getSuit() : char
         * + getValue() : int
         * + toString() : String
         * + equals(o : Card) : boolean
         * + getPrintValue() : String
         * + getCardFormat() : String
         * -------------------------------------------------------
         */

        /*** CONSTANT VARIABLES ***/
        public static final char HEART = '♥';
        public static final char DIAMOND = '♦';
        public static final char CLUB = '♣';
        public static final char SPADE = '♠';

        /*** INSTANCE VARIABLES ***/
        private int cardValue;
        private char cardSuit;

        /*** CONSTRUCTOR METHODS ***/

        //Default Constructor
        public Card(){
            this.cardValue = 1;
            this.cardSuit = '♥';
        }

        //Full Constructor
        public Card(int cardValue, char cardSuit)
        {
            if(this.setAll(cardValue, cardSuit) == true){
                this.cardValue = cardValue;
                this.cardSuit = cardSuit;
            }
            else{
                System.out.println("Error: Incorrect value or suit! ");
            }
        }

        //Copy Constructor
        public Card(Card obj)
        {
            this.cardValue = obj.cardValue;
            this.cardSuit = obj.cardSuit;
        }

        /*** MUTATOR METHODS (SETTERS) ***/

        public boolean setValue(int cardValue) {
            if(cardValue >= 1 && cardValue <= 13){
                this.cardValue = cardValue;
                return true;
            }
            else { return false; }
        }

        public boolean setSuit(char cardSuit) {
            if(cardSuit == HEART || cardSuit == DIAMOND || cardSuit == SPADE || cardSuit == CLUB)
            {
                this.cardSuit = cardSuit;
                return true;
            }
            else { return false; }
        }

        public boolean setAll(int cardValue, char cardSuit){
            if(this.setValue(cardValue) == true && this.setSuit(cardSuit) == true){
                this.cardValue = cardValue;
                this.cardSuit = cardSuit;
                return true;
            }
            else { return false; }
        }

        /*** ACCESSOR METHODS (GETTERS) ***/

        public char getSuit() { return this.cardSuit; }

        public int getValue() { return this.cardValue; }

        /*** OTHER REQUIRED METHODS ***/

        public String toString(){
            return this.cardValue + " " + this.cardSuit;
        }

        public boolean equals(Card otherCard){
            return this.cardValue == ((Card)otherCard).cardValue &&
                    this.cardSuit == ((Card)otherCard).cardSuit;
        }

        public String getPrintValue() {
            if(this.cardValue == 1) { return "A"; }
            else if(this.cardValue == 11) { return "J"; }
            else if(this.cardValue == 12) { return "Q"; }
            else if(this.cardValue == 13) { return "K"; }
            else { return String.valueOf(this.cardValue); }
        }


        public String getCardFormat(){
            if(this.cardValue != 10){
                return  "-------\n|"+this.cardSuit+"   "+this.cardSuit+
                        "|\n|  "+getPrintValue()+"  |\n|"+this.cardSuit+"   "+this.cardSuit+"|\n-------";}
            else{
                return  "-------\n|"+this.cardSuit+"   "+this.cardSuit+"|\n| "+getPrintValue()+"  |\n|"+this.cardSuit+"   "+this.cardSuit+"|\n-------";
            }
        }
    }

    /**
     * Deck Class - Creates an array to combine 4 decks of 52 cards,     * it repeats atleast 4 times each value and suit card.
     * Contains the shuffle() method that change the order of cards      * randomly.
     *
     * @author Martin Velasquez
     * @version 1.1
     */

    /*
     * UML CLASS DIAGRAM:
     * -------------------------------------------------------
     *   Deck
     * -------------------------------------------------------
     * - cards[] :  String
     * - card : Deck.Card
     * + DEFAULT_CARDS : String     //Constant variables array with size of 208
     * -------------------------------------------------------
     * + Deck()
     * + Deck(cards[] : String)
     * + Deck(Deck obj)
     * + setCards(cards :String[]) : void
     * + getCards():String[]
     * + toString() : String
     * + equals(o : Deck) : boolean
     * + shuffle() : String[]
     * -------------------------------------------------------
     */

    /*** CONSTANT VARIABLES ***/
    public static final String[] DEFAULT_CARDS = new String[312];

    /*** INSTANCE VARIABLES ***/
    private String[] cards = new String[312];
    private Deck.Card card = new Deck.Card();

    /*** CONSTRUCTOR METHODS ***/

    //Default Constructor
    public Deck(){
        this.cards = DEFAULT_CARDS;
    }

    //Full Constructor
    public Deck(String[] cards){
        this.setCards(cards);
    }

    //Copy Constructor
    public Deck(Deck obj){
        this.cards = obj.cards;
    }

    /*** MUTATOR METHODS (SETTERS) ***/

    public void setCards(String[] cards){
        this.cards = cards;
    }

    /*** ACCESSOR METHODS (GETTERS) ***/

    public String[] getCards(){
        String[] singleDeck = new String[52];

        for(int i = 0; i < 13; i++)
        {
            card.setAll(i+1,Card.HEART);
            singleDeck[i] = card.getCardFormat();

            card.setAll(i+1,Card.DIAMOND);
            singleDeck[i+13] = card.getCardFormat();

            card.setAll(i+1,Card.CLUB);
            singleDeck[i+26] = card.getCardFormat();

            card.setAll(i+1,Card.SPADE);
            singleDeck[i+39] = card.getCardFormat();
        }

        for(int i = 0; i < 52; i++){
            cards[i] = singleDeck[i];

            cards[i+52] = singleDeck[i];

            cards[i+104] = singleDeck[i];

            cards[i+156] = singleDeck[i];

            cards[i+208] = singleDeck[i];

            cards[i+260] = singleDeck[i];
        }
        return this.cards;
    }

    public String[] createOnlyTwo(){
        String[] singleDeck = new String[300];

        for(int i = 0; i < 300; i++)
        {
            card.setAll(1,Card.HEART);
            singleDeck[i] = card.getCardFormat();
        }

        for(int i = 0; i < 300; i++){
            cards[i] = singleDeck[i];
        }
        return this.cards;
    }

    /*** OTHER REQUIRED METHODS ***/

    @Override
    public String toString()
    {
        return "\nThere are a total of " + this.cards.length + " cards to play.\n" +
                "The cards should look like this example:" + this.card.getCardFormat();
    }

    @Override
    public boolean equals(Object other)
    {
        if(other == null || (!(other instanceof Deck))) {
            return false;
        }

        Deck otherDeck = (Deck) other;
        return this.cards.equals(otherDeck.cards) && this.card.equals(otherDeck.card);
    }

    public String[] shuffle(){
        Random rand = new Random();

        String[] shuffledDeck = this.getCards();

        for(int i = 0; i < cards.length; i++){
            int randomCard = rand.nextInt(cards.length);
            cards[i] =  shuffledDeck[randomCard];
        }
        return cards;
    }
}
