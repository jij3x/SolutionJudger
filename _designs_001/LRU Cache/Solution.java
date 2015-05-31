public class Solution {
    public List<Integer> testLRUCache(int capacity, List<Integer> procedure) {
        LRUCache cache = new LRUCache(capacity);
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0, idx = 1; i < procedure.get(0); i++) {
            int op = procedure.get(idx++);
            switch (op) {
            case 0:
                cache.set(procedure.get(idx++), procedure.get(idx++));
                break;
            case 1:
                result.add(cache.get(procedure.get(idx++)));
                break;
            }
        }
        return result;
    }
}