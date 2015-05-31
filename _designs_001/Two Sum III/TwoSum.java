public class TwoSum {
    private Map<Integer, Integer> table = new HashMap<>();

	public void add(int input) {
	    int count = table.containsKey(input) ? table.get(input) : 0;
	    table.put(input, count + 1);
	}

	public boolean find(int val) {
	    for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
	        int num = entry.getKey();
	        int y = val - num;
	        if (y == num) {
	            if (entry.getValue() >= 2) return true;
	        } else if (table.containsKey(y)) {
	            return true;
	        }
	    }
	    return false;
	}
}
