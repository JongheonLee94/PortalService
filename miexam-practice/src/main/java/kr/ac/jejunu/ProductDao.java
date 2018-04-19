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

        Product product = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                product = new Product();
                product.setId( resultSet.getLong( "id" ) );
                product.setTitle( resultSet.getString( "title" ) );
                product.setPrice( resultSet.getInt( "price" ) );
            }
        } finally {
            //자원을 해지한다. 해지하지 않게 되면 쌓여서 시스템이 다운될 수 있으므로 반드시 해지해야하므로 final로 해준다.
            if (resultSet != null) {//resultSet이 null이면 해지할 게 없으므로
                try {//close에서 에러가 날 수도 있으므로
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close(); //실제로는 커넥션만 close 되면 다 close 된다고 함
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



        return product;
    }


    public Long insert(Product product) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; //반환값 가져온 걸 리턴하여 resultSet에 넣음
        Long id;
        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement("insert into product(title, price)  value(?,?) ");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement( "select last_insert_id()" );
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getLong(1);
        } finally {
            //자원을 해지한다.
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



        return id;
    }

    public void uppdate(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement( "update product set title = ?, price = ? where id= ? " );
            preparedStatement.setString( 1, product.getTitle() );
            preparedStatement.setInt( 2, product.getPrice() );
            preparedStatement.setLong( 3, product.getId() );

            preparedStatement.executeUpdate();

        } finally {
            //자원을 해지한다.
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void delete(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement( "delete from product where id = ?" );
            preparedStatement.setLong( 1,id );

            preparedStatement.executeUpdate();

        } finally {
            //자원을 해지한다.
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
