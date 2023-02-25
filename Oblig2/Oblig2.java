import java.util.*;
import java.io.*;
import java.util.Arrays;

public class Oblig2 {

    static HashMap<String, Movie> movies = new HashMap<String, Movie>();
    static HashMap<String, Actor> actors = new HashMap<String, Actor>();
    static ArrayList<Edge> edges = new ArrayList<Edge>();
    static Actor[] pred;
    static HashMap<Integer, Integer> components = new HashMap<Integer, Integer>();

    public static void main(String[] args) throws IOException {

        long startTime = System.nanoTime();

        readMovies("movies.tsv");
        readActors("actors.tsv");

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000000 + "ms");

        createGraph();

        // Linje 18-25 --> Oppgave 1 

        pred = new Actor[actors.size()];

        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime/1000000 + "ms");

        
        Actor donald_glover = actors.get("nm2255973");
        Actor jeremy_irons = actors.get("nm0000460"); 
        
        Actor scarlett_johansson = actors.get("nm0424060");
        Actor denzel_washington = actors.get("nm0000243");
        
        Actor carrie_coon = actors.get("nm4689420");
        Actor julie_delpy = actors.get("nm0000365");
    
        Actor christian_bale = actors.get("nm0000288");
        Actor angelina_jolie = actors.get("nm0001401");
        
        Actor atle_antonsen = actors.get("nm0031483");
        Actor michael_k_williams = actors.get("nm0931324");
        
        System.out.println("Shortest path for given actors: "+"\n");

        find_shortest_path(donald_glover, jeremy_irons);
        find_shortest_path(scarlett_johansson, denzel_washington);
        find_shortest_path(carrie_coon, julie_delpy);
        find_shortest_path(christian_bale, angelina_jolie);
        find_shortest_path(atle_antonsen, michael_k_williams);

        // Linje 53-57 --> Oppgave 2

        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime/1000000 + "ms");
        
        find_shortest_path_weight(donald_glover, jeremy_irons);
        find_shortest_path_weight(scarlett_johansson, denzel_washington);
        find_shortest_path_weight(carrie_coon, julie_delpy);
        find_shortest_path_weight(christian_bale, angelina_jolie);
        find_shortest_path_weight(atle_antonsen, michael_k_williams);

        // Linje 65-57 --> Oppgave 3

        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("    ");
        System.out.println(totalTime/1000000 + "ms");
        
        all_components();

        // Linje 78 --> Oppgave 4

        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime/1000000 + "ms");


        System.out.println("\nNodes: " + actors.size());
        System.out.println("Edges: " + edges.size());

        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime/1000000 + "ms");
        
    }


    static void all_components() {
        System.out.println("");

        for (Actor actor: actors.values()) {
            actor.visited = false;
        }

        for (Actor actor : actors.values()) {
            if (actor.visited == false) {
                find_component(actor);
            }
        }
        int[] array = new int[components.size()];

        int k = 0;
        for (Integer key: components.keySet()){  
			array[k] = key;
            k++;
		} 
        Arrays.sort(array);

        for (int h = array.length-1; h >= 0; h--) {
            System.out.println("There are "+components.get(array[h])+" components of size "+array[h]);
        }
    }

    static void find_component(Actor a) {

        int elements_in_component = 1;
        Actor start = a;
        LinkedList<Actor> queue = new LinkedList<Actor>();

        queue.add(start);
        start.visited = true;

        while (!queue.isEmpty()) {

            Actor act = queue.remove();
            for (int i = 0; i < act.neighbours.size(); i++) {
                Actor actor = act.neighbours.get(i); 
                if (actor.visited == false) {
                    actor.visited = true;
                    elements_in_component++;
                    //visited_actors.add(actor);  
                    queue.add(actor);   
                }
            }
        }
        if (components.get(elements_in_component) == null) {
            components.put(elements_in_component, 1);
        }
        else {
            int antall = components.get(elements_in_component) + 1;
            components.put(elements_in_component, antall);
        }
        
    }



    static boolean find_shortest_path_weight(Actor a1, Actor a2) {


        int verts = actors.size();
        int k = 0;
        for (Actor actor: actors.values()) {
            actor.visited = false;
            pred[k] = null;
            k++;
            actor.cost = Integer.MAX_VALUE;
        }

        
        PriorityQueue<Actor> queue = new PriorityQueue<Actor>();
        ArrayList<Actor> visited_actors = new ArrayList<Actor>();

        Actor current = a1;
        Actor end = a2;

        float edgeDistance = -1;
        float newDistance = -1;

        
        current.cost = 0;
        queue.add(current);

        while (!queue.isEmpty()) {

            Actor act = queue.poll();
            visited_actors.add(act);
            act.visited = true;

            for (int i = 0; i < act.neighbours.size(); i++) {
                Actor actor = act.neighbours.get(i); 
                if (actor.visited == false) {
                    
                    edgeDistance = 10 - act.bestMovie.get(actor.id).rate;
                    newDistance = act.cost + edgeDistance;

                    if (newDistance < actor.cost) {
                        pred[actor.index] = act;
                        actor.cost = newDistance;
                        queue.add(actor);
                    }
                }
            }
        }

        LinkedList<Actor> path = new LinkedList<Actor>();
        LinkedList<Movie> moviePath = new LinkedList<Movie>();

        Actor crawl = end;
        float cost = crawl.cost;
        path.add(crawl);
        while (pred[crawl.index] != null) {
            Actor a = pred[crawl.index];
            path.add(a);
            crawl = pred[crawl.index];
        }

        for (int j = path.size() - 1; j > 0; j--) {
            Actor act1 = path.get(j);
            Actor act2 = path.get(j-1);
            Movie m = act1.bestMovie.get(act2.id);
            
            moviePath.add(m);
        }


        System.out.println("");
        System.out.println(path.get(path.size()-1).name);
        int y = 0;
        for (int x = path.size() - 2; x >= 0; x--) {
            System.out.println("===["+moviePath.get(y)+"] ===> "+path.get(x).name);
            y++;
        }
        System.out.println(cost);
        return false;
    }


    static boolean find_shortest_path(Actor a1, Actor a2) {

        
        LinkedList<Actor> queue = new LinkedList<Actor>();
        ArrayList<Actor> visited_actors = new ArrayList<Actor>();

        for (int i = 0; i < pred.length; i++) {
            pred[i] = null;
        }

        Actor current = a1;
        current.visited = true;
        Actor end = a2;

        queue.add(current);

        while (!queue.isEmpty()) {

            Actor act = queue.remove();
            for (int i = 0; i < act.neighbours.size(); i++) {
                Actor actor = act.neighbours.get(i); 
                if (actor.visited == false) {
                    actor.visited = true;
                    visited_actors.add(actor);
                    pred[actor.index] = act;
                    queue.add(actor);   

                    if (actor == end) {

                        for (Actor actor2 : visited_actors) {
                            actor2.visited = false;
                        }
                        LinkedList<Actor> path = new LinkedList<Actor>();
                        LinkedList<Movie> moviePath = new LinkedList<Movie>();

                        Actor crawl = end;
                        path.add(crawl);
                        while (pred[crawl.index] != null) {
                            Actor a = pred[crawl.index];
                            path.add(a);
                            crawl = pred[crawl.index];
                        }

                        for (int j = path.size() - 1; j > 0; j--) {
                            Actor act1 = path.get(j);
                            Actor act2 = path.get(j-1);
                            for (Edge edge : edges) {
                                if (edge.actor1 == act1 || edge.actor1 == act2) {
                                    if (edge.actor2 == act1 || edge.actor2 == act2) {
                                        moviePath.add(edge.movie);
                                        break;
                                    }
                                }
                            }
                        }

                        System.out.println(path.size());

                        System.out.println(path.get(path.size()-1).name);
                        int y = 0;
                        for (int x = path.size() - 2; x >= 0; x--) {
                            System.out.print("===["+moviePath.get(y)+"] ===> "+path.get(x).name);
                            System.out.println("");
                            y++;
                        }
                        System.out.println("");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static void addEgdeAdj(ArrayList<ArrayList<Actor>> adj_list, Actor a1, Actor a2) {
        adj_list.get(a1.index).add(a2);
        adj_list.get(a2.index).add(a1);
    }

    static void createGraph() {

    
        for (String m : movies.keySet()) {
            Movie movie = movies.get(m);
            for (int i = 0; i < movie.actors.size(); i++) {
                for (int j = i+1; j < movie.actors.size(); j++) {
                    Actor a1 = movie.actors.get(i);
                    Actor a2 = movie.actors.get(j); 

                    Edge edge = new Edge(movie, a1, a2);

                    a1.addEdge(edge);
                    a1.addActor(a2);
                    a1.addBestMovie(a2, movie);
                    
                    a2.addEdge(edge);
                    a2.addActor(a1);
                    a2.addBestMovie(a1, movie);
    
                    edges.add(edge);
                }
            }
        }
    }

    
    // Metode for å lese inn filmer
    static void readMovies(String fil) throws IOException {
       
        BufferedReader Mfile = new BufferedReader(new FileReader("movies.tsv"));
        String N;

        // lokke som lager movie-objekter og legger de i Hashmapet for movies
        while((N = Mfile.readLine()) != null){
            String[] biter = N.split("\t");
            movies.put(biter[0], new Movie(biter[0], biter[1], Float.parseFloat(biter[2])));
        }
        Mfile.close();
    }

    // Metode for å lese inn skuespillere
    static void readActors(String fil) throws IOException {
        
        BufferedReader Afile = new BufferedReader(new FileReader("actors.tsv"));
        String N;
        int k = 0;

        // lokke som lager actor-objekter og legger de i Hashmapet for actors 
        while((N = Afile.readLine()) != null && k <1000000){
            String[] biter = N.split("\t");
            Actor a = new Actor(biter[0], biter[1], k);

            // lokke som bare legger til de gyldige filmene for hver skuespiller
            for (int j = 2; j < biter.length; j++) {
                if (movies.get(biter[j]) != null){
                    a.addMovies(movies.get(biter[j]));
                    movies.get(biter[j]).addActor(a);
                }
            }
            actors.put(biter[0], a);
            k++;
        }
        Afile.close();
    }   



}
