package ankang.springmvc.homework.dao;

import ankang.springmvc.homework.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-06
 */
public interface ResumeDao extends JpaRepository<Resume, Integer>, JpaSpecificationExecutor<Resume> {

}
