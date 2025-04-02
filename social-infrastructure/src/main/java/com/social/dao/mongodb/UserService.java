package com.social.dao.mongodb;

import com.social.dao.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 创建用户
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // 获取用户通过ID
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    // 获取用户通过名字
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    // 删除用户
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    // 更新用户
    public User updateUser(String id, User updatedUser) {
        // 检查是否存在该用户
        if (userRepository.existsById(id)) {
            updatedUser.setId(id); // 更新用户ID，确保它不丢失
            return userRepository.save(updatedUser); // 使用 save() 来更新
        }
        return null; // 如果用户不存在，返回 null
    }
}
