package ankang.ssm.pojo;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-30
 */
public class Account {

    private String carNo;
    private String name ;
    private Integer money;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "carNo='" + carNo + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
