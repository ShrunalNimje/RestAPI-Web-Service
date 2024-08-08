package my.mood.restAPI.RestAPI.Web.Service.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	// URI versioning
	@GetMapping("v1/person")
	public PersonV1 firstViewOfPerson() {
		return new PersonV1("Shrunal Nimje");
	}
	
	@GetMapping("v2/person")
	public PersonV2 secondViewOfPerson() {
		return new PersonV2(new Name("Shrunal", "Nimje"));
	}
	
	// Request Parameter versioning
	@GetMapping(path = "person", params = "v-1")
	public PersonV1 firstViewOfPersonRequestParameter() {
		return new PersonV1("Shrunal Nimje");
	}
	
	@GetMapping(path = "person", params = "v-2")
	public PersonV2 secondViewOfPersonRequestParameter() {
		return new PersonV2(new Name("Shrunal", "Nimje"));
	}
	
	// Header versioning
	@GetMapping(path = "person", headers = "X-API-VERSION=1")
	public PersonV1 firstViewOfPersonHeader() {
		return new PersonV1("Shrunal Nimje");
	}
	
	@GetMapping(path = "person", headers = "X-API-VERSION=2")
	public PersonV2 secondViewOfPersonHeader() {
		return new PersonV2(new Name("Shrunal", "Nimje"));
	}
	
	// Media type versioning
	@GetMapping(path = "person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 firstViewOfPersonMedia() {
		return new PersonV1("Shrunal Nimje");
	}
	
	@GetMapping(path = "person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 secondViewOfPersonMedia() {
		return new PersonV2(new Name("Shrunal", "Nimje"));
	}
}
