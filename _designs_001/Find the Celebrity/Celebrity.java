public class Celebrity {
    private Relationship relationship;

    Celebrity(Relationship relationship) {
        this.relationship = relationship;
    }

    public int findCelebrity(int n) {
        Queue<Integer> candidates = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }
        while (candidates.size() >= 2) {
            int a = candidates.poll();
            int b = candidates.poll();
            if (relationship.knows(a, b)) candidates.add(b);
            else candidates.add(a);
        }
        return verifyCelebrity(candidates.poll(), n);
    }

    private int verifyCelebrity(int c, int n) {
        for (int i = 0; i < n; i++) {
            if (i == c) continue;
            if (relationship.knows(c, i) || !relationship.knows(i, c))
                return -1;
        }
        return c;
    }
}
