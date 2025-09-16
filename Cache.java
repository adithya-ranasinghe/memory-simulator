public class Cache {
    private int[] cache;
    private boolean[] valid;
    private int blockSize;

    public Cache(int numBlocks, int blockSize) {
        this.cache = new int[numBlocks * blockSize];
        this.valid = new boolean[numBlocks];
        this.blockSize = blockSize;
    }

    public int read(int address, MainMemory memory) {
        int blockNumber = (address / blockSize) % valid.length;
        int blockStart = (address / blockSize) * blockSize;

        if (!valid[blockNumber]) {
            // Load block from memory
            for (int i = 0; i < blockSize; i++) {
                cache[blockNumber * blockSize + i] = memory.read(blockStart + i);
            }
            valid[blockNumber] = true;
        }
        return cache[blockNumber * blockSize + (address % blockSize)];
    }
}
