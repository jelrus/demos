package epam.com.esm.view.dto.request;

import java.util.Objects;

public class SignupDtoRequest extends DtoRequest {

    private String username;

    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignupDtoRequest that = (SignupDtoRequest) o;
        return Objects.equals(username, that.username)
               && Objects.equals(password, that.password)
               && Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, confirmPassword);
    }
}
