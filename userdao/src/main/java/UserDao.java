import java.sql.*;

public class UserDao {
    private final ConnetionMaker connetionMaker;

    public UserDao(ConnetionMaker connetionMaker) {
        this.connetionMaker = connetionMaker;
    }


    public User get(int id) throws ClassNotFoundException, SQLException {
        Connection connection = connetionMaker.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }



    public Integer insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connetionMaker.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "insert into userinfo(name, password) values (?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();


        preparedStatement = connection.prepareStatement( "select last_insert_id()" );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Integer id = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return id;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return connetionMaker.getConnection();
    }

}