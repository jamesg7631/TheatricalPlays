public class Play {

    // The attributes below were initially public.
    // This is an example of data coupling/ global variables.
    // This is bad because ...
    private String name;
    private String type;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }
}
