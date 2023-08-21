package application;

public class AVL<T extends Comparable<T>> extends BST<T> {

	public AVL() {
		root = null;
	}

	public void insert(T key) {
		root = insert(root, key);
	}

	private Node<T> insert(Node<T> root, T key) {
		if (root == null) {
			return new Node<T>(key);
		}
		if (key.compareTo(root.getData()) < 0) {
			root.setLeft(insert(root.getLeft(), key));
		} else {
			root.setRight(insert(root.getRight(), key));
		}
		return balance(root);
	}

	public Node<T> delete(T key) {
		Node<T> ret = super.delete(key);
		root = balance(root);
		return ret;
	}

	private Node<T> balance(Node<T> root) {
		if (root == null) {
			return root;
		}
		int balance = getBalance(root);
		if (balance > 1) {
			if (getBalance(root.getLeft()) > 0) {
				root = rotateRight(root);
			} else {
				root = rotateLeftRight(root);
			}
		} else if (balance < -1) {
			if (getBalance(root.getRight()) < 0) {
				root = rotateLeft(root);
			} else {
				root = rotateRightLeft(root);
			}
		}
		return root;
	}

	private Node<T> rotateRightLeft(Node<T> root) {
		Node<T> temp = root.getRight();
		root.setRight(rotateRight(temp));
		return rotateLeft(root);
	}

	private Node<T> rotateLeft(Node<T> root) {
		Node<T> temp = root.getRight();
		root.setRight(temp.getLeft());
		temp.setLeft(root);
		return temp;
	}

	private Node<T> rotateLeftRight(Node<T> root) {
		Node<T> temp = root.getLeft();
		root.setLeft(rotateLeft(temp));
		return rotateRight(root);
	}

	private Node<T> rotateRight(Node<T> root) {
		Node<T> temp = root.getLeft();
		root.setLeft(temp.getRight());
		temp.setRight(root);
		return temp;
	}

	private int getBalance(Node<T> root) {
		if (root == null) {
			return 0;
		}
		return getHeight(root.getLeft()) - getHeight(root.getRight());
	}

	private int getHeight(Node<T> curr) {
		if (curr == null)
			return 0;
		if (curr.isLeaf())
			return 1;
		else
			return Math.max(1 + getHeight(curr.getLeft()), 1 + getHeight(curr.getRight()));
	}

	public void print() {
		print(root);
	}

	private void print(Node<T> root) {
		if (root == null) {
			return;
		}
		print(root.getLeft());
		System.out.print(root.getData() + " ");
		print(root.getRight());
	}

	public void traverseLevel() {
		int h = getHeight(root);
		int i;
		for (i = 0; i < h; i++) {
			System.out.println(printLevel(root, i, 0));
			System.out.println();
		}
	}

	private String printLevel(Node<T> root, int i, int j) {

		if (root != null) {
			if (i == j)
				return root.getData() + " ";
			if (j > i)
				return "NULL";

			return printLevel(root.getLeft(), i, j + 1) + " " + printLevel(root.getRight(), i, j + 1);
		} else
			return "NULL";

	}

}