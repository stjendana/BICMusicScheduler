package BIC.Vancouver.music_scheduler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class user {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String city;

    private boolean isActive;

    private Integer role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() { return isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public Integer getRole() { return role; }

    public void setRole(Integer role) { this.role = role; }
}
