package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;


@Configuration
public class DaoFactory {
    private String className= "com.mysql.jdbc.Driver" ;
    private String url="jdbc:mysql://localhost/jeju";
    private String userName="jeju" ;
    private String password="jejupw" ;
    @Bean
    public ProductDao productDao(){
        return new ProductDao( datasource() );
    }
    @Bean
    public DataSource datasource()
    {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource(  );
        try {
            dataSource.setDriverClass( (Class<? extends Driver>) Class.forName( className ) );
        } catch (ClassNotFoundException e) {
            new RuntimeException( e );
        }
        dataSource.setUrl( url );
        dataSource.setUsername( userName );
        dataSource.setPassword( password );
        return dataSource;
    }
}
