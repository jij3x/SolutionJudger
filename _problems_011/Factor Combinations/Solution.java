//Actually for a different question setting
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> factors = new ArrayList<List<Integer>>();
        if (n < 4) {
        	return factors;
        }
        
        Hashtable<Integer, List<List<Integer>>> h = new Hashtable<Integer, List<List<Integer>>>();

    	List<Integer> possibleTwo = new ArrayList<Integer>();
    	possibleTwo.add(2);
    	List<List<Integer>> factorsTwo = new ArrayList<List<Integer>>();
    	factorsTwo.add(possibleTwo);
        h.put(2, factorsTwo);
        
        List<Integer> possibleThree = new ArrayList<Integer>();
        possibleThree.add(3);
        List<List<Integer>> factorsThree = new ArrayList<List<Integer>>();
        factorsThree.add(possibleThree);
        h.put(3, factorsThree);
        
        for (int i = 2; i <= n; i++) {
        	if (n % i == 0 && (!h.contains(i))) {
        		h.put(i, findVal(h, i));
        	}
        }
        
        factors = h.get(n);
        factors.remove(factors.size() - 1);
        
        return h.get(n);
    }
    
    private List<List<Integer>> findVal(Hashtable<Integer, List<List<Integer>>> h, int n) {
    	List<List<Integer>> factors = new ArrayList<List<Integer>>();
    	
    	for (int i = 2; i <= Math.sqrt(n); i++) {
    		if (n % i == 0) {
    			int num = n / i;
    			
    			List<List<Integer>> rest = new ArrayList<List<Integer>>();
    			if (h.containsKey(num)) {
    				rest = h.get(num);
    			}
    			else {
    				rest = findVal(h, num);
    			}
    			if (rest.size() != 0) {
	    			for (int j = 0; j < rest.size(); j++) {
	    				List<Integer> comboRest = new ArrayList<Integer>();
	    				comboRest = rest.get(j);
	    				List<Integer> combo = new ArrayList<Integer>();
    					if (comboRest.get(0) >= i) {
		    				combo.add(i);
		    				combo.addAll(comboRest);
    					}
	    				
	    				if (combo.size() != 0) {
		    				factors.add(combo);
	    				}
	    			}
    			}
    		}
    	}
    	List<Integer> first = new ArrayList<Integer>();
    	first.add(n);
    	factors.add(first);
    	
    	
    	return factors;
    }
}