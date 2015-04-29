public class Solution {
    int[] table;
    Solution() {
        table = new int[256];
        table['I'] = 1; table['V'] = 5; 
        table['X'] = 10; table['L'] = 50; 
        table['C'] = 100; table['D'] = 500; 
        table['M'] = 1000;
    }
    public int romanToInt(String s) {
        int prev = 0;
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = table[s.charAt(i)];
            if (curr > prev) {
                total += curr - 2*prev;
            } else {
                total += curr;
            }
            prev = curr;
        }
        return total;
    }
}