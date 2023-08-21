package epam.com.esm.model.facade.impl;

import epam.com.esm.model.facade.UserFacade;
import epam.com.esm.model.service.UserService;
import epam.com.esm.util.UserDtoConverter;
import epam.com.esm.view.dto.request.UserDtoRequest;
import epam.com.esm.view.dto.response.UserDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDtoResponse create(UserDtoRequest req) {
        return UserDtoConverter.convertToResponse(userService.create(UserDtoConverter.convertToUser(req)));
    }

    @Override
    public UserDtoResponse update(UserDtoRequest req, Long id) {
        return UserDtoConverter.convertToResponse(userService.update(UserDtoConverter.convertToUser(req)));
    }

    @Override
    public UserDtoResponse findById(Long id) {
        return UserDtoConverter.convertToResponse(userService.findById(id));
    }

    @Override
    public UserDtoResponse delete(Long id) {
        return UserDtoConverter.convertToResponse(userService.delete(id));
    }

    @Override
    public List<UserDtoResponse> findAll() {
        return userService.findAll().stream().map(UserDtoResponse::new).collect(Collectors.toList());
    }
}