public class Relationship {
    private int[][] relationship;

    Relationship(int[][] relationship) {
        this.relationship = relationship;
    }

    boolean knows(int a, int b) {
        return relationship[a][b] == 1;
    }
}
