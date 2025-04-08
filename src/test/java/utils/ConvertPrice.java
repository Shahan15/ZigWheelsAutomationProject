package utils;

public class ConvertPrice {

    public static String convertPrice(String priceClean) {
        // Remove currency symbols and excess whitespace
        String cleaned = priceClean.replace("Rs.", "").trim();

        // Check if the cleaned string is now empty; if so, return a default value
        if (cleaned.isEmpty()) {
            // choose to either return "0" or a specific value indicating that it wasn't available.
            return "0";
        }

        if (cleaned.contains("Lakh")) {
            cleaned = cleaned.replace("Lakh", "").trim();
            try {
                int converted = (int) (Double.parseDouble(cleaned) * 100000);  // Convert to rupees
                return String.valueOf(converted);
            } catch (NumberFormatException ex) {
                // Log and return a default value if conversion unexpectedly fails
                Base.logger.error("Conversion failed for value: {} - {}", cleaned, ex.getMessage());
                return "0";
            }
        } else if (cleaned.equalsIgnoreCase("Price to be announced")) {
            return "Price to be announced";
        } else {
            // Remove commas and try parsing the number
            cleaned = cleaned.replace(",", "");
            try {
                int converted = Integer.parseInt(cleaned);
                return String.valueOf(converted);
            } catch (NumberFormatException ex) {
                Base.logger.error("Unable to parse price value: {} - {}", cleaned, ex.getMessage());
                return "0";
            }
        }
    }
}
