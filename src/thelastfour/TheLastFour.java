
package thelastfour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
*
* @author Saravanakumar D
* 
**/

public class TheLastFour {
    public static final String KIRAT_BOLI   = "Kirat Boli  ";
    public static final String NS_NODHI     = "N.S Nodhi   ";
    public static final String R_RUMRAH     = "R Rumrah    ";
    public static final String SHASHI_HENRA = "Sashi Henra ";
    
    static int kiratBallsFaced  = 0;
    static int nodhiBallsFaced  = 0;
    static int rumrahBallsFaced = 0;
    static int henraBallsFaced  = 0;
    static int totalRun         = 0;
    static int wktsLeft 		= 4;
    static int oversLeft		= 4;
	static int ballCounter 		= 0;
	static int kiratScore  		= 0;
	static int nodhiScore  		= 0;
	static int rumrahScore		= 0;
	static int henraScore  		= 0;
	static int overCounter		= 0;
    
    public static void main(String[] args) {
    	List <String> playersList = new LinkedList<String>();
    	List <String> commentary  = new ArrayList<String> ();
    	playersList.add(KIRAT_BOLI);
    	playersList.add(NS_NODHI);
    	playersList.add(R_RUMRAH);
    	playersList.add(SHASHI_HENRA);
    	int ballsLeft   = 24;

		for (int balls = ballsLeft; balls > 0; balls--) {
			List<Integer> results = new Vector<Integer>();
			Randomizer.random(playersList.get(0), results);
			int run = TheLastFour.getRun(results, playersList.get(0)); // Always.. batsman in index 0 is considered as on strike
			ballCounter++;
			
			if (run == -1) {  // -1 --> Wicket falls
				commentary.add((overCounter + "." + ballCounter + " " + playersList.get(0) + " Got out "));
				incrementBallCounter(playersList.get(0));
				playersList.remove(0);
				String newBatsman = playersList.get(1); 
				playersList.add(0, newBatsman);
				wktsLeft--;
				if(ballCounter == 6)
						doOverRoutine(playersList, commentary);				
				if (wktsLeft <= 0) {
					System.out.println("Lengaburu lost by " + (40 - totalRun) + " runs. They scored " + totalRun +" Only");
					break;
				}
				continue;
			}	
			
			commentary.add((overCounter + "." + ballCounter + " " + playersList.get(0) + " scores " + run));
			
			switch (playersList.get(0)) {
			case KIRAT_BOLI: {
				kiratScore += run;
				incrementBallCounter(KIRAT_BOLI);
			}
			break;
			case NS_NODHI: {
				nodhiScore += run;
				incrementBallCounter(NS_NODHI);
			}
			break;
			case R_RUMRAH: {
				rumrahScore += run;
				incrementBallCounter(R_RUMRAH);
			}
			break;
			case SHASHI_HENRA: {
				henraScore += run;
				incrementBallCounter(SHASHI_HENRA);
			}
			break;
			}
			if (run == 1 || run == 3 || run == 5) {
				batsmenSwitchOver(playersList);
			}
			
			totalRun = kiratScore + nodhiScore + henraScore + rumrahScore;		
			if(ballCounter == 6)
				doOverRoutine(playersList, commentary);
			
			
			if(totalRun >= 40) {
				System.out.println("Lengaburu won by " + wktsLeft + " wicket and "+ balls + " balls remaining");
				displaySummary();
				System.out.println("");
				displayCommentary(commentary);
				System.out.println("");
				break;
			}
			else if( balls == 1 && totalRun <= 40) {
				System.out.println("Lengaburu lost by " + (40 - totalRun) + " runs. They scored " + totalRun +" Only");
				displaySummary();
				System.out.println("");
				displayCommentary(commentary);
				System.out.println("");
				}
		}
    }
    
    /* This function displays the summary of the match */
    
    static void displaySummary() {
    	System.out.println(KIRAT_BOLI   + " - " + kiratScore  + "(" + kiratBallsFaced  + ")");
    	System.out.println(NS_NODHI     + " - " + nodhiScore  + "(" + nodhiBallsFaced  + ")");
    	System.out.println(R_RUMRAH     + " - " + rumrahScore + "(" + rumrahBallsFaced + ")");
    	System.out.println(SHASHI_HENRA + " - " + henraScore  + "(" + henraBallsFaced  + ")");
    }
    
    /* This function displays the commentary of last 4 overs */
    
    static void displayCommentary(List<String> commentary) {
    	System.out.println("Commentary...");
    	for(String comLine : commentary){
    		System.out.println(comLine);
    	}    	
    }
    
    /* This counts balls faced by each batsmen  */
    
    static void incrementBallCounter(String player){
    	switch(player){
    	case KIRAT_BOLI: kiratBallsFaced++;
    	break;
    	case NS_NODHI: nodhiBallsFaced++;
    	break;
    	case R_RUMRAH: rumrahBallsFaced++;
    	break;
    	case SHASHI_HENRA: henraBallsFaced++;
    	break;
    	}
    }
    
    /* This function will be called at the end of every over. */
    
    static void doOverRoutine(List<String> playersList, List<String> commentary ){
    	if(ballCounter == 6){
			overCounter++;
			batsmenSwitchOver(playersList);		
			ballCounter = 0;
			oversLeft--;
			commentary.add((oversLeft + " Overs left." + ( 40 - totalRun ) + " runs to win"));
		}
    }
    
    /* This function will switch the batsman at the crease */
    
    static List<String> batsmenSwitchOver(List<String> pList){
    	String zero = pList.get(0);
    	String one  = pList.get(1);
    	pList.remove(0);
    	pList.remove(0);
		pList.add(0, one);
		pList.add(1, zero);
    	return pList;
    }
    
    /* This function will return run scored for each ball based on the probability of the player */
    static int getRun(List <Integer> results, String player) {
        int index = new Random().nextInt(100);
        int sum = 0;
        int i=0;
        while(sum < index ) {
             sum = sum + results.get(i++);
        }
        int selected = results.get(Math.max(0,i-1));
        return run(player, selected);
    }

    /* This function will return run scored for each ball based on the probability of the player */
    
	static int run(String player, int selected) {
		int defaultVal = 0;
		switch (player) {
		case KIRAT_BOLI: {
			if (selected == 5) {	// Deciding whether Dot ball or Out(-1) since both have same probability
				int i = new Random().nextInt(2); 
				if (i == 0)
					return 0;
				return -1;
			} else if (selected == 30)
				return 1;
			else if (selected == 25)
				return 2;
			else if (selected == 10)
				return 3;
			else if (selected == 15)
				return 4;
			else if (selected == 1)
				return 5;
			else if (selected == 9)
				return 6;
		}
		break;

		case NS_NODHI: {
			if (selected == 10) {		// Deciding whether Four runs or Dot ball or Out(-1) since all have same probability
				int i = new Random().nextInt(3);
				if (i == 0)
					return 0;
				else if (i == 1)
					return 4;
				else
					return -1;
			} else if (selected == 40)
				return 1;
			else if (selected == 20)
				return 2;
			else if (selected == 5)
				return 3;
			else if (selected == 1)
				return 5;
			else if (selected == 9)
				return 6;
		}
		break;

		case R_RUMRAH: {
			if (selected == 20)
				return 0;
			else if (selected == 30)
				return 1;
			else if (selected == 15)
				return 2;
			else if (selected == 5) {	// Deciding whether Four runs or Three runs since both have same probability
				int i = new Random().nextInt(2);
				if (i == 0)
					return 3;
				return 4;
			} else if (selected == 1)
				return 5;
			else if (selected == 4)
				return 6;
			else if (selected == 20)
				return -1;
		}
		break;

		case SHASHI_HENRA: {
			if (selected == 30)
				return 0;
			else if (selected == 25)
				return 1;
			else if (selected == 5) { 	// Deciding whether Four runs or 2 Runs since both have same probability
				int i = new Random().nextInt(2);
				if (i == 0)
					return 2;
				return 4;
			} else if (selected == 0)
				return 3;
			else if (selected == 1)
				return 5;
			else if (selected == 4)
				return 6;
			else if (selected == 30)
				return -1;
		}
		break;
		}
		return defaultVal;
	}
}
	
	
