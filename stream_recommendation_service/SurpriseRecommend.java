import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SurpriseRecommend implements IRecommendationStrategy {

    public void recommend(int streamType, User user) {
        DataReaderSingleton data = DataReaderSingleton.Instance();
        ArrayList<Streamer> newStreamers = new ArrayList<>();
        ArrayList<Streamer> myStreamers = user.findMyStreamers();
        ArrayList<Stream> streamsFromStreamers = new ArrayList<>();

        for(Streamer s : data.getStreamerList()) {
            if (!myStreamers.contains(s)) {
                newStreamers.add(s);
            }
        }

        for (Streamer ser : newStreamers) {
            for (Stream s : data.getStreamList())
                if (s.getStreamerId() == ser.getId()) {
                    streamsFromStreamers.add(s);
                }
        }

        Collections.sort(streamsFromStreamers, Comparator.comparingLong(Stream::getDateAdded));

        int recNo = 0;
        ArrayList<Stream> streamsToPrint = new ArrayList<>();

        for (int it = streamsFromStreamers.size() - 1; it >= 0 && recNo < 3; it --) {
            streamsToPrint.add(streamsFromStreamers.get(it));
            recNo ++;
        }
        Stream.convertStreamsToJson(streamsToPrint);


    }
}
