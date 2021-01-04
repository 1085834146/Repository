package com.fjndljh.Controller;

import com.fjndljh.entity.Blog;
import com.fjndljh.entity.User;
import com.fjndljh.repository.BlogRepository;
import com.fjndljh.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

//  后端控制器，加入@Controller注解
@Controller
@RequestMapping("/login")   //  访问路径
public class UserController {

    @RequestMapping("/res2")    //  查看所有的博客
    public ModelAndView findAll(User user, HttpSession httpSession) {    //  ModelAndView是用于做数据整合的对象，是MVC本土化方式
        InputStream inputStream =UserController.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
        User res = userRepository.findByUser(user);

        BlogRepository blogRepository = sqlSession.getMapper(BlogRepository.class);

        ModelAndView modelAndView = new ModelAndView(); //  显示数据，返回
        if(res != null){
//            modelAndView.setViewName("forward:/blogPage.jsp");//这种方法只能用forward！
            modelAndView.setViewName("forward:/allBlog.jsp");//这种方法只能用forward！

//            List<Blog> list = blogRepository.findByUser(res);
            List<Blog> list = blogRepository.findAll();
            modelAndView.addObject("username", res.getUserName());
            modelAndView.addObject("password", res.getPassword());
            modelAndView.addObject("blogList", list);
            //  把user对象放入session中
            httpSession.setAttribute("USER_SESSION", res);
            httpSession.setAttribute("username", res.getUserName());
            httpSession.setAttribute("password", res.getPassword());
            httpSession.setAttribute("blogList", list);
            System.out.println(list);
            return modelAndView;    //  返回对象包含：1、返回位置  2、返回的数据
        }
        else {
            modelAndView.setViewName("forward:/erronPage.jsp");
            return modelAndView;
        }
    }

    @RequestMapping("/res3")    //  查看自己的博客
    public ModelAndView findByUser(User user, HttpSession httpSession) {    //  ModelAndView是用于做数据整合的对象，是MVC本土化方式
        InputStream inputStream =UserController.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BlogRepository blogRepository = sqlSession.getMapper(BlogRepository.class);

        ModelAndView modelAndView = new ModelAndView(); //  显示数据，返回
        modelAndView.setViewName("forward:/blogPage.jsp");//这种方法只能用forward！

        List<Blog> list = blogRepository.findByUser(user);
        modelAndView.addObject("blogList", list);
        //  把user对象放入session中
        httpSession.setAttribute("blogList", list);
        return modelAndView;    //  返回对象包含：1、返回位置  2、返回的数据
    }

    @RequestMapping("/sign")
    public String signIn(User user){
        InputStream inputStream =UserController.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
        userRepository.save(user);
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();
        return "login";  //  == login.jsp
    }
}
