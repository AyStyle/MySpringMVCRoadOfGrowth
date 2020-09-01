package ankang.jpa.dao;

import ankang.jpa.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-02
 */
// 符合Spring Data JPA要求的DAO层接口需要继承JpaRepository和JpaSpecificationExecutor
// JpaRepository<操作的实体类类型，主键类型>：封装了基本的CRUD操作
// JpaSpecificationExecutor<操作的实体类类型>：封装了复杂查询的操作（分页，排序）
public interface ResumeDao extends JpaRepository<Resume, Integer>, JpaSpecificationExecutor<Resume> {
}
