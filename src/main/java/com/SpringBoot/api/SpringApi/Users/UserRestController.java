package com.SpringBoot.api.SpringApi.Users;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.annotation.Resource;

@RestController
public class UserRestController {

	private UserDaoService Dservice;
	@Autowired
    private ReportService reportService;	
	public UserRestController(UserDaoService dservice) {
		super();
		Dservice = dservice;
	}



	@GetMapping ("/users")
	public List<User> getAllusers () {
		
		//Populate();
		List<User> users = Dservice.findAllUsers();
		return users;
		
	}
	
	@GetMapping ("/users/{id}")
	public Optional<User> getSpecificUsers (@PathVariable int id) {
		Optional<User> user = Dservice.findOne(id);	
		System.out.println(user);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found with id = " + id);			
		}
		
		return user; 
		
	}
	
	@DeleteMapping ("/users/{id}")
	public ResponseEntity<User>  DeleteUser (@PathVariable int id) {
		
		Optional<User> user = Dservice.findOne(id);	
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found with id = " + id);			
		}
		
		Dservice.RemoveUser(id);		
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/users").build().toUri();
        
        return ResponseEntity.status(303).location(location).build();	
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser (@RequestBody User user) {
		Dservice.saveUser(user);
		int id  = user.getId();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();	
	}
	
	@GetMapping("/generateReport")
    public ResponseEntity<byte[]> generateReport() {
    	try {
    		// Prepare report parameters and data source
            Map<String, Object> parameters = new HashMap<>();
            List<User> dataSource =  Dservice.findAllUsers();// Fetch data from your repository or service

            byte[] reportBytes = reportService.generateReport(parameters, dataSource);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "report.pdf");

            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
    	}
    	 catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
	
	public void Populate () {
		
		Dservice.saveUser(new User ("Ahmed" , LocalDate.now().minusYears(40)));
		Dservice.saveUser(new User ("Mahmoud" , LocalDate.now().minusYears(35)));
		Dservice.saveUser(new User ("Hassan" , LocalDate.now().minusYears(43)));
		Dservice.saveUser(new User ("Sayed" , LocalDate.now().minusYears(52)));
		Dservice.saveUser(new User ("Yousef" , LocalDate.now().minusYears(24)));
		Dservice.saveUser(new User ("Mohamed" , LocalDate.now().minusYears(31)));
		
	}

}
