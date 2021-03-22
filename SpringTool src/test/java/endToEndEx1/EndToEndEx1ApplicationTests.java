package endToEndEx1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EndToEndEx1ApplicationTests {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private UserService service;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	void contextLoads() {
		System.out.println("hi");
	}
	
	@Test
	public void testCreateUser() {
	    UserInfo user = new UserInfo("joshi0951@gmail.com", "joshi", "harsh", 8130501851L);	    
	    UserInfo savedUser = repo.save(user);
	    UserInfo existUser = entityManager.find(UserInfo.class, savedUser.getPhone());
	    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	}
	
	@Test
	public void checkLoginService() {
		boolean ans=service.userLoginCheckTry(8130501851L, "joshi");
		assertEquals(true, ans);
	}
	
	@Test
	public void checkRegisterService() {
		boolean ans=service.userRegistraion(8130501853L, "qwerty", "joshi0951@gmail.com", "harsh");
		System.out.println(ans);
		assertEquals(true, ans);
	}

}
