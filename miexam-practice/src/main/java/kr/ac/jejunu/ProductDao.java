package kr.ac.jejunu;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class ProductDao {
    private  final JdbcTemplate jdbcTemplate;


    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product get(Long id) throws SQLException {
        String sql = "select * from product where id = ?";
        Object[] params = new Object[]{id};
        return jdbcTemplate.queryForObject( sql, params );
    }

    public Long insert(Product product) throws SQLException {
        String sql = "insert into product(title, price)  value(?,?) ";
        Object[] params = new Object[]{product.getTitle(), product.getPrice()};
        KeyHolder keyHolder = new GeneratedKeyHolder(  );
        int update = jdbcTemplate.update(con->{
            PreparedStatement preparedStatement = con.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject( i + 1, params[i] );
            }
            return preparedStatement;
        },keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void uppdate(Product product) throws SQLException {
        String sql = "update product set title = ?, price = ? where id= ? ";
        Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};
        jdbcTemplate.update( sql, params );
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from product where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update( sql, params );
    }

}
