import java.util.ArrayList;
import java.util.List;

public class Course {
    private String Code;
    private String Title;
    private int Credits;
    private String Professor;
    private String Timings;
    private int Semester;
    private List<Student> Enrolled_Students=new ArrayList<>();
    private int maxStudentCount=50;

    public Course(String code, String title, int credits, String professor, String Timings, int semester) {
        this.Code = code;
        this.Title = title;
        this.Credits = credits;
        this.Professor = professor;
        this.Timings = Timings;
        this.Semester = semester;
    }
    //ENCAPSULATION
    public String getCode() {
        return Code;
    }

    public String getTitle() {
        return Title;
    }

    public int getCredits() {
        return Credits;
    }
    public void setCredits(int credits){           //setter: setCredits for Professor to set new credits
        this.Credits = credits;
    }

    public List<Student> getStudents(List<Student> List_Students)
    {
        if (!Enrolled_Students.isEmpty())
        {
            for (Student x : List_Students) {
                if (x.getCurrent_courses().contains(this)) {
                    if(!Enrolled_Students.contains(x)){
                        Enrolled_Students.add(x);
                    }
                }
            }
            return Enrolled_Students;
        }
        else {
            return null;
        }
    }

    public int getStudentCount()
    {
        if (Enrolled_Students == null) {
            return 0;
        }
        return Enrolled_Students.size();
    }

    public void addStudent(Student student)
    {
        if(student!=null)
        {
            this.Enrolled_Students.add(student);
        }
    }
    public void removeStudent(Student student)
    {
        if(student!=null)
        {
            this.Enrolled_Students.remove(student);
        }
    }

    public int getMaxStudentCount()
    {
        return this.maxStudentCount;
    }

    public int getSemester() {
        return Semester;
    }

    public String getProfessor(){
        return Professor;
    }

    public String getTimings(){
        return Timings;
    }
    public void setTimings(String time){     //setter: setTimings for Professor to set new timings
        this.Timings = time;
    }


    public String getLocation(int semester){
        if(Semester==1){
            return "LECTURE HALL COMPLEX ";
        }
        else if(Semester==2){
            return "ACADEMIC BUILDING";
        }
        else{
            return "RESEARCH BUILDING";
        }

    }

    public String getWeeklySchedule(int Semester) {
        if(Semester==1){
            return "MONDAY, WEDNESDAY, SATURDAY";
        }
        else if(Semester==2){
            return "TUESDAY, THURSDAY";
        }
        else{
            return "MONDAY SATURDAY";
        }
    }

    @Override                   //RUNTIME POLYMORPHISM
    public String toString() {
        return "Course Code: " + Code + ", Title: " + Title + ", Credits: " + Credits + ", Professor: " + Professor + ", Semester: " + Semester;
    }
}

