package susilovic.matej.bsa;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Player> players = DataLoader.loadDataForGame("http://espn.go.com/nba/boxscore?id=400790959");

        players.forEach(StatsCalculator::mapStatsTo);

        players.forEach(player -> {
            System.out.println(player.getName() + ", " + Math.round(player.getOffensiveRating()));
        });


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        LocalDateTime startDate = LocalDateTime.of(2014, 10, 28, 0, 0);
//        LocalDateTime endDate = LocalDateTime.of(2014, 12, 31, 0, 0);
//        while (!startDate.isAfter(endDate)) {
//            System.out.println(startDate.format(formatter));
//            startDate = startDate.plusDays(1L);
//        }

    }
}
