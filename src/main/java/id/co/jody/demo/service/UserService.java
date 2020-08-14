package id.co.jody.demo.service;

import id.co.jody.demo.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User addUser(User request);

    User updateUser(User request);

    User getUserDetail(Long userId);

    Page<User> getAllUser(Integer pageIndex, Integer pageSize);

    void deleteUser(Long userId);
}
