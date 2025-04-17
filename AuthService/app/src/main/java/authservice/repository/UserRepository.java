package authservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import authservice.entities.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, String>{
	public UserInfo findByUsername(String username);
}
