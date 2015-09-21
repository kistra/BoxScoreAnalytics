package susilovic.matej.bsa;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

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

}
