public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.isEmpty()) throw new RuntimeException();
        if (stack.pop().equals(minStack.peek())) minStack.pop();
    }

    public int top() {
        if (stack.isEmpty()) throw new RuntimeException();
        return stack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) throw new RuntimeException();
        return minStack.peek();
    }
}