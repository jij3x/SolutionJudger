public class Solution {
    void reverse(int[] num, int start, int end) {
      while (start < end) {
        swap(num, start, end);
        start++;
        end--;
      }
    }
    void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    public void nextPermutation(int[] num) {
        int n = num.length;
        int tail = n-1;
        while (tail > 0 && num[tail] <= num[tail-1]) {
    		tail--;
    	}
    	// in reversed order, so return the reversed.
    	if (tail == 0) {
    		reverse(num, 0, n-1);
    		return;
    	}
        tail--;
    	int head = n-1;
    	while (head >= 0 && num[head] <= num[tail]) {
    		head--;
    	}
    	swap(num, head, tail);
    	reverse(num, tail+1, n-1);
    }
}