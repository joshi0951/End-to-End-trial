package endToEndEx1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(path = "/userLogin.api", consumes = "application/json", produces = "application/json")
	boolean userCheck(@RequestBody UserLoginParameter ulp) {
			return service.userLoginCheckTry(ulp.getPhone(), ulp.getPass());
	}
	
	@PostMapping(path="/addUser.api", consumes = "application/json", produces = "application/json")
	public boolean addNewUserRegistration(@RequestBody UserInfo newUser) {
			boolean status = service.userRegistraion(newUser.getPhone(), newUser.getPassword(), newUser.getEmail(), newUser.getName());
			return status;
	}

}
