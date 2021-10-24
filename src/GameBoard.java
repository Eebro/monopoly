/**
 * This class represents the game board.
 */
public class GameBoard {
    private final int NUM_TILES = 41;
    private final Square[] board; // representation of the game board

    /**
     * Initialize GameBoard.
     */
    public GameBoard() {
        this.board = new Square[NUM_TILES];
        for (int i = 0; i < NUM_TILES; i++) {
            board[i] = makeSquare(i);
        }
        groupProperties();
        groupRailroads();
        groupUtilities();
    }

    /**
     * Get the size of the number of square tiles on the game board.
     * @return  int
     */
    public int size() {
        return this.NUM_TILES;
    }

    /**
     * Get the board.
     * @return  Square[], representation of the board game
     */
    public Square[] getBoard() {
        return this.board;
    }

    /**
     * Get board position from square position.
     * @param position  int, index of the postion
     * @return          Square
     */
    public Square square(int position) {
        return board[position];
    }

    /**
     * Downcast all square that is a property.
     * @param name  String, property name
     * @return      Property
     */
    private Property property(String name) {
        for (Square sq : board) {
            if (sq instanceof Property && sq.name().equals(name)) {
                return (Property) sq;
            }
        }
        return null;
    }

    /**
     * Create the square if the position is being occupied.
     * @param position  int, index of the position
     * @return          Square
     */
    private Square makeSquare(int position) {
        return switch (position) { // Using cases to represent individual squares on board. Clockwise.
            case 0 -> go(position);
            case 1 -> oldKent(position);
            case 2 -> community(position);
            case 3 -> whitechapel(position);
            case 4 -> incomeTax(position);
            case 5 -> kingsCross(position);
            case 6 -> theAngelIslington(position);
            case 7 -> chance(position);
            case 8 -> euston(position);
            case 9 -> pentonville(position);
            case 10 -> justVisiting(position);
            case 11 -> pallMall(position);
            case 12 -> electricCompany(position);
            case 13 -> whitehall(position);
            case 14 -> northumrld(position);
            case 15 -> marylebone(position);
            case 16 -> bow(position);
            case 17 -> community(position);
            case 18 -> marlborough(position);
            case 19 -> vine(position);
            case 20 -> freeParking(position);
            case 21 -> strand(position);
            case 22 -> chance(position);
            case 23 -> fleet(position);
            case 24 -> trafalgar(position);
            case 25 -> fenchurch(position);
            case 26 -> leicester(position);
            case 27 -> conventry(position);
            case 28 -> waterWorks(position);
            case 29 -> piccadilly(position);
            case 30 -> goToJail(position);
            case 31 -> regent(position);
            case 32 -> oxford(position);
            case 33 -> community(position);
            case 34 -> bond(position);
            case 35 -> liverpool(position);
            case 36 -> chance(position);
            case 37 -> park(position);
            case 38 -> superTax(position);
            case 39 -> mayfair(position);
            case 40 -> inJail(position);
            default -> null;
        };
    }

    /**
     * Group properties by color set.
     */
    private void groupProperties() {
        Property brownA = (Property) square(1); // Properties associated with cases. ie case 1.
        Property brownB = (Property) square(3);
        Property skyA = (Property) square(6);
        Property skyB = (Property) square(8);
        Property skyC = (Property) square(9);
        Property pinkA = (Property) square(11);
        Property pinkB = (Property) square(13);
        Property pinkC = (Property) square(14);
        Property orangeA = (Property) square(16);
        Property orangeB = (Property) square(18);
        Property orangeC = (Property) square(19);
        Property redA = (Property) square(21);
        Property redB = (Property) square(23);
        Property redC = (Property) square(24);
        Property yellowA = (Property) square(26);
        Property yellowB = (Property) square(27);
        Property yellowC = (Property) square(29);
        Property greenA = (Property) square(31);
        Property greenB = (Property) square(32);
        Property greenC = (Property) square(34);
        Property blueA = (Property) square(37);
        Property blueB = (Property) square(39);

        // BROWN property group
        brownA.setGroup(brownB);
        brownB.setGroup(brownA);

        // SKY-BLUE property group
        skyA.setGroup(skyB, skyC);
        skyB.setGroup(skyA, skyC);
        skyC.setGroup(skyA, skyB);

        // PINK property group
        pinkA.setGroup(pinkB, pinkC);
        pinkB.setGroup(pinkA, pinkC);
        pinkC.setGroup(pinkA, pinkB);

        // ORANGE property group
        orangeA.setGroup(orangeB, orangeC);
        orangeB.setGroup(orangeA, orangeC);
        orangeC.setGroup(orangeA, orangeB);

        // RED property group
        redA.setGroup(redB, redC);
        redB.setGroup(redA, redC);
        redC.setGroup(redA, redB);

        // YELLOW property group
        yellowA.setGroup(yellowB, yellowC);
        yellowB.setGroup(yellowA, yellowC);
        yellowC.setGroup(yellowA, yellowB);

        // GREEN property group
        greenA.setGroup(greenB, greenC);
        greenB.setGroup(greenA, greenC);
        greenC.setGroup(greenA, greenB);

        // BLUE property group
        blueA.setGroup(blueB);
        blueB.setGroup(blueA);
    }

    /**
     * Group railroads.
     */
    private void groupRailroads() {
        Railroad a = (Railroad) square(5);  // Railroad on square 5
        Railroad b = (Railroad) square(15); // Railroad on square 15
        Railroad c = (Railroad) square(25); // Railroad on square 25
        Railroad d = (Railroad) square(35); // Railroad on square 35

        a.setGroup(b, c, d);
        b.setGroup(a, c, d);
        c.setGroup(a, b, d);
        d.setGroup(a, b, c);
    }

    /**
     * Group utilities.
     */
    private void groupUtilities() {
        Utility a = (Utility) square(12); // Utilities on square 12
        Utility b = (Utility) square(28); // Utilities on square 28

        a.setGroup(b);
        b.setGroup(a);
    }

    /**
     * Setup "GO" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square go(int position) {
        return new Inactive(position, "GO");
    }

    /**
     * Setup "COMMUNITY CHEST" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square community(int position) {
        return new Inactive(position, "COMMUNITY CHEST");
    }

    /**
     * Setup "CHANCE" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square chance(int position) {
        return new Inactive(position, "CHANCE");
    }

    /**
     * Setup "INCOME TAX" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square incomeTax(int position) {
        return new Taxes(position, "INCOME TAX");
    }

    /**
     * Setup "SUPER TAX" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square superTax(int position) {
        return new Taxes(position,"SUPER TAX");
    }

    /**
     * Setup "ELECTRIC COMPANY" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square electricCompany(int position) {
        return new Utility(position, "ELECTRIC COMPANY");
    }

    /**
     * Setup "WATER WORKS" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square waterWorks(int position) {
        return new Utility(position, "WATER WORKS");
    }

    /**
     * Setup "KINGS CROSS STATION" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square kingsCross(int position) {
        return new Railroad(position, "KINGS CROSS STATION");
    }

    /**
     * Setup "MARYLEBONE STATION" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square marylebone(int position) {
        return new Railroad(position, "MARYLEBONE STATION");
    }

    /**
     * Setup "FENCHURCH ST. STATION" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square fenchurch(int position) {
        return new Railroad(position, "FENCHURCH ST. STATION");
    }

    /**
     * Setup "LIVERPOOL ST. STATION" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square liverpool(int position) {
        return new Railroad(position, "LIVERPOOL ST. STATION");
    }

    /**
     * Setup "FREE PARKING" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square freeParking(int position) {
        return new Inactive(position, "FREE PARKING");
    }

    /**
     * Setup "GO TO JAIL" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square goToJail(int position) {
        return new Jail(position, "GO TO JAIL", Jail.JailType.GOTO_JAIL);
    }

    /**
     * Setup "IN JAIL" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square inJail(int position) {
        return new Jail(position, "IN JAIL", Jail.JailType.IN_JAIL);
    }

    /**
     * Setup "JUST VISITING" square tile on the board.
     * @param position  int
     * @return  Square
     */
    public Square justVisiting(int position) {
        return new Jail(position, "JUST VISITING", Jail.JailType.JUST_VISITING);
    }

    /**
     * oldKent Property
     * @param position - int
     * @return Property(position, " Old Kent Road " rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square oldKent(int position) {
        int rent = 2;
        int oneHouse = 10;
        int twoHouse = 30;
        int threeHouse = 90;
        int fourHouse = 160;
        int hotel = 250;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, "Old Kent Road",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * White Chapel Property
     * @param position - int
     * @return Property(position, " Whitechapel Road ",rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square whitechapel(int position) {
        int rent = 4;
        int oneHouse = 20;
        int twoHouse = 60;
        int threeHouse = 180;
        int fourHouse = 320;
        int hotel = 450;
        int propertyCost = 60;
        int houses = 50;
        return new Property(position, "Whitechapel Road",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * The Angel Islington Property
     * @param position - int
     * @return Property(position, " The Angel, Islington ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square theAngelIslington(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, "The Angel, Islington",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Euston Property
     * @param position - int
     * @return Property(position, " Euston Road ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square euston(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 100;
        int houses = 50;
        return new Property(position, "Euston Road",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Penton Ville Property
     * @param position - int
     * @return Property(position, " Pentonville Road ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square pentonville(int position) {
        int rent = 6;
        int oneHouse = 30;
        int twoHouse = 90;
        int threeHouse = 270;
        int fourHouse = 400;
        int hotel = 550;
        int propertyCost = 120;
        int houses = 50;
        return new Property(position, "Pentonville Road",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Pall Mall Property
     * @param position - int
     * @return Property(position, " Pall Mall ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square pallMall(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, "Pall Mall",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * White Hall Property
     * @param position - int
     * @return Property(position, " Whitehall ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square whitehall(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 140;
        int houses = 100;
        return new Property(position, "Whitehall",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Northumrld Property
     * @param position - int
     * @return Property(position, " Northumrl ' d Avenue ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square northumrld(int position) {
        int rent = 10;
        int oneHouse = 50;
        int twoHouse = 150;
        int threeHouse = 450;
        int fourHouse = 625;
        int hotel = 750;
        int propertyCost = 160;
        int houses = 100;
        return new Property(position, "Northumrl'd Avenue",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Bow Property
     * @param position - int
     * @return Property(position, " Bow Street ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square bow(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 750;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, "Bow Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Marborough Property
     * @param position - int
     * @return Property(position, " Marlborough Street ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square marlborough(int position) {
        int rent = 14;
        int oneHouse = 70;
        int twoHouse = 200;
        int threeHouse = 550;
        int fourHouse = 750;
        int hotel = 950;
        int propertyCost = 180;
        int houses = 100;
        return new Property(position, "Marlborough Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Vine Property
     * @param position - int
     * @return Property(position, " Vine Street ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square vine(int position) {
        int rent = 16;
        int oneHouse = 80;
        int twoHouse = 220;
        int threeHouse = 600;
        int fourHouse = 800;
        int hotel = 1000;
        int propertyCost = 200;
        int houses = 100;
        return new Property(position, "Vine Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Strand Property
     * @param position - int
     * @return Property(position, " Strand ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square strand(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, "Strand",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Fleet Property
     * @param position - int
     * @return Property(position, " Fleet Street ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square fleet(int position) {
        int rent = 18;
        int oneHouse = 90;
        int twoHouse = 250;
        int threeHouse = 700;
        int fourHouse = 875;
        int hotel = 1050;
        int propertyCost = 220;
        int houses = 150;
        return new Property(position, "Fleet Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Trafalgar Property
     * @param position - int
     * @return Property(position, " Trafalgar Square ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square trafalgar(int position) {
        int rent = 20;
        int oneHouse = 100;
        int twoHouse = 300;
        int threeHouse = 750;
        int fourHouse = 925;
        int hotel = 1100;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, "Trafalgar Square",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Leicaster Property
     * @param position - int
     * @return Property(position, " Leicester ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square leicester(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 260;
        int houses = 150;
        return new Property(position, "Leicester",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Coventry Property
     * @param position - int
     * @return Property(position, " Conventry Street ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square conventry(int position) {
        int rent = 22;
        int oneHouse = 110;
        int twoHouse = 330;
        int threeHouse = 800;
        int fourHouse = 975;
        int hotel = 1150;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, "Conventry Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Piccadilly Property
     * @param position - int
     * @return Property(position, " Piccadilly ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square piccadilly(int position) {
        int rent = 24;
        int oneHouse = 120;
        int twoHouse = 360;
        int threeHouse = 850;
        int fourHouse = 1025;
        int hotel = 1200;
        int propertyCost = 280;
        int houses = 150;
        return new Property(position, "Piccadilly",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Regent Property
     * @param position - int
     * @return Property(position, " Regent Street ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square regent(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1250;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, "Regent Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Oxford Property
     * @param position - int
     * @return Property(position, " Oxford Street ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square oxford(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 300;
        int houses = 200;
        return new Property(position, "Oxford Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Bond Property
     * @param position - int
     * @return Property(position, " Bond Street ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square bond(int position) {
        int rent = 26;
        int oneHouse = 130;
        int twoHouse = 390;
        int threeHouse = 900;
        int fourHouse = 1100;
        int hotel = 1275;
        int propertyCost = 320;
        int houses = 200;
        return new Property(position, "Bond Street",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Park Property
     * @param position - int
     * @return Property(position, " Park Lane ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square park(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1500;
        int hotel = 1500;
        int propertyCost = 350;
        int houses = 200;
        return new Property(position, "Park Lane",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }

    /**
     * Mayfair Property
     * @param position - int
     * @return Property(position, " Mayfair ", rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses)
     */
    public Square mayfair(int position) {
        int rent = 35;
        int oneHouse = 175;
        int twoHouse = 500;
        int threeHouse = 1100;
        int fourHouse = 1300;
        int hotel = 1500;
        int propertyCost = 400;
        int houses = 200;
        return new Property(position, "Mayfair",
                rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);
    }
}
