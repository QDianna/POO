import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Stream implements Comparable<Stream> {
    private int streamType;
    private int id;
    private int streamGenre;
    private long noOfStreams;
    private int streamerId;
    private long length;
    private long dateAdded;
    private String title;

    public int compareTo(Stream other) {
        return Long.compare(this.noOfStreams, other.noOfStreams);
    }

    public Stream (int streamType, int id, int streamGenre, long noOfStreams, int streamerId, long length, long dateAdded, String title) {
        this.streamType = streamType;
        this.id = id;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
        this.title = title;
    }

    public int getStreamType() {
        return streamType;
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStreamGenre() {
        return streamGenre;
    }

    public void setStreamGenre(int streamGenre) {
        this.streamGenre = streamGenre;
    }

    public long getNoOfStreams() {
        return noOfStreams;
    }

    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public void setStreamerId(int streamerId) {
        this.streamerId = streamerId;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStreamerName() {
        DataReaderSingleton data = DataReaderSingleton.Instance();

        for (Streamer s : data.getStreamerList())
            if (this.streamerId == s.getId())
                return s.getName();
        return null;
    }

    public static void convertStreamsToJson(ArrayList<Stream> streams) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNodeFactory nodeFactory = objectMapper.getNodeFactory();

        ArrayNode items = nodeFactory.arrayNode();

        if (streams.isEmpty()) {
            System.out.println("streams is empty!");
            return;
        }

        for (Stream stream : streams) {
            ObjectNode streamNode = nodeFactory.objectNode();
            streamNode.put("id", String.valueOf(stream.getId()));
            streamNode.put("name", stream.getTitle());
            streamNode.put("streamerName", stream.getStreamerName());
            streamNode.put("noOfListenings", String.valueOf(stream.getNoOfStreams()));

            long lengthInSeconds = stream.getLength();
            String formattedLength = formatDuration(lengthInSeconds);
            streamNode.put("length", formattedLength);

            String formattedDateAdded = formatDate(stream.getDateAdded());
            streamNode.put("dateAdded", formattedDateAdded);

            items.add(streamNode);
        }

        try {
            String jsonArrayString = objectMapper.writeValueAsString(items);
            System.out.println(jsonArrayString);

        } catch (Exception e) {
            System.out.println("json object to String exception");
            e.printStackTrace();
            return;
        }
    }

    private static String formatDuration(long seconds) {
        if (seconds >= 3600) {
            long hours = seconds / 3600;
            long minutes = (seconds % 3600) / 60;
            long remainingSeconds = (seconds % 3600) % 60;

            return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
        } else {
            long minutes = seconds / 60;
            long remainingSeconds = seconds % 60;

            return String.format("%02d:%02d", minutes, remainingSeconds);
        }
    }

    private static String formatDate(long timestamp) {
        Date date = new Date(timestamp * 1000);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        return df.format(date);
    }

    public String printStream() {
        DataReaderSingleton data = DataReaderSingleton.Instance();
        Streamer streamer = data.getStreamerById(streamerId);

        String output = "{";
        output += "\"id\":" + "\"" + getId() + "\",";
        output += "\"name\":" + "\"" + getTitle() + "\",";
        output += "\"streamerName\":" + "\"" + streamer.getName() + "\",";
        output += "\"noOfListenings\":" + "\"" + getNoOfStreams() + "\",";
        output += "\"length\":";

        long h, m, s;
        long l = getLength();
        if (l >= 3600) {
            h = l / 3600;
            String sh = String.format("%02d", h);
            m = (l % 3600) / 60;
            String sm = String.format("%02d", m);
            s = (l % 3600) % 60;
            String ss = String.format("%02d", s);

            output += "\"" + sh + ":" + sm + ":" + ss + "\",";
        }
        else {
            m = l / 60;
            String sm = String.format("%02d", m);
            s = l % 60;
            String ss = String.format("%02d", s);

            output += "\"" + sm + ":" + ss + "\",";
        }

        output += "\"dateAdded\":";

        Date d = new Date(getDateAdded()*1000);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String java_date = df.format(d);

        output += "\"" + java_date + "\"";
        output += "}";

        System.out.println(output);
        return output;
    }


}
