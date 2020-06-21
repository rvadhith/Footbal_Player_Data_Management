package services;

import java.io.IOException;

public interface AdminOperationsInterface {
	void addClub() throws IOException, Exception;
	void viewClub() throws Exception;
	void viewClubWithOptions() throws Exception;
	
	void addPlayers() throws Exception;
	void viewPlayers() throws Exception;
	void updatePlayers() throws NumberFormatException, IOException, Exception;
	void deletePlayers() throws NumberFormatException, IOException, Exception;
	void viewObsoletePlayers() throws Exception;
	void generatePlayerExcelSheet() throws IOException, Exception;
	
	void addPositions() throws Exception;
	void viewPositions() throws Exception;
	void viewPositionsWithOptions() throws Exception;
}
