import java.util.*;

public class opt2{

    public static void optimal(int[] pages, int capacity){
        int[] frames = new int[capacity];
        int pageFaults=0,replace;
        for(int i = 0; i<capacity; i++){
            frames[i]=-1;
        }
        System.out.println("\nOptimal Page Replacement:");
        System.out.println("Page No\tFrames\t\tStatus");
        for(int i =0;i<pages.length;i++) {
            boolean isHit=false;
            replace=1;
            for(int j=0; j<capacity;j++){
                if(frames[j]==pages[i]) {
                    isHit=true;
                    replace=0;
                    break;
                }
            }
            if(!isHit) {
                for(int j=0;j<capacity;j++) {
                    if(frames[j]==-1) {
                        frames[j]=pages[i];
                        replace=0;
                        break;
                    }
                }
                if(replace==1) {
                    int farthest=i,replaceIndex=-1;
                    for(int j=0;j<capacity;j++) {
                        int nextUse=Integer.MAX_VALUE;
                        for(int k=i+1;k<pages.length;k++){
                            if(frames[j]==pages[k]) {
                                nextUse=k;
                                break;
                            }
                        }
                        if(nextUse>farthest) {
                            farthest=nextUse;
                            replaceIndex=j;
                        }
                    }
                    frames[replaceIndex]=pages[i];
                }
                pageFaults++;
                System.out.println(pages[i] + "\t\t" + printFrames(frames) + "\tFault");
            }
            else {
                System.out.println(pages[i] + "\t\t" + printFrames(frames) + "\tHit");
            }
        }
        System.out.println("Total Page Faults using Optimal: " + pageFaults);
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

    public static void main(String args[]) {
        Scanner sc  = new Scanner(System.in);
       System.out.println("Enter number of pages");
       int n = sc.nextInt();

       System.out.print("Enter reference string: ");
       int[] pages = new int[n];
       for (int i = 0; i < n; i++) {
           pages[i] = sc.nextInt();
       }

       System.out.print("Enter number of frames: ");
       int capacity = sc.nextInt();

       optimal(pages, capacity);

       sc.close();
    }
}