package my.mood.restAPI.RestAPI.Web.Service.user;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

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
	// adding link to retrieve all users
	// EntityModel and WebMvcLinkBuilder
	@GetMapping("users/{user_id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int user_id) {
		User oneUser = daoService.findOne(user_id);
		
		if(oneUser == null) {
			throw new UserNotFoundException("User id : " + user_id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(oneUser);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		entityModel.add(link.withRel("all-users"));		
		
        return entityModel;	
	}
	
	
	// create the specific user
	@PostMapping("users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User createdUser = daoService.createUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{user_id}")
				.buildAndExpand(createdUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{user_id}")
	public void deleteOne(@PathVariable int user_id) {
		daoService.deleteOne(user_id);
	}
	
}
