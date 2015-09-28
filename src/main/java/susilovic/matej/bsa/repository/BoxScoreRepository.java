package susilovic.matej.bsa.repository;

import susilovic.matej.bsa.db.Database;
import susilovic.matej.bsa.model.Player;
import susilovic.matej.bsa.mapper.PlayerMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class BoxScoreRepository {

    private static Database db = Database.getDatabase();

    public static void insertPlayers(List<Player> players, LocalDateTime dateTime) {

        String INSERT_DATA_QUERY = "INSERT INTO BOX_SCORE (" +
                "team, opponent, name, \"position\", minutes, started, played, fgmade, fgattempted, _3pmade," +
                "_3pattempted, ftmade, ftattempted, offensiverebounds, defensiverebounds, totalrebounds, assists," +
                "steals, blocks, turnovers, fouls, plusminus, points, teamminutes, teamfgmade, teamfgattempted," +
                "team3pmade, team3pattempted, teamftmade, teamftattempted, teamoffensiverebounds, teamdefensiverebounds," +
                "teamtotalrebounds, teamassists, teamsteals, teamblocks, teamturnovers, teamfouls, teampoints," +
                "opponentpoints, opponentteamoffensiverebounds, opponentteamtotalrebounds, scoringpossessions," +
                "floorpercentage, pointsproduced, offensiverating, \"gameDate\"" +
                ") " +
                "VALUES (" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
                ");";

        players.stream().forEach(player -> {
            try {
                PreparedStatement statement = db.connection.prepareStatement(INSERT_DATA_QUERY);
                PlayerMapper.mapToInsertQuery(statement, player);
                statement.setDate(47, new Date(dateTime.toInstant(ZoneOffset.UTC).toEpochMilli()));
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error inserting data to box_score table.", e);
            }
        });
    }
}
