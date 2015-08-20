public class Vector2D {
  private List<List<Integer> > v;
  private int x, y;
  public Vector2D(List<List<Integer> > vec) {
    v = vec;
    x = 0;
    y = 0;
    while (x < v.size() && v.get(x).size() == 0) x++;
  }

  public int next() {
    int res = v.get(x).get(y);
    while (x < v.size() && y + 1 == v.get(x).size()) {
      x += 1;
      y = -1;
    }
    y += 1;
    return res;
  }

  public boolean hasNext() {
    return x < v.size();
  }
}