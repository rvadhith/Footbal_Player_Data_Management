package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.AgentDAO;
import services.AgentOperations;
import services.Login;
import services.SignUp;
import utility.GetInputAndValidate;

public class AgentController {
	public void LoginAndSignUp() throws Exception {
		AgentDAO agentdao = new AgentDAO();
		agentdao.agentLoginReset();
		System.out.println("***********************************");
		System.out.println("Would you like to Login or SignUp");
		System.out.println("1 - SignUp");
		System.out.println("2 - Login");
		int loginSignUpChoice = 0;
		loginSignUpChoice = GetInputAndValidate.inputValidation(loginSignUpChoice, 1, 2);
		switch(loginSignUpChoice) {
		case 1:
			SignUp sp = new SignUp();
			sp.AgentSignUp();
			break;
		case 2:
			Login login = new Login();
			login.agentLogin();
			break;
		}
	}
	
	public void agentMenu() throws Exception {
		System.out.println("***********************************");
		System.out.println("Enter the choice of operation: ");
		System.out.println("1 - Search players");
		System.out.println("2 - View Bookmarked players");
		System.out.println("3 - Logout");
		int choice = 0;
		choice = GetInputAndValidate.inputValidation(choice, 1, 3);
		AgentOperations ao = new AgentOperations();
		AgentDAO agentdao = new AgentDAO();
		HomescreenController hc = new HomescreenController();
		
		switch(choice) {
		case 1:
			ao.viewFilterPlayers();
			break;
		case 2:
			ao.viewBookmarkedPlayers();
			break;
		case 3:
			int loggedInId = agentdao.agentLoggedInId();
			agentdao.agentLogoutStatusUpdate(loggedInId);
			hc.homescreenMenu();
			break;
		}
	}
}
