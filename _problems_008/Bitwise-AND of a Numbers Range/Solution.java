public class Solution {
    public int rangeBitwiseAnd(int M, int N) {
        int result = M & N;

    	for (int i = 0; i < 32; i++)
    		if (N - M + 1 > (1 << i))
    			result &= ~(1 << i);
    
    	return result;
    }
}