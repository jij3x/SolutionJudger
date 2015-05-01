public class Solution {
    public int maximumGap(int[] num) {
			int len = num.length;
			if (len < 2) {
				return 0;
			}
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for (int i : num) {
				max = Math.max(max, i);
				min = Math.min(min, i);
			}
			int gap = (max - min) / (len - 1);
			if (gap * (len - 1) < (max - min))
				gap++;

			int[] min_value = new int[len - 1];
			int[] max_value = new int[len - 1];
			for (int i = 0; i < len - 1; i++) {
				min_value[i] = Integer.MAX_VALUE;
				max_value[i] = Integer.MIN_VALUE;
			}
			for (int i : num) {
				int index = (i - min) / gap;
				if (index > len - 2) {
					max_value[len - 2] = i;
					min_value[len - 2] = Math.min(min_value[len - 2], i);
				} else {
					min_value[index] = Math.min(min_value[index], i);
					max_value[index] = Math.max(max_value[index], i);
				}
			}
			int re = max_value[0] - min_value[0];
			int prev = max_value[0];
			for (int i = 0; i < len - 1; i++) {
				if (min_value[i] != Integer.MAX_VALUE) {
					re = Math.max(re, min_value[i] - prev);
					prev = max_value[i];
				}
			}
			return re;
		}
}