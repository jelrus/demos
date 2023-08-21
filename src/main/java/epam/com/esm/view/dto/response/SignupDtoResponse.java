package epam.com.esm.view.dto.response;

import java.util.Objects;

public class SignupDtoResponse {

    private String username;

    private String token;

    public SignupDtoResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignupDtoResponse that = (SignupDtoResponse) o;
        return Objects.equals(username, that.username)
               && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, token);
    }
}