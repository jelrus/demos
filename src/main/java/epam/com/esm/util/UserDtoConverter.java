package epam.com.esm.util;

import epam.com.esm.persistence.entity.impl.User;
import epam.com.esm.view.dto.request.UserDtoRequest;
import epam.com.esm.view.dto.response.UserDtoResponse;

public class UserDtoConverter {

    public static User convertToUser(UserDtoRequest req) {
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(req.getPassword());
        return u;
    }

    public static UserDtoResponse convertToResponse(User user) {
        return new UserDtoResponse(user);
    }
}