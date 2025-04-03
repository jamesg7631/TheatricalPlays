public class Performance {

    private String playID;
    private int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public String getPlayID() {
        return this.playID;
    }

    public int getAudience() {
        return this.audience;
    }
}
