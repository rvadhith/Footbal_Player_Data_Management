package controller;

import services.AdminOperations;
import utility.GetInputAndValidate;

//public class AdminController {
//	public void adminMenu() throws Exception {
//		System.out.println("\nEnter the choice of operation: ");
//		System.out.println("1 - Add Club");
//		System.out.println("2 - View Club");
//		System.out.println("3 - Add Position");
//		System.out.println("4 - View Position");
//		System.out.println("5 - Add Player");
//		System.out.println("6 - View Player");
//		System.out.println("7 - Update Player attributes");
//		System.out.println("8 - Delete player");
//		System.out.println("9 - Logout");
//		int choice = 0;
//		choice = GetInputAndValidate.inputValidation(choice, 1, 9);
//		AdminOperations ao = new AdminOperations();
//		HomescreenController hc = new HomescreenController();
//		switch(choice) {
//		case 1:
//			ao.addClub();
//			break;
//		case 2:
//			ao.viewClubWithOptions();
//			break;
//		case 3:
//			ao.addPositions();
//			break;
//		case 4:
//			ao.viewPositionsWithOptions();
//			break;
//		case 5:
//			ao.addPlayers();
//			break;
//		case 6:
//			ao.viewPlayers();
//			break;
//		case 7:
//			ao.updatePlayers();
//			break;
//		case 8:
//			ao.deletePlayers();
//			break;
//		case 9:
//			hc.homescreenMenu();
//			break;
//		}
//	}
//}



public class AdminController {
	public void adminMenu() throws Exception {
		System.out.println("***********************************");
		System.out.println("Enter the choice of operation: ");
		System.out.println("1 - Add/View Club");
		System.out.println("2 - Add/View Position");
		System.out.println("3 - Add/View/Update/Delete Players");
		System.out.println("4 - Logout");
		System.out.println("***********************************");
		int choice = 0;
		choice = GetInputAndValidate.inputValidation(choice, 1, 4);
		AdminOperations ao = new AdminOperations();
		HomescreenController hc = new HomescreenController();
		switch(choice) {
		case 1:
			System.out.println("***********************************");
			System.out.println("Enter the choice of operation: ");
			System.out.println("1 - Add Club");
			System.out.println("2 - View Club");
			System.out.println("***********************************");
			int clubOperationChoice = 0;
			clubOperationChoice = GetInputAndValidate.inputValidation(clubOperationChoice, 1, 2);
			switch(clubOperationChoice) {
			case 1:
				ao.addClub();
				break;
			case 2:
				ao.viewClubWithOptions();
				break;
			}
			break;
		case 2:
			System.out.println("***********************************");
			System.out.println("Enter the choice of operation: ");
			System.out.println("1 - Add Position");
			System.out.println("2 - View Position");
			System.out.println("***********************************");
			int positionOperationChoice = 0;
			positionOperationChoice = GetInputAndValidate.inputValidation(positionOperationChoice, 1, 2);
			switch(positionOperationChoice) {
			case 1:
				ao.addPositions();
				break;
			case 2:
				ao.viewPositionsWithOptions();
				break;
			}
			break;
		case 3:
			System.out.println("***********************************");
			System.out.println("Enter the choice of operation: ");
			System.out.println("1 - Add Player");
			System.out.println("2 - View Players");
			System.out.println("3 - Update Players");
			System.out.println("4 - Delete Players");
			System.out.println("5 - View Obsolete Players");
			System.out.println("6 - Generate excel sheet");
			System.out.println("***********************************");
			int playerOperationChoice = 0;
			playerOperationChoice = GetInputAndValidate.inputValidation(playerOperationChoice, 1, 6);
			switch(playerOperationChoice) {
			case 1:
				ao.addPlayers();
				break;
			case 2:
				ao.viewPlayers();
				break;
			case 3:
				ao.updatePlayers();
				break;
			case 4:
				ao.deletePlayers();
				break;
			case 5:
				ao.viewObsoletePlayers();
				break;
			case 6:
				ao.generatePlayerExcelSheet();
				break;
			}
			break;
		case 4:
			hc.homescreenMenu();
			break;
		}
	}
}






