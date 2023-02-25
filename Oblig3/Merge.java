public class Merge extends Sorter {
    
    void sort() {

        merge_sort(A, A.length);
    }

    int[] merge_sort(int[] A, int n) {
        
        if (lt(n, 2)) {
            return A;
        }

        int mid = n / 2;
        int[] A_1 = new int[mid];
        int[] A_2 = new int[n - mid];
    
        for (int i = 0; lt(i, mid); i++) {
            A_1[i] = A[i];
        }
        for (int i = mid; lt(i, n); i++) {
            A_2[i - mid] = A[i];
        }
        merge_sort(A_1, mid);
        merge_sort(A_2, n - mid);
    
        return merge(A_1, A_2, A);
    }



    int[] merge(int[] A_1, int[] A_2, int[] A) {

        int i = 0;
        int j = 0;

        while (lt(i, A_1.length) && lt(j, A_2.length)) {

            if (lt(A_1[i], A_2[j])) {
                A[i+j] = A_1[i];
                i++;
            }
            else {
                A[i+j] = A_2[j];
                j++;
            }
        }
        while (lt(i, A_1.length)) {
            A[i+j] = A_1[i];
            i++;
        }
        while (lt(j, A_2.length)) {
            A[i+j] = A_2[j];
            j++;
        }
        return A;
    }


    String algorithmName() {
        return "merge";
    }

}
