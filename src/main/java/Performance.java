public class Performance {

    // WHat actually is audience. Is it the number of people in the audience.
    // If so, I would think it would make more sense if each performance stores its own amount and credits.
    // I think maybe a better solution exists. However, it makes a lot more sense than the print statement
    // holding this information

    // For now I'm going to let Play deal with this instead but may change later.
    // Maybe not actually since the amount ide dependent on both the Play class and the audience which Play
    // does not have access to. Might indeed make more sense to be in Invoice

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
