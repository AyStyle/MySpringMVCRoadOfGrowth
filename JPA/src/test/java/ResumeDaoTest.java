import ankang.jpa.dao.ResumeDao;
import ankang.jpa.pojo.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-02
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class ResumeDaoTest {

    /**
     * 注入测试的dao
     */
    @Autowired
    private ResumeDao resumeDao;

    /*
     * dao层接口调用：
     *     1.基础的增删改查
     *     2.专门针对查询的详细分析使用
     */

    /**
     * 根据id查询
     */
    @Test
    public void testFindById() {
        final Optional<Resume> byId = resumeDao.findById(1);
        System.out.println(byId.get());
    }

    /**
     * 查询一个对象
     */
    @Test
    public void testFindOne() {
        final Resume conditionBean = new Resume();
        conditionBean.setName("ak");

        final Example<Resume> condition = Example.of(conditionBean);
        final Optional<Resume> one = resumeDao.findOne(condition);

        final Resume resume = one.get();
        System.out.println(resume);

    }


    /**
     * 保存对象
     */
    @Test
    public void testSave() {
        // 新增和更新都使用save方法，通过传入对象的主键有无来区分：没有主键就是新增，有主键就是更新

        // 更新
        final Resume updateBean = new Resume();
        updateBean.setId(3);
        updateBean.setAddress("深圳");
        updateBean.setName("ak");
        updateBean.setPhone("312");

        final Resume update = resumeDao.save(updateBean);
        System.out.println(update);

        // 插入
        final Resume saveBean = new Resume();
        saveBean.setName("ankangkang");
        saveBean.setAddress("广州");
        saveBean.setPhone("132");

        final Resume save = resumeDao.save(saveBean);
        System.out.println(save);
    }

    /**
     * 根据id删除
     */
    @Test
    public void testDeleteById() {
        resumeDao.deleteById(4);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        resumeDao.findAll().forEach(System.out::println);
    }

    /**
     * 使用JPQL查询
     */
    @Test
    public void testFindByJpql() {
        final Resume one = resumeDao.findByJpql(2);
        System.out.println(one);
    }

    /**
     * 使用SQL查询
     */
    @Test
    public void testFindBySql() {
        final Resume one = resumeDao.findBySql(1);
        System.out.println(one);
    }

    /**
     * 使用SQL查询
     */
    @Test
    public void testFindByMethod() {
        final List<Resume> list = resumeDao.findByNameLikeAndAddress("a%" , "上海");

        list.forEach(System.out::println);
    }

    /**
     * 动态查询，查询单个对象，这里调用的方式是JpaSpecificationExecutor接口中的方法
     */
    @Test
    public void testSpecification() {

        /*
         * 动态组装查询条件
         * */
        final Specification<Resume> resumeSpecification = new Specification<>() {
            /**
             * Creates a WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
             * {@link Root} and {@link CriteriaQuery}.
             *
             * @param root            must not be {@literal null}. 获取需要查询的对象属性
             * @param query           must not be {@literal null}. 自定义查询方式，用不上
             * @param criteriaBuilder must not be {@literal null}. 构建查询条件，内部封装了很多查询条件（精准查询，模糊查询）
             * @return a {@link Predicate}, may be {@literal null}.
             */
            @Override
            public Predicate toPredicate(Root<Resume> root , CriteriaQuery<?> query , CriteriaBuilder criteriaBuilder) {
                // 获取属性
                final Path<Object> name = root.get("name");

                // 使用构建器，针对name属性构建条件（精准查询），例如：name = 'ak'
                // 参数1：代表属性对象 -> name
                // 参数2：代表要等于的值 -> ak
                final Predicate predicate = criteriaBuilder.equal(name , "ak");

                return predicate;
            }

        };

        final Optional<Resume> one = resumeDao.findOne(resumeSpecification);
        final Resume resume = one.get();
        System.out.println(resume);
    }

    /**
     * 动态查询，多条件查询
     */
    @Test
    public void testSpecificationMultipleCondition() {

        /*
         * 动态组装查询条件
         * */
        final Specification<Resume> resumeSpecification = new Specification<>() {
            /**
             * Creates a WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
             * {@link Root} and {@link CriteriaQuery}.
             *
             * @param root            must not be {@literal null}. 获取需要查询的对象属性
             * @param query           must not be {@literal null}. 自定义查询方式，用不上
             * @param criteriaBuilder must not be {@literal null}. 构建查询条件，内部封装了很多查询条件（精准查询，模糊查询）
             * @return a {@link Predicate}, may be {@literal null}.
             */
            @Override
            public Predicate toPredicate(Root<Resume> root , CriteriaQuery<?> query , CriteriaBuilder criteriaBuilder) {
                // 获取属性
                final Path<Object> name = root.get("name");
                final Path<Object> address = root.get("address");

                // 生成两个条件
                final Predicate predicate1 = criteriaBuilder.equal(name , "ak");
                final Predicate predicate2 = criteriaBuilder.like(address.as(String.class) , "深%");

                // 组合条件
                final Predicate predicate = criteriaBuilder.and(predicate1 , predicate2);

                return predicate;
            }

        };

        final Optional<Resume> one = resumeDao.findOne(resumeSpecification);
        final Resume resume = one.get();
        System.out.println(resume);
    }

    /**
     * 排序查询
     */
    @Test
    public void testSort() {
        resumeDao.findAll(Sort.by(Sort.Order.desc("id"))).forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void testPage() {
        /*
         * 参数一：页码
         * 参数二：每页的个数
         * 参数三：是否排序
         * */
        final Pageable pageable =  PageRequest.of(0 , 2 , Sort.by(Sort.Order.desc("id")));

        resumeDao.findAll(pageable).forEach(System.out::println);
    }

}
