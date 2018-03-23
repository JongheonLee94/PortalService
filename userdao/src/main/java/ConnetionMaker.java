import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnetionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException;
//        Class.forName( "com.mysql.jdbc.Driver" );
//        return DriverManager.getConnection( "jdbc:mysql://localhost/jeju?characterEncoding=utf-8&useSSL=false"
//                , "root", "0000" );

}