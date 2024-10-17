import java.util.*;

class BST<T extends Comparable<T>> {
    private Node<T> root;

    public BST() {
        root = null;
    }

    // 1. Insertar un nodo
    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> root, T value) {
        if (root == null) 
            return new Node<>(value); 
        if (value.compareTo(root.value) < 0) 
            root.left = insertRec(root.left, value);
        else if (value.compareTo(root.value) > 0) 
            root.right = insertRec(root.right, value);
        return root;
    }

    // 2. Buscar un nodo
    public boolean search(T value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node<T> root, T value) {
        if (root == null)
            return false;
        if (value.equals(root.value))
            return true;
        return value.compareTo(root.value) < 0 ? searchRec(root.left, value) : searchRec(root.right, value);
    }

    // 3. Eliminar un nodo
    public void delete(T value) {
        root = deleteRec(root, value);
    }

    private Node<T> deleteRec(Node<T> root, T value) {
        if (root == null)
            return root;

        if (value.compareTo(root.value) < 0)
            root.left = deleteRec(root.left, value);
        else if (value.compareTo(root.value) > 0)
            root.right = deleteRec(root.right, value);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    private T minValue(Node<T> root) {
        T minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    // 4. Recorridos
    public void inOrderTraversal() {
        inOrderRec(root);
    }

    private void inOrderRec(Node<T> root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    public void preOrderTraversal() {
        preOrderRec(root);
    }

    private void preOrderRec(Node<T> root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    public void postOrderTraversal() {
        postOrderRec(root);
    }

    private void postOrderRec(Node<T> root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.value + " ");
        }
    }

    // 5. Encontrar el valor mínimo
    public T findMin() {
        if (root == null) throw new NoSuchElementException("Tree is empty");
        return minValue(root);
    }

    // 6. Encontrar el valor máximo
    public T findMax() {
        if (root == null) throw new NoSuchElementException("Tree is empty");
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    // 7. Altura del árbol
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node<T> root) {
        if (root == null)
            return 0;
        return Math.max(heightRec(root.left), heightRec(root.right)) + 1;
    }

    // 8. Verificar si el árbol está balanceado
    public boolean isBalanced() {
        return isBalancedRec(root);
    }

    private boolean isBalancedRec(Node<T> root) {
        if (root == null)
            return true;

        int leftHeight = heightRec(root.left);
        int rightHeight = heightRec(root.right);

        return Math.abs(leftHeight - rightHeight) <= 1 && isBalancedRec(root.left) && isBalancedRec(root.right);
    }

    // 9. Recorrido por niveles (Level-Order Traversal)
    public void levelOrderTraversal() {
        if (root == null) return;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            System.out.print(current.value + " ");

            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
    }

    // 10. Limpiar el árbol
    public void clear() {
        root = null;
    }

    // 11. Contar nodos
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(Node<T> root) {
        if (root == null)
            return 0;
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }
}

