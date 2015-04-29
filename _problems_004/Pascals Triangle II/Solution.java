public class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> row = new ArrayList<Integer>();
        row.add(1);
        if (rowIndex == 0) return row;
        for (int i = 1; i <= rowIndex; i++) {
            int left = row.get(0);
            for (int j = 1; j < i; j++) {
                int temp = row.get(j);
                row.set(j, left + row.get(j));
                left = temp;
            }
            row.add(1);
        }
        return row;
    }
}