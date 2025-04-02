package utils;

public class ConvertPrice {

    public static String convertPrice(String priceClean) {
        String cleaned = priceClean.replace("Rs.", "").trim();

        if (cleaned.contains("Lakh")) {
            cleaned = cleaned.replace("Lakh", "").trim();
            int converted = (int) (Double.parseDouble(cleaned) * 100000);//convert to rupees
            return String.valueOf(converted);
        } else if (cleaned.equalsIgnoreCase("Price to be announced")) {
            return "Price to be announced";
        } else {
            cleaned = cleaned.replace(",", "");
            int converted = Integer.parseInt(cleaned);
            return String.valueOf(converted);
        }
    }



}

