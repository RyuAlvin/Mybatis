package cn.lognpage.dao;

import cn.lognpage.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 分页方式一：在SQL层面实现分页
    public List<User> getUserList(Map<String,Integer> map);

    public List<User> getUserListByRowBounds();
}
