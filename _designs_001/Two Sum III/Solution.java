public class Solution {
    public List<Boolean> testTwoSumIII(List<Integer> procedure) {
        TwoSum twoSum = new TwoSum();
        List<Boolean> result = new ArrayList<Boolean>();
        for (int i = 0, idx = 1; i < procedure.get(0); i++) {
            int op = procedure.get(idx++);
            switch (op) {
            case 0:
                twoSum.add(procedure.get(idx++));
                break;
            case 1:
                result.add(twoSum.find(procedure.get(idx++)));
                break;
            }
        }
        return result;
    }
}