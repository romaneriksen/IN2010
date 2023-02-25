class Quick extends Sorter {

    void sort() {

        quicksort(A, 0, n-1);
    }

    int[] quicksort(int A[], int low, int high) {

        if (geq(low, high)) {
            return A;
        }

        int p = partition(A, low, high);
        quicksort(A, low, p-1);
        quicksort(A, p+1, high);
        return A;
    }


    int partition(int A[], int low, int high) {

        int p = choose_pivot(A, low, high);
        swap(p, high);
        int pivot = A[high];
        int left = low;
        int right = high-1;

        while(leq(left, right)) {

            while (leq(left, right) && leq(A[left], pivot)) {
                left++;
            }
            while (geq(right, left) && geq(A[right], pivot)) {
                right--;
            }
            if (lt(left, right)) {
                swap(left, right);
            }
        }
        swap(left, high);
        return left;
    }


    int choose_pivot(int A[], int low, int high) {
        //int size = A.length - 1;
        int a = A[low], b = A[high], c = A[(low+high)/2];
        
         // Checking for b
        if ((lt(a, b) && lt(b, c)) || (lt(c, b) && lt(b, a))) {
            return high;
        }
            
        // Checking for a
        else if ((lt(b, a) && lt(a, c)) || (lt(c, a) && lt(a, b))) {
            return low;
        }
            
        else {
            return (low+high)/2;
            
        }
            
    }

    String algorithmName() {
        return "quick";
    }
}
