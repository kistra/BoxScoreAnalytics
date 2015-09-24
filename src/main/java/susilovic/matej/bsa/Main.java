package susilovic.matej.bsa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        LocalDateTime startDate = LocalDateTime.of(2015, 2, 11, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2015, 2, 11, 0, 0);
        loadGamesBetween(startDate, endDate);

    }

    private static void loadGamesBetween(LocalDateTime startDate, LocalDateTime endDate) throws IOException {

        LocalDateTime start = startDate;
        List<String> gameIds = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        while (!start.isAfter(endDate)) {
            String date = startDate.format(formatter);
            gameIds = getGameIdsFor(date);
            start = start.plusDays(1L);
        }

        for (String gameId : gameIds) {
            List<Player> players = DataLoader.loadDataForGame("http://espn.go.com/nba/boxscore?id=" + gameId);

            players.forEach(StatsCalculator::mapStatsTo);

            players.forEach(player -> {
                System.out.println(player.getName() + ", " + Math.round(player.getOffensiveRating()));
            });
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }

    private static List<String> getGameIdsFor(String date) throws IOException {

        Document website = Jsoup.connect("http://espn.go.com/nba/scoreboard/_/date/" + date).get();

        String script = ((DataNode) website.select("script").get(7).childNode(0)).getWholeData();

        List<String> allMatches = new ArrayList<String>();
        Matcher matcher = Pattern.compile("http://espn\\.go\\.com/nba/boxscore\\?gameId=\\d\\d\\d\\d\\d\\d\\d\\d\\d")
                           .matcher(script);
        while (matcher.find()) {
            allMatches.add(matcher.group().replace("http://espn.go.com/nba/boxscore?gameId=", ""));
        }

        return allMatches;
    }
}
