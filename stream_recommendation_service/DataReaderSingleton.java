import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataReaderSingleton {
    private static DataReaderSingleton uniqueInstance;
    private DataReaderSingleton() {}

    public static DataReaderSingleton Instance() {
        if (uniqueInstance == null)
            uniqueInstance = new DataReaderSingleton();
        return uniqueInstance;
    }

    private ArrayList<Streamer> streamerList = new ArrayList<>();
    private ArrayList<Stream> streamList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    private RecommendationStrategy strategy = new RecommendationStrategy();

    public void resetDatabase() {
        streamerList.clear();
        streamList.clear();
        userList.clear();
    }
    public void readStreamers(String file) {
        try {
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);
            String[] line;

            csvr.readNext();

            while ( (line = csvr.readNext()) != null) {
                Streamer newStreamer = StreamerFactory.createStreamerFromLine(line);
                streamerList.add(newStreamer);
            }
        }
        catch (Exception e) {
            System.out.println("readStreamers exception");
        }
    }

    public void readStreams(String file) {
        try {
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);
            String[] line;

            csvr.readNext();

            while ( (line = csvr.readNext()) != null) {
                Stream newStream = StreamFactory.createStreamFromLine(line);
                streamList.add(newStream);
            }
        }
        catch (Exception e) {
            System.out.println("readStreams exception");
        }
    }

    public void readUsers(String file) {
        try {
            FileReader fr = new FileReader(file);
            CSVReader csvr = new CSVReader(fr);
            String[] line;

            csvr.readNext();

            while ( (line = csvr.readNext()) != null) {
                User newUser = User.createUserFromLine(line);
                userList.add(newUser);
            }
        }
        catch (Exception e) {
            System.out.println("readUsers exception");
        }
    }

    public Streamer getStreamerById(int id) {
        for (Streamer s : streamerList)
            if (s.getId() == id)
                return s;
        return null;
    }

    public Stream getStreamById(int id) {
        for (Stream s : streamList)
            if (s.getId() == id)
                return s;
        return null;
    }

    public void readCommands(String file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ( (line = br.readLine()) != null) {
                String[] lineContents = line.split(" ");
                int id = Integer.parseInt(lineContents[0]);

                if(findStreamer(id)) {
                    switch (lineContents[1]) {
                        case "ADD":
                            for (Streamer s : streamerList)
                                if (s.getId() == id) {
                                    streamList.add(s.addNewStream(id, lineContents));
                                }
                            break;

                        case "LIST":
                            StreamIterator sIt = new StreamIterator(streamList);
                            ArrayList<Stream> streamsToList = new ArrayList<>();
                            while (sIt.hasNext()) {
                                Stream s = sIt.next();
                                if (s != null && s.getStreamerId() == id) {
                                    streamsToList.add(s);
                                }
                            }
                            Stream.convertStreamsToJson(streamsToList);
                            break;

                        case "DELETE":
                            int streamId = Integer.parseInt(lineContents[2]);
                            for (Streamer s : streamerList)
                                if (s.getId() == id) {
                                    s.deleteStream(id, streamList, streamId);
                                }
                            break;

                        default:
                            System.out.println("default");

                    }
                }

                else if (findUser(id)) {
                    switch (lineContents[1]) {
                        case "LIST":
                            for (User u : userList)
                                if(u.getId() == id)
                                    u.printHistory();
                            break;

                        case "LISTEN":
                            int streamId = Integer.parseInt(lineContents[2]);
                            for (User u : userList)
                                if (u.getId() == id) {
                                    u.listen(streamId);
                                }
                            break;

                        case "RECOMMEND":
                            strategy.setStrategy(new PreferenceRecommend());
                            String streamTypeR = lineContents[2];
                            for (User u : userList) {
                                if (u.getId() == id) {
                                    switch (streamTypeR) {
                                        case "SONG":
                                            strategy.generateRecommendation(1, u);
                                            break;
                                        case "PODCAST":
                                            strategy.generateRecommendation(2, u);
                                            break;
                                        case "AUDIOBOOK":
                                            strategy.generateRecommendation(3, u);
                                            break;
                                    }
                                }
                            }
                            break;

                        case "SURPRISE":
                            strategy.setStrategy(new SurpriseRecommend());
                            String streamTypeS = lineContents[2];
                            for (User u : userList) {
                                if (u.getId() == id) {
                                    switch (streamTypeS) {
                                        case "SONG":
                                            strategy.generateRecommendation(1, u);
                                            break;
                                        case "PODCAST":
                                            strategy.generateRecommendation(2, u);
                                            break;
                                        case "AUDIOBOOK":
                                            strategy.generateRecommendation(3, u);
                                            break;
                                    }
                                }
                            }
                            break;

                        default:
                            System.out.println("default");
                    }
                } else {
                    System.out.println("unknown id");
                }
            }
        }
        catch (Exception e) {
            System.out.println("commands exception");
            e.printStackTrace();
        }
    }

    public ArrayList<Streamer> getStreamerList() {
        return streamerList;
    }

    public void setStreamerList(ArrayList<Streamer> streamerList) {
        this.streamerList = streamerList;
    }

    public ArrayList<Stream> getStreamList() {
        return streamList;
    }

    public void setStreamList(ArrayList<Stream> streamList) {
        this.streamList = streamList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    public boolean findUser(int id) {
        for (User u : userList)
            if (id == u.getId())
                return true;
        return false;
    }
    public boolean findStreamer(int id) {
        for (Streamer s : streamerList)
            if (id == s.getId())
                return true;
        return false;
    }

}
