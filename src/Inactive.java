/**
 * This class represents an inactive square tile on the board game.
 */
public class Inactive implements Square {
    private final int position;
    private final String name;

    public Inactive(int position, String name) {
        this.position = position;
        this.name = name;
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
        return false;
    }

    @Override
    public boolean isOwned() {
        return false;
    }

    @Override
    public int cost() {
        return 0;
    }

    @Override
    public void purchase(Player player) {
    }

    @Override
    public int rent(int value) {
        return 0;
    }

    @Override
    public Player owner() {
        return null;
    }

    public int getRent() {
        return 0;
    }

    public int getPropertyCost() {
        return 0;
    }
}
