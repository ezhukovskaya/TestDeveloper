package application.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String login;
    private String password;

    @Override
    public String toString() {
        return String.format("%s:%s@", this.login, this.password);
    }
}
