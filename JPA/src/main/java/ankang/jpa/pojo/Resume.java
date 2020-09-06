package ankang.jpa.pojo;

import javax.persistence.*;

/**
 * 简历实体类（在类中要使用注解建立实体类和数据表之间的映射关系以及字段和属性的映射关系）
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-01
 */
// 1.@Entity和@Table注解表明实体类和数据表之间的映射关系
@Entity
@Table(name = "db_spring.tb_resume")
// 2.实体类属性和表字段的映射关系
// @Id：标识主键
// @GeneratedValue：标识主键的生成策略
// @Column：建立属性和字段的映射关系
public class Resume {

    /**
     * 生成策略的常用方式：
     * GenerationType.IDENTITY -> 依赖数据库中的主键自增功能：MySQL
     * GenerationType.SEQUENCE -> 依赖序列来产生主键：Oracle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
