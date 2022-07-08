import java.util.ArrayList;

class Main {

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Node root = makeTree(array, 0);
        printTree(root);
        ArrayList<ArrayList<Integer>> result = new ArrayList();

        findPath(result, new ArrayList(), 15, root);
        System.out.println(result);

    }

    static void findPath(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> tmp, int target, Node root) {
        if (target < 0 || root == null) {
            return;
        }
        tmp.add(root.val);
        if (target == root.val && root.left == null && root.right == null) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        findPath(result, tmp, target - root.val, root.left);
        findPath(result, tmp, target - root.val, root.right);
        tmp.remove(tmp.size() - 1);
    }

    static Node makeTree(int[] array, int i) {
        if (i >= array.length) {
            return null;
        }
        Node root = new Node(array[i]);
        root.left = makeTree(array, i * 2 + 1);
        root.right = makeTree(array, i * 2 + 2);
        return root;
    }

    static void printTree(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val);
        printTree(root.left);
        printTree(root.right);
    }

}

class Node {
    public Node(int val) {
        this.val = val;
    }

    public int val;
    public Node left;
    public Node right;
}
