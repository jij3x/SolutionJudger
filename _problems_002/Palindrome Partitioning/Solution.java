public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (s.isEmpty()) {
            result.add(new ArrayList<String>());
            return result;
        }

        for (int i = 1; i <= s.length(); i++) {
            String head = s.substring(0, i);
            if (head.equals((new StringBuilder(head)).reverse().toString())) {
                for (List<String> list : partition(s.substring(i))) {
                    list.add(0, s.substring(0, i));
                    result.add(list);
                }
            }
        }
        return result;
    }
}