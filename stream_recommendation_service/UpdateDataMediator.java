public class UpdateDataMediator implements IUpdateDataMediator {
    public void updateUsersHistory(int streamId) {
        DataReaderSingleton data = DataReaderSingleton.Instance();
        for (User u : data.getUserList()){
            for (int sId : u.getStreams())
                if (sId == streamId)
                    u.getStreams().remove(sId);
        }
    }

    public void updateNoOfStreams(int streamId) {
        DataReaderSingleton data = DataReaderSingleton.Instance();
        for (Stream s : data.getStreamList()) {
            if (s.getId() == streamId) {
                s.setNoOfStreams(s.getNoOfStreams() + 1);
                return;
            }
        }
    }
}
