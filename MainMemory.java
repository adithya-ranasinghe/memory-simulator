public class MainMemory {
    private int[] memory;

    public MainMemory(int size) {
        memory = new int[size];
        // Initialize memory with dummy data
        for (int i = 0; i < size; i++) {
            memory[i] = i % 256;
        }
    }

    public int read(int physicalAddress) {
        return memory[physicalAddress];
    }
}