package ankang.springmvc.homework.controller;

import ankang.springmvc.homework.pojo.Resume;
import ankang.springmvc.homework.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-06
 */
@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username , @RequestParam("password") String password , HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();

        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("isAuth" , true);
            modelAndView.setViewName("/jsp/list.jsp");
        } else {
            session.setAttribute("isAuth" , false);
            modelAndView.addObject("msg" , "用户名或密码错误，请重新登录！");
            modelAndView.setViewName("/jsp/login.jsp");
        }

        return modelAndView;
    }

    /**
     * 当请求没有登录时，跳转到这里，然后重新登录
     *
     * @return
     */
    @RequestMapping(value = "/unLogin")
    public ModelAndView unLogin() {
        final ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/jsp/login.jsp");

        return modelAndView;
    }

    /**
     * 列表页
     *
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/list.jsp");

        return modelAndView;
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public List<Resume> queryAll() {
        return resumeService.queryAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Resume save(@RequestBody Resume resume) {
        return resumeService.save(resume);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(Integer id) {
        resumeService.delete(id);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Resume query(Integer id) {
        return resumeService.query(id);
    }

}
