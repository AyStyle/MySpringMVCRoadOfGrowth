import ankang.springmvc.homework.dao.ResumeDao;
import ankang.springmvc.homework.pojo.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-12
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:spring.xml")
public class ResumeDaoTest {

    @Autowired
    private ResumeDao resumeDao;

    @Test
    public void testFindAll(){
        resumeDao.findAll().forEach(System.out::println);
    }

    @Test
    public void testFindById(){
        System.out.println(resumeDao.findById(1));
    }

    @Test
    public void testCreateOne(){
        final Resume resume = new Resume();
        resume.setName("ankang");
        resume.setPhone("111");
        resume.setAddress("beijing");
        final Resume save = resumeDao.save(resume);
        System.out.println(save);
    }

    @Test
    public void testUpdateOne(){
        final Resume resume = new Resume();
        resume.setId(1);
        resume.setName("ak");
        resume.setPhone("222");
        resume.setAddress("shanghai");
        final Resume save = resumeDao.save(resume);
        System.out.println(save);
    }

    @Test
    public void testDeleteOne(){
        resumeDao.deleteById(1);
    }

}
