import java.util.*;
import java.io.*;

class BalanceArray {
    public static void main(String[] args) throws IOException { 
        ArrayList<Integer> arrayList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N;
        while((N = br.readLine()) != null){
            arrayList.add(Integer.parseInt(N));
        }
        int[] list = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            list[i] = arrayList.get(i);
        }
        balanceArray(list, 0, list.length-1);    
    } 

    static void balanceArray(int[] list, int start, int end) {
        if (start > end) {
            return;
        }
        int middle = (start+end)/2;
        System.out.println(list[middle]);
        balanceArray(list, middle+1, end);
        balanceArray(list, start, middle-1);
    }
}