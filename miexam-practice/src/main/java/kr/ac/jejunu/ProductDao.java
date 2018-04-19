package kr.ac.jejunu;


import java.sql.*;

public class ProductDao {

    private JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
    
    public Product get(Long id) throws  SQLException {
            StatementStrategy statementStrategy = new GetProductStatementStrategy(id);
        return jdbcContext.jdbcContextForGet( statementStrategy );
    }

    public Long insert(Product product) throws  SQLException {
            StatementStrategy statementStrategy = new InsertProductStatementStrategy(product);
        return jdbcContext.jdbcContextForInsert( statementStrategy );
    }

    public void uppdate(Product product) throws SQLException {
            StatementStrategy statementStrategy =new UpdateProductStatementStrategy(product);
        jdbcContext.jdbcContextForUpdate( statementStrategy );
    }

    public void delete(Long id) throws SQLException {
            StatementStrategy statementStrategy = new DeleteProductStamentStrategy(id);
        jdbcContext.jdbcContextForUpdate( statementStrategy );
    }


}
