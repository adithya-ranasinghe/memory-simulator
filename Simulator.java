import java.io.*;
import java.util.*;

public class Simulator {
    private TLB tlb;
    private PageTable pageTable;
    private Cache cache;
    private MainMemory memory;
    private int frameCounter = 0; // for allocating frames

    public Simulator() {
        this.tlb = new TLB(16);
        this.pageTable = new PageTable();
        this.cache = new Cache(16, 16); // 16 blocks, block size 16
        this.memory = new MainMemory(65536);
    }

    public int readVirtualAddress(int virtualAddress) {
        int pageNumber = (virtualAddress >> 8) & 0xFF; // upper 8 bits
        int offset = virtualAddress & 0xFF; // lower 8 bits

        // Step 1: TLB lookup
        Integer frame = tlb.getFrame(pageNumber);

        if (frame == null) {
            // Step 2: Page table lookup
            if (!pageTable.contains(pageNumber)) {
                // Page fault → assign new frame
                pageTable.setMapping(pageNumber, frameCounter++);
            }
            frame = pageTable.getFrame(pageNumber);
            tlb.put(pageNumber, frame);
        }

        // Step 3: Compute physical address
        int physicalAddress = (frame << 8) | offset;

        // Step 4: Read from cache (loads from memory if needed)
        return cache.read(physicalAddress, memory);
    }

    public static void main(String[] args) {
        Simulator sim = new Simulator();

        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("READ")) {
                    String hexAddr = line.split(" ")[1];
                    int virtualAddr = Integer.decode(hexAddr);
                    int data = sim.readVirtualAddress(virtualAddr);
                    System.out.printf("Virtual: %s -> Data: %d\n", hexAddr, data);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading input.txt");
        }
    }
}
