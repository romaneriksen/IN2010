import java.util.ArrayList;
import java.io.*;

class Kattunge {
    public static void main(String[] args) throws IOException {
        ArrayList<Node> nodes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int start = Integer.parseInt(br.readLine());
        Node kattunge = new Node(start);
        nodes.add(kattunge);
        String[] str = br.readLine().split(" ");
        int[] line = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            line[i] = Integer.parseInt(str[i]);
        }
        Boolean inList;

        while (line[0] != -1) {
            inList = false;
            Node parent = new Node(line[0]);
            for (int i = 0; i < nodes.size(); i++){
                if (line[0] == nodes.get(i).data) {
                    parent = nodes.get(i);
                    inList = true;
                }
            }
            if (!inList) {
                nodes.add(parent);
            }
            for (int i = 1; i < line.length; i++) {
                inList = false;
                Node v = new Node(line[i]);
                for (int j = 0; j < nodes.size(); j++){
                    if (line[i] == nodes.get(j).data) {
                        v = nodes.get(j);
                        inList = true;
                    }
                }
                if (!inList) {
                    nodes.add(v);
                }  
                v.setParent(parent); 
            }
            str = br.readLine().split(" ");
            line = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                line[i] = Integer.parseInt(str[i]);
            }
        }
        findPath(kattunge);
    }
    
    static class Node {

        int data;
        Node parent;

        Node(int x) {
            data = x;
        }

        void setParent(Node p) {
            parent = p;
        }
    } 

    static void findPath(Node x) {

        Node v = x;
        while (v.parent != null) {
            System.out.println(v.data);
            v = v.parent;
        }
        System.out.println(v.data);
    }
}
