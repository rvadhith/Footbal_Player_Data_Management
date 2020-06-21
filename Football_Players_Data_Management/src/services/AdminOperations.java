package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import controller.AdminController;
import dao.ClubDAO;
import dao.PlayerDAO;
import dao.PositionDAO;
import model.Clubs;
import model.Players;
import model.Position;
import utility.ExcelGeneration;
import utility.GetInputAndValidate;

public class AdminOperations implements AdminOperationsInterface {
	AdminController ac = new AdminController(); 
	
	@Override
	public void addClub() throws Exception {
		ClubDAO clubdao = new ClubDAO();
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		int clubId = clubdao.ClubIdIncrmenter();
		System.out.println("***********************************");
		System.out.println("Enter the club name: ");
		String clubName = inp.readLine();
		System.out.println("***********************************");
		Clubs club = new Clubs(clubId,clubName);
		clubdao.addClub(club);
		ac.adminMenu();
	}

	@Override
	public void viewClub() throws Exception {
		ClubDAO clubdao = new ClubDAO();
		
		List<Clubs> list = null;
		try {
			list = clubdao.listAllClubs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("***********************************");
		System.out.format("|%-7s|%-25s|%n", "Club Id","Club name");
		System.out.println("***********************************");
		for(Clubs club: list){
			System.out.format("|%-7d|%-25s|%n",club.getId(), club.getName());
			//System.out.println(club.getId() + " " + club.getName());
		}	
		System.out.println("***********************************");
	}
	
	@Override
	public void viewClubWithOptions() throws Exception {
		viewClub();
		ac.adminMenu();
	}
	


	@Override
	public void addPlayers() throws Exception {
			BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
			PlayerDAO playerdao = new PlayerDAO();
			ClubDAO clubdao = new ClubDAO();
			PositionDAO positiondao = new PositionDAO();
			
			int playerId = playerdao.getNextId();
			System.out.println("***********************************");
			String playerName = "";
			boolean iterationRequired = false;
			System.out.println("Enter the player name: ");
			while(!iterationRequired) {
				playerName = inp.readLine();
				if(playerName.matches("^[a-zA-Z ]*$")){
					iterationRequired = true;
				}
				else {
					System.out.println("Only spaces and letters are allowed");
					iterationRequired = false;
				}
			}
			

			
			System.out.println("***********************************");
			System.out.println("Enter the player age: ");
			System.out.println("Minimum age - 0; Maximum age - 100");
			int playerAge = 0;
			playerAge = GetInputAndValidate.inputValidation(playerAge, 0, 100);
			
			System.out.println("***********************************");
			System.out.println("Following is the list of clubs along with their ids");
			viewClub();
			List<Integer> clubIds = new ArrayList<Integer>();
			clubIds = clubdao.getMinMaxClubId();
			int minClubId = clubIds.get(0);
			int maxClubId = clubIds.get(1);
			System.out.println("***********************************");
			System.out.println("Enter the club id: ");
			int playerClubId = 0;
			playerClubId = GetInputAndValidate.inputValidation(playerClubId, minClubId, maxClubId);
			Clubs club = new Clubs(playerClubId, null);
			
			System.out.println("***********************************");
			System.out.println("Following is the list of positions along with their ids");
			viewPositions();
			List<Integer> positionIds = new ArrayList<Integer>();
			positionIds = positiondao.getMinMaxPositionId();
			int minPositionId = positionIds.get(0);
			int maxPositionId = positionIds.get(1);
			System.out.println("***********************************");
			System.out.println("Enter the player position id: ");
			int playerPositionId = 0;
			playerPositionId = GetInputAndValidate.inputValidation(playerPositionId, minPositionId, maxPositionId);
			Position position = new Position(playerPositionId, null);
			
			System.out.println("***********************************");
			System.out.println("Enter the overallStats score of player: ");
			System.out.println("Minimum overall stats score - 0; Maximum overall stats score - 100");
			int playerOverallStats = 0;
			playerOverallStats = GetInputAndValidate.inputValidation(playerOverallStats, 0, 100);
			
			System.out.println("***********************************");
			System.out.println("Enter the height of player in centimeters: ");
			System.out.println("Minimum height - 1 cm; Maximum height - 400 cm");
			int playerHeight = 0;
			playerHeight = GetInputAndValidate.inputValidation(playerHeight, 1, 400);
			
			System.out.println("***********************************");
			System.out.println("Enter the pace of player: ");
			System.out.println("Minimum pace - 0; Maximum pace - 100");
			int playerPace = 0;
			playerPace = GetInputAndValidate.inputValidation(playerPace, 0, 100);
			
			System.out.println("***********************************");
			System.out.println("Enter the strength of player: ");
			System.out.println("Minimum strength - 0; Maximum strength - 100");
			int playerStrength = 0;
			playerStrength = GetInputAndValidate.inputValidation(playerStrength, 0, 100);
			
			System.out.println("***********************************");
			System.out.println("Enter the value of player: ");
			System.out.println("Minimum value - 0; Maximum value - 1 billion");
			int playerValue = 0;
			playerValue = GetInputAndValidate.inputValidation(playerValue, 0, 1000000000);
			
			System.out.println("***********************************");
			System.out.println("Enter the contract time left for the player in months: ");
			System.out.println("Minimum contract time left - 0; Maximum contract time left - 120 months");
			int playerContractTimeLeft = 0;
			playerContractTimeLeft = GetInputAndValidate.inputValidation(playerContractTimeLeft, 0, 120);
			Players player = new Players(playerId, playerName, playerAge, club, position, playerOverallStats, playerHeight, playerPace, playerStrength, playerValue, playerContractTimeLeft);
			playerdao.addPlayer(player);
			ac.adminMenu();
	}

	@Override
	public void viewPlayers() throws Exception {
		PlayerDAO playerdao = new PlayerDAO();

		List<Players> list = null;
		try {
			list = playerdao.listAllPlayers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(list.size() > 0) {
			System.out.println("***********************************************************************************************************************************************************");
			System.out.format("|%-3s|%-25s|%-3s|%-20s|%-15s|%-13s|%-12s|%-4s|%-8s|%-13s|%-27s|%n", "Id","Name","Age","Club","Position","Overall Stats","Height (cms)","Pace","Strength","Value (Euros)","Contract time left (months)");
			System.out.println("***********************************************************************************************************************************************************");
			for(Players player: list){
				System.out.format("|%-3d|%-25s|%-3d|%-20s|%-15s|%-13d|%-12d|%-4d|%-8d|%-13d|%-27d|%n",player.getId(), player.getName(), player.getAge(), player.getClubs().getName(), player.getPosition().getPositionName(), player.getOverallStats(), player.getHeight(), player.getPace(), player.getStrength(), player.getBasePrice(), player.getContractTimeLeft());
				//System.out.println(player.getId() + " " + player.getName() + " " + player.getAge() + " " + player.getClubs().getName() + " " + player.getPosition().getPositionName() + " " + player.getOverallStats() + " " + player.getHeight() + " " + player.getPace() + " " + player.getStrength() + " " + player.getBasePrice() + " " + player.getContractTimeLeft());
			}
			System.out.println("***********************************************************************************************************************************************************");
			ac.adminMenu();	
		}
		else {
			System.out.println("***********************************");
			System.out.println("No players data exist");
			ac.adminMenu();
		}
	}

	@Override
	public void updatePlayers() throws Exception {
		PlayerDAO playerdao = new PlayerDAO();
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		
		
		List<Integer> playerIds = new ArrayList<Integer>();
		List<Players> list = null;
		try {
			list = playerdao.listAllPlayers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(list.size() > 0) {
			System.out.println("***********************************************************************************************************************************************************");
			System.out.format("|%-3s|%-25s|%-3s|%-20s|%-15s|%-13s|%-12s|%-4s|%-8s|%-13s|%-27s|%n", "Id","Name","Age","Club","Position","Overall Stats","Height (cms)","Pace","Strength","Value (Euros)","Contract time left (months)");
			System.out.println("***********************************************************************************************************************************************************");
			for(Players player: list){
				playerIds.add(player.getId());
				System.out.format("|%-3d|%-25s|%-3d|%-20s|%-15s|%-13d|%-12d|%-4d|%-8d|%-13d|%-27d|%n",player.getId(), player.getName(), player.getAge(), player.getClubs().getName(), player.getPosition().getPositionName(), player.getOverallStats(), player.getHeight(), player.getPace(), player.getStrength(), player.getBasePrice(), player.getContractTimeLeft());
			}
			System.out.println("***********************************************************************************************************************************************************");
			
			System.out.println("\nEnter the id of the player");
			
			int playerId = 0;
			
			
			boolean choiceValidation = false;
			 while(!choiceValidation) {
				 try {
					 playerId = Integer.parseInt(inp.readLine());
					 if(playerIds.contains(playerId)) {
						 choiceValidation = true;
						 //System.out.println("The choice you have entered is " + input);
					 }
					 else {
						 System.out.println("***********************************");
						 System.out.println("The entered id does not exist. Please enter the player id displayed above");
						 choiceValidation = false;
					 }
				} catch (NumberFormatException | IOException e) {
					choiceValidation = false;
					System.out.println("***********************************");
					System.out.println("The entry is not a number. Please enter the player id displayed above");
				}
			 }
			
			
			System.out.println("***********************************");
			System.out.println("Select the attribute to be updated");
			System.out.println("1 - Update Age");
			System.out.println("2 - Update Club");
			System.out.println("3 - Update Position");
			System.out.println("4 - Update Overall Stats");
			System.out.println("5 - Update Height");
			System.out.println("6 - Update Pace");
			System.out.println("7 - Update Strength");
			System.out.println("8 - Update Value");
			System.out.println("9 - Update Contract Time Left");
			System.out.println("***********************************");
			int updateAttributeChoice = 0;
			updateAttributeChoice = GetInputAndValidate.inputValidation(updateAttributeChoice, 1, 9);
			switch(updateAttributeChoice) {
			case 1:
				System.out.println("***********************************");
				System.out.println("Enter the new age: ");
				System.out.println("Minimum age - 0; Maximum age - 100");
				int newAge = 0;
				newAge = GetInputAndValidate.inputValidation(newAge, 0, 100);
				playerdao.updatePlayerAge(playerId, newAge);
				break;
			case 2:
				ClubDAO clubdao = new ClubDAO();
				viewClub();
				List<Integer> clubIds = new ArrayList<Integer>();
				clubIds = clubdao.getMinMaxClubId();
				int minClubId = clubIds.get(0);
				int maxClubId = clubIds.get(1);
				System.out.println("Enter the new club id: ");
				int playerClubId = 0;
				playerClubId = GetInputAndValidate.inputValidation(playerClubId, minClubId, maxClubId);
				playerdao.updatePlayerClub(playerId, playerClubId);
				break;
			case 3:
				PositionDAO positiondao = new PositionDAO();
				viewPositions();
				List<Integer> positionIds = new ArrayList<Integer>();
				positionIds = positiondao.getMinMaxPositionId();
				int minPositionId = positionIds.get(0);
				int maxPositionId = positionIds.get(1);
				System.out.println("***********************************");
				System.out.println("Enter the player position id: ");
				int playerPositionId = 0;
				playerPositionId = GetInputAndValidate.inputValidation(playerPositionId, minPositionId, maxPositionId);
				playerdao.updatePlayerPosition(playerId, playerPositionId);
				break;
			case 4:
				System.out.println("***********************************");
				System.out.println("Enter the new overall stats score: ");
				System.out.println("Minimum overall stats score - 0; Maximum overall stats score - 100");
				int newOverallStatsScore = 0;
				newOverallStatsScore = GetInputAndValidate.inputValidation(newOverallStatsScore, 0, 100);
				playerdao.updatePlayerOverallStatsScore(playerId, newOverallStatsScore);
				break;
			case 5:
				System.out.println("***********************************");
				System.out.println("Enter the new height: ");
				System.out.println("Minimum height - 1 cm; Maximum height - 400 cm");
				int newHeight = 0;
				newHeight = GetInputAndValidate.inputValidation(newHeight, 1, 400);
				playerdao.updatePlayerHeight(playerId, newHeight);
				break;
			case 6:
				System.out.println("***********************************");
				System.out.println("Enter the new pace: ");
				System.out.println("Minimum pace - 0; Maximum pace - 100");
				int newPace = 0;
				newPace = GetInputAndValidate.inputValidation(newPace, 0, 100);
				playerdao.updatePlayerPace(playerId, newPace);
				break;
			case 7:
				System.out.println("***********************************");
				System.out.println("Enter the new strength: ");
				System.out.println("Minimum strength - 0; Maximum strength - 100");
				int newStrength = 0;
				newStrength = GetInputAndValidate.inputValidation(newStrength, 0, 100);
				playerdao.updatePlayerStrength(playerId, newStrength);
				break;
			case 8:
				System.out.println("***********************************");
				System.out.println("Enter the new value: ");
				System.out.println("Minimum value - 0; Maximum value - 1 billion");
				int newValue = 0;
				newValue = GetInputAndValidate.inputValidation(newValue, 0, 1000000000);
				playerdao.updatePlayerValue(playerId, newValue);
				break;
			case 9:
				System.out.println("***********************************");
				System.out.println("Enter the new contract Time Left: ");
				System.out.println("Minimum contract time left - 0; Maximum contract time left - 120 months");
				int newContractTimeLeft = 0;
				newContractTimeLeft = GetInputAndValidate.inputValidation(newContractTimeLeft, 0, 120);
				playerdao.updatePlayerContractTimeLeft(playerId, newContractTimeLeft);
				break;
			}
			ac.adminMenu();
		}
		else {
			System.out.println("***********************************");
			System.out.println("No players exist to be updated");
			ac.adminMenu();
		}		
	}

	@Override
	public void deletePlayers() throws Exception {
		PlayerDAO playerdao = new PlayerDAO();
		List<Players> playerList = null;
		try {
			playerList = playerdao.listAllPlayers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Integer> playerIds = new ArrayList<Integer>();
		
		if(playerList.size() > 0) {
			System.out.println("***********************************************************************************************************************************************************");
			System.out.format("|%-3s|%-25s|%-3s|%-20s|%-15s|%-13s|%-12s|%-4s|%-8s|%-13s|%-27s|%n", "Id","Name","Age","Club","Position","Overall Stats","Height (cms)","Pace","Strength","Value (Euros)","Contract time left (months)");
			System.out.println("***********************************************************************************************************************************************************");
			for(Players player: playerList){
				playerIds.add(player.getId());
				System.out.format("|%-3d|%-25s|%-3d|%-20s|%-15s|%-13d|%-12d|%-4d|%-8d|%-13d|%-27d|%n",player.getId(), player.getName(), player.getAge(), player.getClubs().getName(), player.getPosition().getPositionName(), player.getOverallStats(), player.getHeight(), player.getPace(), player.getStrength(), player.getBasePrice(), player.getContractTimeLeft());
			}
			System.out.println("***********************************************************************************************************************************************************");
			System.out.println("\nEnter the id to be deleted");
			BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
			
			int idToBeDeleted = 0;
			
			
			boolean choiceValidation = false;
			 while(!choiceValidation) {
				 try {
					 idToBeDeleted = Integer.parseInt(inp.readLine());
					 if(playerIds.contains(idToBeDeleted)) {
						 choiceValidation = true;
						 //System.out.println("The choice you have entered is " + input);
					 }
					 else {
						 System.out.println("***********************************");
						 System.out.println("The entered id doesnt exist. Please enter the player id displayed above");
						 choiceValidation = false;
					 }
				} catch (NumberFormatException | IOException e) {
					choiceValidation = false;
					System.out.println("***********************************");
					System.out.println("The entry is not a number. Please enter the player id displayed above");
				}
			 }
			
			playerdao.addObsoletePlayer(idToBeDeleted);
			playerdao.deletePlayer(idToBeDeleted);
	
			ac.adminMenu();	
		}
		else {
			System.out.println("No players exist to be deleted");
			ac.adminMenu();
		}
		
	}
	
	@Override
	public void viewObsoletePlayers() throws Exception {
		PlayerDAO playerdao = new PlayerDAO();
		List<Players> ObsoletePlayerList = null;
		try {
			ObsoletePlayerList = playerdao.listAllObsoletePlayers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(ObsoletePlayerList.size() > 0) {
			System.out.println("***********************************************************************************************************************************************************");
			System.out.format("|%-3s|%-25s|%-3s|%-20s|%-15s|%-13s|%-12s|%-4s|%-8s|%-13s|%-27s|%n", "Id","Name","Age","Club","Position","Overall Stats","Height (cms)","Pace","Strength","Value (Euros)","Contract time left (months)");
			System.out.println("***********************************************************************************************************************************************************");
			for(Players obsoletePlayer: ObsoletePlayerList){
				System.out.format("|%-3d|%-25s|%-3d|%-20s|%-15s|%-13d|%-12d|%-4d|%-8d|%-13d|%-27d|%n", obsoletePlayer.getId(), obsoletePlayer.getName(), obsoletePlayer.getAge(), obsoletePlayer.getClubs().getName(), obsoletePlayer.getPosition().getPositionName(), obsoletePlayer.getOverallStats(), obsoletePlayer.getHeight(), obsoletePlayer.getPace(), obsoletePlayer.getStrength(), obsoletePlayer.getBasePrice(), obsoletePlayer.getContractTimeLeft());
			}
			System.out.println("***********************************************************************************************************************************************************");
			ac.adminMenu();	
		}
		else {
			System.out.println("***********************************");
			System.out.println("No players have been deleted so far");
			ac.adminMenu();
		}
	}

	@Override
	public void addPositions() throws Exception {
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("Enter the position id: ");
		int positionId = 0;
		try {
			positionId = Integer.parseInt(inp.readLine());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("***********************************");
		System.out.println("Enter the position name: ");
		String positionName = null;
		try {
			positionName = inp.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Position position = new Position(positionId,positionName);
		PositionDAO positiondao = new PositionDAO();
		positiondao.addPosition(position);
		ac.adminMenu();
	}

	@Override
	public void viewPositions() throws Exception {
		PositionDAO positiondao = new PositionDAO();
		
		List<Position> list = null;
		try {
			list = positiondao.listAllPosition();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("*****************************");
		System.out.format("|%-11s|%-15s|%n", "Position Id","Position");
		System.out.println("*****************************");
		for(Position position: list){
			System.out.format("|%-11d|%-15s|%n", position.getPositionId(),position.getPositionName());
			//System.out.println(position.getPositionId() + " " + position.getPositionName());
		}
		System.out.println("*****************************");
	}

	@Override
	public void viewPositionsWithOptions() throws Exception {
		viewPositions();
		ac.adminMenu();
	}

	@Override
	public void generatePlayerExcelSheet() throws Exception {
		Players player = new Players();
		PlayerDAO playerdao = new PlayerDAO();

		List<Players> list = new ArrayList<Players>();
		try {
			list = playerdao.listAllPlayers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(list.size() > 0) {
			ExcelGeneration excel = new ExcelGeneration();
			HSSFWorkbook hwb = excel.excelGenerate(player, list);
			ac.adminMenu();	
		}
		else {
			System.out.println("***********************************");
			System.out.println("No players data exist");
			ac.adminMenu();
		}
	}
}
