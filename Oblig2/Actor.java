import java.util.*;


public class Actor implements Comparable<Actor> {
    String id;
    String name;
    int index;

    ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Actor> neighbours = new ArrayList<Actor>();
    HashMap<String, Movie> bestMovie = new HashMap<String, Movie>();
    boolean visited = false;
    float cost;
    Actor parent;

    public Actor(String i, String n, int ix){
        id = i;
        name = n;
        index = ix;
    }

    @Override
    public int compareTo(Actor act) {
        if (cost == act.cost) {
            return 0;
        }
        else if (cost > act.cost) {
            return 1;
        }
        return -1;
        // returnerer 0 hvis cost = act.cost
        // returnerer negativt verdi hvis cost < act.cost
        // returnerer positiv verdi hvis cost > act.cost

    }

    void addActor(Actor actor) {
        neighbours.add(actor);
    }

    void addEdge(Edge e){
        edges.add(e);
    }

    void addMovies(Movie m){
        movies.add(m);

    }
    
    void addBestMovie(Actor actor, Movie movie) {

        if (bestMovie.get(actor.id) == null) {
            bestMovie.put(actor.id, movie);
        }
        else {
            if (bestMovie.get(actor.id).rate < movie.rate) {
                bestMovie.put(actor.id, movie);
            }
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name + " Id: " + id + ". Antall filmer: "  + movies.size();
    }
    
}
