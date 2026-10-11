import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Student extends User  {     //INTERFACE AND INHERITANCE

    private List<Course> Registered_courses;
    public List<Complaint> Complaints;
    public List<Course> Semester_courses;
    public List<String> Prerequisites;
    private int Current_Credits = 0;


    public List<Feedback<Integer, String, String>> feed = new ArrayList<>();//for Assignment-2

    public Student(String email, String password, String name) {
        super(email, password, name);

        this.Registered_courses = new ArrayList<>();
        this.Complaints = new ArrayList<>();
        this.Semester_courses = new ArrayList<>();  //courses which student wanted to see for a particular semester
        this.Prerequisites = new ArrayList<>();
        Prerequisites.add("Prerequisites: None");
        Prerequisites.add("Prerequisites: CS101 - INTRO TO PYTHON");
        Prerequisites.add("Prerequisites: CS201 - DATA STRUCTURES");
    }


    //1. VIEW AVAILABLE COURSES
    public void View_courses(List<Course> All_courselist) {
        Scanner scanner = new Scanner(System.in);
        this.Semester_courses.clear();

        System.out.println("Select your semester: 1, 2, 3");
        int selectedSemester = scanner.nextInt();
        scanner.nextLine();

        for (Course x : All_courselist) {
            if (x.getSemester() == selectedSemester) {
                this.Semester_courses.add(x);
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                System.out.println("Course Code: " + x.getCode() + " | Title: " + x.getTitle() +" | Weekly Schedule: " + x.getWeeklySchedule(x.getSemester())
                        + " | Timings: " + x.getTimings()
                        + " | Credits: " + x.getCredits() + " | Professor: " + x.getProfessor()
                        + " | Semester: " + x.getSemester());

                if (selectedSemester == 1) {
                    System.out.println(Prerequisites.get(0));
                } else if (selectedSemester == 2) {
                    System.out.println(Prerequisites.get(1));
                } else if (selectedSemester == 3) {
                    System.out.println(Prerequisites.get(2));
                }
            }
        }
    }

    //2. REGISTER FOR COURSES
    public void Register_courses(List<Course> All_courses) throws CourseFullException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter course code you want to register: ");
        String courseCode = scanner.nextLine();

        for (Course x : All_courses) {
            if (x.getCode().equals(courseCode)) {
                if (Registered_courses.contains(x)) {
                    System.out.println("You have already registered for this course");
                }
                else if (x.getCredits() + Current_Credits > 20) {   //if credit limits exceeds than 20 prints message
                    System.out.println("Credit limit exceeded!");
                }
                else if((x.getStudentCount()+1) > x.getMaxStudentCount())
                {
                    throw new CourseFullException("Course " + x.getCode() + " is full.");
                }
                else {
                    Registered_courses.add(x);
                    System.out.println("All prerequisites met");
                    System.out.println("Course registered: " + x.getTitle());
                    Current_Credits +=x.getCredits();
                    x.addStudent(this);
                }
                break;
            }
        }
    }

    //3. VIEW SCHEDULE
    public void View_schedule() {
        if (Registered_courses.isEmpty()){
            System.out.println("No course registered");
        }
        else{
            for (Course x : Registered_courses) {
                System.out.println( "Course : " + x.getTitle() + "\nWeekly Schedule : "+ x.getWeeklySchedule(x.getSemester())
                        + "\nProfessor : " + x.getProfessor() + "\nLocation :" + x.getLocation(x.getSemester()) + "\nTimings : " + x.getTimings());

                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    //4. TRACK ACADEMIC PROGRESS
    public void Calculate_CGPA() {

        Course x = Semester_courses.get(0);
        if(x.getSemester()==1){
            System.out.println("No courses completed in previous semester");
        }
        double SGPA1, CGPA1= 0;
        SGPA1 = (6+7)/2.0;//SGPA OF SEM 1
        CGPA1 = SGPA1;           //CGPA FOR SEM 1
        if(x.getSemester()==2){
            System.out.println("SGPA: "+ SGPA1 + "CGPA: " + CGPA1);
        }
        else if(x.getSemester()==3) {
            double SGPA2 = (7+8)/2.0; //SGPA FOR SEM 1,2
            double CGPA2 = (SGPA1 + SGPA2) / 2.0;   //CGPA FOR SEM 1,2
            System.out.println("SGPA: "+ SGPA2 + "CGPA: " + CGPA2);
        }

    }

    //5. DROP COURSES
    public void Drop_Course() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course code to drop: ");
        String code = scanner.nextLine();
        for (Course x: Registered_courses) {
            if (x.getCode().equals(code)) {
                Registered_courses.remove(x);
                x.removeStudent(this);
                System.out.println(code + " : Course successfully dropped");
                return;
            }
        }
        System.out.println("Course does not exist");
    }

    //6. SUBMIT COMPLAINTS
    public void Submit_complaint() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter complaint description and date: ");
        String Complaint_Description = scanner.nextLine();


        Complaints.add(new Complaint(Complaint_Description));  //calls the Complaint class constructor
        System.out.println("Complaint submitted.");
    }

    //ASSIGNMENT-2
    // ENTER THE FEEDBACKS
    public void Enter_feedbacks(){
        if(!Registered_courses.isEmpty()){
            Scanner scanner = new Scanner(System.in);
            int i = 0;
            System.out.println("Your subjects: ");
            for(Course x : Registered_courses){
                System.out.println(++i + ": " + x.getTitle());
            }
            System.out.println("For which of the subject you want to give feedback: ");
            String subject = scanner.nextLine();

            System.out.println("Ratings(1-10): ");
            int ratings = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Text: ");
            String text = scanner.nextLine();

            Feedback<Integer, String, String> f = new Feedback<>(ratings, text, subject);
            feed.add(f);
        }
        else{
            System.out.println("No registered courses");
        }
    }

    public List<Feedback<Integer, String, String>> Get_feedbacks(){
        return feed;
    }
    public List<Course> getCurrent_courses() {  //ENCAPSULATION
        return Registered_courses;
    }
}
