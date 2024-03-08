public class StreamFactory {
    public static Stream createStream(int type, int id, int genre, long noOfStreams, int streamerId, long length, long dateAdded, String title) {
        switch (type) {
            case 1:
                return new Music(id, genre, noOfStreams, streamerId, length, dateAdded, title);
            case 2:
                return new Podcast(id, genre, noOfStreams, streamerId, length, dateAdded, title);
            case 3:
                return new Audiobook(id, genre, noOfStreams, streamerId, length, dateAdded, title);
            default:
                System.out.println("Invalid stream type");
                return null;
        }
    }

    public static Stream createNewStreamForStreamer(int streamerId, String[] line) {
        int type = Integer.parseInt(line[2]);
        int id = Integer.parseInt(line[3]);
        int genre = Integer.parseInt(line[4]);
        long length = Long.parseLong(line[5]);
        String name = line[6];
        for (int i = 7; i < line.length; i ++)
            name += " " + line[i];

        long noOfStreams = 0;
        long data = System.currentTimeMillis();

        switch (type) {
            case 1:
                return new Music(id, genre, noOfStreams, streamerId, length, data, name);
            case 2:
                return new Podcast(id, genre, noOfStreams, streamerId, length, data, name);
            case 3:
                return new Audiobook(id, genre, noOfStreams, streamerId, length, data, name);
            default:
                return null;
        }
    }

    public static Stream createStreamFromLine(String[] line) {
        int type = Integer.parseInt(line[0]);
        int id = Integer.parseInt(line[1]);
        int genre = Integer.parseInt(line[2]);
        long noOfStreams = Long.parseLong(line[3]);
        int streamerId = Integer.parseInt(line[4]);
        long length = Long.parseLong(line[5]);
        long data = Long.parseLong(line[6]);
        String name = line[7];

        switch (type) {
            case 1:
                return new Music(id, genre, noOfStreams, streamerId, length, data, name);
            case 2:
                return new Podcast(id, genre, noOfStreams, streamerId, length, data, name);
            case 3:
                return new Audiobook(id, genre, noOfStreams, streamerId, length, data, name);
            default:
                return null;
        }
    }

}
