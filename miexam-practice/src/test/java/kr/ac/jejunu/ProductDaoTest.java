package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class ProductDaoTest {
    ProductDao productDao;
    DaoFactory daoFactory;
    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext( DaoFactory.class );
        productDao = applicationContext.getBean( "productDao",ProductDao.class );
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {

        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }
    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle( "제주오메기떡" );
        product.setPrice( 15000 );
        Long id = productDao.insert( product );

        Product insertedProduct = productDao.get( id );
        assertEquals(insertedProduct.getId(), id);
        assertEquals(insertedProduct.getTitle(), product.getTitle());
        assertEquals(insertedProduct.getPrice(), product.getPrice());
    }
}