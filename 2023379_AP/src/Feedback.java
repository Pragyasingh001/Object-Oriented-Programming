import java.util.ArrayList;
import java.util.List;

public class Feedback<X,Y,Z> {
    private final List<X> Rate = new ArrayList<>();
    private final List<Y> Text = new ArrayList<>();
    private final List<Z> course = new ArrayList<>();

    public Feedback(X x, Y y, Z z){
        Rate.add(x);
        Text.add(y);
        course.add(z);

    }

    public List<X> getRate() {
        return Rate;
    }

    public List<Y> getText() {
        return Text;
    }

    public List<Z> getCourse(){
        return course;
    }
}

