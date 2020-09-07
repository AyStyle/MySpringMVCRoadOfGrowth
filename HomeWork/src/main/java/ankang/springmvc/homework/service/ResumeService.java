package ankang.springmvc.homework.service;

import ankang.springmvc.homework.pojo.Resume;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-06
 */
public interface ResumeService {

    List<Resume> queryAll();

    Resume query(Integer id);

    Resume save(Resume resume);

    void delete(Integer id);

}
