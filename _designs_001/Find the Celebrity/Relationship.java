public class Relationship {
    private int[][] relationship;

    Reader4(int[][] relationship) {
        this.relationship = relationship;
    }

    boolean knows(int a, int b) {
        return relationship[a][b] == 1;
    }
}
