package services;

import java.io.IOException;
import java.util.List;

import model.Players;

public interface AgentOperationsInterface {
	void viewFilterPlayers() throws Exception, IOException;
	List<Players> searchAllPlayers() throws Exception;
	List<Integer> searchPlayersWithFilters() throws NumberFormatException, IOException, Exception;
	void viewBookmarkedPlayers() throws Exception;
	void bookmarkPlayers(List<Integer> filteredPlayerIds, List<Integer> bookmarkedPlayerIds) throws Exception;
}
