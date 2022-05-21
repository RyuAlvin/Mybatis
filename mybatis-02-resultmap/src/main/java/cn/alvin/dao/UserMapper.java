package cn.alvin.dao;

import cn.alvin.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<User> getUserList();

    public User getUserById(Integer id);

    public User getUserById1(Integer id);

    public User getUserById2(Integer id);
}
