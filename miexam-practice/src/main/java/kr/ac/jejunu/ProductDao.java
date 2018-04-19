package kr.ac.jejunu;


import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {

    private final DataSource dataSource;
    public ProductDao(DataSource dataSource) {
        this.dataSource=dataSource;
    }


    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Product product;
        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setTitle(resultSet.getString("title"));
            product.setPrice(resultSet.getInt("price"));
        } finally {
            //자원을 해지한다.
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }



        return product;
    }


    public Long insert(Product product) throws ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();

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

}
