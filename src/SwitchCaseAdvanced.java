public class SwitchCaseAdvanced {

    enum Priority { LOW, MEDIUM, HIGH, CRITICAL }
    enum HttpMethod { GET, POST, PUT, DELETE, PATCH }

    static int priorityScore(Priority p) {
        return switch (p) {
            case LOW      -> 1;
            case MEDIUM   -> 5;
            case HIGH     -> 10;
            case CRITICAL -> 100;
        };
    }

    static String handleHttpMethod(HttpMethod method, String resource) {
        return switch (method) {
            case GET    -> "Fetching " + resource;
            case POST   -> "Creating new " + resource;
            case PUT    -> "Replacing " + resource;
            case DELETE -> "Deleting " + resource;
            case PATCH  -> "Updating fields of " + resource;
        };
    }

    static double convertCurrency(double amount, String from, String to) {
        // Normalize to USD first
        double usd = switch (from.toUpperCase()) {
            case "USD" -> amount;
            case "EUR" -> amount * 1.08;
            case "GBP" -> amount * 1.27;
            case "JPY" -> amount * 0.0067;
            default    -> throw new IllegalArgumentException("Unknown currency: " + from);
        };
        return switch (to.toUpperCase()) {
            case "USD" -> usd;
            case "EUR" -> usd / 1.08;
            case "GBP" -> usd / 1.27;
            case "JPY" -> usd / 0.0067;
            default    -> throw new IllegalArgumentException("Unknown currency: " + to);
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Priority System ===");
        for (Priority p : Priority.values())
            System.out.printf("%-8s → score: %d%n", p, priorityScore(p));

        System.out.println("\n=== HTTP Method Router ===");
        for (HttpMethod m : HttpMethod.values())
            System.out.println(handleHttpMethod(m, "users"));

        System.out.println("\n=== Currency Converter ===");
        System.out.printf("$100 USD → EUR: %.2f%n", convertCurrency(100, "USD", "EUR"));
        System.out.printf("£50 GBP  → JPY: %.0f%n", convertCurrency(50,  "GBP", "JPY"));
        System.out.printf("¥10000 JPY → USD: $%.2f%n", convertCurrency(10000,"JPY","USD"));
    }
}
