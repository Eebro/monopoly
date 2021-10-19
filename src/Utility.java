public class Utility implements Square {
    private final int COST = 150;
    private final String name;
    private final int position;
    private Utility other;
    private int numOwned;
    private HumanPlayer owner;
    private boolean owned;
    private final RollDice dice;

    public Utility(int position, String name) {
        this.position = position;
        this.name = name;
        this.dice = new RollDice();
    }

    public void setOther(Utility other) {
        this.other = other;
    }

    @Override
    public int position() {
        return this.position;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isOwnable() {
        return true;
    }

    @Override
    public boolean isOwned() {
        return owned;
    }

    @Override
    public int cost() {
        return this.COST;
    }

    @Override
    public void purchase(HumanPlayer player) {
        owned = true;
        owner = player;
        numOwned = 1;
        for (Square sq : player.properties()) {
            if (sq instanceof Utility) {
                numOwned++;
            }
        }
    }

    @Override
    public int rent(int roll) {
        if (roll == 0) {
            roll = dice.rollDice().value;
        }
        int ONE = 4;
        int TWO = 10;

        if (owner.equals(other.owner())) {
            return TWO * roll;
        } else {
            return ONE * roll;
        }
    }

    @Override
    public HumanPlayer owner() {
        return owner;
    }
}