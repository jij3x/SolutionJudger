public class Solution {
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (numRows == 0) return ret;
        ArrayList<Integer> row = new ArrayList<Integer>();
        row.add(1);
        ret.add(row);
        if (numRows == 1) return ret;
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = ret.get(ret.size()-1);
            row = new ArrayList<Integer>();
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(lastRow.get(j-1) + lastRow.get(j));
            }
            row.add(1);
            ret.add(row);
        }
        return ret;
    }
}