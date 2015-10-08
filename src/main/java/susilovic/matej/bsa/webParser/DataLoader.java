package susilovic.matej.bsa.webParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import susilovic.matej.bsa.mapper.PlayerMapper;
import susilovic.matej.bsa.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static final int AWAY_TEAM = 0;
    public static final int HOME_TEAM = 1;

    private static Document website;

    public static List<Player> loadDataForGame(String gameUrl) throws IOException {

        website = Jsoup.connect(gameUrl).timeout(0).get();

        String awayTeam = getNameFor(AWAY_TEAM);
        String homeTeam = getNameFor(HOME_TEAM);

        List<Player> awayPlayers = loadDataForAwayTeam(awayTeam, homeTeam);
        List<Player> homePlayers = loadDataForHomeTeam(awayTeam, homeTeam);

//        printGameDataFor(awayPlayers);
//        System.out.println();
//        printGameDataFor(homePlayers);

        loadOpponentTeamData(awayPlayers, homePlayers);

        List<Player> players = new ArrayList<>();
        players.addAll(awayPlayers);
        players.addAll(homePlayers);
        return players;
    }

    private static List<Player> loadDataForAwayTeam(String awayTeam, String homeTeam) {

        List<Player> awayPlayers = new ArrayList<>();

        awayPlayers.addAll(loadPlayers(getAwayStarterNodes(), awayTeam, true));
        awayPlayers.addAll(loadPlayers(getAwayBenchNodes(), awayTeam, false));
        loadTeamTotals(awayPlayers, getAwayTeamTotals());
        loadOpponentData(awayPlayers, homeTeam, getHomeTeamPoints());

        return awayPlayers;
    }

    private static List<Player> loadDataForHomeTeam(String awayTeam, String homeTeam) {

        List<Player> homePlayers = new ArrayList<>();

        homePlayers.addAll(loadPlayers(getHomeStarterNodes(), homeTeam, true));
        homePlayers.addAll(loadPlayers(getHomeBenchNodes(), homeTeam, false));
        loadTeamTotals(homePlayers, getHomeTeamTotals());
        loadOpponentData(homePlayers, awayTeam, getAwayTeamPoints());

        return homePlayers;
    }

    private static String getNameFor(int team) {

        return website.select("tr.team-color-strip").get(team).children().get(0).textNodes().get(0).text();
    }

    private static List<Node> getAwayStarterNodes() {

        return website.select("div.mod-content").get(1).children().get(0).children().get(1).childNodes();
    }

    private static List<Node> getAwayBenchNodes() {

        return website.select("div.mod-content").get(1).children().get(0).children().get(3).childNodes();
    }

    private static Node getAwayTeamTotals() {

        return website.select("div.mod-content").get(1).children().get(0).children().get(5).childNodes().get(0);
    }

    private static List<Node> getHomeStarterNodes() {

        return website.select("div.mod-content").get(1).children().get(0).children().get(7).childNodes();
    }

    private static List<Node> getHomeBenchNodes() {

        return website.select("div.mod-content").get(1).children().get(0).children().get(9).childNodes();
    }

    private static Node getHomeTeamTotals() {

        return website.select("div.mod-content").get(1).children().get(0).children().get(11).childNodes().get(0);
    }

    private static List<Player> loadPlayers(List<Node> playerNodes, String teamName, boolean starters) {

        List<Player> players = new ArrayList<>();

        playerNodes.forEach(playerNode -> {
            Player player = PlayerMapper.mapToPlayer(playerNode);
            player.setTeam(teamName);
            player.setStarted(starters);
            players.add(player);
        });

        return players;
    }

    private static void loadTeamTotals(List<Player> awayPlayers, Node awayTeamTotals) {

        long teamMinutesInt = Math.round(awayPlayers.stream().mapToDouble(Player::getMinutes).sum() / 5);
        final double teamMinutes = teamMinutesInt * 5;

        awayPlayers.forEach(player -> {
            PlayerMapper.mapTeamTotalsToPlayer(player, awayTeamTotals);
            player.setTeamMinutes(teamMinutes);
        });
    }

    private static int getAwayTeamPoints() {

        return Integer.parseInt(((TextNode) website.select("div.mod-content").get(1).children().get(0).children().get(5).childNodes().get(0).childNodes().get(13).childNodes().get(0).childNode(0)).text());
    }

    private static int getHomeTeamPoints() {

        return Integer.parseInt(((TextNode) website.select("div.mod-content").get(1).children().get(0).children().get(11).childNodes().get(0).childNodes().get(13).childNodes().get(0).childNode(0)).text());
    }

    private static void loadOpponentData(List<Player> players, String opponent, int opponentPoints) {

        players.forEach(player -> {
            player.setOpponent(opponent);
            player.setOpponentPoints(opponentPoints);
        });
    }

    private static void loadOpponentTeamData(List<Player> awayPlayers, List<Player> homePlayers) {

        awayPlayers.forEach(player -> {
            player.setOpponentTeamOffensiveRebounds(homePlayers.get(0).getTeamOffensiveRebounds());
            player.setOpponentTeamTotalRebounds(homePlayers.get(0).getTeamTotalRebounds());
        });

        homePlayers.forEach(player -> {
            player.setOpponentTeamOffensiveRebounds(awayPlayers.get(0).getTeamOffensiveRebounds());
            player.setOpponentTeamTotalRebounds(awayPlayers.get(0).getTeamTotalRebounds());
        });
    }

    private static void printGameDataFor(List<Player> players) {

        players.forEach(System.out::println);
    }
}
