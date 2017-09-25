/**
 * Created by gorram on 9/24/17.
 */
import java.util.Arrays;
import java.util.stream.IntStream;
public class YahtzeeScore {
    public int score;
    public boolean yahtzee;
    public boolean lstraight;
    public boolean house;
    public boolean three;

    /**
     * Creates a YahtzeeScore object with zero points and the spaces to score a yahtzee,
     * full house, large straight, and three of a kind empty.
     */
    public YahtzeeScore(){
        score=0;
        yahtzee=false;
        lstraight=false;
        house=false;
        three=false;
    }

    /**
     * Checks what kind of roll is being scored and passes the dice values on to the appropriate method
     * @param dice
     * @param type
     */
    public void roll(int[] dice, String type){
        if (type=="yahtzee"){
            yahtzee(dice);
        }
        else if (type=="straight"){
            straight(dice);
        }
        else if (type=="house"){
            house(dice);
        }
        else if (type=="three"){
            three(dice);
        }
    }

    /**
     * Scores the dice as three of a kind if the space is empty and if they meet the qualifications
     * (if they don't meet qualifications the space is scored as 0)
     * @param dice
     */
    public void three(int[] dice){
        boolean valid=true;
        if (!three){
            Arrays.sort(dice);
            if ((dice[0]!=dice[2])&&(dice[2]!=dice[4])&&(dice[1]!=dice[3])) {
                valid=false;
            }
            if (valid) {
                score = score + IntStream.of(dice).sum();
            }
            three = true;
        }
    }

    /**
     * Scores the dice as a full house if the space is empty and if they meet the qualifications
     * (if they don't meet qualifications the space is scored as 0)
     * @param dice
     */
    public void house(int[] dice){
        boolean valid=true;
        if (!house){
            Arrays.sort(dice);
            if ((dice[1]!=dice[0])||(dice[3]!=dice[4])||((dice[2]!=dice[1])&&(dice[2]!=dice[3]))) {
                valid=false;
            }
            if (valid) {
                score = score + 25;
            }
            house = true;
        }
    }

    /**
     * Scores the dice as a large straight if the space is empty and if they meet the qualifications
     * (if they don't meet qualifications the space is scored as 0)
     * @param dice
     */
    public void straight(int[] dice){
        boolean valid=true;
        if (!lstraight){
            Arrays.sort(dice);
            for (int i =1; i<5; i++) {
                if (dice[i-1]!=dice[i]-1){
                    valid=false;
                }
            }
            if (valid) {
                score = score + 40;
            }
            lstraight = true;
        }
    }

    /**
     * Scores the dice as a yahtzee if the space is empty and if they meet the qualifications
     * (if they don't meet qualifications the space is scored as 0)
     * @param dice
     */
    public void yahtzee(int[] dice){
        boolean valid=true;
        if (!yahtzee){
            for (int i =1; i<5; i++) {
                if (dice[0]!=dice[i]){
                    valid=false;
                }
            }
            if (valid) {
                score = score + 50;
            }
            yahtzee = true;
        }
    }

    /**
     * Returns the current point value of the scorer
     * @return score
     */
    public int points(){
        return score;
    }
}
