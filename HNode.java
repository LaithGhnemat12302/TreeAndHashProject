package application;

public class HNode<T extends Comparable<T>> {
	private T data;
	private char state = 'N';

	public HNode() {
	}

	public HNode(T data) {
		this.data = data;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		if (state == 'D' || state == 'N' || state == 'F')
			this.state = state;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isFull() {
		return state != 'D' && state != 'N';
	}

	@Override
	public String toString() {
		return data + "";
	}
}