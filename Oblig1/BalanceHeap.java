import java.util.*;
import java.io.*;

class BalanceHeap {
    public static void main(String[] args) throws IOException { 
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N;
        while((N = br.readLine()) != null){
            heap.offer(Integer.parseInt(N));
        }
        balanceHeap(heap, 0, heap.size()-1);    
    }

    static void balanceHeap(PriorityQueue<Integer> heap, int start, int end) {
        if (start > end) {
            return;
        }
        int middle = (start+end)/2;
        PriorityQueue<Integer> leftHeap = new PriorityQueue<>();
        for (int i = 0; i < middle; i++) {
            leftHeap.offer(heap.poll());
        }
        System.out.println(heap.poll());
        balanceHeap(heap, 0, heap.size()-1);
        balanceHeap(leftHeap, 0, leftHeap.size()-1); 
    }
}