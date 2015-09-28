package susilovic.matej.bsa;

import susilovic.matej.bsa.model.Player;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class StatsCalculator {

    private static double SHOOTER_ASSISTANT_CREDIT_RATIO = 0.5;

    public static void mapAdvancedStatsTo(Player player) {

        if(!player.hasPlayed()) {
            return;
        }

        player.setScoringPossessions(getScoringPossessions(player));
        player.setFloorPercentage(getFloorPercentage(player));
        player.setPointsProduced(getPointsProduced(player));
        player.setOffensiveRating(getOffensiveRating(player));
    }

    private static double getFgPart(Player player) {

        if(player.getFgAttempted() == 0) {
            return 0;
        }

        double effectiveFgPercentage = getEffectiveFgPercentage(player);
        double qAst = getQAst(player);

        return player.getFgMade() * (1 - SHOOTER_ASSISTANT_CREDIT_RATIO * effectiveFgPercentage * qAst);
    }

    private static double getQAst(Player player) {

        if(player.getMinutes() == 0) {
            return 0;
        }

        double minutesPlayedPercentage = player.getMinutes() / (player.getTeamMinutes() / 5);
        double minutesBenchedPercentage = 1 - minutesPlayedPercentage;

        double q5 = 1.14 * ((player.getTeamAssists() - player.getAssists()) / player.getTeamFgMade());

        double teamAssistPerMinute = player.getTeamAssists() / player.getTeamMinutes();
        double teamFgPerMinute = player.getTeamFgMade() / player.getTeamMinutes();

        double q12 = (teamAssistPerMinute * player.getMinutes() * 5 - player.getAssists())
                /
                (teamFgPerMinute * player.getMinutes() * 5 - player.getFgMade());

        double qAst = (minutesPlayedPercentage * q5) + (minutesBenchedPercentage * q12);

        return min(max(qAst, 0), 1);
    }

    private static double getAstPart(Player player) {

        double teammatesFgPoints = player.getTeamPoints() - player.getTeamFtMade();
        double playerFgPoints = player.getPoints() - player.getFtMade();
        double teammatesFgAttempts = player.getTeamFgAttempted() - player.getFgAttempted();

        double teammatesEffectiveFgPercentage = (teammatesFgPoints - playerFgPoints) / (2 * teammatesFgAttempts);

        /* check if this is credit ratio */
        return SHOOTER_ASSISTANT_CREDIT_RATIO * teammatesEffectiveFgPercentage * player.getAssists();
    }

    /* find explanation in the book for team fraction of free throw possessions with at least one made shot*/
    private static double getFtPart(Player player) {

        if(player.getFtAttempted() == 0) {
            return 0;
        }

        double ftPercentage = player.getFtMade() / player.getFtAttempted();
        double ftPossessionsWithAtLeastOneMadeShot = 1 - Math.pow(1 - ftPercentage, 2);

        return ftPossessionsWithAtLeastOneMadeShot * 0.4 * player.getFtAttempted();
    }

    private static double getTeamScoringPossessions(Player player) {

        double teamFtPercentage = player.getTeamFtMade() / player.getTeamFtAttempted();
        double teamFtPossessionsWithAtLeastOneMadeShot = 1 - Math.pow(1 - teamFtPercentage, 2);

        return player.getTeamFgMade() + teamFtPossessionsWithAtLeastOneMadeShot * player.getTeamFtAttempted() * 0.4;
    }

    private static double getTeamOffRebPercentage(Player player) {

        double opponentTeamDefensiveReboundsWithTeamRebounds = player.getOpponentTeamTotalRebounds() - player.getOpponentTeamOffensiveRebounds();
        double possibleOffensiveRebounds = player.getTeamOffensiveRebounds() + opponentTeamDefensiveReboundsWithTeamRebounds;

        return player.getTeamOffensiveRebounds() / possibleOffensiveRebounds;
    }

    private static double getTeamPlayPercentage(Player player) {

        double teamScoringPossessions = getTeamScoringPossessions(player);
        double teamTotalPossessions = player.getTeamFgAttempted() + player.getTeamFtAttempted() * 0.4 + player.getTeamTurnovers();

        return teamScoringPossessions / teamTotalPossessions;
    }

    /* needs better explanation */
    private static double getTeamOffRebWeight(Player player) {

        double teamOffRebPercentage = getTeamOffRebPercentage(player);
        double teamPlayPercentage = getTeamPlayPercentage(player);

        return ((1 - teamOffRebPercentage) * teamPlayPercentage)
                /
                ((1 - teamOffRebPercentage) * teamPlayPercentage + teamOffRebPercentage * (1 - teamPlayPercentage));
    }

    /* explain team play percentage and its usage */
    private static double getOffRebPart(Player player) {

        double teamPlayPercentage = getTeamPlayPercentage(player);
        double teamOrbWeight = getTeamOffRebWeight(player);

        return player.getOffensiveRebounds() * teamOrbWeight * teamPlayPercentage;
    }

    public static double getScoringPossessions(Player player) {

        double fgPart = getFgPart(player);
        double astPart = getAstPart(player);
        double ftPart = getFtPart(player);

        double teamScoringPossessions = getTeamScoringPossessions(player);
        double teamPlayPercentage = getTeamPlayPercentage(player);
        double teamOrbWeight = getTeamOffRebWeight(player);

        double orbPart = getOffRebPart(player);

        return (fgPart + astPart + ftPart) * (1 - (player.getTeamOffensiveRebounds() / teamScoringPossessions) * teamOrbWeight * teamPlayPercentage) + orbPart;
    }

    private static double getMissedFgPart(Player player) {

        double teamOffRebPercentage = getTeamOffRebPercentage(player);
        double missedFg = player.getFgAttempted() - player.getFgMade();

        return missedFg * (1 - 1.07 * teamOffRebPercentage);
    }

    private static double getMissedFtPart(Player player) {

        if(player.getFtAttempted() == 0) {
            return 0.4 * player.getFtAttempted();
        }

        double missedFtPercentage = 1 - (player.getFtMade() / player.getFtAttempted());

        return (Math.pow(missedFtPercentage, 2)) * 0.4 * player.getFtAttempted();
    }

    private static double getTotalPossessions(Player player) {

        double scoringPossessions = getScoringPossessions(player);
        double missedFgPart = getMissedFgPart(player);
        double missedFtPart = getMissedFtPart(player);

        return scoringPossessions + missedFgPart + missedFtPart + player.getTurnovers();
    }

    public static double getFloorPercentage(Player player) {

        double scoringPossessions = getScoringPossessions(player);
        double totalPossessions = getTotalPossessions(player);

        return scoringPossessions / totalPossessions;
    }

    private static double getPointsProducedFgPart(Player player) {

        if(player.getFgAttempted() == 0) {
            return 0;
        }

        double pointsFromFg = 2 * (player.getFgMade() + 0.5 * player.get_3pMade());
        double effectiveFgPercentage = getEffectiveFgPercentage(player);

                                /* check if this is credit ratio */
        return pointsFromFg * (1 - SHOOTER_ASSISTANT_CREDIT_RATIO * (effectiveFgPercentage) * getQAst(player));
    }

    private static double getEffectiveFgPercentage(Player player) {

        return (player.getPoints() - player.getFtMade()) / (2 * player.getFgAttempted());
    }

    private static double getPointsProducedAssistPart(Player player) {

        double teammatesFgMade = player.getTeamFgMade() - player.getFgMade();
        double teammates3pMade = player.getTeam3pMade() - player.get_3pMade();
        double teammatesEffFgPercentage = (teammatesFgMade + 0.5 * teammates3pMade) / teammatesFgMade;
        double teamPointsFromFg = player.getTeamPoints() - player.getTeamFtMade();
        double pointsFromFg = player.getPoints() - player.getFtMade();
        double teammatesFgAttempted = player.getTeamFgAttempted() - player.getFgAttempted();

        return teammatesEffFgPercentage * ((teamPointsFromFg - pointsFromFg) / (2 * teammatesFgAttempted)) * player.getAssists();
    }

    /* refactor this */
    private static double pointsProducedOffRebPart(Player player) {

        double teamOrbWeight = getTeamOffRebWeight(player);
        double teamPlayPercentage = getTeamPlayPercentage(player);
        double teamMissedFtPercentage = 1 - (player.getTeamFtMade() / player.getTeamFtAttempted());

        return player.getOffensiveRebounds() * teamOrbWeight * teamPlayPercentage * (player.getTeamPoints() / (player.getTeamFgMade() + (1 - Math.pow(teamMissedFtPercentage, 2)) * 0.4 * player.getTeamFtAttempted()));
    }

    public static double getPointsProduced(Player player) {

        double teamScoringPossessions = getTeamScoringPossessions(player);
        double teamPlayPercentage = getTeamPlayPercentage(player);
        double teamOrbWeight = getTeamOffRebWeight(player);
        double fgPart = getPointsProducedFgPart(player);
        double assistPart = getPointsProducedAssistPart(player);
        double offRebPart = pointsProducedOffRebPart(player);

        return (fgPart + assistPart + player.getFtMade()) * (1 - (player.getTeamOffensiveRebounds() / teamScoringPossessions) * teamOrbWeight * teamPlayPercentage) + offRebPart;
    }

    public static double getOffensiveRating(Player player) {

        double totalPossessions = getTotalPossessions(player);
        double pointsProduced = getPointsProduced(player);

        return 100 * (pointsProduced / totalPossessions);
    }
}
