package application;

public class Node<Data extends Comparable<Data>> {
	private Data data;
	private Node<Data> left;
	private Node<Data> right;

	public Node(Data data) {
		this.data = data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Node<Data> getLeft() {
		return left;
	}

	public void setLeft(Node<Data> left) {
		this.left = left;
	}

	public Node<Data> getRight() {
		return right;
	}

	public boolean isLeaf() {
		return !hasLeft() && !hasRight();
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public void setRight(Node<Data> right) {
		this.right = right;
	}

	public Data getData() {
		return data;
	}

	@Override
	public String toString() {
		return data + "";
	}
}