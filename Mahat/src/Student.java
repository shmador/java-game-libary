
public class Student {
	int studentId;
	int grade;
	public Student(int id) {
		studentId = id;
	}
	public int getCode() {
		int temp = studentId;
		for (int i = 0; i < 3; i++)
			temp /= 10;
		return temp % 100;
	}
	public String toString() {
		return ""+getCode();
	}
}
