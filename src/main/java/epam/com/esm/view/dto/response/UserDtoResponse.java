package epam.com.esm.view.dto.response;

import epam.com.esm.persistence.entity.impl.User;

import java.util.Objects;

public class UserDtoResponse extends DtoResponse {

    private String username;

    public UserDtoResponse(User user) {
        super(user.getId());
        setUsername(user.getUsername());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDtoResponse that = (UserDtoResponse) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username);
    }
}