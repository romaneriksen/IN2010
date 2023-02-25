public class Selection extends Sorter {
    
    void sort() {

       selection_sort();
       //selection_sort2();
    }

    void selection_sort() {

        int i, j, k;

        for (i = 0; lt(i, n-1); i++) {
            
            k = i;

            for (j = i+1; lt(j, n); j++) {

                if ( lt(A[j], A[k]) ) {
                    k = j;
                }
            }

            if (!eq(i, k)) {
                swap(i, k);
            }
        }
    }

    

    String algorithmName() {

        return "selection";
    }
}


