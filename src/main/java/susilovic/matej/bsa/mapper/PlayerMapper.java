package susilovic.matej.bsa.mapper;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import susilovic.matej.bsa.model.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerMapper {

    public static Player mapToPlayer(Node playerNode) {

        Player player = new Player();

        player.setName(((TextNode) playerNode.childNodes().get(1).childNode(0).childNode(0)).text());
        player.setPosition(((TextNode) playerNode.childNodes().get(1).childNode(1)).text().replace(", ", ""));

        if(playerNode.childNodes().size() == 3 ) {
            player.setPlayed(false);
            return player;
        }

        player.setMinutes(Double.parseDouble(((TextNode) playerNode.childNodes().get(2).childNode(0)).text()));
        if(player.getMinutes() != 0) {
            player.setPlayed(true);
        }

        player.setFgMade(Double.parseDouble(((TextNode) playerNode.childNodes().get(3).childNode(0)).text().split("-")[0]));
        player.setFgAttempted(Double.parseDouble(((TextNode) playerNode.childNodes().get(3).childNode(0)).text().split("-")[1]));
        player.set_3pMade(Double.parseDouble(((TextNode) playerNode.childNodes().get(4).childNode(0)).text().split("-")[0]));
        player.set_3pAttempted(Double.parseDouble(((TextNode) playerNode.childNodes().get(4).childNode(0)).text().split("-")[1]));
        player.setFtMade(Double.parseDouble(((TextNode) playerNode.childNodes().get(5).childNode(0)).text().split("-")[0]));
        player.setFtAttempted(Double.parseDouble(((TextNode) playerNode.childNodes().get(5).childNode(0)).text().split("-")[1]));
        player.setOffensiveRebounds(Double.parseDouble(((TextNode) playerNode.childNodes().get(6).childNode(0)).text()));
        player.setDefensiveRebounds(Double.parseDouble(((TextNode) playerNode.childNodes().get(7).childNode(0)).text()));
        player.setTotalRebounds(Double.parseDouble(((TextNode) playerNode.childNodes().get(8).childNode(0)).text()));
        player.setAssists(Double.parseDouble(((TextNode) playerNode.childNodes().get(9).childNode(0)).text()));
        player.setSteals(Double.parseDouble(((TextNode) playerNode.childNodes().get(10).childNode(0)).text()));
        player.setBlocks(Double.parseDouble(((TextNode) playerNode.childNodes().get(11).childNode(0)).text()));
        player.setTurnovers(Double.parseDouble(((TextNode) playerNode.childNodes().get(12).childNode(0)).text()));
        player.setFouls(Double.parseDouble(((TextNode) playerNode.childNodes().get(13).childNode(0)).text()));
        player.setPlusMinus(Double.parseDouble(((TextNode) playerNode.childNodes().get(14).childNode(0)).text()));
        player.setPoints(Double.parseDouble(((TextNode) playerNode.childNodes().get(15).childNode(0)).text()));

        return player;
    }

    public static Player mapTeamTotalsToPlayer(Player player, Node playerNode) {

        player.setTeamFgMade(Double.parseDouble(((TextNode) playerNode.childNodes().get(1).childNodes().get(0).childNode(0)).text().split("-")[0]));
        player.setTeamFgAttempted(Double.parseDouble(((TextNode) playerNode.childNodes().get(1).childNodes().get(0).childNode(0)).text().split("-")[1]));
        player.setTeam3pMade(Double.parseDouble(((TextNode) playerNode.childNodes().get(2).childNodes().get(0).childNode(0)).text().split("-")[0]));
        player.setTeam3pAttempted(Double.parseDouble(((TextNode) playerNode.childNodes().get(2).childNodes().get(0).childNode(0)).text().split("-")[1]));
        player.setTeamFtMade(Double.parseDouble(((TextNode) playerNode.childNodes().get(3).childNodes().get(0).childNode(0)).text().split("-")[0]));
        player.setTeamFtAttempted(Double.parseDouble(((TextNode) playerNode.childNodes().get(3).childNodes().get(0).childNode(0)).text().split("-")[1]));
        player.setTeamOffensiveRebounds(Double.parseDouble(((TextNode) playerNode.childNodes().get(4).childNodes().get(0).childNode(0)).text()));
        player.setTeamDefensiveRebounds(Double.parseDouble(((TextNode) playerNode.childNodes().get(5).childNodes().get(0).childNode(0)).text()));
        player.setTeamTotalRebounds(Double.parseDouble(((TextNode) playerNode.childNodes().get(6).childNodes().get(0).childNode(0)).text()));
        player.setTeamAssists(Double.parseDouble(((TextNode) playerNode.childNodes().get(7).childNodes().get(0).childNode(0)).text()));
        player.setTeamSteals(Double.parseDouble(((TextNode) playerNode.childNodes().get(8).childNodes().get(0).childNode(0)).text()));
        player.setTeamBlocks(Double.parseDouble(((TextNode) playerNode.childNodes().get(9).childNodes().get(0).childNode(0)).text()));
        player.setTeamTurnovers(Double.parseDouble(((TextNode) playerNode.childNodes().get(10).childNodes().get(0).childNode(0)).text()));
        player.setTeamFouls(Double.parseDouble(((TextNode) playerNode.childNodes().get(11).childNodes().get(0).childNode(0)).text()));
        player.setTeamPoints(Double.parseDouble(((TextNode) playerNode.childNodes().get(13).childNodes().get(0).childNode(0)).text()));

        return player;
    }

    public static void mapToInsertQuery(PreparedStatement statement, Player player) throws SQLException {

        statement.setString(1, player.getTeam());
        statement.setString(2, player.getOpponent());
        statement.setString(3, player.getName());
        statement.setString(4, player.getPosition());
        statement.setDouble(5, player.getMinutes());
        statement.setBoolean(6, player.hasStarted());
        statement.setBoolean(7, player.hasPlayed());
        statement.setDouble(8, player.getFgMade());
        statement.setDouble(9, player.getFgAttempted());
        statement.setDouble(10,player.get_3pMade());
        statement.setDouble(11, player.get_3pAttempted());
        statement.setDouble(12, player.getFtMade());
        statement.setDouble(13, player.getFtAttempted());
        statement.setDouble(14, player.getOffensiveRebounds());
        statement.setDouble(15, player.getDefensiveRebounds());
        statement.setDouble(16, player.getTotalRebounds());
        statement.setDouble(17, player.getAssists());
        statement.setDouble(18, player.getSteals());
        statement.setDouble(19, player.getBlocks());
        statement.setDouble(20, player.getTurnovers());
        statement.setDouble(21, player.getFouls());
        statement.setDouble(22, player.getPlusMinus());
        statement.setDouble(23, player.getPoints());
        statement.setDouble(24, player.getTeamMinutes());
        statement.setDouble(25, player.getTeamFgMade());
        statement.setDouble(26, player.getTeamFgAttempted());
        statement.setDouble(27, player.getTeam3pMade());
        statement.setDouble(28, player.getTeam3pAttempted());
        statement.setDouble(29, player.getTeamFtMade());
        statement.setDouble(30, player.getTeamFtAttempted());
        statement.setDouble(31, player.getTeamOffensiveRebounds());
        statement.setDouble(32, player.getTeamDefensiveRebounds());
        statement.setDouble(33, player.getTeamTotalRebounds());
        statement.setDouble(34, player.getTeamAssists());
        statement.setDouble(35, player.getTeamSteals());
        statement.setDouble(36, player.getTeamBlocks());
        statement.setDouble(37, player.getTeamTurnovers());
        statement.setDouble(38, player.getTeamFouls());
        statement.setDouble(39, player.getTeamPoints());
        statement.setDouble(40, player.getOpponentPoints());
        statement.setDouble(41, player.getOpponentTeamOffensiveRebounds());
        statement.setDouble(42, player.getOpponentTeamTotalRebounds());
        statement.setDouble(43, player.getScoringPossessions());
        statement.setDouble(44, player.getFloorPercentage());
        statement.setDouble(45, player.getPointsProduced());
        statement.setDouble(46, player.getOffensiveRating());
    }
}
