public class Taxes implements Square {
    private final int position;
    private final String name;
    private final int tax;

    public Taxes(int position, String name) {
        this.position = position;
        this.name = name;
        if (this.name.equals("Income Tax")) {
            this.tax = 200;
        } else {
            this.tax = 100;
        }
    }

    public int tax() {
        return this.tax;
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
    public void purchase(HumanPlayer player) {

    }

    @Override
    public int rent(int value) {
        return 0;
    }

    @Override
    public HumanPlayer owner() {
        return null;
    }
}