package BIC.Vancouver.music_scheduler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class userMinistry {

    @Id
    private Integer userId;

    private String ministryId;

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getMinistryId() { return ministryId; }

    public void setMinistryId(String ministryId) { this.ministryId = ministryId; }
}
