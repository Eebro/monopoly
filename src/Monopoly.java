import java.util.*;

public class Monopoly {
    private RollDice rollDice;
    private GameState gameState;
    private boolean isBankrupt;

    public Monopoly() {
        this.rollDice = new RollDice();
        this.isBankrupt = false;
        this.gameState = new GameState();
        gameState.players = new LinkedList<>();
        gameState.decisionState = DecisionState.NONE;
        gameState.gameBoard = new GameBoard();
        gameState.currentPlayer = null;
        Input input = new Input();
        initialize(input);
    }

    /**
     * Different decision states during a player's turn.
     */
    public enum DecisionState {
        NONE, BUY_HOUSE, BUY_PROPERTY, SELL_PROPERTY, SELL_HOUSE, FUNDS, PAY_RENT, RECEIVE_RENT
    }

    /**
     * Track the game state.
     */
    public class GameState {
        public DecisionState decisionState;
        public Queue<Player> players;
        public GameBoard gameBoard;
        public Player currentPlayer;
    }

    /**
     * Play loop that requests for user inputs.
     */
    public void play() {
        while (gameState.players.size() > 1) {
            try {
                gameState.currentPlayer = gameState.players.remove();
                turn();
                if (!isBankrupt) {
                    gameState.players.add(gameState.currentPlayer);
                }
                isBankrupt = false;
            } catch (NoSuchElementException e) {
                System.err.println("Game failed to be initialized");
                return;
            } finally {
                printState();
            }
        }
        Player winner = gameState.players.remove();
        System.out.println("Monopoly winner: " + winner.name());
    }

    /**
     * Initializes game starting conditions.
     */
    public void initialize(Input input) {
        // Ask user for number of players participating
        System.out.print("How many players would like to play?");
        int numPlayers = input.inputInt();
        while (numPlayers < 2 || numPlayers > 8) {
            System.out.println("Try Again! You must have a min of 2 and max of 8 players: ");
            numPlayers = input.inputInt();
        }
        // Ask user to input name of players
        for (int i = 0; i< numPlayers; i++) {
            System.out.print("Player #" + i + ": Enter your character name: ");
            String playerName = input.inputString();
            HumanPlayer newPlayer = new HumanPlayer(playerName);
            gameState.players.add(newPlayer);

        }
        // Print all the player names
        System.out.println("\n There are: " + numPlayers + " players, with the usernames: ");
        for (Player p: gameState.players) {
            System.out.println(p.name());
        }
    }

    public void turn() {
        // Roll Dice
        // Move
        // handleSquare
        // addition
    }

    /**
     * Prints the state of the players name, current balance and their properties owned.
     */
    public void printState() {
        Player player = gameState.currentPlayer;

        System.out.println("Player name: " + player.name());
        System.out.println("Current balance: $" + player.getMoney());
        System.out.println("Current position: " + player.getPosition()); // Fix this to get square tile name position
        System.out.println("Properties owned: ");

        for (Square s: player.properties()){
            System.out.println(s.name());
        }
        // More additional information about houses, jail, etc., later
    }

    /**
     * Handle how a player interacts with the square tile they land upon.
     * @param player    Player
     * @param square    Square
     * @param roll      int
     */
    public void handleSquare(Player player, Square square, int roll) {
        boolean owned = square.isOwned();
        boolean ownable = square.isOwnable();

        if (!owned && ownable) {
            unowned(player, square);
        }
        else if (owned) {
            owned(player, square, roll);
        }
        else if (square instanceof Taxes) {
            //TODO
            // Deal with Tax squares
        }
        else if (square instanceof  Jail) {
            //TODO
            // Deal with Jail square
        }
    }

    private void unowned(Player player, Square square) {
        int cost = square.cost();
    }

    private void owned(Player player, Square square, int roll) {
        int rent = square.rent(roll);
        if (square instanceof Utility) {
            //TODO increase rent depends on roll value
        }
        else if (square instanceof Railroad) {
            //TODO check rules on railroad rent
            rent *= 2;
        }
        Player owner = square.owner();
        if (player.name().equals(owner.name())) { return; }

        boolean noMoney = false;
        System.out.println("You have landed on the " + square.name() + " and must pay" + rent + " in rent.");
        if (player.getMoney() < rent) {
            noMoney = true;
            System.out.println("You do not have sufficient funds for this payment");
        }
        if (!noMoney) {
            player.exchangeMoney(-1 * rent);
            owner.exchangeMoney(rent);
        } else {
            //TODO trade assets for money
        }
    }

    private void buyProperty(Player player, Square square) {
        if (player == null || square == null) return;
        if (!square.isOwnable()) return;
        player.addProperty(square);
        square.purchase(player);
    }

//    public void payRent(Square square){
//        Property property = (Property) square;
//        if (pla- property.rent(0) < 0){
//            System.out.println("cannot pay rent on this property, current balance: $" + money + ". Property rent: $" + property.rent(0));
//        }
//        else{
//            money -= property.rent(0);
//        }
//    }


    public static void main(String[] args) {
        Monopoly monopoly = new Monopoly();
        monopoly.play();
    }
}