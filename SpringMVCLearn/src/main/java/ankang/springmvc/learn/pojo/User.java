package ankang.springmvc.learn.pojo;


/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-21
 */
public class User {

    private String name;
    private Integer age;
    private User innerUser;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setInnerUser(User innerUser) {
        this.innerUser = innerUser;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public User getInnerUser() {
        return innerUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", innerUser=" + innerUser +
                '}';
    }
}
