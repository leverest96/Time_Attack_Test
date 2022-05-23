package SijunVer;

public class Baseball {
    private String team;
    private String number;
    private int strike;
    private int ball;
    private int out;

    public Baseball(String team, String number, int strike, int ball, int out) {
        this.team = team;
        this.number = number;
        this.strike = strike;
        this.ball = ball;
        this.out = out;
    }
    public String getTeam(){
        return team;
    }

    @Override
    public String toString() {
        return " : "+this.number+" : Strike : "+strike+", Ball : "+ball+", Out: "+out;
    }
}