import java.util.ArrayList;
import java.util.List;

public class Invoice {

    // By having public attributes, other classes are relying on the internal details of
    // the class which may change in future. Refactored to use getters and setters.

    // Also, I changed performances to provide a deep copy of the performances array.
    // This is to ensure that when we provide a getter to the performances of the array.
    // performances List cannot be changed. Neither instances of Performance objects in the list.
    // This refactoring is an example of Safe classes I think. Not sure though. I don't think this is an example of
    // coupling or cohesion though.
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
