public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 0 || k > nums.length || k <= 0) {
			return -1;
		} else {
			k = nums.length - k;

			int start = 0;
			int end = nums.length - 1;

			int pivotIndex = partition(nums, start, end);

			while (pivotIndex != k) {
				if (pivotIndex < k)
					start = pivotIndex + 1;
				else if (pivotIndex > k)
					end = pivotIndex - 1;
				else
					return nums[pivotIndex];
				pivotIndex = partition(nums, start, end);
			}

			return nums[pivotIndex];
		}
	}

	private int partition(int[] nums, int start, int end) {
		int i = start;
		int j = end - 1;
		int pivotIndex = end;
		int pivotValue = nums[pivotIndex];

		while (i <= j) {
			while (i < pivotIndex && nums[i] < pivotValue)
				i++;
			while (j >= start && nums[j] >= pivotValue)
				j--;
			if (i < j)
				swap(nums, i++, j--);
		}

		swap(nums, i, pivotIndex);
		return i;
	}

	private void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
}