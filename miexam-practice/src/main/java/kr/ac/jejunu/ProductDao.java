package kr.ac.jejunu;


import java.sql.*;

public class ProductDao {

    private JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws  SQLException {
        StatementStrategy statementStrategy = connection -> {
            String sql = "select * from product where id = ?";
            Object[] params =new Object[] {id};
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            for(int i = 0 ; i<params.length;i++){
                preparedStatement.setObject( i+1,params[i] );
            }
                return preparedStatement;
        };
        return jdbcContext.jdbcContextForGet( statementStrategy );
    }

    public Long insert(Product product) throws  SQLException {
        StatementStrategy statementStrategy =connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into product(title, price)  value(?,?) ", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, product.getTitle());
                preparedStatement.setInt(2, product.getPrice());
                return preparedStatement;

        };
        return jdbcContext.jdbcContextForInsert( statementStrategy );
    }

    public void uppdate(Product product) throws SQLException {
        String sql = "update product set title = ?, price = ? where id= ? ";
        Object[] params = new Object[]{product.getTitle(),product.getPrice(),product.getId()};
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            for(int i=0; i<params.length;i++){
                preparedStatement.setObject( i+1,params[i] );
            }
                return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate( statementStrategy );
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from product where id = ?";
        Object[] params = new Object[]{id};
        StatementStrategy statementStrategy =connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            for(int i=0; i<params.length;i++){
                preparedStatement.setObject( i+1,params[i] );
            }
                return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate( statementStrategy );
    }


}
