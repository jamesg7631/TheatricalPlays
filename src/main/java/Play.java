public class Play {

    // The attributes below were initially public.
    // This is an example of data coupling/ global variables.
    // This is bad because ... Very difficult to track changes and debug

    // Actually on second thoughts this seems more like content coupling.
    // By having public attributes, other classes are relying on the internal details of
    // the class which may change in future. Refactored to use getters and setters.

    // Is this now interface coupling
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
