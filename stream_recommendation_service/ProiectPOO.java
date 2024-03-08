public class ProiectPOO {

    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        DataReaderSingleton data = DataReaderSingleton.Instance();
        data.readStreamers("src/main/resources/" + args[0]);
        data.readStreams("src/main/resources/" + args[1]);
        data.readUsers("src/main/resources/" + args[2]);
        data.readCommands("src/main/resources/" + args[3]);
        data.resetDatabase();
        System.out.println("");
    }
}
