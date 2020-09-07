package ankang.springmvc.homework.service;

import ankang.springmvc.homework.dao.ResumeDao;
import ankang.springmvc.homework.pojo.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-06
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Override
    public List<Resume> queryAll() {
        return resumeDao.findAll();
    }

    @Override
    public Resume query(Integer id) {
        return resumeDao.findById(id).get();
    }

    @Override
    public Resume save(Resume resume) {
        return resumeDao.save(resume);
    }

    @Override
    public void delete(Integer id) {
        resumeDao.deleteById(id);
    }
}
