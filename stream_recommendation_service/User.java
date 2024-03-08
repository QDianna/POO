import java.util.*;

public class User {
    private UpdateDataMediator mediator = new UpdateDataMediator();
    private int id;
    private String name;
    private ArrayList<Integer> streams;

    public User(int id, String name, ArrayList<Integer> streams) {
        this.id = id;
        this.name = name;
        this.streams = streams;
    }

    public User createUser(int id, String name, ArrayList<Integer> streams) {
        return new User(id, name, streams);
    }

    public static User createUserFromLine(String[] line) {
        int id = Integer.parseInt(line[0]);
        String name = line[1];
        ArrayList<Integer> streams = new ArrayList<>();
        String[] list = line[2].split(" ");
        for (String cell : list) {
            int streamId = Integer.parseInt(cell);
            streams.add(streamId);
        }

        return new User(id, name, streams);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<Integer> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<Integer> streams) {
        this.streams = streams;
    }

    public void listen(int streamId) {
        this.streams.add(streamId);
        mediator.updateNoOfStreams(streamId);
    }

    public void printHistory() {
        DataReaderSingleton data = DataReaderSingleton.Instance();
        ArrayList<Stream> streamsToList = new ArrayList<>();

        for (Integer s : streams) {
            streamsToList.add(data.getStreamById(s));
        }

        Stream.convertStreamsToJson(streamsToList);
    }

    public ArrayList<Streamer> findMyStreamers() {
        ArrayList<Streamer> myStreamers = new ArrayList<>();
        DataReaderSingleton data = DataReaderSingleton.Instance();

        for (Integer sId : this.streams) {
            Stream s = data.getStreamById(sId);
            int streamerId = s.getStreamerId();
            Streamer ser = data.getStreamerById(streamerId);
            myStreamers.add(ser);
        }

        if (myStreamers.isEmpty())
            return null;
        else
            return myStreamers;
    }

}
