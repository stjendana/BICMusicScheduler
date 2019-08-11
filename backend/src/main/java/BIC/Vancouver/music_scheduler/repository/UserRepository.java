package BIC.Vancouver.music_scheduler.repository;

import BIC.Vancouver.music_scheduler.model.user;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<user, Integer> {
}
