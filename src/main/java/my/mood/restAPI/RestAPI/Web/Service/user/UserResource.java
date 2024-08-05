package my.mood.restAPI.RestAPI.Web.Service.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	public UserDaoService daoService;
	
	public UserResource(UserDaoService daoService) {
		this.daoService = daoService;
	}
	
	
	// return all the users
	@GetMapping("users")
	public List<User> retrieveAllUsers() {
		return daoService.findAll();
	}
	
	
	// return the specific user
	@GetMapping("users/{user_id}")
	public User retrieveOneUser(@PathVariable int user_id) {
		return daoService.findOne(user_id);
	}
	
	
	// create the specific user
	@PostMapping("users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User createdUser = daoService.createUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{user_id}")
				.buildAndExpand(createdUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
}
