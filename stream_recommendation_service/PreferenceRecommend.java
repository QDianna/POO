import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PreferenceRecommend implements IRecommendationStrategy {
    public void recommend(int streamType, User user) {
        DataReaderSingleton data = DataReaderSingleton.Instance();
        ArrayList<Stream> streamsFromStreamers = new ArrayList<>();
        ArrayList<Streamer> myStreamers = user.findMyStreamers();

        myStreamers.removeIf(streamer -> streamer.getStreamerType() != streamType);

        for (Streamer ser : myStreamers) {
            for (Stream s : data.getStreamList())
                if (s.getStreamerId() == ser.getId() && !user.getStreams().contains(s.getId())) {
                    streamsFromStreamers.add(s);
                }

        }

        Collections.sort(streamsFromStreamers, Comparator.comparingLong(Stream::getNoOfStreams));

        int recNo = 0;
        ArrayList<Stream> streamsToPrint = new ArrayList<>();

        for (int it = streamsFromStreamers.size() - 1; it >= 0 && recNo < 5; it --) {
            streamsToPrint.add(streamsFromStreamers.get(it));
            recNo ++;
        }

        Stream.convertStreamsToJson(streamsToPrint);
    }
}
