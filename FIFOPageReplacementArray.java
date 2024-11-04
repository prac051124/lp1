import java.util.Scanner;

public class FIFOPageReplacementArray {
    public static void fifo(int[] pages, int capacity) {
        int[] frames = new int[capacity];
        int pageFaults = 0, index = 0;
        boolean isHit;
        for (int i = 0; i < capacity; i++) {
            frames[i] = -1;
        }      
        System.out.println("\nFIFO Page Replacement:");
        System.out.println("Page No\tFrames\t\tStatus");
        for (int page : pages) {
            isHit = false;
            for (int i = 0; i < capacity; i++) {
                if (frames[i] == page) {
                    isHit = true;
                    break;
                }
            }
            if (!isHit) {
                frames[index] = page;
                index = (index + 1) % capacity;
                pageFaults++;
                System.out.println(page + "\t\t" + printFrames(frames) + "\tFault");
            } else {
                System.out.println(page + "\t\t" + printFrames(frames) + "\tHit");
            }
        }
        System.out.println("Total Page Faults using FIFO: " + pageFaults);
    }
    private static String printFrames(int[] frames) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] != -1) {
                sb.append(frames[i]);
            } else {
                sb.append(" ");
            }
            if (i < frames.length - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of pages: ");
        int n = scanner.nextInt();
        System.out.print("Enter reference string: ");
        int[] pages = new int[n];
        for (int i = 0; i < n; i++) {
            pages[i] = scanner.nextInt();
        }
        System.out.print("Enter number of frames: ");
        int capacity = scanner.nextInt();
        fifo(pages, capacity);
        scanner.close();
    }
}