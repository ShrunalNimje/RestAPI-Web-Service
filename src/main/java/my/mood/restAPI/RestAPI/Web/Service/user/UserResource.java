package my.mood.restAPI.RestAPI.Web.Service.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import my.mood.restAPI.RestAPI.Web.Service.jpa.PostRepository;
import my.mood.restAPI.RestAPI.Web.Service.jpa.UserRepository;

@RestController
public class UserResource {

	@Autowired
	public UserDaoService daoService;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public PostRepository postRepository;
	
	public UserResource(UserDaoService daoService, UserRepository userRepository, PostRepository postRepository) {
		this.daoService = daoService;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	
	// return all the users
	@GetMapping("users")
	public List<User> retrieveAllUsers() {
		// return daoService.findAll();
		return userRepository.findAll();
	}
	
	
	// return the specific user
	// adding link to retrieve all users
	// EntityModel and WebMvcLinkBuilder
	@GetMapping("users/{user_id}")
	public EntityModel<User> retrieveOneUser(@PathVariable int user_id) {
		// User oneUser = daoService.findOne(user_id);
		Optional<User> oneUser = userRepository.findById(user_id);
		
		if(oneUser.equals(null)) {
			throw new UserNotFoundException("User id : " + user_id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(oneUser.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		entityModel.add(link.withRel("all-users"));		
		
        return entityModel;	
	}
	
	
	// create the specific user
	@PostMapping("users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		// User createdUser = daoService.createUser(user);
		User createdUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{user_id}")
				.buildAndExpand(createdUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{user_id}")
	public void deleteOne(@PathVariable int user_id) {
		// daoService.deleteOne(user_id);
		userRepository.deleteById(user_id);
	}
	
	// retrieve all posts for specific user
	@GetMapping("users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.equals(null)) {
			throw new UserNotFoundException("Id : " + id);
		}
		
		return user.get().getPosts();
	}
	
	// create post for specific users
	@PostMapping("users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.equals(null)) {
			throw new UserNotFoundException("id : " + id);
		}
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
