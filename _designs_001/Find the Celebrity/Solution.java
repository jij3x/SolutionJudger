public class Solution {
    public String testCelebrity(int[][] relationship) {
        Celebrity celebrity = new Celebrity(new Relationship(relationship));
        return celebrity.findCelebrity(relationship.length);
    }
}