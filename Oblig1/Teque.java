import java.io.*;

class Teque {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            String cmd = line[0];
            int x = Integer.parseInt(line[1]);
            if (cmd.equals("push_back")) {
                push_back(x);
            }
            else if (cmd.equals("push_front")) {
                push_front(x);
            }
            else if (cmd.equals("push_middle")) {
                push_middle(x);
            }
            else if (cmd.equals("get")) {
                get(x);
            }
        }
    }

    static Node head;
    static Node tail; 
    static int counter = 0;  
    
    static class Node {

        int data;
        Node next;

        Node(int x) {
            data = x;
            counter++;
        }
    }

    static int size() {
        return counter;
    }
    static void printOut() {
        Node node = head;
        for (int i = 0; i < size(); i++) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    static void push_back(int x) {
    
        Node v = new Node(x);
        if (head == null) {
            head = v;
            tail = v;
        }
        else {
            tail.next = v;
            tail = v;
        }
    }

    static void push_front(int x) {

        Node v = new Node(x);
        if (head == null) {
            head = v;
            tail = v;
        }
        else {
            v.next = head;
            head = v;
        }
    }

    static void push_middle(int x) {

        Node v = new Node(x);
        if (head == null) {
            head = v;
            tail = v;
        }
        else {
            Node node = head;
            for (int i = 0; i < (size()-2)/2; i++) { // Oppdaterer teller foer innsetting
                node = node.next;
            }
            v.next = node.next;
            node.next = v;
        }    
    }

    static void get(int i) {
        Node node = head;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        System.out.println(node.data);
    }
}