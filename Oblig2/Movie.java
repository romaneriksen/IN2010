import java.util.*;

public class Movie {

    String name;
    String id;
    float rate;
    ArrayList<Actor> actors = new ArrayList<Actor>();

    public Movie(String i, String n, float r){
        name = n;
        id = i;
        rate = r;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name + " (" + rate + ")";
    }

    void addActor(Actor a){
        actors.add(a);

    }
    
}