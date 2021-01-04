package com.fjndljh.Controller;

import com.fjndljh.entity.Blog;
import com.fjndljh.entity.Reply;
import com.fjndljh.entity.User;
import com.fjndljh.repository.BlogRepository;
import com.fjndljh.repository.ReplyRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/blog")   //  访问路径
public class BlogController {

     @RequestMapping("/delete1")
    public ModelAndView deleteBlog1(Integer id, HttpSession httpSession){
         System.out.println(id);
         System.out.println("delete a blog");
         InputStream inputStream =UserController.class.getClassLoader().getResourceAsStream("config.xml");
         SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
         SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
         SqlSession sqlSession = sqlSessionFactory.openSession();
         BlogRepository blogRepository = sqlSession.getMapper(BlogRepository.class);
         ReplyRepository replyRepository = sqlSession.getMapper(ReplyRepository.class);

         ModelAndView modelAndView = new ModelAndView(); //  显示数据，返回
         replyRepository.deleteReplyByBlogID(id);   //  若删除博客，在其下的评论也应该被全部删除
         String userName = blogRepository.findById(id).getUserName();
         Integer num = blogRepository.deleteBlog1(id);
         sqlSession.commit();
         if(num != null) {
             System.out.println("num = " + num);
         }
         User tmp = (User)httpSession.getAttribute("USER_SESSION");
         System.out.println(tmp);
         List<Blog> list = blogRepository.findByUser(tmp);
         System.out.println(list);
         sqlSession.close();
         modelAndView.setViewName("forward:/blogPage.jsp");
         modelAndView.addObject("blogList", list);
         httpSession.setAttribute("blogList", list);
         return modelAndView;
     }

     @RequestMapping("/view")
     public String showBlog(Integer id, HttpSession session){
         System.out.println("view a blog of id = " + id);
         InputStream inputStream =UserController.class.getClassLoader().getResourceAsStream("config.xml");
         SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
         SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
         SqlSession sqlSession = sqlSessionFactory.openSession();
         BlogRepository blogRepository = sqlSession.getMapper(BlogRepository.class);

         Blog blog = blogRepository.findById(id);
         System.out.println(blog);
         // 博客对象放入session中，在跳转后的页面将会用到
         session.setAttribute("BLOG_SESSION",blog);
         session.setAttribute("标题", blog.getTitle());
         session.setAttribute("作者", blog.getUserName());
         session.setAttribute("文章", blog.getArticle());
         session.setAttribute("时间", blog.getBlogDate());

        //  在博客正文下展示所有回复，按时间升序排
         ReplyRepository replyRepository = sqlSession.getMapper(ReplyRepository.class);
         List<Reply> list = replyRepository.findByBlogID(id);
         System.out.println("回复: " + list);
         session.setAttribute("replyList", list);
         return "showBlog";
     }
}
