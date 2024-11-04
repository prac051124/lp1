import java.util.Scanner;
import java.util.Arrays;

public class MemoryManagement {

    // First Fit Memory Allocation
    public static void firstFit(int blockSizes[], int processSizes[], int numProcesses, int numBlocks) {
        int allocation[] = new int[numProcesses];
        Arrays.fill(allocation, -1); // Initially, no process is allocated any block

        boolean[] blockAllocated = new boolean[numBlocks]; // Track if block is fully allocated

        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numBlocks; j++) {
                if (!blockAllocated[j] && blockSizes[j] >= processSizes[i]) {
                    allocation[i] = j;
                    blockAllocated[j] = true; // Mark the block as allocated
                    break;
                }
            }
        }
        printAllocation(allocation, "First Fit", numProcesses);
    }

    // Next Fit Memory Allocation (No Wrap Around)
    public static void nextFit(int blockSizes[], int processSizes[], int numProcesses, int numBlocks) {
        int allocation[] = new int[numProcesses];
        Arrays.fill(allocation, -1);

        int lastAllocatedIndex = 0; // Start from the first block

        for (int i = 0; i < numProcesses; i++) {
            boolean allocated = false;

            // Traverse blocks starting from the last allocated index without wrapping around
            for (int j = lastAllocatedIndex; j < numBlocks; j++) {
                if (blockSizes[j] >= processSizes[i]) {
                    allocation[i] = j; // Allocate the block to the process
                    blockSizes[j] -= processSizes[i]; // Reduce the block size
                    lastAllocatedIndex = j + 1; // Move index to the next block for future allocations
                    allocated = true;
                    break;
                }
            }

            // If no block was found, we stop the allocation (no wrap-around)
            if (i == numProcesses - 1) {
                lastAllocatedIndex = numBlocks; // No more blocks to traverse
            }
        }

        printAllocation(allocation, "Next Fit (No Wrap Around)", numProcesses);
    }

    // Best Fit Memory Allocation
    public static void bestFit(int blockSizes[], int processSizes[], int numProcesses, int numBlocks) {
        int allocation[] = new int[numProcesses];
        Arrays.fill(allocation, -1);

        boolean[] blockAllocated = new boolean[numBlocks];

        for (int i = 0; i < numProcesses; i++) {
            int bestIdx = -1;
            for (int j = 0; j < numBlocks; j++) {
                if (!blockAllocated[j] && blockSizes[j] >= processSizes[i]) {
                    if (bestIdx == -1 || blockSizes[j] < blockSizes[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockAllocated[bestIdx] = true; // Mark block as fully allocated
            }
        }
        printAllocation(allocation, "Best Fit", numProcesses);
    }

    // Worst Fit Memory Allocation
    public static void worstFit(int blockSizes[], int processSizes[], int numProcesses, int numBlocks) {
        int allocation[] = new int[numProcesses];
        Arrays.fill(allocation, -1);

        boolean[] blockAllocated = new boolean[numBlocks];

        for (int i = 0; i < numProcesses; i++) {
            int worstIdx = -1;
            for (int j = 0; j < numBlocks; j++) {
                if (!blockAllocated[j] && blockSizes[j] >= processSizes[i]) {
                    if (worstIdx == -1 || blockSizes[j] > blockSizes[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }

            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockAllocated[worstIdx] = true; // Mark block as fully allocated
            }
        }
        printAllocation(allocation, "Worst Fit", numProcesses);
    }

    // Helper method to print memory allocation
    private static void printAllocation(int allocation[], String method, int numProcesses) {
        System.out.println("\nMemory Allocation using " + method + ":");
        for (int i = 0; i < numProcesses; i++) {
            if (allocation[i] != -1) {
                System.out.println("Process " + (i + 1) + " allocated to Block " + (allocation[i] + 1));
            } else {
                System.out.println("Process " + (i + 1) + " not allocated");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of blocks and processes
        System.out.print("Enter number of memory blocks: ");
        int numBlocks = sc.nextInt();

        System.out.print("Enter number of processes: ");
        int numProcesses = sc.nextInt();

        int blockSizes[] = new int[numBlocks];
        int processSizes[] = new int[numProcesses];

        // Input block sizes
        System.out.println("Enter sizes of the memory blocks: ");
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Block " + (i + 1) + " size: ");
            blockSizes[i] = sc.nextInt();
        }

        // Input process sizes
        System.out.println("Enter sizes of the processes: ");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Process " + (i + 1) + " size: ");
            processSizes[i] = sc.nextInt();
        }

        // Cloning the block sizes to use for each method
        // int blockSizesFirstFit[] = blockSizes.clone();
        // int blockSizesNextFit[] = blockSizes.clone();
        // int blockSizesBestFit[] = blockSizes.clone();
        // int blockSizesWorstFit[] = blockSizes.clone();

        // Execute each memory allocation strategy
        firstFit(blockSizes, processSizes, numProcesses, numBlocks);
        nextFit(blockSizes, processSizes, numProcesses, numBlocks);
        bestFit(blockSizes, processSizes, numProcesses, numBlocks);
        worstFit(blockSizes, processSizes, numProcesses, numBlocks);

        sc.close();
    }
}
