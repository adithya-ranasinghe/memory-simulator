import java.util.HashMap;

public class PageTable {
    private HashMap<Integer, Integer> pageToFrame;

    // Constructor
    public PageTable() {
        pageToFrame = new HashMap<>();
    }

    // Check if page exists in table
    public boolean contains(int page) {
        return pageToFrame.containsKey(page);
    }

    // Add or update a mapping
    public void setMapping(int page, int frame) {
        pageToFrame.put(page, frame);
    }

    // Get frame from page
    public Integer getFrame(int page) {
        return pageToFrame.get(page);
    }
}