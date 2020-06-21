package controller;

import services.Login;
import utility.GetInputAndValidate;

public class HomescreenController {
	public void homescreenMenu() throws Exception {
		System.out.println("Please enter your choice: ");
		System.out.println("1 - Admin");
		System.out.println("2 - Agent");
		int choice = 0;
		choice = GetInputAndValidate.inputValidation(choice, 1, 2);
		AdminController admincontroller = new AdminController();
		AgentController agentcontroller = new AgentController();
		Login login = new Login();
		switch(choice) {
		case 1:
			login.adminLogin();
			admincontroller.adminMenu();
			break;
		case 2:
			agentcontroller.LoginAndSignUp();
			agentcontroller.agentMenu();
			break;
		}
	}
}
