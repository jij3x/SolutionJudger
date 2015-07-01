public class MyStack {
    // Push element x onto stack.
    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> q2 = new LinkedList<Integer>();
    int c = 0;

    public void push(int x) {
        if (q1.isEmpty()) {
            q2.add(x);

        } else {
            q1.add(x);
        }
        c++;
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (this.empty())
            return;
        if (q2.isEmpty()) {
            for (int i = 0; i < c - 1; i++) {
                int x = q1.remove();
                q2.add(x);
            }
            q1.remove();
        } else {
            for (int i = 0; i < c - 1; i++) {
                int x = q2.remove();
                q1.add(x);
            }
            q2.remove();
        }
        c--;
    }

    // Get the top element.
    public int top() {
        int z = -1;
        if (q2.isEmpty()) {
            for (int i = 0; i < c - 1; i++) {
                int x = q1.remove();
                q2.add(x);
            }
            z = q1.peek();
            q2.add(q1.remove());
        }
        if (q1.isEmpty()) {
            for (int i = 0; i < c - 1; i++) {
                int x = q2.remove();
                q1.add(x);
            }
            z = q2.peek();
            q1.add(q2.remove());
        }
        return z;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        if (q1.isEmpty() && q2.isEmpty())
            return true;
        return false;
    }
}
