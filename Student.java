package application;

public class Student implements Comparable<Student> {
	private String name;
	private int ID;
	private double avg;
	private char gender;

	public Student(String name, int ID, double avg, char gender) {
		this.name = name;
		this.ID = ID;
		this.avg = avg;
		this.gender = gender;
	}

	public Student(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public char getGender() {
		return gender;
	}

	@Override
	public int compareTo(Student o) {
		return name.compareTo(o.getName());
	}

	@Override
	public int hashCode() {
		return Math.abs(name.hashCode());
	}

	@Override
	public String toString() {
		return "Name: " + name + ", ID: " + ID + ", avg: " + avg + ", gender: " + gender;
	}

}