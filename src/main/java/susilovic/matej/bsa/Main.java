package susilovic.matej.bsa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import susilovic.matej.bsa.db.Database;
import susilovic.matej.bsa.model.Player;
import susilovic.matej.bsa.repository.BoxScoreRepository;
import susilovic.matej.bsa.webParser.DataLoader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String ESPN_SCOREBOARD_URL = "http://espn.go.com/nba/scoreboard/_/date/";
    private static final String SCRIPT_GAME_URL_REGEX_PATTERN = "http://espn\\.go\\.com/nba/boxscore\\?gameId=\\d\\d\\d\\d\\d\\d\\d\\d\\d";
    private static final String ESPN_SCRIPT_GAME_URL = "http://espn.go.com/nba/boxscore?gameId=";

    private static Database db = Database.getDatabase();

    public static void main(String[] args) throws IOException {

        LocalDateTime startDate = LocalDateTime.of(2015, 2, 11, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2015, 2, 11, 0, 0);

        List<Player> players = loadGamesBetween(startDate, endDate);
        //TODO refactor to call this method with gameDate parameter
        BoxScoreRepository.insertPlayers(players, startDate);
    }

    private static List<Player> loadGamesBetween(LocalDateTime startDate, LocalDateTime endDate) throws IOException {

        List<String> gameIds = getGameIdsForPeriodBetween(startDate, endDate);

        List<Player> playerData = new ArrayList<Player>();

        for (String gameId : gameIds) {
            List<Player> players = DataLoader.loadDataForGame("http://espn.go.com/nba/boxscore?id=" + gameId);
            players.forEach(StatsCalculator::mapAdvancedStatsTo);
            players.forEach(player -> {
                System.out.println(player.getName() + ", " + Math.round(player.getOffensiveRating()));
            });

            playerData.addAll(players);
        }

        return playerData;
    }

    private static List<String> getGameIdsForPeriodBetween(LocalDateTime startDate, LocalDateTime endDate) throws IOException {

        LocalDateTime start = startDate;
        List<String> gameIds = new ArrayList<String>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        while (!start.isAfter(endDate)) {
            String date = startDate.format(formatter);
            gameIds.addAll(getGameIdsForDate(date));
            start = start.plusDays(1L);
        }
        return gameIds;
    }

    private static List<String> getGameIdsForDate(String date) throws IOException {

        Document website = Jsoup.connect(ESPN_SCOREBOARD_URL + date).get();
        String script = ((DataNode) website.select("script").get(7).childNode(0)).getWholeData();

        List<String> allMatches = new ArrayList<String>();
        Matcher matcher = Pattern.compile(SCRIPT_GAME_URL_REGEX_PATTERN)
                           .matcher(script);
        while (matcher.find()) {
            allMatches.add(matcher.group().replace(ESPN_SCRIPT_GAME_URL, ""));
        }

        return allMatches;
    }
}
