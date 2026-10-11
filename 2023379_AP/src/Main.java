import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Professor> professors = new ArrayList<>();
    private static List<TA> TAs = new ArrayList<>();

    private static Administrator admin = new Administrator("admin", "a1", "Admin");


    public static void main(String[] args) throws CourseFullException, InvalidLoginException {

        Scanner scanner = new Scanner(System.in);

        testingData();

        while (true) {
            System.out.println("WECLOME TO IIITD COURSE REGISTRATION SYSTEM");
            System.out.println("1. Enter the System");
            System.out.println("2. Exit the System");
            System.out.println("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 2) {
                break;
            }
            System.out.println("Login as:");
            System.out.println("1. Student");
            System.out.println("2. Professor");
            System.out.println("3. Administrator");
            System.out.println("4. Teaching Assistant");
            System.out.println("\nEnter your choice: ");
            int c = scanner.nextInt();
            scanner.nextLine();

            try
            {
                switch (c)
                {
                    case 1:
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();

                        Student is_validstudent = null;
                        for (Student x : students) //checking if the entered details valid or not
                        {
                            if (x.getEmail().equals(email) && x.getPassword().equals(password))
                            {
                                is_validstudent = x;
                                break;
                            }
                        }

                        if (is_validstudent != null)
                        {
                            Student_Menu(is_validstudent, courses);
                        }
                        else
                        {
                            throw new InvalidLoginException("Email or password is wrong.");
                        }
                        break;

                    case 2: // Professor
                        System.out.print("Enter email: ");
                        email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();

                        Professor is_validprofessor = null;
                        for (Professor x : professors)
                        {    //checking if the entered details valid or not
                            if (x.getEmail().equals(email) && x.getPassword().equals(password))
                            {
                                is_validprofessor = x;
                                break;
                            }
                        }

                        if (is_validprofessor != null)
                        {
                            Professor_Menu(is_validprofessor, students);
                        }
                        else
                        {
                            throw new InvalidLoginException("Email or password is wrong.");
                        }
                        break;

                    case 3: // Administrator
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();

                        if (admin.getPassword().equals(password))
                        {
                            Admin_Menu(admin, courses, students, professors);
                        }
                        else
                        {
                            throw new InvalidLoginException("Password is wrong.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter email: ");
                        email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();

                        TA is_validTA = null;
                        for (TA x : TAs) 
                        {
                            if (x.getEmail().equals(email) && x.getPassword().equals(password))
                            {
                                is_validTA = x;
                                break;
                            }
                        }

                        if (is_validTA != null)
                        {
                            TAMenu(is_validTA, students);
                        }
                        else
                        {
                            throw new InvalidLoginException("Email or password is wrong.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
            catch (InvalidLoginException | CourseFullException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void Student_Menu(Student student, List<Course> courses) throws CourseFullException
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("1. Handle courses: ");
            System.out.println("    1i. View Available Courses");
            System.out.println("    1ii. Register for Courses");
            System.out.println("    1iii. Drop Courses");
            System.out.println("2. View Student Information:");
            System.out.println("    2i. View Schedule: ");
            System.out.println("    2ii. Track Academic Progress");
            System.out.println("3. Handle Complaints: ");
            System.out.println("    3i. Submit Complaints");
            System.out.println("    3ii. View Complaints");
            System.out.println("4. Enter feedbacks"); //ASSIGNMENT 2
            System.out.println("5. Log out");
            System.out.println("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1i":
                    student.View_courses(courses);
                    break;
                case "1ii":
                    try
                    {
                        student.Register_courses(courses);
                    }
                    catch (CourseFullException e)
                    {
                        System.out.println("An error occured: " + e.getMessage());
                    }
                    break;
                case "2i":
                    student.View_schedule();
                    break;
                case "2ii":
                    student.Calculate_CGPA();
                    break;
                case "1iii":
                    student.Drop_Course();
                    break;
                case "3i":
                    student.Submit_complaint();
                    break;
                case "3ii":
                    int i = 1;
                    for (Complaint comp:student.Complaints){
                        System.out.println(String.valueOf(i) + ". " + comp.Des + " " +comp.status);
                        i++;
                    }
                    break;
                case "4":
                    student.Enter_feedbacks();
                    break;
                case "5":
                    System.out.println("Logging out...");
                    System.out.println("Logged out");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void Professor_Menu(Professor professor, List<Student> students)
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("1. Manage Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. View feedbacks");   //ASSIGNMENT 2
            System.out.println("4. Log out");
            System.out.println("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    professor.Manage_Courses();
                    break;
                case "2":
                    professor.View_Enrolled_Students(students);
                    break;
                case "3":
                    professor.your_feedbacks();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    System.out.println("Logged out");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void Admin_Menu(Administrator admin, List<Course> courses, List<Student> students, List<Professor> professors)
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Records");
            System.out.println("3. View and Handle Complaints");
            System.out.println("4. Logged out...");
            System.out.println("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    admin.ManageCourseCatalog(courses,professors);
                    break;
                case "2":
                    admin.ManageStudentRecords(students);
                    break;
                case "3":
                    admin.HandleComplaints(students);
                    break;
                case "4":
                    System.out.println("Logging out...");
                    System.out.println("Logged out");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void TAMenu(TA ta, List<Student> students)
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("1. View student");
            System.out.println("2. Log out");
            System.out.println("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    ta.view_student(students);
                    break;
                case "2":
                    System.out.println("Logging out...");
                    System.out.println("Logged out");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }



    private static void testingData()
    {
        Course course1=new Course("CS101", "INTRO TO PYTHON", 4, "MR. SKY","9:30AM - 11:00AM" ,1);
        courses.add(course1);
        Course course2 = new Course("MTH101", "ALGEBRA", 4, "Dr. MITCHELL", "11:30AM - 1:00PM" , 1);
        courses.add(course2);
        Course course3 = new Course("CS201", "DATA STRUCTURES AND ALGORITHMS", 4, "MR. DAVID", "9:00AM - 10:30AM", 2);
        courses.add(course3);
        Course course4 = new Course("MATH201", "PROBABILITY", 4, "Dr. ADAM", "11:00AM - 12:30PM", 2);
        courses.add(course4);
        Course course5 = new Course("CS301", "ADVANCED PROGRAMMING", 4, "MR. SS", "3:00PM - 4:30PM", 3);
        courses.add(course5);
        Course course6 = new Course("MATH301", "CALCULUS", 4, "Dr. JOHNSON", "11:00AM - 12:30PM", 3);
        courses.add(course6);

        // Create professors list
        professors.add(new Professor("prof1", "p1", "MR. SKY", course1));
        professors.add(new Professor("prof2", "p2", "Dr. MITCHELL", course2));
        professors.add(new Professor("prof3", "p3", "MR. DAVID", course3));
        professors.add(new Professor("prof4", "p4", "Dr. ADAM", course4));
        professors.add(new Professor("prof5", "p5", "MR. SS", course5));
        professors.add(new Professor("prof6", "p6", "Dr. JOHNSON", course6));


        // Create students list
        students.add(new Student("student1", "s1", "PRAGYA"));
        students.add(new Student("student2", "s2", "AAKRITI" ));
        students.add(new Student("student3", "s3", "AAKASH"));

        TAs.add(new TA("ta1", "t1", "THOMAS", course1));
        TAs.add(new TA("ta2", "t2", "TOM", course2));
        TAs.add(new TA("ta3", "t3", "JERRY", course3));
        TAs.add(new TA("ta4", "t4", "JAMES", course4));
        TAs.add(new TA("ta5", "t5", "EMILY", course5));
        TAs.add(new TA("ta6", "t6", "PAT", course6));




    }
}