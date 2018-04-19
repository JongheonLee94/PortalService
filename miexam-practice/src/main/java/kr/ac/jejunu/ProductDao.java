package kr.ac.jejunu;


import java.sql.*;

public class ProductDao {

    private JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws  SQLException {
        Long id1 = id;
        //람다표현식 자바 1.8부터
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
                preparedStatement.setLong(1, id);
                return preparedStatement;
        };

//               PreparedStatement preparedStatement  new StatementStrategy() {
//            private Long id = id1;
//
//            @Override
//            public PreparedStatement makeStatement(Connection connection) throws SQLException {
//                PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
//                preparedStatement.setLong(1, id);
//                return preparedStatement;
//            }
//        };
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
