import java.util.List;
import java.util.Scanner;
import java.util.ArrayList; // Add this import

public class Professor extends User {
    private String office_hours;
    private Course course;
    public List<Student> Enrolled_Students = new ArrayList<>();
    public Professor(String email, String password, String name, Course c) {
        super(email, password, name);
        this.course = c;
    }

    public void Manage_Courses() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. READ");  //ask professor if it wants to READ or UPDATE courses
        System.out.println("2. UPDATE");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Your Course Code: " + course.getCode());
                System.out.println("Your Course Title: " + course.getTitle());
                System.out.println("Your Credits: " + course.getCredits());
                System.out.println("Your Timings: " + course.getTimings());
                System.out.println("Your Semester: " + course.getSemester());
                break;

            case 2:
                System.out.println("Choose which of this you want to update: ");
                System.out.println("1. Timings");
                System.out.println("2. Credits");
                System.out.println("3. Office hours");

                int c = scanner.nextInt();
                scanner.nextLine();

                if (c == 1) {
                    System.out.println("Enter new timings:");       //update timings
                    String new_timings = scanner.nextLine();
                    course.setTimings(new_timings);
                    System.out.println("Timings updated");
                } else if (c == 2) {
                    System.out.println("Enter new credits:");       //update credits
                    int new_credits = scanner.nextInt();
                    scanner.nextLine();
                    course.setCredits(new_credits);
                } else if (c == 3) {
                    System.out.println("Enter new office hours:");  //update office hours
                    this.office_hours = scanner.nextLine();
                    System.out.println("Your office hours are: " + office_hours);
                }
                break;
        }
    }

    public void View_Enrolled_Students(List<Student> students) {
        System.out.println("Enrollment for your course: " + course.getTitle());

        for (Student x : students) {
            if (x.getCurrent_courses().contains(course)) {
                if(!Enrolled_Students.contains(x)){
                    Enrolled_Students.add(x);
                }
            }
        }

        if (Enrolled_Students.isEmpty()) {
            System.out.println("No students have enrolled in your course yet");
        }
        else {
            System.out.println("Enrolled students:");
            for (Student x : Enrolled_Students) {
                System.out.println("Student: " + x.getName() + " | Email: " + x.getEmail());
            }
        }
    }

    //Assignment 2
    public void your_feedbacks(){

        int feed = 0;

        for(Student x : Enrolled_Students){
            List<Feedback<Integer, String, String>> feedbacks = x.Get_feedbacks();

            for (Feedback<Integer, String, String> f : feedbacks) {
                String Title = f.getCourse().getFirst();
                if(Title.equals(course.getTitle())){
                    feed = 1;
                    System.out.println("Rating: " + f.getRate().getFirst());
                    System.out.println("Comment: " + f.getText().getFirst());
                    System.out.println("------------------------------------------------------------------------------");
                }
            }
        }
        if(feed == 0){
            System.out.println("FEEDBACKS EMPTY!");
        }
    }
}

