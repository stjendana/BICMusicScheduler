package BIC.Vancouver.music_scheduler.dao;

import BIC.Vancouver.music_scheduler.model.user;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<user, Integer> {
}