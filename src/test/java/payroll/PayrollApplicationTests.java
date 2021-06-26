package payroll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import dao.EmployeeRepository;
import model.Employee;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PayrollApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/employees/1")).andDo(print()).andExpect(status().isOk());
				//.andExpect(jsonPath("$.content").value(2))
	}

	@Test
	public void whenFindAll() {
		//given
		Employee emp = new Employee();
		emp.setName("Aparna");
		emp.setRole("developer");
		entityManager.persist(emp);
		entityManager.flush();

	    Employee secondemp = new Employee();
	    secondemp.setName("Kumari");
	    secondemp.setRole("developer");
	    entityManager.persist(secondemp);
	    entityManager.flush();

	    //when
	    List<Employee> emplist = employeeRepository.findAll();

	    //then
	    assertThat(emplist.size()).isEqualTo(2);
	 }

}
