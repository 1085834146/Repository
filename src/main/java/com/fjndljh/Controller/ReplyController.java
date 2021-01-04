package com.fjndljh.Controller;

import com.fjndljh.entity.Blog;
import com.fjndljh.entity.Reply;
import com.fjndljh.entity.User;
import com.fjndljh.repository.ReplyRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @RequestMapping("/delete")
    public String deleteReply(Integer id, HttpSession httpSession) {    //  删除回复
        InputStream inputStream = UserController.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReplyRepository replyRepository = sqlSession.getMapper(ReplyRepository.class);

        Integer num = replyRepository.deleteReply(id);
        sqlSession.commit();
        sqlSession.close();
        if (num != null) {
            System.out.println("delete reply  is " + id);
        }
        Blog blog = (Blog) httpSession.getAttribute("BLOG_SESSION");
        long blogID = blog.getBlogID();
        return "redirect:/blog/view?id=" + blogID;
    }

    @RequestMapping("/doReply")
    public String doReply(String words, HttpSession httpSession){   //  回复
        InputStream inputStream = UserController.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReplyRepository replyRepository = sqlSession.getMapper(ReplyRepository.class);

        // 取出session中的博客和本用户对象，get信息组成reply对象
        User user = (User)httpSession.getAttribute("USER_SESSION");
        Blog blog = (Blog)httpSession.getAttribute("BLOG_SESSION");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());    // new Date()为获取当前系统时间

        Reply reply = new Reply(0, blog.getBlogID(), user.getUserName(), words, time);
        System.out.println(reply);
        Integer num = replyRepository.save(reply);
        sqlSession.commit();
        return "redirect:/blog/view?id=" + blog.getBlogID();
    }
}
