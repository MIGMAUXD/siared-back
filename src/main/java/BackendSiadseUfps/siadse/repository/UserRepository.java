package BackendSiadseUfps.siadse.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import BackendSiadseUfps.siadse.entity.User;




public interface UserRepository extends JpaRepository<User, Integer> {
	  Optional<User> findById(User integer);
	  
	    User findByEmail(String email);
	    

}
