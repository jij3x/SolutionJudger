public class Solution {
    public int evalRPN(List<String> s) {
      Stack<Integer> val = new Stack<Integer>();
      int n = s.size();
      for (int i = 0; i < n; i++) {
        Integer x = 0, y = 0;
        if (s.get(i).equals("+")) {
          y = val.pop();
          x = val.pop();
          val.push(x + y);
        } else if (s.get(i).equals("-")) {
          y = val.pop();
          x = val.pop();
          val.push(x - y);
        } else if (s.get(i).equals("*")) {
          y = val.pop();
          x = val.pop();
          val.push(x * y);
        } else if (s.get(i).equals("/")) {
          y = val.pop();
          x = val.pop();
          val.push(x / y);
        } else {
          val.push(Integer.parseInt(s.get(i)));
        }
      }
      return val.pop();
    }
}