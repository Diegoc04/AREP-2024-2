public class BSTTest {
    public static void main(String[] args) {
        // Crear un nuevo BST
        BST<Integer> bst = new BST<>();

        // Insertar valores
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Pruebas\n");

        // Mostrar el árbol en diferentes recorridos

        // 1. Recorrido en orden (in-order traversal)
        System.out.println("En orden transversal (In-Order):");
        bst.inOrderTraversal(); // Output: 20 30 40 50 60 70 80
        System.out.println();

        // 2. Recorrido en preorden (pre-order traversal)
        System.out.println("En preorden transversal (Pre-Order):");
        bst.preOrderTraversal(); // Output: 50 30 20 40 70 60 80
        System.out.println();

        // 3. Recorrido en postorden (post-order traversal)
        System.out.println("En postorden transversal (Post-Order):");
        bst.postOrderTraversal(); // Output: 20 40 30 60 80 70 50
        System.out.println();

        // Buscar valores
        System.out.println("\nBuscar 50: " + bst.search(50)); // Output: true
        System.out.println("\nBuscar 120: " + bst.search(120)); // Output: false

        // Encontrar el valor mínimo y máximo
        System.out.println("\nValor mínimo: " + bst.findMin()); // Output: 20
        System.out.println("\nValor máximo: " + bst.findMax()); // Output: 80

        // Altura del árbol
        System.out.println("\nAltura del árbol: " + bst.height()); // Output: 3

        // Verificar si el árbol está balanceado
        System.out.println("\n¿El árbol está balanceado? " + bst.isBalanced()); // Output: true

        // Recorrido por niveles (Level-Order Traversal)
        System.out.println("\nRecorrido por niveles (Level-Order):");
        bst.levelOrderTraversal(); // Output: 50 30 70 20 40 60 80
        System.out.println();

        // Contar nodos
        System.out.println("\nNúmero de nodos: " + bst.countNodes()); // Output: 7

        // Eliminar un nodo (sin hijos, con un hijo y con dos hijos)
        System.out.println("\nEliminar el nodo 20 (sin hijos)\n");
        bst.delete(20);
        bst.inOrderTraversal(); // Output: 30 40 50 60 70 80
        System.out.println();

        System.out.println("\nEliminar el nodo 30 (con un hijo)\n");
        bst.delete(30);
        bst.inOrderTraversal(); // Output: 40 50 60 70 80
        System.out.println();

        System.out.println("\nEliminar el nodo 50 (con dos hijos)\n");
        bst.delete(50);
        bst.inOrderTraversal(); // Output: 40 60 70 80
        System.out.println();

        // Limpiar el árbol
        System.out.println("\nLimpiar el árbol...");
        bst.clear();
        System.out.println("¿El árbol está vacío? " + (bst.countNodes() == 0)); // Output: true
    }
}
