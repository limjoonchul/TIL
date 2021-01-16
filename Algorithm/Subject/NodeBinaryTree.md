# 과제 - 노드 기반 완전 이진 트리 구현

```java
import java.util.ArrayList;

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

class BinaryTree3 {
    private Node root;
    private boolean isFound;

    public BinaryTree3(int [] array) {
        Node [] nodeArray = new Node[array.length];
        for (int i = 0; i < array.length; i++) {
            nodeArray[i] = new Node(array[i], null, null);
        }

        for (int i = 0; i < array.length; i++) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < array.length) {
                nodeArray[i].left = nodeArray[left];
            }
            if (right < array.length) {
                nodeArray[i].right = nodeArray[right];
            }
        }
        root = nodeArray[0];
    }

    public void preorder() {
        preorderHelper(root);
        System.out.println();
    }

    private void preorderHelper(Node curr) {
        System.out.print(curr.value + " ");

        if (curr.left != null){
            preorderHelper(curr.left);
        }

        if (curr.right != null){
            preorderHelper(curr.right);
        }
    }

    public void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(Node curr) {

        if (curr.left != null){
            inorderHelper(curr.left);
        }

        System.out.print(curr.value + " ");

        if (curr.right != null){
            inorderHelper(curr.right);
        }
    }

    public void postorder() {
        postorderHelper(root);
        System.out.println();
    }

    private void postorderHelper(Node curr) {
        if (curr.left != null){
            postorderHelper(curr.left);
        }

        if (curr.right != null){
            postorderHelper(curr.right);
        }

        System.out.print(curr.value + " ");
    }

    public boolean bfs(int value) {
        isFound = false;
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(root);

        int rear = 0;
        int front = 0;

        Node newNode = root;

        while (true) {
            if (newNode.value == value){
                isFound = true;
                break;
            }

            if (newNode.left != null) {
                rear++;
                nodes.add(newNode.left);
            }
            if (newNode.right != null) {
                rear++;
                nodes.add(newNode.right);
            }

            if (rear == front){
                break;
            }
            front++;
            newNode = nodes.get(newNode.value + 1);

        }
        return isFound;
    }

    public boolean dfs(int value) {
        isFound = false;
        dfsHelper(root, value);
        return isFound;
    }

    private void dfsHelper(Node curr, int value) {
        if (isFound) {
            return;
        }

        if (curr.value == value) {
            isFound = true;
            return;
        }

        if (curr.left != null){
            dfsHelper(curr.left, value);
        }

        if (curr.right != null) {
            dfsHelper(curr.right, value);
        }
    }

}
public class NodeBinaryTreeTest {
    public static void main(String[] args) {
        int [] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinaryTree3 tree = new BinaryTree3(array);

        tree.preorder();
        tree.inorder();
        tree.postorder();

        System.out.println(tree.bfs(2));
        System.out.println(tree.bfs(10));

        System.out.println(tree.dfs(5));
        System.out.println(tree.dfs(14));
    }
}

```