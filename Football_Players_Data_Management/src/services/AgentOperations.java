package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import controller.AgentController;
import dao.AgentDAO;
import dao.PlayerDAO;
import dao.PositionDAO;
import model.Players;
import model.Position;
import utility.GetInputAndValidate;

public class AgentOperations implements AgentOperationsInterface {
	AgentController agentcontroller = new AgentController();
	
	//To search all players
	@Override
	public List<Players> searchAllPlayers() throws Exception {
		PlayerDAO playerdao = new PlayerDAO();
		List<Players> allPlayers = new ArrayList<Players>();
		allPlayers = playerdao.listAllPlayers();
		return allPlayers;
	}	
	
	@Override
	public List<Integer> searchPlayersWithFilters() throws Exception {
		List<Integer> searchTerms = new ArrayList<Integer>();
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		
		//To get age choice
		System.out.println("***********************************");
		System.out.println("Kindly specify the age range: ");
		System.out.println("1 - Search players of all age");
		System.out.println("2 - Custom age range");
		int ageChoice = 0;
		int minAge = 0;
		int maxAge = 100;
		ageChoice = GetInputAndValidate.inputValidation(ageChoice, 1, 2);
		switch(ageChoice) {
		case 1:
			minAge = 0;
			maxAge = 100;
			break;
		case 2:
			System.out.println("***********************************");
			System.out.println("Please enter the minimum age");
			int minimumAgeChoice = 0;
			minimumAgeChoice = GetInputAndValidate.inputValidation(minimumAgeChoice, 0, 99);
			System.out.println("Please enter the maximum age");
			int maximumAgeChoice = 0;
			maximumAgeChoice = GetInputAndValidate.inputValidation(maximumAgeChoice, minimumAgeChoice, 100);
			minAge = minimumAgeChoice;
			maxAge = maximumAgeChoice;
			break;
		}
		searchTerms.add(minAge);
		searchTerms.add(maxAge);
		
		//To get position id
		AdminOperations ao = new AdminOperations();
		ao.viewPositions();
		PositionDAO positiondao = new PositionDAO();
		List<Integer> positionIds = new ArrayList<Integer>();
		positionIds = positiondao.getMinMaxPositionId();
		int minPositionId = positionIds.get(0);
		int maxPositionId = positionIds.get(1);
		System.out.println("***********************************");
		System.out.println("Enter the player position id: ");
		int playerPositionId = 0;
		playerPositionId = GetInputAndValidate.inputValidation(playerPositionId, minPositionId, maxPositionId);
		searchTerms.add(playerPositionId);
		
		//To get overall stats choice
		System.out.println("***********************************");
		System.out.println("Kindly specify the overall stats score range: ");
		System.out.println("1 - Search all players");
		System.out.println("2 - Custom overall stats range");
		int overallStatsChoice = 0;
		int minOverallStats = 0;
		int maxOverallStats = 100;
		overallStatsChoice = GetInputAndValidate.inputValidation(overallStatsChoice, 1, 2);
		switch(overallStatsChoice) {
		case 1:
			minOverallStats = 0;
			maxOverallStats = 100;
			break;
		case 2:
			System.out.println("***********************************");
			System.out.println("Please enter the minimum overall Stats score");
			int minOverallStatsChoice = 0;
			minOverallStatsChoice = GetInputAndValidate.inputValidation(minOverallStatsChoice, 0, 99);
			System.out.println("Please enter the maximum overall Stats score");
			int maxOverallStatsChoice = 0;
			maxOverallStatsChoice = GetInputAndValidate.inputValidation(maxOverallStatsChoice, minOverallStatsChoice, 100);
			minOverallStats = minOverallStatsChoice;
			maxOverallStats = maxOverallStatsChoice;
			break;
		}
		searchTerms.add(minOverallStats);
		searchTerms.add(maxOverallStats);
		
		
		//To get height choice
		System.out.println("***********************************");
		System.out.println("Kindly specify the height range: ");
		System.out.println("1 - Search all players");
		System.out.println("2 - Custom height range");
		int heightChoice = 0;
		int minHeight = 0;
		int maxHeight = 100;
		heightChoice = GetInputAndValidate.inputValidation(heightChoice, 1, 2);
		switch(heightChoice) {
		case 1:
			minHeight = 0;
			maxHeight = 400;
			break;
		case 2:
			System.out.println("***********************************");
			System.out.println("Please enter the minimum height");
			int minHeightChoice = 0;
			minHeightChoice = GetInputAndValidate.inputValidation(minHeightChoice, 0, 399);
			System.out.println("Please enter the maximum height");
			int maxHeightChoice = 0;
			maxHeightChoice = GetInputAndValidate.inputValidation(maxHeightChoice, minHeightChoice, 400);
			minHeight = minHeightChoice;
			maxHeight = maxHeightChoice;
			break;
		}
		searchTerms.add(minHeight);
		searchTerms.add(maxHeight);
		
		
		//To get pace choice
		System.out.println("***********************************");
		System.out.println("Kindly specify the pace range: ");
		System.out.println("1 - Search all players");
		System.out.println("2 - Custom pace range");
		int paceChoice = 0;
		int minPace = 0;
		int maxPace = 100;
		paceChoice = GetInputAndValidate.inputValidation(paceChoice, 1, 2);
		switch(paceChoice) {
		case 1:
			minPace = 0;
			maxPace = 100;
			break;
		case 2:
			System.out.println("***********************************");
			System.out.println("Please enter the minimum pace");
			int minPaceChoice = 0;
			minPaceChoice = GetInputAndValidate.inputValidation(minPaceChoice, 0, 99);
			System.out.println("Please enter the maximum pace");
			int maxPaceChoice = 0;
			maxPaceChoice = GetInputAndValidate.inputValidation(maxPaceChoice, minPaceChoice, 100);
			minPace = minPaceChoice;
			maxPace = maxPaceChoice;
			break;
			}
		searchTerms.add(minPace);
		searchTerms.add(maxPace);
		
		
		//To get strength choice
		System.out.println("***********************************");
		System.out.println("Kindly specify the strength range: ");
		System.out.println("1 - Search all players");
		System.out.println("2 - Custom strength range");
		int strengthChoice = 0;
		int minStrength = 0;
		int maxStrength = 100;
		strengthChoice = GetInputAndValidate.inputValidation(strengthChoice, 1, 2);
		switch(strengthChoice) {
		case 1:
			minStrength = 0;
			maxStrength = 100;
			break;
		case 2:
			System.out.println("***********************************");
			System.out.println("Please enter the minimum strength");
			int minStrengthChoice = 0;
			minStrengthChoice = GetInputAndValidate.inputValidation(minStrengthChoice, 0, 99);
			System.out.println("Please enter the maximum strength");
			int maxStrengthChoice = 0;
			maxStrengthChoice = GetInputAndValidate.inputValidation(maxStrengthChoice, minStrengthChoice, 100);
			minStrength = minStrengthChoice;
			maxStrength = maxStrengthChoice;
			break;
		}
		searchTerms.add(minStrength);
		searchTerms.add(maxStrength);
		
		
		//To get price choice
		System.out.println("***********************************");
		System.out.println("Kindly specify the price range: ");
		System.out.println("1 - Search all players");
		System.out.println("2 - Custom price range");
		int priceChoice = 0;
		int minPrice = 0;
		int maxPrice = 1000000000;
		priceChoice = GetInputAndValidate.inputValidation(priceChoice, 1, 2);
		switch(priceChoice) {
		case 1:
			minPrice = 0;
			maxPrice = 1000000000;
			break;
		case 2:
			System.out.println("***********************************");
			System.out.println("Please enter the minimum price");
			int minPriceChoice = 0;
			minPriceChoice = GetInputAndValidate.inputValidation(minPriceChoice, 0, 999999999);
			System.out.println("Please enter the maximum price");
			int maxPriceChoice = 0;
			maxPriceChoice = GetInputAndValidate.inputValidation(maxPriceChoice, minPriceChoice, 1000000000);
			minPrice = minPriceChoice;
			maxPrice = maxPriceChoice;
			break;
		}
		searchTerms.add(minPrice);
		searchTerms.add(maxPrice);
		
		
		//To get contract time left choice
		System.out.println("***********************************");
		System.out.println("Kindly specify the contract time left range: ");
		System.out.println("1 - Search all players");
		System.out.println("2 - Custom contract time left range");
		int contractTimeLeftChoice = 0;
		int minContractTimeLeft = 0;
		int maxContractTimeLeft = 120;
		contractTimeLeftChoice = GetInputAndValidate.inputValidation(contractTimeLeftChoice, 1, 2);
		switch(contractTimeLeftChoice) {
		case 1:
			minContractTimeLeft = 0;
			maxContractTimeLeft = 120;
			break;
		case 2:
			System.out.println("***********************************");
			System.out.println("Please enter the minimum contract time left");
			int minContractTimeLeftChoice = 0;
			minContractTimeLeftChoice = GetInputAndValidate.inputValidation(minContractTimeLeftChoice, 0, 119);
			System.out.println("Please enter the maximum contract time left");
			int maxContractTimeLeftChoice = 0;
			maxContractTimeLeftChoice = GetInputAndValidate.inputValidation(maxContractTimeLeftChoice, minContractTimeLeftChoice, 120);
			minContractTimeLeft = minContractTimeLeftChoice;
			maxContractTimeLeft = maxContractTimeLeftChoice;
			break;
		}
		searchTerms.add(minContractTimeLeft);
		searchTerms.add(maxContractTimeLeft);
		return searchTerms;
	}
	
	
	
	//To view bookmarked players
	@Override
	public void viewBookmarkedPlayers() throws Exception {
		AgentDAO agentdao = new AgentDAO();
		int agentId = agentdao.agentLoggedInId();
		List<Players> list = null;
		list = agentdao.getBookmarkedPlayer(agentId);
		
		if(list.size() > 0) {
			System.out.println("***********************************************************************************************************************************************************");
			System.out.format("|%-3s|%-25s|%-3s|%-20s|%-15s|%-13s|%-12s|%-4s|%-8s|%-13s|%-27s|%n", "Id","Name","Age","Club","Position","Overall Stats","Height (cms)","Pace","Strength","Value (Euros)","Contract time left (months)");
			System.out.println("***********************************************************************************************************************************************************");
			for(Players player: list){
				System.out.format("|%-3d|%-25s|%-3d|%-20s|%-15s|%-13d|%-12d|%-4d|%-8d|%-13d|%-27d|%n",player.getId(), player.getName(), player.getAge(), player.getClubs().getName(), player.getPosition().getPositionName(), player.getOverallStats(), player.getHeight(), player.getPace(), player.getStrength(), player.getBasePrice(), player.getContractTimeLeft());
			}
			System.out.println("***********************************************************************************************************************************************************");
		}
		else{
			System.out.println("***********************************");
			System.out.println("There is no bookmarked players");
		}
		agentcontroller.agentMenu();
	}

	//Selection of search type before bookmarking
	@Override
	public void viewFilterPlayers() throws Exception, IOException {
		AgentDAO agentdao = new AgentDAO();
		PlayerDAO playerdao = new PlayerDAO();
		List<Players> list = null;
		
		System.out.println("Enter the choice for player search");
		System.out.println("1 - Search all players");
		System.out.println("2 - Search by individual parameters");
		int playerSearchChoice = 0;
		playerSearchChoice = GetInputAndValidate.inputValidation(playerSearchChoice, 1, 2);
		switch(playerSearchChoice) {
		case 1:
			list = searchAllPlayers();
			break;
		case 2:
			List<Integer> searchList = searchPlayersWithFilters();
			list = playerdao.filterPlayers(searchList);
			break;
		}
		
		
		if(list.size() > 0) {
			ArrayList<Integer> filteredIds = new ArrayList<Integer>();
			System.out.println("***********************************************************************************************************************************************************");
			System.out.format("|%-3s|%-25s|%-3s|%-20s|%-15s|%-13s|%-12s|%-4s|%-8s|%-13s|%-27s|%n", "Id","Name","Age","Club","Position","Overall Stats","Height (cms)","Pace","Strength","Value (Euros)","Contract time left (months)");
			System.out.println("***********************************************************************************************************************************************************");
			for(Players player: list){
				filteredIds.add(player.getId());
				System.out.format("|%-3d|%-25s|%-3d|%-20s|%-15s|%-13d|%-12d|%-4d|%-8d|%-13d|%-27d|%n",player.getId(), player.getName(), player.getAge(), player.getClubs().getName(), player.getPosition().getPositionName(), player.getOverallStats(), player.getHeight(), player.getPace(), player.getStrength(), player.getBasePrice(), player.getContractTimeLeft());
			}
			System.out.println("***********************************************************************************************************************************************************");
			int agentId = agentdao.agentLoggedInId();
			List<Integer> bookmarkedIds = agentdao.getBookmarkedPlayerIds(agentId);
			
			bookmarkPlayers(filteredIds, bookmarkedIds);
		}
		else{
			System.out.println("***********************************");
			System.out.println("There is no player data to display. Please modify your search parameters");
		}
		
		agentcontroller.agentMenu();
	}

	//To bookmark players
	@Override
	public void bookmarkPlayers(List<Integer> filteredPlayerIds, List<Integer> bookmarkedPlayerIds) throws Exception {
		AgentDAO agentdao = new AgentDAO();
		ArrayList<Integer> playerIds = new ArrayList<Integer>();
		int playerId = 0;
		int agentId = agentdao.agentLoggedInId();
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("***********************************");
		System.out.println("Would you like to bookmark any players");
		System.out.println("1 - Yes");
		System.out.println("2 - No");
		int bookmarkPlayersChoice = 0;
		bookmarkPlayersChoice = GetInputAndValidate.inputValidation(bookmarkPlayersChoice, 1, 2);
		switch(bookmarkPlayersChoice) {
		case 1:
			System.out.println("***********************************");
			System.out.println("Based on the search results, a maximum of " + filteredPlayerIds.size() + " players can be bookmarked");
			System.out.println("Enter the number of players to be bookmarked");
			int noOfPlayers = 0;
			noOfPlayers = GetInputAndValidate.inputValidation(noOfPlayers, 1, filteredPlayerIds.size()); 
			int iterationCount = 1;
			while(iterationCount <= noOfPlayers) {
				System.out.println("***********************************");
				System.out.println("Kindly enter the id one by one to be bookmarked");
				playerId = Integer.parseInt(inp.readLine());
				if(!bookmarkedPlayerIds.contains(playerId)) {
					if(filteredPlayerIds.contains(playerId)) {
						agentdao.bookmarkPlayers(agentId, playerId);
						System.out.println("***********************************");
						System.out.println("The player with id " + playerId + " has been bookmarked");
						iterationCount++;
					}
					else {
						System.out.println("***********************************");
						System.out.println("The entered player id does not exist in the search result");
						System.out.println("Kindly re-enter the id present in the search result");
					}
				}
				else {
					System.out.println("***********************************");
					System.out.println("The player with id " + playerId + " is already bookmarked");
					iterationCount++;
				}		
			}
			break;
		case 2:
			break;
		}
		agentcontroller.agentMenu();
	}
}
