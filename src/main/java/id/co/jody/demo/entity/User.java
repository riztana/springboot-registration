package id.co.jody.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 50, nullable = false)
    @NotBlank(message = "first name is needed")
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    @NotBlank(message = "last name is needed")
    private String lastName;

    @Column(name = "phone_number", length = 20, nullable = false, unique = true)
    @NotBlank(message = "phone number is needed")
    private String phoneNumber;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank(message = "email is needed")
    private String email;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "gender", length = 1)
    private Character gender;
}
