package BIC.Vancouver.music_scheduler.repository;

import BIC.Vancouver.music_scheduler.model.user;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<user, Integer> {

    @Query("SELECT u FROM user u where u.username = :username")
    user findUserByUserName(@Param("username") String email);
}
