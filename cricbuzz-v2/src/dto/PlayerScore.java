package dto;

public class PlayerScore {
    String name;
    Integer runs;
    Integer sixes;
    Integer fours;
    Integer ballFaced;

    public PlayerScore(String name, Integer runs, Integer sixes, Integer fours, Integer ballFaced) {
        this.name = name;
        this.runs = runs;
        this.sixes = sixes;
        this.fours = fours;
        this.ballFaced = ballFaced;
    }

    @Override
    public String toString() {
        return "PlayerScore{" +
                "name='" + name + '\'' +
                ", runs=" + runs +
                ", sixes=" + sixes +
                ", fours=" + fours +
                ", ballFaced=" + ballFaced +
                '}';
    }
}
