package cn.ryu.dao;

import cn.ryu.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public List<User> getUserList();

    public void addUser(User user);

    public void addUser2(Map<String,Object> map);

    public List<User> getUserLike(String name);

    public List<User> getUserLike1(String name);
}
