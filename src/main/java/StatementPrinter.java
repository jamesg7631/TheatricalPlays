import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    // Should I have enums for the different play types

    // The current print method breaks Single responsibility principle. Highly coupled since it is performing many tasks such as
    // calculating the amount and credits earned instead of just printing them out
    private class StatementEntry {
        private String playName;
        private int amount;
        private int numberOfSeats;

        public StatementEntry(String playName, int amount, int numberOfSeats) {
            this.playName = playName;
            this.amount = amount;
            this.numberOfSeats = numberOfSeats;
        }

        public int getAmount() {
            return this.amount;
        }

        public String toString() {
            // Need to fix the coupling with local
            NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
            return String.format("  %s: %s (%s seats)\n", playName, frmt.format(amount / 100), numberOfSeats);
        }
    }

    private String moneyFormatter(int amount) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(amount / 100);
    }

    public String print(Invoice invoice, Map<String, Play> plays) {
        StringBuilder output = new StringBuilder();
        output.append(String.format("Statement for %s\n", invoice.getCustomer()));
        List<StatementEntry> statementEntryList = createStatements(invoice, plays);
        int totalAmount = calculateTotalAmount(statementEntryList);
        int totalCredits = calculateListCredits(invoice, plays);

        for (StatementEntry statementEntry: statementEntryList) {
            output.append(statementEntry);
        }
        output.append(String.format("Amount owed is %s\n", moneyFormatter(totalAmount)));
        output.append(String.format("You earned %s credits\n", totalCredits));

        return output.toString();
    }

    private int calculateTotalAmount(List<StatementEntry> statementEntries) {
        int total = 0;
        for (StatementEntry statementEntry: statementEntries) {
            total += statementEntry.getAmount();
        }
        return total;
    }

    private int calculateListCredits(Invoice invoice, Map<String, Play> plays) {
        int total = 0;
        for (Performance performance: invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
            total += calculateCredits(play.getType(), performance.getAudience());
        }
        return total;
    }

    private int calculateCredits(String playType, int audienceSize) {
        int volumeCredits = Math.max(audienceSize - 30, 0);
        if ("comedy".equals(playType)) {
            volumeCredits += Math.floor(audienceSize / 5);
        }
        return volumeCredits;
    }

    private List<StatementEntry> createStatements(Invoice invoice, Map<String, Play> plays) {
        List<StatementEntry> statementEntryList = new ArrayList<>();
        for (Performance performance: invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
            StatementEntry statementEntry = createStatement(performance, play);
            statementEntryList.add(statementEntry);
        }
        return statementEntryList;
    }

    private StatementEntry createStatement(Performance performance, Play play) {

        return new StatementEntry(play.getName(), amount(play.getType(), performance.getAudience()), performance.getAudience());
    }

    private int amount(String playType, int audienceSize) {
        int amount = 0;
        int audienceBonusSizeQualifier;
        switch (playType) {
            case "tragedy":
                audienceBonusSizeQualifier = 30;
                amount = 40000;
                if (audienceSize > audienceBonusSizeQualifier) {
                    amount += 1000 * (audienceSize - audienceBonusSizeQualifier);
                }
                break;
            case "comedy":
                amount = 30000;
                audienceBonusSizeQualifier = 20;
                if (audienceSize > audienceBonusSizeQualifier) {
                    amount += 10000 + 500 * (audienceSize- audienceBonusSizeQualifier);
                }
                amount += 300 * audienceSize;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return amount;
    }
}
