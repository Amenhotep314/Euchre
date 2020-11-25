
/**
 * <h1>Card</h1>
 * This class contains all attributes and values associated with each card in the deck
 *
 * @author Nathan Lenzini
 * @version 1.0
 * @since 2020-04-14
 */
public class Card
{
    /**
     * an integer representing the card's suite, from 0 to 3
     */
    private int suite;
    /**
     * an integer representing which particular card this is, from 0 to 5
     */
    private int card;
    /**
     * an integer representing the card's fixed power, barring the rules of the game. This value
     * doesn't change
     */
    private final int ogPower;
    /**
     * an integer representing the card's power, from 0 to 17
     */
    private int power;
    /**
     * a String which stores the name of the card
     */
    private final String name;
    private boolean inPlay;

    /**
     * the constructor for the Card class
     *
     * @param name the name of the card in the form of "<em>card</em> of <em>suite</em>"
     */
    public Card(String name)
    {
        inPlay = false;
        this.name = name;
        String cardStr, suiteStr;
        cardStr = this.name.substring(0, this.name.indexOf("of") - 1);
        suiteStr = this.name.substring(this.name.indexOf("of") + 3);

        if(cardStr.equals("nine"))
        {
            card = 0;
        }

        if(cardStr.equals("ten"))
        {
            card = 1;
        }

        if(cardStr.equals("jack"))
        {
            card = 2;
        }

        if(cardStr.equals("queen"))
        {
            card = 3;
        }

        if(cardStr.equals("king"))
        {
            card = 4;
        }

        if(cardStr.equals("ace"))
        {
            card = 5;
        }

        ogPower = card;
        power = ogPower;

        if(suiteStr.equals("clubs"))
        {
            suite = 0;
        }

        if(suiteStr.equals("diamonds"))
        {
            suite = 1;
        }

        if(suiteStr.equals("hearts"))
        {
            suite = 2;
        }

        if(suiteStr.equals("spades"))
        {
            suite = 3;
        }
    }

    /**
     * a method to set the power of the card back to its original level
     */
    public void resetPower()
    {
        power = ogPower;
    }

    /**
     * a method to increase the value of a card because it belongs to the suit which was led
     */
    public void suitIsLed()
    {
        power += 6;
    }

    /**
     * a method to increase the value of a card because it belongs to the suit which is trump
     */
    public void suitIsTrump()
    {
        power += 12;
    }

    public void setInPlay(boolean inPlay)
    {
        this.inPlay = inPlay;
    }

    public String getName()
    {
        return name;
    }
}