import java.util.*;

/**
 * This is a class representation of a human player of this game.
 */
public class HumanPlayer implements Player {
    private final Input input;
    private final Collection<Square> properties;
    private final String playerName;
    private int money;
    private int position;

    /**
     * Initialize HumanPlayer.
     * @param playerName String
     */
    public HumanPlayer(String playerName) {
        this.input = new Input();
        this.properties = new LinkedList<>();
        this.playerName = playerName;
        this.money = 1500;
        this.position = 0;
    }

    /**
     * @param numTiles int
     * moves the player
     */
    @Override
    public void move(int numTiles) {
        this.position += numTiles;
        int BOARD_SIZE = 40;
        if (position >= BOARD_SIZE) {
            position -= BOARD_SIZE;
            exchangeMoney(200);
        }
    }

    /**
     * passing the go tile giving the player $200
     * @param newPosition
     */
    @Override
    public void moveTo(int newPosition) {
        if (newPosition < position) {
            exchangeMoney(200);
        }
        position = newPosition;
    }

    /**
     * get the posaition of the player
     * @return the position
     */
    @Override
    public int getPosition() {
        return this.position;
    }

    /**
     * Properties owned by the player.
     * @return  Collection<Square>
     */
    @Override
    public Collection<Square> properties() {
        return new LinkedList<>(properties);
    }

    /**
     * Get the name of the player.
     * @return  String
     */
    @Override
    public String name() {
        return this.playerName;
    }

    /**
     * Get the amount of money owned by a player.
     * @return  int
     */
    @Override
    public int getMoney() {
        return this.money;
    }

    /**
     * Method for player to collect addition money.
     * @param money int
     */
    @Override
    public void exchangeMoney(int money) {
        this.money += money;
    }

    /**
     * Method when a player buys a property.
     * @param square    Square
     */
    @Override
    public void addProperty(Square square) {
        if (!square.isOwnable()) {
            throw new IllegalArgumentException("This property can not be purchased");
        } else {
            properties.add(square);
            square.purchase(this);
        }
    }

    /**
     * sell property
     * @param square Square
     */
    @Override
    public void removeProperty(Square square) {
        properties.remove(square);
        money += square.cost();
    }

    /**
     * @param state Monopoly.GameState
     * @return boolean
     */
    @Override
    public boolean inputBool(Monopoly.GameState state) {
        return this.input.inputBool();
    }

    /**
     * @param state Monopoly.GameState
     * @return int
     */
    @Override
    public int inputInt(Monopoly.GameState state) {
        return this.input.inputInt();
    }

    /**
     * @param state   Monopoly.GameState
     * @param choices String[]
     * @return int
     */
    @Override
    public int inputDecision(Monopoly.GameState state, String[] choices) {
        return this.input.inputDecision(choices);
    }
}