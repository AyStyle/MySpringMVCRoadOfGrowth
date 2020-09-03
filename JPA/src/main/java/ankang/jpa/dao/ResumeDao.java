package ankang.jpa.dao;

import ankang.jpa.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-02
 */
// 符合Spring Data JPA要求的DAO层接口需要继承JpaRepository和JpaSpecificationExecutor
// JpaRepository<操作的实体类类型，主键类型>：封装了基本的CRUD操作
// JpaSpecificationExecutor<操作的实体类类型>：封装了复杂查询的操作（分页，排序）
public interface ResumeDao extends JpaRepository<Resume, Integer>, JpaSpecificationExecutor<Resume> {

    /*
     * ========================== 针对查询的使用进行分析 ==========================
     * 方式一：调用继承接口中的犯法，例如：findOne()、findAll()、findById()
     *
     * 方式二：引入JPQL（JPA查询语言）语句进行查询，JPQL语句类似于SQL语句
     *         SQL操作的是数据表和字段
     *         JPQL操作对象和属性
     *
     * 方式三：引入原生的SQL语句
     *
     * 方式四：可以在接口中自定义方法，而且不必引入jpql或者SQL语句，这种方式叫做方法命名规则查询，
     *         也就是说定义的接口方法名是按照一定规则形成的，那么框架就能够理解我们的意图
     *
     * 方式五：动态查询，Service层传入DAO层的条件不确定，把Service拿到条件封装成一个对象传递给DAO层，这个对象就叫做Specification（对条件的一个封装）
     * */


    /**
     * 方式二：JPQL查询
     * ?1：表示使用方法中传入的第一个参数
     *
     * @return
     */
    @Query("from Resume where id = ?1")
    Resume findByJpql(Integer id);

    /**
     * 方式三：Sql产线
     * ?1：表示使用方法中传入的第一个参数
     * nativeQuery属性表示使用原生SQL查询
     *
     * @return
     */
    @Query(value = "SELECT * FROM db_spring.resume WHERE id = ?1", nativeQuery = true)
    Resume findBySql(Integer id);

    /**
     * 方式四：方法命名规则查询
     *         按照name模糊查询（like）
     *         规则：方法名以findBy开头，
     *               然后跟属性名（首字母大写），
     *               接着跟查询方式（模糊查询，等价查询），如果不写查询方式，则：等价查询
     *               如果有多个条件使用And或Or拼接另一个属性
     *
     */
    List<Resume> findByNameLikeAndAddress(String name,String address);
}
