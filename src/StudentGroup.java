import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentGroup implements GroupOperationService {

	private Student[] students;
	int size = 0;

	public StudentGroup(int length) {
	}

	public StudentGroup() {
		students = new Student[2];
		this.size = students.length;
	}

	// implementation
	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		if (students == null) {
			throw new IllegalArgumentException();
		}
		this.students = students;
		this.size = students.length;
	}

	public Student getStudent(int index) {
		if (index >= students.length || index < 0) {
			throw new IllegalArgumentException();
		}
		return students[index];
	}

	public void setStudent(Student student, int index) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if (index >= students.length || index < 0) {
			throw new IllegalArgumentException();
		}
		if (students[index] == null) {
			students[index] = student;
			size++;
		} else {
			Student[] studentsNew = new Student[students.length + 1];
			int i, y;

			for (i = 0, y = 0; i < studentsNew.length & y < index; i++, y++) {
				studentsNew[i] = students[y];
			}
			studentsNew[index] = student;

			for (i = index + 1, y = index; i < studentsNew.length & y < students.length; i++, y++) {
				studentsNew[i] = students[y];
			}
			students = studentsNew;
			size = students.length;
		}
	}

	public void addFirst(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		setStudent(student, 0);
	}

	public void addLast(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		Student[] studentsNew = new Student[students.length + 1];
		int i, y;
		for (i = 0, y = 0; i < studentsNew.length & y < students.length; i++, y++) {
			studentsNew[i] = students[y];
		}
		studentsNew[studentsNew.length - 1] = student;
		students = studentsNew;
		size = students.length;

	}

	public void add(Student student, int index) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if (index >= students.length || index < 0) {
			throw new IllegalArgumentException();
		}
		setStudent(student, index);
	}

	// ”дал¤ет ѕ≈–¬ќ≈ Ќј…ƒ≈ЌЌќ≈ —ќ¬ѕјƒ≈Ќ»≈
	public void remove(int index) {
		if (index >= students.length || index < 0) {
			throw new IllegalArgumentException();
		}
		Student[] studentsNew = new Student[students.length - 1];
		int i, y;

		for (i = 0, y = 0; i < studentsNew.length & y < index; i++, y++) {
			studentsNew[i] = students[y];
		}
		for (i = index, y = index + 1; i < studentsNew.length & y < students.length; i++, y++) {
			studentsNew[i] = students[y];
		}
		students = studentsNew;
		size = students.length;
	}

	public void remove(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = findIndexStudent(student);
		if (index != -1) {
			remove(index);
		}
	}

	private int findIndexStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = -1;
		if (student != null) {
			for (int i = 0; i < students.length; i++) { // ѕ≈–≈ќѕ–≈ƒ≈Ћ»“№ equals!!!!!!!!!!!!
				if (student.equals(students[i])) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	// ”ƒјЋя≈“ студентов после данного индекса, включа¤ студента с индексом
	public void removeFromIndex(int index) {
		if (index >= students.length || index < 0) {
			throw new IllegalArgumentException();
		}
		Student[] studentsNew = new Student[index];

		int i, y;
		for (i = 0, y = 0; i < studentsNew.length & y < index; i++, y++) {
			studentsNew[i] = students[y];
		}
		students = studentsNew;
		size = students.length;
	}

	public void removeFromElement(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = findIndexStudent(student);
		removeFromIndex(index);

	}

	// ”ƒјЋя≈“ студентов до данного индекса, включа¤ студента с индексом
	public void removeToIndex(int index) {
		if (index >= students.length || index < 0) {
			throw new IllegalArgumentException();
		}
		int deleteposition = index + 1;
		Student[] studentsNew = new Student[students.length - deleteposition];
		int i, y;
		for (i = 0, y = deleteposition; i < studentsNew.length & y < students.length; i++, y++) {
			studentsNew[i] = students[y];
		}
		students = studentsNew;
		size = students.length;
	}

	public void removeToElement(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = findIndexStudent(student);
		removeToIndex(index);

	}

	public void bubbleSort() {
		for (int i = 0; i < students.length - 1; i++) {
			for (int j = 0; j < i; j++) {
				if (students[j].compareTo(students[j + 1]) > 0) {
					Student buffer = students[j];
					students[j] = students[j + 1];
					students[j + 1] = buffer;
				}
			}
		}
	}

	public Student[] getByBirthDate(Date date) {
		if (date == null) {
			throw new IllegalArgumentException();
		}
		Student[] newStudents = null;
		newStudents = new Student[students.length];
		int i, y;

		for (i = 0, y = 0; i < newStudents.length & y < students.length; y++) {
			if (students[y] != null) {
				String dateStr = transformDateIntoString(date);
				String dateStudentStr = transformDateIntoString(students[y].getBirthDate());

				if (dateStr.equals(dateStudentStr)) {
					newStudents[i] = students[y];
					i++;
				}
			}
		}
		return newStudents;
	}

	private String transformDateIntoString(Date date) {
		if (date == null) {
			throw new IllegalArgumentException();
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sf.format(date);
		return dateStr;
	}

	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		if (firstDate == null || lastDate == null) {
			throw new IllegalArgumentException();
		}
		Student[] newStudents = null;
		newStudents = new Student[students.length];
		int i, y;

		for (i = 0, y = 0; i < newStudents.length & y < students.length; y++) {
			if (students[y] != null) {
				if (firstDate.getTime() < students[y].getBirthDate().getTime()
						& students[y].getBirthDate().getTime() < lastDate.getTime()) {
					newStudents[i] = students[y];
					i++;
				}
			}
		}
		return newStudents;
	}

	public Student[] getNearBirthDate(Date date, int days) {
		if (date == null) {
			throw new IllegalArgumentException();
		}
		long milliseconds = date.getTime();
		long addMilliseconds = days * 86_400_000L;

		Date dateLate = new Date(milliseconds + addMilliseconds);
		return getBetweenBirthDates(date, dateLate);
	}

	public int getCurrentAgeByDate(int indexOfStudent) {
		if (indexOfStudent >= students.length || indexOfStudent < 0) {
			throw new IllegalArgumentException();
		}
		int age = 0;
		Student student = getStudent(indexOfStudent);
		long year = 31_536_000_000L;
		age = (int) ((new Date().getTime() - student.getBirthDate().getTime()) / year);
		return age;
	}

	public Student[] getStudentsByAge(int age) {
		Student[] studentsNew = new Student[students.length];
		int i, y;
		for (i = 0, y = 0; i < studentsNew.length & y < students.length; y++) {
			if (getCurrentAgeByDate(y) == age) {
				studentsNew[i] = students[y];
				i++;
			}
		}
		return studentsNew;
	}

	public Student[] getStudentsWithMaxAvgMark() {
     	Student[] studentsNew = new Student[students.length];
     	double maxAvgMark = 0;
     	int y, q;
     	maxAvgMark = students[0].getAvgMark();
		for (y = 0, q=1; y < students.length & q<students.length; y++, q++) {
			if (students[y].getAvgMark() < students[q].getAvgMark()) {
				maxAvgMark = students[q].getAvgMark();
			}
		}
		for (y = 0, q=0; y < studentsNew.length & q<students.length; q++) {
			if (students[q].getAvgMark()==maxAvgMark) {
				studentsNew[y]=students[q];
				y++;
			}			
		}		
		return studentsNew;
	}

	public Student getNextStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = findIndexStudent(student);
		return getStudent(index + 1);
	}			
}
