package endToEndEx1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	EntityManager entityManager;
	
	
	@Transactional
	public boolean userLoginCheckTry(long phone, String pass) {
		try {
			UserInfo ref = entityManager.find(UserInfo.class, phone);
			long id = ref.getPhone();
			String password = ref.getPassword();
			if (phone==id & pass.equals(password)) {
				System.out.println("LOGIN SUCCESSFULL");
				return true;
			} else {
				System.out.println("PASSWORD INCORRECT");
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println("USER DOES NOT EXIST");
			return false;
		}
	}
	
	@Transactional
	public boolean userRegistraion(long phone, String pass, String email, String name) {
				if(!repo.existsById(phone)) {
					UserInfo user = new UserInfo(email, pass, name, phone);
					
					List<UserInfo> allUsers=repo.findAll();
				    
				    List<String> emails= new ArrayList<String>();
				    
				    for (UserInfo userInfo : allUsers) {
						emails.add(userInfo.getEmail());
					}
				    
				    if (emails.contains(user.getEmail())) {
				    	System.out.println("Email Already Registered");
						return false;
					}
						repo.save(user);
				    
				    System.out.println("User Registration sucessfull");
				    return true;
				}
				System.out.println("User Already exists");
				return false;
	}

}
