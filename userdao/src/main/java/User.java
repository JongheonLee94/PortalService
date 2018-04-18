import lombok.Data;
//lombok data어노테이션은 get/set을 대신 만들어줌
@Data
public class User {


    private Integer id;
    private String name;
    private String password;
}
