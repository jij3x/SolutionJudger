public class Solution {
    public List<Integer> testZigzagIterator(List<Integer> v1, List<Integer> v2) {
        ZigzagIterator it = new ZigzagIterator(v1, v2);
        List<Integer> result = new ArrayList<Integer>();

        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }
}