package kr.ac.jejunu;


import org.springframework.jdbc.core.JdbcTemplate;

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
        return jdbcTemplate.insert( sql, params );
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
