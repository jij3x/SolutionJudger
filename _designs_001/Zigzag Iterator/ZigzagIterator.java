public class ZigzagIterator {
    Iterator i0, i1;
    Iterator it;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.i0 = v1.iterator(); this.i1 = v2.iterator();
        this.it = i0.hasNext() ? i0 : i1;
    }

    public int next() {
        int val = (int) it.next();
        if (it == i0 && i1.hasNext())
            it = i1;
        else if (it == i1 && i0.hasNext())
            it = i0;
        return val;
    }

    public boolean hasNext() {
        return it.hasNext();
    }
}
