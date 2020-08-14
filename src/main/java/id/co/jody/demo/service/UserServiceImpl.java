package id.co.jody.demo.service;

import id.co.jody.demo.entity.User;
import id.co.jody.demo.exception.DataException;
import id.co.jody.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepositoryImpl;

    @Autowired
    public UserServiceImpl(UserRepository userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }

    @Override
    public Page<User> getAllUser(Integer pageIndex, Integer pageSize) {
        return userRepositoryImpl.findAll(PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public User getUserDetail(Long userId) {
        Optional<User> existing = userRepositoryImpl.findById(userId);
        if (existing.isPresent()) {
            return existing.get();
        } else {
            throw new DataException("User not found");
        }
    }

    public User addUser(User request) {
        User existing = userRepositoryImpl.findByPhoneNumberOrEmail(request.getPhoneNumber(), request.getEmail());
        if (existing != null) {
            throw new DataException("Email or Phone Number Already Registered");
        }
        return userRepositoryImpl.save(request);
    }

    @Override
    public User updateUser(User request) {
        User existing = userRepositoryImpl.findByPhoneNumberOrEmail(request.getPhoneNumber(), request.getEmail());
        if (existing == null) {
            throw new DataException("User not found");
        }
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setBirthDate(request.getBirthDate());
        existing.setGender(request.getGender());
        return userRepositoryImpl.save(existing);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> existing = userRepositoryImpl.findById(userId);
        if (existing.isPresent()) {
            userRepositoryImpl.delete(existing.get());
        } else {
            throw new DataException("User not found");
        }
    }
}
