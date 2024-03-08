public class StreamerFactory {
    public static Streamer createStreamer(int type, int id, String name) {
        switch (type) {
            case 1:
                return new Musician(id, name);
            case 2:
                return new PodcastHost(id, name);
            case 3:
                return new AudiobookAuthor(id, name);
            default:
                System.out.println("Invalid streamer type");
                return null;
        }
    }

    public static Streamer createStreamerFromLine(String[] line) {
        int type = Integer.parseInt(line[0]);
        int id = Integer.parseInt(line[1]);
        String name = line[2];
        switch (type) {
            case 1:
                return new Musician(id, name);
            case 2:
                return new PodcastHost(id, name);
            case 3:
                return new AudiobookAuthor(id, name);
            default:
                return null;
        }
    }

}
