public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker() );
    }

    public  ConnetionMaker getConnectionMaker(){
        return new JejuConnetionMaker();
    }
}
