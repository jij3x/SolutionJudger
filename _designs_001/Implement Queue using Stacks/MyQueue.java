public class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    // Push element x onto queue.
    public void push(int x) {
        s1.push(x);
    }
    
    private void shiftStacks() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }

    // Removes the element in front of the queue.
    public void pop() {
        shiftStacks();
        s2.pop();
    }

    // Get the front element.
    public int peek() {
        shiftStacks();
        return s2.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}