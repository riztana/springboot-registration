package id.co.jody.demo.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import id.co.jody.demo.entity.User;
import id.co.jody.demo.exception.DataException;
import id.co.jody.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@RestController
public class UserController {
    private UserService userServiceImpl;

    public UserController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user")
    public ResponseEntity<Page<User>> getAllUser(@RequestParam("pageSize") Integer pageSize,
                                                 @RequestParam("pageIndex") Integer pageIndex) {
        return ResponseEntity.ok(userServiceImpl.getAllUser(pageIndex, pageSize));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserDetail(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userServiceImpl.getUserDetail(userId));
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody User request) {
        String phoneNumber = validatePhoneNumber(request.getPhoneNumber());
        request.setPhoneNumber(phoneNumber);
        return ResponseEntity.ok(userServiceImpl.addUser(request));
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User request) {
        String phoneNumber = validatePhoneNumber(request.getPhoneNumber());
        request.setPhoneNumber(phoneNumber);
        return ResponseEntity.ok(userServiceImpl.updateUser(request));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userServiceImpl.deleteUser(userId);
        return ResponseEntity.ok("Delete Success");
    }


    private String validatePhoneNumber(String phone) {
        PhoneNumberUtil numberUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phoneNumber;
        try {
            phoneNumber = numberUtil.parse(phone, "ID");
        } catch (NumberParseException e) {
            throw new DataException("Please enter valid Indonesian phone number");
        }
        if (!numberUtil.isValidNumber(phoneNumber)) {
            throw new DataException("Please enter valid Indonesian phone number");
        }
        return "+62" + phoneNumber.getNationalNumber();
    }
}
