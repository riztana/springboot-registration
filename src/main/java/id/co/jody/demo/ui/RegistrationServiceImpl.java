package id.co.jody.demo.ui;

import id.co.jody.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RegistrationServiceImpl implements RegistrationService {


    @Override
    public void register(User user) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/mitrais/user");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(builder.build().encode().toUri(), user, User.class);
    }
}
