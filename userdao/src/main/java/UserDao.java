import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource connetionMaker) {
        this.dataSource = connetionMaker;
    }


    public User get(int id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new GetUserStatementStrategy(id);

            preparedStatement= statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return user;
    }


    public Integer insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer id;
        try {
            connection =  dataSource.getConnection();
            StatementStrategy statementStrategy =new InsertUserStatementStrategy(user);
            preparedStatement=statementStrategy.makeStatement( connection );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            id = resultSet.getInt(1);
        } finally {
            if (resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return id;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return  dataSource.getConnection();
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy= new UppdateUserStatementStrategy(user);
            preparedStatement =statementStrategy.makeStatement( connection);
//            preparedStatement = makePreparedStatement( user, connection );

            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }

//    private PreparedStatement makePreparedStatement(User user, Connection connection) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                "update userinfo set name = ?, password = ? where id = ?");
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getPassword());
//        preparedStatement.setInt(3, user.getId());
//    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
            preparedStatement = statementStrategy.makeStatement( connection ); //파라미터를 제네릭하게 오브젝트로 받을 수 있지만 커넥션만 가지게 make를 바꿈
//            preparedStatement = connection.prepareStatement(
//                    "delete from userinfo where id = ?");
//            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }



}