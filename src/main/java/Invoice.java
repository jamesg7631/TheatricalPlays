import java.util.ArrayList;
import java.util.List;

public class Invoice {

    // Similar issue to the Play Class.
    // We should not expose the internal data of the Invoice class.
    // This is an example of X coupling
    // This coupling is bad because ...
    private String customer;
    private List<Performance> performances;

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    public String getCustomer() {
        return this.customer;
    }

    public List<Performance> getPerformances() {
        List<Performance> copyPerformances = new ArrayList<>();
        for (Performance performance: this.performances) {
            Performance copyPerformance = new Performance(performance.getPlayID(), performance.getAudience());
            copyPerformances.add(copyPerformance);
        }

        return copyPerformances;
    }
}
