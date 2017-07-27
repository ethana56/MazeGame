
package ethan.solver.domain;

public class Node<T> {
    private T node;
    private T left;
    private T right;
    public Node(T node) {
        this.node = node;
    }
    
    public void addLeft(T left) {
        this.left = left;
    }
    
    public void addRight(T right) {
        this.right = right;
    }
    
    public T getLeft() {
        return this.left;
    }
    
    public T getRight() {
        return this.right;
    }
}
