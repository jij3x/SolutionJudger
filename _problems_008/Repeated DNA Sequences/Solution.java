public class Solution {
    private static final int MASK = (1 << 20) - 1;
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> sequences = new ArrayList<>();
        Map<Integer, Boolean> occurredOnce = new HashMap<>();
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash << 2) | encode(s.charAt(i));
            hash &= MASK;
            if (i < 9) continue;
            if (occurredOnce.containsKey(hash)) {
                if (occurredOnce.get(hash)) {
                    sequences.add(s.substring(i - 9, i + 1));
                    occurredOnce.put(hash, false);
                }
            } else {
                occurredOnce.put(hash, true);
            }
        }
        return sequences;
    }
    
    private int encode(char c) {
        switch (c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default:  throw new IllegalStateException("Invalid code " + c);
        }
    }
}