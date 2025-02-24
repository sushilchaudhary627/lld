package dto;

public class PlayerScoreDetail {
    String name;
    Integer runs;
    Integer sixes;
    Integer fours;
    Integer ballFaced;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public Integer getSixes() {
        return sixes;
    }

    public void setSixes(Integer sixes) {
        this.sixes = sixes;
    }

    public Integer getFours() {
        return fours;
    }

    public void setFours(Integer fours) {
        this.fours = fours;
    }

    public Integer getBallFaced() {
        return ballFaced;
    }

    public void setBallFaced(Integer ballFaced) {
        this.ballFaced = ballFaced;
    }

    @Override
    public String toString() {
        return "\nPlayerScoreDetail{" +
            "name='" + name + '\'' +
            ", runs=" + runs +
            ", sixes=" + sixes +
            ", fours=" + fours +
            ", ballFaced=" + ballFaced +
            '}';
    }
}
