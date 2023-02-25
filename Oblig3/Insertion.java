

class Insertion extends Sorter {

    void sort() {


        for (int i = 1; lt(i, n); i++) {

            int j = i;

            while (j > 0 && lt(A[j], A[j-1])) {
                swap(j, j-1);
                j--;
            } 
        }
    }

    String algorithmName() {
        return "insertion";
    }
}
