import java.util.ArrayList;

public class StreamerIterator implements IMyIterator<Streamer> {
    private ArrayList<Streamer> streamers;
    private int p;

    public StreamerIterator(ArrayList<Streamer> streamers) {
        this.streamers = streamers;
        this.p = 0;
    }

    @Override
    public boolean hasNext() {
        return p < streamers.size();
    }

    @Override
    public Streamer next() {
        if (hasNext()) {
            return streamers.get(p ++);
        } else {
            System.out.println("No more streamers left");
            return null;
        }
    }
}