public class Solution {
    public int findKthLargest(int[] nums, int k) {
		k = nums.length - k;
		int start = 0, end = nums.length - 1;
		int pivotIndex = 0;
		do {
			pivotIndex = partition(nums, start, end);
			if (pivotIndex < k)
				start = pivotIndex + 1;
			else if (pivotIndex > k)
				end = pivotIndex - 1;
		} while (pivotIndex != k);
		return nums[k];
	}

    private int partition(int[] nums, int start, int end) {
        int pivot = start, temp;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) start++;
            while (start <= end && nums[end] > nums[pivot]) end--;
            if (start > end) break;
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        temp = nums[end];
        nums[end] = nums[pivot];
        nums[pivot] = temp;
        return end;
    }

    public int findKthLargest2(int[] nums, int k) {
		final int R = (1 << 8);
		final int bitmask = R - 1;
		int[] aux = new int[nums.length];
		for (int i = 0; i < 4; i++) {
			int[] count = new int[R + 1];
			for (int num : nums) {
				int c = (num >>> (i * 8)) & bitmask;
				count[c + 1]++;
			}
			for (int r = 0; r < R; r++) count[r + 1] += count[r];
			if (i == 3) {
				int shift1 = count[R] - count[R/2];
				int shift2 = count[R/2];
				for (int r = 0; r < R/2; r++)
					count[r] += shift1;
				for (int r = R/2; r < R; r++)
					count[r] -= shift2;
			}
			for (int num : nums) {
				int c = (num >>> (i * 8)) & bitmask;
				aux[count[c]++] = num;
			}
			System.arraycopy(aux, 0, nums, 0, nums.length);
		}
		return nums[nums.length - k];
    }
}