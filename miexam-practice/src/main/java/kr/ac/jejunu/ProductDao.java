package kr.ac.jejunu;


import sun.security.action.GetPropertyAction;

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
            StatementStrategy statementStrategy = new GetProductStatementStrategy(id);
            preparedStatement = statementStrategy.makeStatement( connection );


            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                product = new Product();
                product.setId( resultSet.getLong( "id" ) );
                product.setTitle( resultSet.getString( "title" ) );
                product.setPrice( resultSet.getInt( "price" ) );
            }
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



        return product;
    }


    public Long insert(Product product) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new InsertProductStatementStrategy(product);
            preparedStatement = statementStrategy.makeStatement( connection );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
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
            StatementStrategy statementStrategy =new UpdateProductStatementStrategy(product);
            preparedStatement = statementStrategy.makeStatement( connection );

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
            StatementStrategy statementStrategy = new DeleteProductStamentStrategy(id);
            preparedStatement =statementStrategy.makeStatement( connection );


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
