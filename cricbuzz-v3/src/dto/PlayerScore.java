package dto;

public class PlayerScore {
    String name;
    int runs;
    int sixes;
    int fours;
    int ballFaced;

    public PlayerScore(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getBallFaced() {
        return ballFaced;
    }

    public void setBallFaced(int ballFaced) {
        this.ballFaced = ballFaced;
    }

    @Override
    public String toString() {
        return "\nPlayerScore{" +
                "name='" + name + '\'' +
                ", runs=" + runs +
                ", sixes=" + sixes +
                ", fours=" + fours +
                ", ballFaced=" + ballFaced +
                '}';
    }
}
