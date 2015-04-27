public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        int[] ori = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            ori[i] = num[i];
        }
        do {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < num.length; i++) {
                arr.add(num[i]);
            }
            ret.add(arr);
            nextPermutation(num);
        } while (!eqArray(ori, num));
        return ret;
    }
    boolean eqArray(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
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
