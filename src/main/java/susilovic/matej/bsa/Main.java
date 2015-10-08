package susilovic.matej.bsa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import susilovic.matej.bsa.db.Database;
import susilovic.matej.bsa.model.Player;
import susilovic.matej.bsa.repository.BoxScoreRepository;
import susilovic.matej.bsa.webParser.DataLoader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String ESPN_SCOREBOARD_URL = "http://espn.go.com/nba/scoreboard/_/date/";
    private static final String WINDOW_ESPN_SCOREBOARD_DATA = "window.espn.scoreboardData";
    private static final String SCRIPT_GAME_URL_REGEX_PATTERN = "http://espn\\.go\\.com/nba/boxscore\\?gameId=\\d\\d\\d\\d\\d\\d\\d\\d\\d";
    private static final String ESPN_SCRIPT_GAME_URL = "http://espn.go.com/nba/boxscore?gameId=";

    private static Database db = Database.getDatabase();

    public static void main(String[] args) throws IOException {

        LocalDateTime startDate = LocalDateTime.of(2015, 4, 2, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2015, 4, 15, 0, 0);

        LocalDateTime start = startDate;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        while (!start.isAfter(endDate)) {
            String date = start.format(formatter);

            System.out.println(date);

            List<String> gameIds = getGameIdsForDate(date);

            if(gameIds.isEmpty()) {
                System.out.println("No game Ids found for " + date);
                break;
            }

            for (String gameId : gameIds) {
                List<Player> players = getBoxScoreDateAndCalculateAdvanced(gameId);
                BoxScoreRepository.insertPlayers(players, start);
            }

            start = start.plusDays(1L);
        }
    }

    private static List<String> getGameIdsForDate(String date) throws IOException {

        Elements scripts = Jsoup.connect(ESPN_SCOREBOARD_URL + date).timeout(0).get().select("script");

        for(Element script : scripts) {
            if(script.childNodes().size() == 0) {
                continue;
            }

            String scriptContent = ((DataNode) script.childNode(0)).getWholeData();

            if(!scriptContent.startsWith(WINDOW_ESPN_SCOREBOARD_DATA)) {
                continue;
            }

            List<String> allMatches = new ArrayList<String>();
            Matcher matcher = Pattern.compile(SCRIPT_GAME_URL_REGEX_PATTERN).matcher(scriptContent);
            while (matcher.find()) {
                allMatches.add(matcher.group().replace(ESPN_SCRIPT_GAME_URL, ""));
            }

            return allMatches;
        }

        return Collections.emptyList();
    }

    private static List<Player> getBoxScoreDateAndCalculateAdvanced(String gameId) throws IOException {

        List<Player> players = DataLoader.loadDataForGame("http://espn.go.com/nba/boxscore?id=" + gameId);
        players.forEach(StatsCalculator::mapAdvancedStatsTo);
        players.forEach(player -> {
            System.out.println(player.getName() + ", " + Math.round(player.getOffensiveRating()));
        });
        return players;
    }
}
