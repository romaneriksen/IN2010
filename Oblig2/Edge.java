public class Edge {

    Movie movie;
    float rate;
    Actor actor1;
    Actor actor2;


    public Edge(Movie m, Actor a1, Actor a2){
        movie = m;
        rate = movie.rate;
        actor1 = a1;
        actor2 = a2;
    }
    
}
