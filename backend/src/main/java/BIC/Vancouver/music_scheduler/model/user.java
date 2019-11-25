package BIC.Vancouver.music_scheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    private String username;

    private String password;

    private String city;

    private boolean isActive;

    private Integer role;

    private String ministry;

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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() { return isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public Integer getRole() { return role; }

    public void setRole(Integer role) { this.role = role; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getMinistry() { return ministry; }

    public void setMinistry(String ministry) { this.ministry = ministry; }
}
