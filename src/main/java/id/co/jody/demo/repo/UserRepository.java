package id.co.jody.demo.repo;

import id.co.jody.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumberOrEmail(String phone, String email);
}
