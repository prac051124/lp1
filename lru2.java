import java.util.*;

class lru2{

    public static void LRU(int[] pages, int capacity){
        int[] frames = new int[capacity];
        int pageFaults=0,time=0;
        boolean isHit;
        int[] lastUsed = new int[capacity];
        for(int i = 0 ; i < capacity; i++) {
            frames[i]=-1;
            lastUsed[i]=-1;
        }
        System.out.println("\nLRU Page Replacement:");
        System.out.println("Page No\tFrames\t\tLast Used\t\tStatus");
        for(int page:pages) {
            isHit=false;
            for(int i = 0; i<capacity;i++){
                if(frames[i]==page){
                    isHit=true;
                    lastUsed[i]=time;
                    break;
                } 
            }
            if(!isHit) {
                int lruIndex=-1;
                for(int i=0; i<capacity;i++){
                    if(frames[i]==-1) {
                        lruIndex=i;
                        break;
                    }
                }
                if(lruIndex==-1){
                    lruIndex=0;
                    for(int i=1;i<capacity;i++) {
                        if(lastUsed[i]<lastUsed[lruIndex]) {
                            lruIndex=i;
                        }
                    }
                }
                frames[lruIndex]=page;
                lastUsed[lruIndex]=time;
                pageFaults++;
                System.out.println(page + "\t\t" + printFrames(frames) + "\t" + printFrames(lastUsed) + "\tFault");
            }
            else {
                System.out.println(page + "\t\t" + printFrames(frames) + "\t" + printFrames(lastUsed) + "\tHit");
            }           
            time++;
        }
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
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of frames");
        int frames = sc.nextInt();
        System.out.println("Enter number of pages");
        int pageSize= sc.nextInt();
        int[] pages = new int[pageSize];
        System.out.println("Enter pages");
        for(int i = 0; i<pageSize;i++) {
            pages[i]=sc.nextInt();
        }
        LRU(pages,frames);
    }
}