package application;

public class Department implements Comparable<Department> {
	private String name;
	private HashTable<Student> students = new HashTable<>(1000);

	public HashTable<Student> getStudents() {
		return students;
	}

	public String printStudents() {
		String s = "";
		for (int i = 0; i < students.getSize(); i++) {
			if (students.get(i).getState() == 'F')
				s += students.get(i).getData().getName() + "/" + students.get(i).getData().getID() + "/"
						+ students.get(i).getData().getAvg() + "/" + students.get(i).getData().getGender() + "\n";
		}
		return s;
	}

	public Student get(int index) {
		return students.get(index).getData();
	}

	public String getName() {
		return name;
	}

	public Department(String name) {
		this.name = name;
	}

	public void addStudent(Student s) {
		students.insert(s);
	}

	@Override
	public int compareTo(Department o) {
		return name.compareTo(o.getName());
	}

	@Override
	public String toString() {
		return "name=" + name;
	}

}