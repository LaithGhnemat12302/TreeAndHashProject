package application;

@SuppressWarnings("unchecked")
public class HashTable<T extends Comparable<T>> {
	private HNode<T>[] hash = new HNode[10];
	private int size = 10;

	public HashTable() {
		initialization();
	}

	public HashTable(int size) {
		this.size = size;
		hash = new HNode[size];
		initialization();
	}

	public int getSize() {
		return size;
	}

	public HNode<T> get(int index) {
		return hash[index];
	}

	public void insert(T data) {
		int j = hash(data);
		hash[j] = new HNode<>(data);
		hash[j].setState('F');
	}

	public T delete(T data) {
		int j = search(data);
		if (j != -1) {
			delete(j);
			return data;
		} else
			return null;
	}
	
	public void delete(int j) {
		hash[j].setState('D');
	}

	public int search(T data) {
		int h = data.hashCode(), j = 1, i = h % hash.length, index = -1;
		while (hash[i].isFull()) {
			if (hash[i].getData().compareTo(data) == 0) {
				index = i;
				break;
			}
			i = (h + (int) Math.pow(j, 2)) % hash.length;
			j++;
		}
		return index;
	}

	private void initialization() {
		for (int i = 0; i < hash.length; i++) {
			hash[i] = new HNode<>();
		}
	}

	private int hash(T data) {
		int h = data.hashCode(), j = 1, i = h % hash.length;
		while (hash[i].isFull()) {
			i = (h + (int) Math.pow(j, 2)) % hash.length;
			j++;
		}
		return i;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (HNode<T> thNode : hash) {
			if (thNode.getState() != 'D')
				s.append(thNode).append("\n");
			else
				s.append("null ");
		}
		return s.toString();
	}
}