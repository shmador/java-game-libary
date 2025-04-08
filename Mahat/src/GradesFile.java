import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GradesFile {
	Queue<Student>[] arr;
	@SuppressWarnings("unchecked")
	public GradesFile() {
		arr = (Queue<Student>[]) new LinkedList[100];
		for (int i = 0; i < 100; i++)
			arr[i] = new LinkedList<>();
	}
	public void moveStudent(int k, int j) {
		if (0 > k || k > arr.length) return;
		if (0 > j || j > arr.length) return;
		if (isEmpty(k)) return;
		Student s = arr[k].remove();
		arr[j].add(s);
	}
	public Student getStudent(int k) {
		return arr[k].peek();
	}
	public boolean isEmpty(int k) {
		return arr[k].isEmpty();
	}
	public boolean listIsGood(int k) {
		if (isEmpty(k))
			return true;
		if (arr[k].peek().getCode() != k)
			return false;
		Student first = arr[k].remove();
		arr[k].add(first);
		boolean good = true;
		while (arr[k].peek() != first) {
			Student s = arr[k].remove();
			int code = s.getCode();
			if (code != k) 
				good = false;
			arr[k].add(s);
		}
		return good;
	}
	public static void main(String[] args) {
		GradesFile grades = new GradesFile();
		for (int i = 0; i < 100; i ++) {
			int code = 10000000 + (int)(Math.random()*(99999999-10000000+1));
			Student s = new Student(code);
			int ind = (int)(Math.random()*100);
			grades.addStudent(ind, s);
		}
		grades.print();
		sortGradeFile(grades);
		grades.print();
		
	}
	public static void sortGradeFile(GradesFile grades) {
		for (int i = 0; i < 100; i++) {
			if (!grades.listIsGood(i)) {
				Student first = grades.getStudent(i);
				if (first == null)
					continue;
				grades.moveStudent(i, i);
				while (grades.getStudent(i) != first) {
					Student s = grades.getStudent(i);
					int code = s.getCode();
					if (code != i)
						grades.moveStudent(i, code);
					else
						grades.moveStudent(i, i);
				}
				int code = first.getCode();
				if (first.getCode() != i)
					grades.moveStudent(i, code);
			}
		}
	}
	public void addStudent(int k, Student s) {
		arr[k].add(s);
	}
	public boolean isFileGood() {
		for (int i = 0; i < 100; i++)
			if (!listIsGood(i))
				return false;
		return true;
	}
	public void printFile(int k) {
		System.out.println(arr[k]);
	}
	public void print() {
		for (int i = 0; i < 100; i ++) {
			System.out.print(""+i+": ");
			printFile(i);
		}
	}
}
