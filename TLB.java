import java.util.LinkedHashMap;
import java.util.Map;

public class TLB {
    private final int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    public TLB(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public Integer getFrame(int pageNumber) {
        return cache.get(pageNumber);
    }

    public void put(int pageNumber, int frameNumber) {
        cache.put(pageNumber, frameNumber);
    }
}
