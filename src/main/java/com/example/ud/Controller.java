package com.example.ud;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Arrays;


public class Controller {
    /**
     * Controller Class:
     * Creates and controls all GUI objects to work
     * The class contains buttons, labels, arrays and special methods to make the app works properly.
     *
     * @author Martin Velasquez
     * @version 1.1
     */

    /** CONSTANT VARIABLES **/
    private final String[] Deck = new Deck().shuffle();
    //private final String[] customDeck = new Deck().createOnlyTwo();

    /** VARIABLES **/
    private User userCards;
    private User firstSplitUserCards;
    private User secSplitUserCards;
    private CPU cpuCards;
    private ArrayList<String> gameDeck = new ArrayList<>(Arrays.asList(Deck));
    //private ArrayList<String> customGameDeck = new ArrayList<>(Arrays.asList(customDeck));
    private int timesButtonClicked = 0;
    private int timesSplitClicked = 0;
    private int restartClick = 0;
    private int totalCoins = 490;
    private int betCoins = 10;
    private int totalPoints = 0;
    private int cpuTotalPoints = 0;
    private int firstHalfPoints = 0;
    private int secondHalfPoints = 0;

    //Label Variables
    @FXML
    private Label infoLabel = new Label();
    @FXML
    private Label spotOnePlayer = new Label();
    @FXML
    private Label spotTwoPlayer = new Label();
    @FXML
    private Label spotThreePlayer = new Label();
    @FXML
    private Label spotFourPlayer = new Label();
    @FXML
    private Label spotFivePlayer = new Label();
    @FXML
    private Label spotOneCPU = new Label();
    @FXML
    private Label spotTwoCPU = new Label();
    @FXML
    private Label spotThreeCPU = new Label();
    @FXML
    private Label spotFourCPU = new Label();
    @FXML
    private Label spotFiveCPU = new Label();
    @FXML
    private Label coinsLabel = new Label();
    @FXML
    private Label betLabel = new Label();
    @FXML
    private Label totalCoinsLabel = new Label();
    @FXML
    private Label betCoinsLabel = new Label();
    @FXML
    private Label playerCardsValue = new Label();
    @FXML
    private Label dealerCardsValue = new Label();

    //Buttons Variables
    @FXML
    private Button hitButton;
    @FXML
    private Button doubleButton;
    @FXML
    private Button splitButton;
    @FXML
    private Button standButton;
    @FXML
    private Button raiseBet;
    @FXML
    private Button downBet;

    /** REQUIRED METHODS **/

    /**
     * initialize() - starts the app before any button is clicked.
     * enable the raiseBet & downBet button to can be clicked.
     * creates an arrayList of the deck object Cards and assign five to the User hand and CPU hand and deletes each card to prevent to be repeated.
     * Check if the coins is less or equals to 0.
     */

    @FXML
    public void initialize(){

        doubleButton.setDisable(true);
        splitButton.setDisable(true);
        standButton.setDisable(true);
        raiseBet.setDisable(false);
        downBet.setDisable(false);
        coinsLabel.setText(Integer.toString(totalCoins));

        totalPoints = 0;
        firstHalfPoints = 0;
        secondHalfPoints = 0;

        timesSplitClicked = 0;
        timesButtonClicked = 0;

        String[] playerCards = new String[5];
        String[] dealerCards = new String[5];
        if(gameDeck.size() < 15)
        {
            gameDeck = new ArrayList<>(Arrays.asList(Deck));
        }
        for(int i = 0;i < 5;i++) {
            playerCards[i] = gameDeck.get(i);
            gameDeck.remove(i);
        }
        for(int i = 0;i < 5;i++){
            dealerCards[i] = gameDeck.get(i);
            gameDeck.remove(i);
        }
//        if(customGameDeck.size() < 15)
//        {
//            customGameDeck = new ArrayList<>(Arrays.asList(customDeck));
//        }
//        for(int i = 0;i < 5;i++) {
//            playerCards[i] = customGameDeck.get(i);
//            customGameDeck.remove(i);
//        }
//        for(int i = 0;i < 5;i++){
//            dealerCards[i] = customGameDeck.get(i);
//            customGameDeck.remove(i);
//        }
        userCards = new User(totalCoins,playerCards);
        cpuCards = new CPU(dealerCards);

        if(totalCoins <= 0){
            hitButton.setDisable(true);
            raiseBet.setDisable(true);
            downBet.setDisable(true);
            infoLabel.setText("GAME OVER!\nReopen The App\nTo PLay Again.");
            infoLabel.setTextAlignment(TextAlignment.CENTER);
        }

    }

    /**
     * textColor() - check the suit of the card objects and then assigns a color between red and black based of the suit.
     * @param hand the Player object card that will be checked.
     * @param spot checks where the card will be located.
     * @param index checks the index of the array in the Player hand.
     */
    public void textColor(Player hand, Label spot,int index){
        if(hand.getPlayerHand(index).contains("♥") || hand.getPlayerHand(index).contains("♦")){
            spot.setTextFill(Color.RED);
        }
        else{
            spot.setTextFill(Color.BLACK);
        }
    }

    /**
     * winCondition() - check the User points to evaluate if the User loss or win.
     * If the player click the STAND button, the method will check the points of the CPU to give a result.
     * Add a restartClick to send the app to initialize() method with the current coins.
     */
    public void winCondition() {
        if(totalPoints > 21){
            configureWinCondition();
            totalCoins -= betCoins;
            infoLabel.setText("You Lose!\nTo continue click HIT one time then change your bet.");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
            playerCardsValue.setText("Player Cards Value: " + totalPoints);

        }
        else if(totalPoints == 21){
            configureWinCondition();
            totalCoins += (betCoins * 2);
            infoLabel.setText("You Win!\nYou get: " + betCoins * 2 +" coins\nTo continue click HIT one time then change your bet.");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
            playerCardsValue.setText("Player Cards Value: " + totalPoints);
        }
        else if(standButton.isDisable() && timesButtonClicked > 0){
            dealerPoints();
            if(cpuTotalPoints > 21 && totalPoints < 21) {
                totalCoins += (betCoins * 2);
                infoLabel.setText("You Win!\nTo continue click HIT one time then change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            }
            else if(cpuTotalPoints < totalPoints){
                totalCoins += (betCoins * 2);
                infoLabel.setText("You Win!\nTo continue click HIT one time then change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            }
            else if(cpuTotalPoints > totalPoints) {
                totalCoins -= betCoins;
                infoLabel.setText("You Lose!\nTo continue click HIT one time then change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            }
            else if(cpuTotalPoints == totalPoints){
                totalCoins += betCoins;
                infoLabel.setText("Draw!\nTo continue click HIT one time then change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            }
            restartClick = 2;
        }
    }

    /**
     * splitWinCondition() - As winCondition method check the result of the player, with the addition of a second Hand of cards.
     * Only works if the SPLIT button is clicked.
     */
    public void splitWinCondition() {
        if(firstHalfPoints > 21 && secondHalfPoints > 21){
            configureWinCondition();
            totalCoins -= betCoins;
            infoLabel.setText("You Lose!\nTo continue click HIT one time then change your bet.");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
            playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
        }
        else if(firstHalfPoints > 21 || secondHalfPoints > 21){
            timesButtonClicked = 0;
            configureWinCondition();
            totalCoins -= betCoins/2;
            infoLabel.setText("You Lose In One Hand!\nTo continue click HIT one time then change your bet.");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
            playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
        }
        else if(firstHalfPoints == 21 && secondHalfPoints == 21){
            timesSplitClicked = 0;
            configureWinCondition();
            totalCoins += (betCoins * 2);
            infoLabel.setText("You Win!\nYou get: " + betCoins * 2 +
                    " coins\nIf want to continue click HIT one time then change your bet.");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
            playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
        }
        else if(firstHalfPoints == 21 || secondHalfPoints == 21){
            timesButtonClicked = 0;
            configureWinCondition();
            totalCoins += betCoins;
            infoLabel.setText("You Win In One Hand!\nYou get: " + betCoins * 2 +
                    " coins\nTo continue click HIT one time then change your bet.");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
            playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
        }
        else if(standButton.isDisable() && timesSplitClicked > 0){
            if(cpuTotalPoints > 21) {
                if(secondHalfPoints < 21 && firstHalfPoints < 21){
                    totalCoins += (betCoins * 2);
                    infoLabel.setText("You Win!\nTo continue click HIT one time then change your bet.");
                    infoLabel.setAlignment(Pos.CENTER);
                    coinsLabel.setText(Integer.toString(totalCoins));
                    betLabel.setText(Integer.toString(betCoins));
                    totalCoinsLabel.setText("Total Coins: " + totalCoins);
                    betCoinsLabel.setText("Bet Coins: " + betCoins);
                    playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
                    dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
                }
                else if(secondHalfPoints < 21 || firstHalfPoints < 21){
                    totalCoins += betCoins;
                    infoLabel.setText("You Win in One Hand!\nTo continue click HIT one time then change your bet.");
                    infoLabel.setAlignment(Pos.CENTER);
                    coinsLabel.setText(Integer.toString(totalCoins));
                    betLabel.setText(Integer.toString(betCoins));
                    totalCoinsLabel.setText("Total Coins: " + totalCoins);
                    betCoinsLabel.setText("Bet Coins: " + betCoins);
                    playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
                    dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
                }

            }
            else if(cpuTotalPoints < firstHalfPoints){
                if(cpuTotalPoints < secondHalfPoints){
                    totalCoins += (betCoins * 2);
                    infoLabel.setText("You Win!\nTo continue click HIT one time then change your bet.");
                    infoLabel.setAlignment(Pos.CENTER);
                    coinsLabel.setText(Integer.toString(totalCoins));
                    betLabel.setText(Integer.toString(betCoins));
                    totalCoinsLabel.setText("Total Coins: " + totalCoins);
                    betCoinsLabel.setText("Bet Coins: " + betCoins);
                }
                else if(cpuTotalPoints > secondHalfPoints){
                    totalCoins += betCoins;
                    infoLabel.setText("You Win In One Hand!\nTo continue click HIT one time then change your bet.");
                    infoLabel.setAlignment(Pos.CENTER);
                    coinsLabel.setText(Integer.toString(totalCoins));
                    betLabel.setText(Integer.toString(betCoins));
                    totalCoinsLabel.setText("Total Coins: " + totalCoins);
                    betCoinsLabel.setText("Bet Coins: " + betCoins);
                    playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
                    dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
                }
            }
            else if(cpuTotalPoints > firstHalfPoints) {
                if(cpuTotalPoints > secondHalfPoints){
                    totalCoins -= betCoins;
                    infoLabel.setText("You Lose!\nTo continue click HIT one time then change your bet.");
                    infoLabel.setAlignment(Pos.CENTER);
                    coinsLabel.setText(Integer.toString(totalCoins));
                    betLabel.setText(Integer.toString(betCoins));
                    totalCoinsLabel.setText("Total Coins: " + totalCoins);
                    betCoinsLabel.setText("Bet Coins: " + betCoins);
                    playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
                    dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
                }
                else if(cpuTotalPoints < secondHalfPoints){
                    totalCoins += betCoins;
                    infoLabel.setText("You Win In One Hand!\nTo continue click HIT one time then change your bet.");
                    infoLabel.setAlignment(Pos.CENTER);
                    coinsLabel.setText(Integer.toString(totalCoins));
                    betLabel.setText(Integer.toString(betCoins));
                    totalCoinsLabel.setText("Total Coins: " + totalCoins);
                    betCoinsLabel.setText("Bet Coins: " + betCoins);
                    playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
                    dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
                }
            }
            else{
                totalCoins += betCoins;
                infoLabel.setText("Draw!\n" +
                        "To continue click HIT one time then change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            }
            restartClick = 2;
        }
    }

    /**
     * dealerPoints() - gets the sums of the dealer card value and takes cards for the dealer while the cpuTotalPoints is less than 17.
     * Only works when the user click the stand button, if the User gets 21 or more than 21 there's not need to get the dealerPoints.
     */
    public void dealerPoints(){
            if(cpuTotalPoints < 17) {
                spotOneCPU.setText(cpuCards.getPlayerHand(0));
                textColor(cpuCards,spotOneCPU, 0);
                spotOneCPU.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                cpuTotalPoints += cpuCards.checkBlackJack(cpuCards, 0, cpuTotalPoints);
                infoLabel.setText("You get: " + betCoins * 2 + " coins" +
                        "\nTo continue change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            if(cpuTotalPoints < 17) {
                spotTwoCPU.setText(cpuCards.getPlayerHand(1));
                textColor(cpuCards,spotTwoCPU, 1);
                spotTwoCPU.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                cpuTotalPoints += cpuCards.checkBlackJack(cpuCards, 1, cpuTotalPoints);
                infoLabel.setText("You get: " + betCoins * 2 + " coins" +
                        "\nTo continue change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            }
                spotThreeCPU.setText(cpuCards.getPlayerHand(2));
                textColor(cpuCards,spotThreeCPU, 2);
                spotThreeCPU.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                cpuTotalPoints += cpuCards.checkBlackJack(cpuCards, 2, cpuTotalPoints);
                infoLabel.setText("You get: " + betCoins * 2 + " coins" +
                        "\nTo continue change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            if(cpuTotalPoints < 17) {
                spotFourCPU.setText(cpuCards.getPlayerHand(3));
                textColor(cpuCards,spotFourPlayer, 3);
                spotFourCPU.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                cpuTotalPoints += cpuCards.checkBlackJack(cpuCards, 3, cpuTotalPoints);
                infoLabel.setText("You get: " + betCoins * 2 + " coins" +
                        "\nTo continue change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            }
            if(cpuTotalPoints < 17) {
                spotFiveCPU.setText(cpuCards.getPlayerHand(4));
                textColor(cpuCards,spotFiveCPU, 4);
                spotFiveCPU.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                cpuTotalPoints += cpuCards.checkBlackJack(cpuCards, 4, cpuTotalPoints);
                infoLabel.setText("You get: " + betCoins * 2 + " coins" +
                        "\nTo continue change your bet.");
                infoLabel.setAlignment(Pos.CENTER);
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
                dealerCardsValue.setText("Dealer Cards Value: " + cpuTotalPoints);
            }
            }
    }

    /**
     * organizeLabel() - sets the card label in the pane with their suits and values, also use the textColor() method.
     * @param spot Checks where the card will be located.
     * @param index the value in the array of the Player hand object.
     * @param hand Check which Player object array is used.
     */
    public void organizeLabel(Label spot,int index,Player hand){
        spot.setText(hand.getPlayerHand(index));
        textColor(hand,spot,index);
        spot.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        spot.setAlignment(Pos.TOP_CENTER);

    }

    /** BUTTONS METHODS **/

    /**
     * onHitButtonClick() - Disable the bet buttons and add Cards to the labels according to the times is clicked.
     * Check the winCondition method and the restartClick value on each click to give a result.
     */
    @FXML
    protected void onHitButtonClick() {

            standButton.setDisable(false);
            raiseBet.setDisable(true);
            downBet.setDisable(true);

            if (timesButtonClicked == 0) {
                organizeLabel(spotOnePlayer, 0, userCards);
                totalPoints += userCards.checkBlackJack(userCards, 0, totalPoints);
                organizeLabel(spotTwoPlayer, 1, userCards);
                totalPoints += userCards.checkBlackJack(userCards, 1, totalPoints);
                enableDoubleButton(1);
//                if (betCoins * 2 <= totalCoins && totalCoins > 0) {
//                    doubleButton.setDisable(false);
//                }
                if (userCards.getPointsValue(0) == userCards.getPointsValue(1) && timesButtonClicked == 0) {
                    splitButton.setDisable(false);
                }

                organizeLabel(spotOneCPU, 0, cpuCards);

                infoLabel.setText("You take a card!");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
            } else if (timesButtonClicked > 0) {
                //doubleButton.setDisable(true);
                switch (timesButtonClicked) {
                    case 1:
                        organizeLabel(spotThreePlayer, 2, userCards);
                        totalPoints += userCards.checkBlackJack(userCards, 2, totalPoints);
                        enableDoubleButton(2);
                        break;
                    case 2:
                        organizeLabel(spotFourPlayer, 3, userCards);
                        totalPoints += userCards.checkBlackJack(userCards, 3, totalPoints);
                        enableDoubleButton(3);
                        break;
                    case 3:
                        organizeLabel(spotFivePlayer, 4, userCards);
                        totalPoints += userCards.checkBlackJack(userCards, 4, totalPoints);
                        enableDoubleButton(4);
                        break;
                }
                infoLabel.setText("You take a card!");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + totalPoints);
            }
            if(timesButtonClicked == 6 && timesSplitClicked > 0){
                userCards = new User(totalCoins,null);
                timesSplitClicked = 0;
                totalPoints = 0;
                initialize();
                spotOnePlayer.setText(null);
                spotOnePlayer.setBackground(null);
                spotTwoPlayer.setText(null);
                spotTwoPlayer.setBackground(null);
                spotThreePlayer.setText(null);
                spotThreePlayer.setBackground(null);
                spotFourPlayer.setText(null);
                spotFourPlayer.setBackground(null);
                spotFivePlayer.setText(null);
                spotFivePlayer.setBackground(null);
                spotOneCPU.setText(null);
                spotOneCPU.setBackground(null);
                spotTwoCPU.setText(null);
                spotTwoCPU.setBackground(null);
                spotThreeCPU.setText(null);
                spotThreeCPU.setBackground(null);
                spotFourCPU.setText(null);
                spotFourCPU.setBackground(null);
                spotFiveCPU.setText(null);
                spotFiveCPU.setBackground(null);
                infoLabel.setText("Change your bet.");
            }

            timesButtonClicked++;

            if (restartClick == 1) {
                timesButtonClicked = 0;
                cpuTotalPoints = 0;
                betCoins = 10;
                initialize();
                spotOnePlayer.setText(null);
                spotOnePlayer.setBackground(null);
                spotTwoPlayer.setText(null);
                spotTwoPlayer.setBackground(null);
                spotThreePlayer.setText(null);
                spotThreePlayer.setBackground(null);
                spotFourPlayer.setText(null);
                spotFourPlayer.setBackground(null);
                spotFivePlayer.setText(null);
                spotFivePlayer.setBackground(null);
                spotOneCPU.setText(null);
                spotOneCPU.setBackground(null);
                spotTwoCPU.setText(null);
                spotTwoCPU.setBackground(null);
                spotThreeCPU.setText(null);
                spotThreeCPU.setBackground(null);
                spotFourCPU.setText(null);
                spotFourCPU.setBackground(null);
                spotFiveCPU.setText(null);
                spotFiveCPU.setBackground(null);
                infoLabel.setText("Change your bet.");
            }
            restartClick = 0;
            winCondition();
    }

    private void enableDoubleButton(int index){

        if (betCoins * 2 <= totalCoins && totalCoins > 0) {

            boolean userHasAce = false;

            for (int i = 0; i <= index; i++){
                if (userCards.checkDouble(userCards, i)){
                    userHasAce = true;
                }
            }

            if (userHasAce && (totalPoints >= 16 && totalPoints <= 18)) {
                doubleButton.setDisable(false);
            } else if (!userHasAce && (totalPoints >= 9 && totalPoints <= 11)) {
                doubleButton.setDisable(false);
            }
            else {
                doubleButton.setDisable(true);
            }
        }
        else {
            doubleButton.setDisable(true);
        }
    }

    /**
     * onDoubleButtonClick() - multiplies by 2 the total of betCoins
     * It works only if the HIT button is clicked one time and if the User still have available coins.
     * After the HIT is clicked a second time the doubleButton is disabled.
     * If the User wins get the double of the new betCoins.
     */
    @FXML
    protected void onDoubleButtonClick() {
            totalCoins -= betCoins;
            betCoins *= 2;
            infoLabel.setText("You choose the Double bet!");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
            playerCardsValue.setText("Player Cards Value: " + totalPoints);
            doubleButton.setDisable(true);
    }

    /**
     * onSplitButtonClick() - creates a second hand and double the coins, if the player wins in one hand get the betCoins
     * and if the player wins on both hands get the double of betCoins
     * Only works if the first two cards has the same value
     * To get more cards the User must click the SPLIT button again and to stop getting cards the User must click the STAND button.
     * To check the result the button method use the splitWinCondition() method.
     */
    @FXML
    protected void onSplitButtonClick() {

            timesSplitClicked++;
            totalPoints = 0;

            if (timesSplitClicked == 1) {

                hitButton.setDisable(true);
                infoLabel.setText("You split your cards");
                betCoins *= 2;
                String[] splitNewDeck = new String[5];
                String[] splitOldDeck = new String[5];
                splitNewDeck[0] = userCards.getPlayerHand(1);
                for (int i = 0; i < 5; i++) {
                    splitOldDeck[i] = userCards.getPlayerHand(i);
                }
                splitOldDeck[1] = gameDeck.get(0);
                gameDeck.remove(0);

                for (int i = 1; i < 5; i++) {
                    splitNewDeck[i] = gameDeck.get(i);
                    gameDeck.remove(i);
                }

                firstSplitUserCards = new User(0, splitNewDeck);
                secSplitUserCards = new User(0, splitOldDeck);

                if (gameDeck.size() < 15) {
                    gameDeck = new ArrayList<>(Arrays.asList(Deck));
                }

                firstHalfPoints += firstSplitUserCards.checkBlackJack(firstSplitUserCards, 0, firstHalfPoints);
                secondHalfPoints += firstSplitUserCards.checkBlackJack(secSplitUserCards, 0, secondHalfPoints);

                infoLabel.setText("You take two cards!\nClick SPLIT again to take more cards.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);
            } else if (timesSplitClicked > 1) {
                firstHalfPoints += firstSplitUserCards.checkBlackJack(firstSplitUserCards, timesSplitClicked, firstHalfPoints);
                secondHalfPoints += firstSplitUserCards.checkBlackJack(secSplitUserCards, timesSplitClicked, secondHalfPoints);

                infoLabel.setText("You take two cards!\nClick SPLIT again to take more cards.");
                infoLabel.setAlignment(Pos.CENTER);
                coinsLabel.setText(Integer.toString(totalCoins));
                betLabel.setText(Integer.toString(betCoins));
                totalCoinsLabel.setText("Total Coins: " + totalCoins);
                betCoinsLabel.setText("Bet Coins: " + betCoins);
                playerCardsValue.setText("Player Cards Value: " + firstHalfPoints + " & " + secondHalfPoints);

            }

            doubleButton.setDisable(true);
            raiseBet.setDisable(false);
            downBet.setDisable(false);
            if (restartClick == 1) {
                timesButtonClicked = 6;
                timesSplitClicked = 0;
                cpuTotalPoints = 0;
                betCoins = 10;
                initialize();
                spotOnePlayer.setText(null);
                spotOnePlayer.setBackground(null);
                spotTwoPlayer.setText(null);
                spotTwoPlayer.setBackground(null);
                spotThreePlayer.setText(null);
                spotThreePlayer.setBackground(null);
                spotFourPlayer.setText(null);
                spotFourPlayer.setBackground(null);
                spotFivePlayer.setText(null);
                spotFivePlayer.setBackground(null);
                spotOneCPU.setText(null);
                spotOneCPU.setBackground(null);
                spotTwoCPU.setText(null);
                spotTwoCPU.setBackground(null);
                spotThreeCPU.setText(null);
                spotThreeCPU.setBackground(null);
                spotFourCPU.setText(null);
                spotFourCPU.setBackground(null);
                spotFiveCPU.setText(null);
                spotFiveCPU.setBackground(null);
                infoLabel.setText("Change your bet.");
            }

            splitWinCondition();
    }

    /**
     * onStandButtonClick() - if the button is clicked the user stop getting more cards
     * and goes to the winCondition() or splitWinCondition() methods.
     * Works on both HIT and SPLIT methods.
     * only works if the HIT button is clicked one time.
     */
    @FXML
    protected void onStandButtonClick(){

        if(timesSplitClicked == 0) {

            infoLabel.setText("You decide to \nDon't take more cards");
            standButton.setDisable(true);
            doubleButton.setDisable(true);
            dealerPoints();
            raiseBet.setDisable(false);
            downBet.setDisable(false);
            if (restartClick == 1) {
                totalPoints = 0;
                cpuTotalPoints = 0;
                betCoins = 10;
                initialize();
                spotOnePlayer.setText(null);
                spotOnePlayer.setBackground(null);
                spotTwoPlayer.setText(null);
                spotTwoPlayer.setBackground(null);
                spotThreePlayer.setText(null);
                spotThreePlayer.setBackground(null);
                spotFourPlayer.setText(null);
                spotFourPlayer.setBackground(null);
                spotFivePlayer.setText(null);
                spotFivePlayer.setBackground(null);
                spotOneCPU.setText(null);
                spotOneCPU.setBackground(null);
                spotTwoCPU.setText(null);
                spotTwoCPU.setBackground(null);
                spotThreeCPU.setText(null);
                spotThreeCPU.setBackground(null);
                spotFourCPU.setText(null);
                spotFourCPU.setBackground(null);
                spotFiveCPU.setText(null);
                spotFiveCPU.setBackground(null);
                infoLabel.setText("Change your bet.");
            }
            winCondition();
            restartClick = 1;
        }
        else if(timesSplitClicked > 0){
            infoLabel.setText("You decide to \nDon't take more cards");
            standButton.setDisable(true);
            doubleButton.setDisable(true);
            dealerPoints();
            splitButton.setDisable(true);
            raiseBet.setDisable(false);
            downBet.setDisable(false);
            if (restartClick == 1) {
                totalPoints = 0;
                cpuTotalPoints = 0;
                timesButtonClicked = 0;
                betCoins = 10;
                initialize();
                spotOnePlayer.setText(null);
                spotOnePlayer.setBackground(null);
                spotTwoPlayer.setText(null);
                spotTwoPlayer.setBackground(null);
                spotThreePlayer.setText(null);
                spotThreePlayer.setBackground(null);
                spotFourPlayer.setText(null);
                spotFourPlayer.setBackground(null);
                spotFivePlayer.setText(null);
                spotFivePlayer.setBackground(null);
                spotOneCPU.setText(null);
                spotOneCPU.setBackground(null);
                spotTwoCPU.setText(null);
                spotTwoCPU.setBackground(null);
                spotThreeCPU.setText(null);
                spotThreeCPU.setBackground(null);
                spotFourCPU.setText(null);
                spotFourCPU.setBackground(null);
                spotFiveCPU.setText(null);
                spotFiveCPU.setBackground(null);
                infoLabel.setText("Change your bet.");
            }
            splitWinCondition();
            restartClick = 1;
        }
    }

    /**
     * onRaiseBetClick() - raise the bet coins if the user totalCoins is more than 10 and the betCoins is less than 50 per turn.
     * gets disabled after the HIT button is clicked one time.
     * enable the HIT button.
     */
    @FXML
    protected void onRaiseBetClick() {
        hitButton.setDisable(false);

        if(totalCoins > 0 && betCoins < 50) {
            totalCoins -= 10;
            betCoins += 10;

            infoLabel.setText("You raise, your bet \n* Click Raise Bet to add 10 coins in your bet.\n" +
                    "* Click Down Bet to remove 10 coins in your bet.\n" +
                    "* Click HIT one time to finish your bet.");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
        }
    }

    /**
     * onDownBetClick() - down the bet coins if the betCoins is more than 10.
     * gets disabled after the HIT button is clicked one time.
     * enable the HIT button.
     */
    @FXML
    protected void onDownBetClick() {
        hitButton.setDisable(false);

        if(betCoins > 10 ) {
            totalCoins += 10;
            betCoins -= 10;

            infoLabel.setText("You lower, your bet \n* Click Raise Bet to add 10 coins in your bet.\n" +
                    "* Click Down Bet to remove 10 coins in your bet.\n" +
                    "* Click HIT one time to finish your bet.");
            infoLabel.setAlignment(Pos.CENTER);
            coinsLabel.setText(Integer.toString(totalCoins));
            betLabel.setText(Integer.toString(betCoins));
            totalCoinsLabel.setText("Total Coins: " + totalCoins);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
        }
    }

    private void configureWinCondition() {
        standButton.setDisable(true);
        splitButton.setDisable(true);
        doubleButton.setDisable(true);
        restartClick++;
    }
}