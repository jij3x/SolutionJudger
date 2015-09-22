public class Solution {
    public int testCelebrity(int[][] relationship) {
        Celebrity celebrity = new Celebrity(new Relationship(relationship));
        return celebrity.findCelebrity(relationship.length);
    }
}