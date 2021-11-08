import java.util.Collection;

import static org.junit.Assert.*;

public class HumanPlayerTest {
    private final HumanPlayer humanPlayer = new HumanPlayer("tester");
    private String playerName;
    private int money;
    private int position;
    int rent = 2;
    int oneHouse = 10;
    int twoHouse = 30;
    int threeHouse = 90;
    int fourHouse = 160;
    int hotel = 250;
    int propertyCost = 60;
    int houses = 50;
    private final Square oldkent = new Property(position, "OLD KENT ROAD",
                            rent, oneHouse, twoHouse, threeHouse, fourHouse, hotel, propertyCost, houses);

    int position2;
    private final Square whitechapel = new Property(position2, "WHITECHAPEL ROAD",
            4, 20, 60, 180, 320, 450, 60, 50);


    /**
     * tests the player name is returned correctly
     */
    @org.junit.Test
    public void name() {
        assertEquals("tester", humanPlayer.name());
    }


    /**
     * tests moving the player a specified number of tiles
     */
    @org.junit.Test
    public void move() {
        position = 0;
        humanPlayer.setMoney(1500);
        humanPlayer.move(5);
        assertEquals(5, humanPlayer.getPosition() );

        //passed go, collect $200
        //do one lap around the board
        humanPlayer.move(43);
        assertEquals(8, humanPlayer.getPosition());
        assertEquals(1700, humanPlayer.getMoney());

        //did not pass go, do not collect $200
        humanPlayer.setMoney(1500);
        humanPlayer.move(4);
        assertEquals(12, humanPlayer.getPosition());
        assertEquals(1500, humanPlayer.getMoney());
    }



    /**
     * tests moving the player to a specified tile
     */
    @org.junit.Test
    public void moveTo() {
        position = 0;
        humanPlayer.setMoney(1500);
        humanPlayer.moveTo(5);
        assertEquals(5, humanPlayer.getPosition() );

        //passed go, collect $200
        humanPlayer.moveTo(2);
        assertEquals(2, humanPlayer.getPosition());
        assertEquals(1700, humanPlayer.getMoney());


        //did not pass go, do not collect $200
        humanPlayer.setMoney(1500);
        humanPlayer.moveTo(4);
        assertEquals(4, humanPlayer.getPosition());
        assertEquals(1500, humanPlayer.getMoney());
    }


    /**
     * tests exchanging money for paying rent, purchasing properties
     */
    @org.junit.Test
    public void exchangeMoney(){
        humanPlayer.setMoney(1500);
        humanPlayer.exchangeMoney(100);
        assertEquals(1600, humanPlayer.getMoney());

        humanPlayer.setMoney(1500);
        humanPlayer.exchangeMoney(0);
        assertEquals(1500, humanPlayer.getMoney());

        //remove money (pay rent)
        humanPlayer.setMoney(1500);
        humanPlayer.exchangeMoney(-100);
        assertEquals(1400, humanPlayer.getMoney());

    }


    /**
     * tests adding properties
     */
    @org.junit.Test
    public void addProperty() {
        //add a property
        humanPlayer.addProperty(oldkent);
        assertEquals(oldkent, humanPlayer.properties().toArray()[0]);

        //add a property that is not ownable
        humanPlayer.removeProperty(oldkent);
        oldkent.isOwned();
        humanPlayer.addProperty(oldkent);
        assertEquals(oldkent, humanPlayer.properties().toArray()[0]);

        //add a property that is ownable
        humanPlayer.removeProperty(oldkent);
        oldkent.isOwnable();
        humanPlayer.addProperty(oldkent);
        assertEquals(oldkent, humanPlayer.properties().toArray()[0]);


    }


    /**
     * tests removing properties
     */
    @org.junit.Test
    public void removeProperty() {
        //remove a property
        humanPlayer.setMoney(1500);
        humanPlayer.addProperty(oldkent);
        humanPlayer.exchangeMoney(-60);
        humanPlayer.removeProperty(oldkent);
        assertEquals(0,  humanPlayer.properties().toArray().length);

        //test that they get the money back when selling
        humanPlayer.setMoney(1500);
        humanPlayer.addProperty(oldkent);
        humanPlayer.exchangeMoney(-60);
        humanPlayer.removeProperty(oldkent);
        assertEquals(1500,  humanPlayer.getMoney());



    }


}