package DBObjects;

public class Team {
	int idTeam;
	String sportsDiscipline;
	int idCaptain;

	public Team(int idTeam, String sportsDiscipline, int idCaptain) {
		this.idTeam = idTeam;
		this.sportsDiscipline = sportsDiscipline;
		this.idCaptain = idCaptain;
	}
}