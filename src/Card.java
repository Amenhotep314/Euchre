
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
    private final int suite;
    /**
     * an integer representing which particular card this is, from 0 to 5
     */
    private final int card;
    /**
     * an integer representing the card's power, from 0 to 17
     */
    private int power;
    /**
     * a String which stores the name of the card
     */
    private final String name;
    /**
     * a boolean which is true if this card has been thrown this trick
     */
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

        switch(cardStr)
        {
            case "nine" -> card = 0;
            case "ten" -> card = 1;
            case "jack" -> card = 2;
            case "queen" -> card = 3;
            case "king" -> card = 4;
            default -> card = 5;
        }

        power = card;

        switch(suiteStr)
        {
            case "clubs" -> suite = 0;
            case "diamonds" -> suite = 1;
            case "hearts" -> suite = 2;
            default -> suite = 3;
        }
    }

    /**
     * a method to set the power of the card back to its original level
     */
    public void resetPower()
    {
        power = card;
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