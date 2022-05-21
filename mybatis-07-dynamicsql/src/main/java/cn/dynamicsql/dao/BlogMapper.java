package cn.dynamicsql.dao;

import cn.dynamicsql.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * 什么是动态SQL：动态SQL就是根据不同的条件生成不同的SQL语句。
 * 所谓的动态SQL，本质上还是SQL语句，只是我们可以在SQL层面，去执行一个逻辑代码。
 *
 * 动态SQL是MyBatis的强大特性之一。
 * 如果你使用过JDBC或其它类似框架的经验，你就能体会到根据不同条件拼接SQL语句的痛苦。
 * 例如拼接时要确保不能忘记添加必要的空格，还要注意去掉列表最后一个列名的逗号。
 * 日用动态SQL这一特性可以彻底摆脱这种痛苦。
 *
 * 动态SQL元素和JSTL或基于类似XMl的文本处理器相似。
 * 在MyBatis之前的版本中，有很多元素需要花时间了解。
 * MyBatis3大大精简了元素种类，现在只需学习原来一般的元素即可。
 * MyBtatis采用功能强大的基于OGNL的表达式来淘汰其它大部分元素。
 *
 * OGNL：
 *      对象导航图语言（Object Graph Navigation Language），简称OGNL，
 * 是应用于Java中的一个开源的表达式语言（Expression Language），它被集成在Struts2等框架中，
 * 作用是对数据进行访问，它拥有类型转换、访问对象方法、操作集合对象等功能。
 */
public interface BlogMapper {

    int addBlog(Blog blog);

    List<Blog> queryBlogIf(Map map);

    List<Blog> queryBlogWhere(Map map);

    Integer updateBlog(Map map);

    List<Blog> queryBlogChoose(Map map);

    List<Blog> queryBlogWithFragment(Map map);

    List<Blog> queryBlogForeach1(Map map);

    List<Blog> queryBlogForeach2(List<String> list);
}
