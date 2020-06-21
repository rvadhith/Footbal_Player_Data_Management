package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.AgentDAO;
import model.Admin;

public class Login {
	//Agent Login Validation
	public void agentLogin() throws Exception {
		AgentDAO agentdao = new AgentDAO();
		boolean loginValidation = false; 
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		while(!loginValidation) {
			System.out.println("***********************************");
			System.out.println("Enter your username");
			String username = inp.readLine();
			System.out.println("Enter your password");
			String password = inp.readLine();
			loginValidation = agentdao.agentLoginValidation(username, password);
			if(loginValidation == true) {
				System.out.println("Login successful");
				break;
			}
			else {
				System.out.println("The entered username/password is wrong");
			}
		}
	}

	//Admin Login Validation
	public void adminLogin() throws Exception {
		Admin admin = new Admin("admin", "ad123");
		boolean loginValidation = false; 
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		while(!loginValidation) {
			System.out.println("***********************************");
			System.out.println("Enter your username");
			String checkUsername = inp.readLine();
			System.out.println("Enter your password");
			String checkPassword = inp.readLine();
			if(admin.getUsername().equals(checkUsername) && admin.getPassword().equals(checkPassword)) {
				System.out.println("Login successful");
				break;
			}
			else {
				System.out.println("The entered username/password is wrong");
				loginValidation = false;
			}
		}
	}
}
