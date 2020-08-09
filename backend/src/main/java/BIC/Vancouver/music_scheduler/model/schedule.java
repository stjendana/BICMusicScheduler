package BIC.Vancouver.music_scheduler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class schedule {

    public schedule(Integer id, Integer ministryId, Integer userId, Long date) {
        this.id = id;
        this.ministryId = ministryId;
        this.userId = userId;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer ministryId;

    private Integer userId;

    private Long date;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id;}

    public Integer getMinistryId(){return  ministryId;}
    public void setMinistryId(Integer ministryId){this.ministryId = ministryId;}

    public Integer getUserId(){ return  userId;}
    public void setUserId(Integer userId){this.userId = userId;}

    public Long getDate(){ return date;}
    public void setDate(Long date){this.date = date;}
}
