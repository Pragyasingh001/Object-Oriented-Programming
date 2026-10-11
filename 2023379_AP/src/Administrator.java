import java.util.List;
import java.util.Scanner;


public class Administrator extends User  {
    public Administrator(String email, String password, String name) {
        super(email, password, name);
    }

    public void View_courses(List<Course> courses){
        System.out.println("Course catalog:");
        for (Course x : courses) {
            System.out.println(x);
        }
    }

    public void ManageCourseCatalog(List<Course> courses, List<Professor> professors) {
        System.out.println("1.View all courses");
        System.out.println("2.Add courses");
        System.out.println("3.Delete courses");

        System.out.println("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1){
            View_courses(courses);
        }
        else if(choice==2){
            System.out.print("Enter the course code: ");
            String course_code = scanner.nextLine();

            System.out.print("Enter course title: ");
            String course_title = scanner.nextLine();

            System.out.print("Enter credits: ");
            int course_credits = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter professor name: ");
            String professor_name = scanner.nextLine();

            System.out.print("Enter timings: ");
            String course_timings = scanner.nextLine();

            System.out.print("Enter semester: ");
            int semester = scanner.nextInt();
            scanner.nextLine();


            Course newcourse = new Course(course_code, course_title, course_credits, professor_name, course_timings, semester);
            courses.add(newcourse);
            System.out.println("Course added successfully");

            System.out.print("Enter professor Email of the course you created: ");
            String email = scanner.nextLine();

            System.out.println("Enter professor password of the course you created:");
            String pass = scanner.nextLine();

            professors.add(new Professor(email, pass, professor_name, newcourse));
        }
        else if(choice == 3){
            System.out.print("Enter the course code you want to delete: ");
            String code = scanner.nextLine();
            scanner.nextLine();
            for(Course x: courses){
                if(x.getCode().equals(code)){
                    courses.remove(x);
                    break;
                }
                else{
                    System.out.println("course not found");
                }
            }
        }
    }

    public void ManageStudentRecords(List<Student> students) {

        System.out.println("1.VIEW STUDENT DETAILS");
        System.out.println("2.UPDATE STUDENT DETAILS");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice == 1){
            System.out.println("Students Details: ");
            for (Student x : students) {
                System.out.println("Student: " + x.getName() + " | Email: " + x.getEmail() +
                        " | Student current semester courses: "+ x.getCurrent_courses());
            }
        }

        if(choice==2){
            System.out.println("Enter the student's email whose name you want to update:");
            String new_email = scanner.nextLine();
            Student newstudent = null;

            for(Student x: students){
                if(x.getEmail().equals(new_email)){
                    newstudent = x;
                    break;
                }
            }
            System.out.println("enter the new name for the student: ");
            String newname = scanner.nextLine();
            assert newstudent != null;
            newstudent.setName(newname);
        }
    }

    public void HandleComplaints(List<Student> students){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the EMAIL of student whose complaint you want to resolve: ");
        String student_email = scanner.nextLine();

        for(Student x: students){
            if(x.getEmail().equals(student_email))
            {
                int i = 1;
                for (Complaint comp:x.Complaints)       //view the complaints
                {
                    System.out.println(String.valueOf(i) + ". " + comp.Des + " " +comp.status);
                    i++;
                }
                System.out.println("Enter Complaint ID to resolve:");
                int ID = scanner.nextInt();
                if(ID> 0 && ID<=x.Complaints.size())
                {
                    x.Complaints.get(ID-1).status = "RESOLVED";     //updating the complaints
                    System.out.println("The Complaint has been resolved successfully");
                }
                else
                {
                    System.out.println("Invalid Complaint ID.");
                }
                break;
            }
        }
    }

}

