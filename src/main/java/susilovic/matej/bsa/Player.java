package susilovic.matej.bsa;

public class Player {

    private String team;
    private String opponent;

    private String name;
    private String position;

    private double minutes;
    private boolean started;
    private boolean played;

    private double fgMade;
    private double fgAttempted;
    private double _3pMade;
    private double _3pAttempted;
    private double ftMade;
    private double ftAttempted;
    private double offensiveRebounds;
    private double defensiveRebounds;
    private double totalRebounds;
    private double assists;
    private double steals;
    private double blocks;
    private double turnovers;
    private double fouls;
    private double plusMinus;
    private double points;

    private double teamMinutes;
    private double teamFgMade;
    private double teamFgAttempted;
    private double team3pMade;
    private double team3pAttempted;
    private double teamFtMade;
    private double teamFtAttempted;
    private double teamOffensiveRebounds;
    private double teamDefensiveRebounds;
    private double teamTotalRebounds;
    private double teamAssists;
    private double teamSteals;
    private double teamBlocks;
    private double teamTurnovers;
    private double teamFouls;

    private double teamPoints;
    private double opponentPoints;
    private double opponentTeamOffensiveRebounds;
    private double opponentTeamTotalRebounds;

    private double scoringPossessions;
    private double floorPercentage;
    private double pointsProduced;
    private double offensiveRating;

    public String getTeam() {

        return team;
    }

    public void setTeam(String team) {

        this.team = team;
    }

    public String getOpponent() {

        return opponent;
    }

    public void setOpponent(String opponent) {

        this.opponent = opponent;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPosition() {

        return position;
    }

    public void setPosition(String position) {

        this.position = position;
    }

    public double getMinutes() {

        return minutes;
    }

    public void setMinutes(double minutes) {

        this.minutes = minutes;
    }

    public boolean hasStarted() {

        return started;
    }

    public void setStarted(boolean started) {

        this.started = started;
    }

    public boolean hasPlayed() {

        return played;
    }

    public void setPlayed(boolean played) {

        this.played = played;
    }

    public double getFgMade() {

        return fgMade;
    }

    public void setFgMade(double fgMade) {

        this.fgMade = fgMade;
    }

    public double getFgAttempted() {

        return fgAttempted;
    }

    public void setFgAttempted(double fgAttempted) {

        this.fgAttempted = fgAttempted;
    }

    public double get_3pMade() {

        return _3pMade;
    }

    public void set_3pMade(double _3pMade) {

        this._3pMade = _3pMade;
    }

    public double get_3pAttempted() {

        return _3pAttempted;
    }

    public void set_3pAttempted(double _3pAttempted) {

        this._3pAttempted = _3pAttempted;
    }

    public double getFtMade() {

        return ftMade;
    }

    public void setFtMade(double ftMade) {

        this.ftMade = ftMade;
    }

    public double getFtAttempted() {

        return ftAttempted;
    }

    public void setFtAttempted(double ftAttempted) {

        this.ftAttempted = ftAttempted;
    }

    public double getOffensiveRebounds() {

        return offensiveRebounds;
    }

    public void setOffensiveRebounds(double offensiveRebounds) {

        this.offensiveRebounds = offensiveRebounds;
    }

    public double getDefensiveRebounds() {

        return defensiveRebounds;
    }

    public void setDefensiveRebounds(double defensiveRebounds) {

        this.defensiveRebounds = defensiveRebounds;
    }

    public double getTotalRebounds() {

        return totalRebounds;
    }

    public void setTotalRebounds(double totalRebounds) {

        this.totalRebounds = totalRebounds;
    }

    public double getAssists() {

        return assists;
    }

    public void setAssists(double assists) {

        this.assists = assists;
    }

    public double getSteals() {

        return steals;
    }

    public void setSteals(double steals) {

        this.steals = steals;
    }

    public double getBlocks() {

        return blocks;
    }

    public void setBlocks(double blocks) {

        this.blocks = blocks;
    }

    public double getTurnovers() {

        return turnovers;
    }

    public void setTurnovers(double turnovers) {

        this.turnovers = turnovers;
    }

    public double getFouls() {

        return fouls;
    }

    public void setFouls(double fouls) {

        this.fouls = fouls;
    }

    public double getPlusMinus() {

        return plusMinus;
    }

    public void setPlusMinus(double plusMinus) {

        this.plusMinus = plusMinus;
    }

    public double getPoints() {

        return points;
    }

    public void setPoints(double points) {

        this.points = points;
    }

    public double getTeamMinutes() {

        return teamMinutes;
    }

    public void setTeamMinutes(double teamMinutes) {

        this.teamMinutes = teamMinutes;
    }

    public double getTeamFgMade() {

        return teamFgMade;
    }

    public void setTeamFgMade(double teamFgMade) {

        this.teamFgMade = teamFgMade;
    }

    public double getTeamFgAttempted() {

        return teamFgAttempted;
    }

    public void setTeamFgAttempted(double teamFgAttempted) {

        this.teamFgAttempted = teamFgAttempted;
    }

    public double getTeam3pMade() {

        return team3pMade;
    }

    public void setTeam3pMade(double team3pMade) {

        this.team3pMade = team3pMade;
    }

    public double getTeam3pAttempted() {

        return team3pAttempted;
    }

    public void setTeam3pAttempted(double team3pAttempted) {

        this.team3pAttempted = team3pAttempted;
    }

    public double getTeamFtMade() {

        return teamFtMade;
    }

    public void setTeamFtMade(double teamFtMade) {

        this.teamFtMade = teamFtMade;
    }

    public double getTeamFtAttempted() {

        return teamFtAttempted;
    }

    public void setTeamFtAttempted(double teamFtAttempted) {

        this.teamFtAttempted = teamFtAttempted;
    }

    public double getTeamOffensiveRebounds() {

        return teamOffensiveRebounds;
    }

    public void setTeamOffensiveRebounds(double teamOffensiveRebounds) {

        this.teamOffensiveRebounds = teamOffensiveRebounds;
    }

    public double getTeamDefensiveRebounds() {

        return teamDefensiveRebounds;
    }

    public void setTeamDefensiveRebounds(double teamDefensiveRebounds) {

        this.teamDefensiveRebounds = teamDefensiveRebounds;
    }

    public double getTeamTotalRebounds() {

        return teamTotalRebounds;
    }

    public void setTeamTotalRebounds(double teamTotalRebounds) {

        this.teamTotalRebounds = teamTotalRebounds;
    }

    public double getTeamAssists() {

        return teamAssists;
    }

    public void setTeamAssists(double teamAssists) {

        this.teamAssists = teamAssists;
    }

    public double getTeamSteals() {

        return teamSteals;
    }

    public void setTeamSteals(double teamSteals) {

        this.teamSteals = teamSteals;
    }

    public double getTeamBlocks() {

        return teamBlocks;
    }

    public void setTeamBlocks(double teamBlocks) {

        this.teamBlocks = teamBlocks;
    }

    public double getTeamTurnovers() {

        return teamTurnovers;
    }

    public void setTeamTurnovers(double teamTurnovers) {

        this.teamTurnovers = teamTurnovers;
    }

    public double getTeamFouls() {

        return teamFouls;
    }

    public void setTeamFouls(double teamFouls) {

        this.teamFouls = teamFouls;
    }

    public double getTeamPoints() {

        return teamPoints;
    }

    public void setTeamPoints(double teamPoints) {

        this.teamPoints = teamPoints;
    }

    public double getOpponentPoints() {

        return opponentPoints;
    }

    public void setOpponentPoints(double opponentPoints) {

        this.opponentPoints = opponentPoints;
    }

    public double getOpponentTeamOffensiveRebounds() {

        return opponentTeamOffensiveRebounds;
    }

    public void setOpponentTeamOffensiveRebounds(double opponentTeamOffensiveRebounds) {

        this.opponentTeamOffensiveRebounds = opponentTeamOffensiveRebounds;
    }

    public double getOpponentTeamTotalRebounds() {

        return opponentTeamTotalRebounds;
    }

    public void setOpponentTeamTotalRebounds(double opponentTeamTotalRebounds) {

        this.opponentTeamTotalRebounds = opponentTeamTotalRebounds;
    }

    public double getScoringPossessions() {

        return scoringPossessions;
    }

    public void setScoringPossessions(double scoringPossessions) {

        this.scoringPossessions = scoringPossessions;
    }

    public double getFloorPercentage() {

        return floorPercentage;
    }

    public void setFloorPercentage(double floorPercentage) {

        this.floorPercentage = floorPercentage;
    }

    public double getPointsProduced() {

        return pointsProduced;
    }

    public void setPointsProduced(double pointsProduced) {

        this.pointsProduced = pointsProduced;
    }

    public double getOffensiveRating() {

        return offensiveRating;
    }

    public void setOffensiveRating(double offensiveRating) {

        this.offensiveRating = offensiveRating;
    }

    @Override public String toString() {

        return "Player{" +
                "team='" + team + '\'' +
                ", opponent='" + opponent + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", minutes=" + minutes +
                ", started=" + started +
                ", played=" + played +
                ", fgMade=" + fgMade +
                ", fgAttempted=" + fgAttempted +
                ", _3pMade=" + _3pMade +
                ", _3pAttempted=" + _3pAttempted +
                ", ftMade=" + ftMade +
                ", ftAttempted=" + ftAttempted +
                ", offensiveRebounds=" + offensiveRebounds +
                ", defensiveRebounds=" + defensiveRebounds +
                ", totalRebounds=" + totalRebounds +
                ", assists=" + assists +
                ", steals=" + steals +
                ", blocks=" + blocks +
                ", turnovers=" + turnovers +
                ", fouls=" + fouls +
                ", plusMinus=" + plusMinus +
                ", points=" + points +
                ", teamMinutes=" + teamMinutes +
                ", teamFgMade=" + teamFgMade +
                ", teamFgAttempted=" + teamFgAttempted +
                ", team3pMade=" + team3pMade +
                ", team3pAttempted=" + team3pAttempted +
                ", teamFtMade=" + teamFtMade +
                ", teamFtAttempted=" + teamFtAttempted +
                ", teamOffensiveRebounds=" + teamOffensiveRebounds +
                ", teamDefensiveRebounds=" + teamDefensiveRebounds +
                ", teamTotalRebounds=" + teamTotalRebounds +
                ", teamAssists=" + teamAssists +
                ", teamSteals=" + teamSteals +
                ", teamBlocks=" + teamBlocks +
                ", teamTurnovers=" + teamTurnovers +
                ", teamFouls=" + teamFouls +
                ", teamPoints=" + teamPoints +
                ", opponentPoints=" + opponentPoints +
                ", opponentTeamOffensiveRebounds=" + opponentTeamOffensiveRebounds +
                ", opponentTeamTotalRebounds=" + opponentTeamTotalRebounds +
                ", scoringPossessions=" + scoringPossessions +
                ", floorPercentage=" + floorPercentage +
                ", pointsProduced=" + pointsProduced +
                ", offensiveRating=" + offensiveRating +
                '}';
    }
}
