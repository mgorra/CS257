/**
 * @author Miriam Gorra
 * CS 257
 * [project] Test-driven developement
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class YahtzeeScoreTest {
    @Test
    public void main() {
        testYahtzee();
        testStraight();
        testHouse();
        testThree();
        testAll();
    }
    /**
     * Tests a YahtzeeScorer object on scoring rolls as yahtzees
     * Checks getting the score, scoring a yahtzee,
     * attempting to double-score the yahtzee space (doesn't change score),
     * and scoring a non-yahtzee in the yahtzee space (as 0)
     */
    public void testYahtzee(){
        int[] yahtzee = {5,5,5,5,5};
        int[] notyahtzee= {5,5,5,5,4};
        YahtzeeScore scorer=new YahtzeeScore();
        assertEquals(0, scorer.points());
        scorer.roll(yahtzee, "yahtzee");
        assertEquals(50, scorer.points());
        scorer.roll(yahtzee, "yahtzee");
        assertEquals(50, scorer.points()); //no change since the space was already filled
        scorer.yahtzee=false; //allows a second roll to be scored in the yahtzee space in order to test a non-yahtzee
        scorer.roll(notyahtzee, "yahtzee");
        assertEquals(50, scorer.points()); //no change since the score added was 0
    }
    /**
     * Tests a YahtzeeScorer object on scoring rolls as large straights
     * Checks scoring a large straight,
     * attempting to double-score the large straight space (doesn't change score),
     * and scoring a non-large straight in the large straight space (as 0)
     */
    public void testStraight(){
        int[] straight = {1,4,3,2,5};
        int[] notstraight= {1,4,3,2,4};
        YahtzeeScore scorer=new YahtzeeScore();
        scorer.roll(straight, "straight");
        assertEquals(40, scorer.points());
        scorer.roll(straight, "straight");
        assertEquals(40, scorer.points()); //no change since the space was already filled
        scorer.lstraight=false; //allows a second roll to be scored in the large straight space in order to test a non-large straight
        scorer.roll(notstraight, "straight");
        assertEquals(40, scorer.points()); //no change since the score added was 0
    }
    /**
     * Tests a YahtzeeScorer object on scoring rolls as full houses
     * Checks scoring a full house,
     * attempting to double-score the full house space (doesn't change score),
     * and scoring a non-full house in the full house space (as 0)
     */
    public void testHouse(){
        int[] house = {2,3,2,2,3};
        int[] nothouse= {2,2,2,3,2};
        int [] nothouse2={1,2,3,4,5};
        int [] nothouse3={2,2,3,6,6};
        YahtzeeScore scorer=new YahtzeeScore();
        scorer.roll(house, "house");
        assertEquals(25, scorer.points());
        scorer.roll(house, "house");
        assertEquals(25, scorer.points()); //no change since the space was already filled
        scorer.house=false; //allows a second roll to be scored in the full house space in order to test a non-full house
        scorer.roll(nothouse, "house"); //no change since the score added was 0
        assertEquals(25, scorer.points());
        scorer.house=false;
        scorer.roll(nothouse2, "house"); //tests additional hands to check the qualifications for being a full house
        assertEquals(25, scorer.points());
        scorer.house=false;
        scorer.roll(nothouse3, "house");
        assertEquals(25, scorer.points());
    }
    /**
     * Tests a YahtzeeScorer object on scoring rolls as three-of-a-kind
     * Checks scoring a three-of-a-kind,
     * attempting to double-score the three-of-kind space (doesn't change score),
     * and scoring a non-three-of-a-kind in the three-of-a-kind space (as 0)
     */
    public void testThree(){
        int[] three = {4,4,4,1,2};
        int[] notthree= {4,4,2,2,1};
        YahtzeeScore scorer=new YahtzeeScore();
        scorer.roll(three, "three");
        assertEquals(15, scorer.points());
        scorer.roll(three, "three");
        assertEquals(15, scorer.points()); //no change since the space was already filled
        scorer.three=false; //allows a second roll to be scored in the three-of-a-kind space in order to test a non-three-of-a-kind
        scorer.roll(notthree, "three");
        assertEquals(15, scorer.points()); //no change since the score added was 0
    }
    /**
     * Tests scoring all the different kinds of rolls on one scorer
     */
    public void testAll(){
        int[] yahtzee = {5,5,5,5,5};
        int[] straight = {1,4,3,2,5};
        int[] house = {2,3,2,2,3};
        int[] three = {4,4,4,1,2};
        YahtzeeScore scorer=new YahtzeeScore();
        scorer.roll(yahtzee, "yahtzee");
        scorer.roll(straight, "straight");
        scorer.roll(house, "house");
        scorer.roll(three, "three");
        assertEquals(130, scorer.points());
    }
}
