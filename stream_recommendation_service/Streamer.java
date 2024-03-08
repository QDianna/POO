import java.util.ArrayList;

public abstract class Streamer {
    private UpdateDataMediator mediator = new UpdateDataMediator ();
    /* mediator was used to facilitate communication between streamer actions
    and application data changes */
    private int streamerType;
    /* 1- musician 2- podcast host 3- audiobook author */
    private int id;
    private String name;
    public Streamer(int streamerType, int id, String name) {
        this.streamerType = streamerType;
        this.id = id;
        this.name = name;
    }

    void setStreamerType(int type) { this.streamerType = type; }
    int getStreamerType() { return this.streamerType; }

    void setId(int id) {
        this.id = id;
    }
    int getId() {
        return this.id;
    }

    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }

    public Stream addNewStream(int id, String[] lineContents) {
        return StreamFactory.createNewStreamForStreamer(id, lineContents);
    }

    public void deleteStream(int id, ArrayList<Stream> streams, int streamId) {
        StreamIterator sIt = new StreamIterator(streams);
        while(sIt.hasNext()) {
            Stream s = sIt.next();
            if (s.getStreamerId() == id && s.getId() == streamId) {
                sIt.remove();
                break;
            }
        }
        mediator.updateUsersHistory(streamId);

    }

}
