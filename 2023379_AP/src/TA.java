import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TA extends Student{
    private Course course;
    public List<Student> your_students = new ArrayList<>();
    String office_hours;

    public TA(String email, String password, String name, Object b) {
        super(email, password, name);
        if (b instanceof Course) {
            this.course = (Course) b;
        }
        else {
            System.out.println("Invalid course object provided.");
        }
    }

    public void view_student(List<Student> students){
        Scanner scanner = new Scanner(System.in);
        your_students.clear();

        System.out.println("Enrollment for your course: " + course.getTitle());
        for (Student x : students) {
            if (x.getCurrent_courses().contains(course)){
                if(!your_students.contains(x)){
                    your_students.add(x);
                }
            }
            if (your_students.isEmpty()) {
                System.out.println("No students have enrolled in your course yet");
            }
            else {
                System.out.println("Enrolled students:");
                for (Student y : your_students) {
                    System.out.println("Student: " + y.getName() + " | Email: " + y.getEmail());
                }
            }
        }
    }
}
