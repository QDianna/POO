import java.util.ArrayList;

public class StreamIterator implements IMyIterator<Stream> {
    private ArrayList<Stream> streams;
    private int p;

    public StreamIterator(ArrayList<Stream> streams) {
        this.streams = streams;
        this.p = 0;
    }

    @Override
    public boolean hasNext() {
        return p < streams.size();
    }

    @Override
    public Stream next() {
        if (hasNext()) {
            return streams.get(p ++);
        } else {
            System.out.println("No more streams left");
            return null;
        }
    }

    public void remove() {
        if (p == 0) {
            System.out.println("You need to call next before deleting first element");
        } else {
            streams.remove(p - 1);
        }
    }
}
