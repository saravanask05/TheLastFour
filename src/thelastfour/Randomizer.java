
package thelastfour;

import java.util.List;
import static thelastfour.TheLastFour.*;

/**
 *
 * @author Saravanakumar D
 * 
**/

public class Randomizer {
	
	public static void random(String player, List <Integer> results ) {
    	switch (player){
    	case KIRAT_BOLI:{
    		results.add(5);
    		results.add(30);
    		results.add(25);
    		results.add(10);
    		results.add(15);
    		results.add(1);
    		results.add(9);
    		results.add(5);
    		}
    	break;
    	
    	case NS_NODHI: {
    		results.add(10);
    		results.add(40);
    		results.add(20);
    		results.add(5);
    		results.add(10);
    		results.add(1);
    		results.add(4);
    		results.add(10);
    	}
    	break;
    	
    	case R_RUMRAH: {
    		results.add(20);
    		results.add(30);
    		results.add(15);
    		results.add(5);
    		results.add(5);
    		results.add(1);
    		results.add(4);
    		results.add(20);
    	}
    	break;
    	
    	case SHASHI_HENRA: {
    		results.add(30);
    		results.add(25);
    		results.add(5);
    		results.add(0);
    		results.add(5);
    		results.add(1);
    		results.add(4);
    		results.add(30);
    	}
    	break;
    	
    	}
    }
}
