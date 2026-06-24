public class CricketRadixSorter {

    static class Delivery {
        int over;
        int ball;

        Delivery(int over, int ball) {
            this.over = over;
            this.ball = ball;
        }

        public String toString() {
            return "(" + over + "," + ball + ")";
        }
    }

    // Counting Sort by Ball Number (1-12)
    static void countSortBall(Delivery[] arr) {
        int n = arr.length;
        Delivery[] output = new Delivery[n];
        int[] count = new int[13];

        for (Delivery d : arr)
            count[d.ball]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i].ball] - 1] = arr[i];
            count[arr[i].ball]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // Counting Sort by Over Number (0-49)
    static void countSortOver(Delivery[] arr) {
        int n = arr.length;
        Delivery[] output = new Delivery[n];
        int[] count = new int[50];

        for (Delivery d : arr)
            count[d.over]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i].over] - 1] = arr[i];
            count[arr[i].over]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // LSD Radix Sort
    static void radixSort(Delivery[] arr) {
        countSortBall(arr); // Least Significant Key

        System.out.print("Pass 1 (Sorted by Ball): ");
        printArray(arr);

        countSortOver(arr); // Most Significant Key

        System.out.print("Pass 2 (Sorted by Over): ");
        printArray(arr);
    }

    static void printArray(Delivery[] arr) {
        for (Delivery d : arr)
            System.out.print(d + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        Delivery[] records = {
                new Delivery(2,4),
                new Delivery(1,1),
                new Delivery(3,6),
                new Delivery(1,5),
                new Delivery(2,2),
                new Delivery(3,1),
                new Delivery(1,3),
                new Delivery(2,4),
                new Delivery(3,4),
                new Delivery(1,2)
        };

        System.out.println("CRICKET SCORING API SORTER");
        System.out.print("Original Data: ");
        printArray(records);

        radixSort(records);

        System.out.print("Final Sorted Data: ");
        printArray(records);
    }
}