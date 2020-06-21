package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.AgentDAO;
import model.Agents;

public class SignUp {
	public void AgentSignUp() throws Exception {
		AgentDAO agentdao = new AgentDAO();
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in)); 
		
		String userName = "";
		String password = "";
		boolean iterationRequired = true;
		
		List<String> usernameList = new ArrayList<String>();
		usernameList = agentdao.agentSignUpValidation();
		
		while(iterationRequired) {
			System.out.println("Kindly specify your username");
			userName = inp.readLine();
			if(!(usernameList.contains(userName))) {
				System.out.println("Kindly specify your password");
				password = inp.readLine();
				iterationRequired = false;
				break;
			}
			else {
				System.out.println("The entered username already exists");
				iterationRequired = true;
			}
		}
		
		int agentId = agentdao.agentIdIncrmenter();
		Agents agent = new Agents(agentId, userName, password);
		agentdao.agentSignUp(agent);
	}
}
