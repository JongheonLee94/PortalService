package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {

    private final ConnectionMaker connectionMaker;
    public ProductDao(ConnectionMaker connectionMaker) {
        this.connectionMaker=connectionMaker;
    }


    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getInt("price"));

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return product;
    }


    public Long insert(Product product) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into product(title, price)  value(?,?) ");
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setInt(2, product.getPrice());

        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement( "select last_insert_id()" );
        ResultSet resultSet = preparedStatement.executeQuery(); //반환값 가져온 걸 리턴하여 resultSet에 넣음
        resultSet.next();

        Long id = resultSet.getLong(1);

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        return connectionMaker.getConnection();
    }
}