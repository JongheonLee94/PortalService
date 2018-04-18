package kr.ac.jejunu;

public class DaoFactory {
    public ProductDao getProductDao(){
        return new ProductDao( getConnection() );
    }
    public ConnectionMaker getConnection() {
        return new JejuConnectionMaker();
    }
}
