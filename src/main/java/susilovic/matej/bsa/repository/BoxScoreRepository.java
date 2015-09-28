package susilovic.matej.bsa.repository;

import susilovic.matej.bsa.db.Database;
import susilovic.matej.bsa.model.Player;
import susilovic.matej.bsa.mapper.PlayerMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BoxScoreRepository {

    private static Database db = Database.getDatabase();

    public static void insertPlayers(List<Player> players) {

        String INSERT_DATA_QUERY = "INSERT INTO BOX_SCORE (" +
                "team, opponent, name, \"position\", minutes, started, played, fgmade, fgattempted, _3pmade," +
                "_3pattempted, ftmade, ftattempted, offensiverebounds, defensiverebounds, totalrebounds, assists," +
                "steals, blocks, turnovers, fouls, plusminus, points, teamminutes, teamfgmade, teamfgattempted," +
                "team3pmade, team3pattempted, teamftmade, teamftattempted, teamoffensiverebounds, teamdefensiverebounds," +
                "teamtotalrebounds, teamassists, teamsteals, teamblocks, teamturnovers, teamfouls, teampoints," +
                "opponentpoints, opponentteamoffensiverebounds, opponentteamtotalrebounds, scoringpossessions," +
                "floorpercentage, pointsproduced, offensiverating" +
                ") " +
                "VALUES (" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
                ");";

        players.stream().forEach(player -> {
            try {
                PreparedStatement statement = db.connection.prepareStatement(INSERT_DATA_QUERY);
                PlayerMapper.mapToInsertQuery(statement, player);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error inserting data to box_score table.", e);
            }
        });
    }
}
