public class Solution {
    public String countAndSay(int n) {
        String out = "1";
        if (n == 1) return out;
        for (int i=1; i<n; i++) {
            out = run(out);
        }
        return out;
    }    
    private String run(String input) {
        StringBuffer sb = new StringBuffer();
        
        for (int i=0; i<input.length(); ) {
            char c = input.charAt(i);
            int count = 1;
            while (++i < input.length() && input.charAt(i) == c) {
                count++;
            }
            sb.append(count);
            sb.append(c);
        }
        
        return sb.toString();
    }
}