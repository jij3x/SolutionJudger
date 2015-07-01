import java.util.ArrayList;

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        if (nums == null || nums.length == 0)
            return res;
        int start = 0;
        int end = 0;

        while (end < nums.length) {
            StringBuilder sb = new StringBuilder();
            while (end < nums.length - 1 && nums[end] == nums[end + 1] - 1) {
                end++;
            }
            if (start == end) {
                sb.append(nums[start]);
                res.add(sb.toString());
            } else {
                sb.append(nums[start]);
                sb.append("->");
                sb.append(nums[end]);
                res.add(sb.toString());
            }
            end++;
            start = end;
        }

        return res;
    }
}
