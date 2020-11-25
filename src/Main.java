import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * <h1>Euchre</h1>
 * This program is designed to direct all the decisions made by a player in a game of Euchre. While
 * it is the responsibility of the player to physically play the cards, the program will do all of
 * the decision-making.
 *
 * @author Nathan Lenzini
 * @version 1.0
 * @since 2020-04-14
 */
public class Main
{
    /**
     * a two-dimensional array to hold instances of the Card class
     */
    public static Card[][] deck;
    /**
     * an array to contain the player's hand
     */
    public static Card[] hand = new Card[5];
    /**
     * an integer representing the player who is the dealer, from 1 to 4
     */
    public static int dealer;
    /**
     * an integer which represents the current player, from 1 to 4, assigned to the player after the dealer at the beginning of each round
     */
    public static int player;
    /**
     * The first-third player team's overall score, which must reach 9 in order for them to win
     */
    public static int oneThreeScore = 0;
    /**
     * The second-fourth player team's overall score, which must reach 9 in order for them to win
     */
    public static int twoFourScore = 0;
    /**
     * The first-third player team's score for the round, which is reset every 5 tricks
     */
    public static int oneThreeRoundScore = 0;
    /**
     * The second-fourth player team's score for the round, which is reset every 5 tricks
     */
    public static int twoFourRoundScore = 0;
    /**
     * The first-third player team's score for the trick, the sum of the powers of their cards thrown
     */
    public static int oneThreeTrickScore = 0;
    /**
     * The second-fourth player team's score for the trick, the sum of the powers of their cards thrown
     */
    public static int twoFourTrickScore = 0;

    /**
     * the main method
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args)
    {
        log("Compiled");
        deck = buildCards();
        //welcome();
        game();
    }

    /**
     * a method with the names of all cards in a Euchre deck
     *
     * @return a two-dimensional array containing instances of the class Card
     */
    public static Card[][] buildCards()
    {
        Card[][] cards = new Card[4][6];
        cards[0][0] = new Card("nine of clubs");
        cards[0][1] = new Card("ten of clubs");
        cards[0][2] = new Card("jack of clubs");
        cards[0][3] = new Card("queen of clubs");
        cards[0][4] = new Card("king of clubs");
        cards[0][5] = new Card("ace of clubs");
        cards[1][0] = new Card("nine of diamonds");
        cards[1][1] = new Card("ten of diamonds");
        cards[1][2] = new Card("jack of diamonds");
        cards[1][3] = new Card("queen of diamonds");
        cards[1][4] = new Card("king of diamonds");
        cards[1][5] = new Card("ace of diamonds");
        cards[2][0] = new Card("nine of hearts");
        cards[2][1] = new Card("ten of hearts");
        cards[2][2] = new Card("jack of hearts");
        cards[2][3] = new Card("queen of hearts");
        cards[2][4] = new Card("king of hearts");
        cards[2][5] = new Card("ace of hearts");
        cards[3][0] = new Card("nine of spades");
        cards[3][1] = new Card("ten of spades");
        cards[3][2] = new Card("jack of spades");
        cards[3][3] = new Card("queen of spades");
        cards[3][4] = new Card("king of spades");
        cards[3][5] = new Card("ace of spades");
        log("Deck successfully built");
        return cards;
    }

    /**
     * a method to display the welcome text to the user
     */
    public static void welcome()
    {
        System.out.println("\nWelcome to EUCHRE by Nathan Lenzini, an intelligent system designed to understand and play the game");
        System.out.println("of Euchre.\n");
        delay(4000);
        System.out.println("While using this system, you will not be responsible for any decisions made during the game. You");
        System.out.println("will be prompted to enter the cards in your hand, the cards being thrown, and other simple pieces of");
        System.out.println("information such as the suit which is called. To make entering cards fast and easy, each card will");
        System.out.println("be represented by two letters with no capitalization or spacing. First will be the letter");
        System.out.println("representing the suit (c for clubs, d for diamonds, etc...). Next will come the letter representing");
        System.out.println("the card (n for nine, q for queen, etc...). So, the king of spades would be notated ks.\n");
        delay(18000);
        System.out.println("Similarly, when asked to enter a suit, simply use the appropriate abbreviation.\n");
        delay(2000);
        System.out.println("For ease of use, this system will consider every player numbered. I am number one, my partner is");
        System.out.println("number three, the player to your left is number two, and the player to your right is number four.\n");
        delay(6000);
        log("Welcome messages displayed");
    }

    public static void game()
    {
        dealer = getPlayer("Which player will deal first?");

        while(oneThreeScore < 9 && twoFourScore < 9)
        {
            log("Player " + dealer + " deals");

            for(int i = 0; i < hand.length; i += 1)
            {
                hand[i] = getCard("What is the " + (i + 1) + " card in your hand?");
                log(hand[i].getName() + " is in hand");
            }

        }
    }

    /**
     * a method to get input from the user regarding a particular player
     *
     * @param prompt the text explaining the input that the program needs
     * @return an integer representing a player, from 1 to 4
     */
    public static int getPlayer(String prompt)
    {
        Scanner input = new Scanner(System.in);
        boolean goodInput = false;
        String returnValue = "";

        do
        {
            System.out.println(prompt + " (Enter a number from 1 to 4):");
            returnValue = input.nextLine();

            if(returnValue.equals("1") || returnValue.equals("2") || returnValue.equals("3") || returnValue.equals("4"))
            {
                goodInput = true;
            }

            else
            {
                System.out.println("The input you have provided does not match the query.");
            }
        }
        while(!goodInput);

        return Integer.parseInt(returnValue);
    }

    /**
     * a method to take user input specifying a card in the Euchre deck
     * @param prompt the string of text to show the user describing the desired input
     * @return a Card object from the array Deck
     */
    public static Card getCard(String prompt)
    {
        Scanner input = new Scanner(System.in);
        boolean goodInput = false;
        String returnValue;
        int returnSuit = 0;
        int returnCard = 0;

        do
        {
            System.out.println(prompt + " (Enter a card in the two-character format):");
            returnValue = input.nextLine();

            if((returnValue.length() == 2) && (returnValue.charAt(0) == 'n' || returnValue.charAt(0) == 't' || returnValue.charAt(0) == 'j' || returnValue.charAt(0) == 'q' || returnValue.charAt(0) == 'k' || returnValue.charAt(0) == 'a') && (returnValue.charAt(1) == 'c' || returnValue.charAt(1) == 'd' || returnValue.charAt(1) == 'h' || returnValue.charAt(1) == 's'))
            {
                goodInput = true;
            }

            else
            {
                System.out.println("The input you have provided does not match the query.");
            }
        }
        while(!goodInput);

        if(returnValue.charAt(1) == 'd')
        {
            returnSuit = 1;
        }

        if(returnValue.charAt(1) == 'h')
        {
            returnSuit = 2;
        }

        if(returnValue.charAt(1) == 's')
        {
            returnSuit = 3;
        }

        if(returnValue.charAt(0) == 't')
        {
            returnCard = 1;
        }

        if(returnValue.charAt(0) == 'j')
        {
            returnCard = 2;
        }

        if(returnValue.charAt(0) == 'q')
        {
            returnCard = 3;
        }

        if(returnValue.charAt(0) == 'k')
        {
            returnCard = 4;
        }

        if(returnValue.charAt(0) == 'a')
        {
            returnCard = 5;
        }

        return deck[returnSuit][returnCard];
    }

    /**
     * a method to write lines of text to a log file for later analysis and debugging
     *
     * @param text the text to be written to a log file
     */
    public static void log(String text)
    {
        try
        {
            FileWriter writer = new FileWriter("log.txt", true);
            SimpleDateFormat formatter = new SimpleDateFormat("<yyyy-MM-dd HH:mm:ss> ");
            Date date = new Date();
            writer.write(formatter.format(date) + text + "\n");
            writer.close();
        }

        catch(IOException e)
        {
            System.out.println("Warning: Logging failed");
        }
    }

    /**
     * A method to contain the exception catching necessary to cause a pause in the program
     *
     * @param milliseconds the time to wait, in milliseconds
     */
    public static void delay(long milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }

        catch(InterruptedException ignored)
        {
        }
    }
}