public class Solution {
    int[] P = new int[12];
    public Solution() {
        P[0] = 1;
        for (int i = 1; i < 12; i++) {
            P[i] = i*P[i-1];
        }
    }
    public String getPermutation(int n, int k) {
        StringBuffer input = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            input.append(i);
        }
        return find(input.toString(), k-1);
    }
    
    private String find(String input, int k) {
        int N = input.length();
        if (N == 1) return input;
        int i = k/P[N-1];
        int j = k%P[N-1];
        return input.charAt(i) + find(input.substring(0, i) + input.substring(i+1), j);
    }
}
