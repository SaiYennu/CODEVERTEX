
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {
    private JComboBox<String> baseCurrency, targetCurrency;
    private JTextField amountField;
    private JLabel resultLabel;

    // Constructor
    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Currencies
        String[] currencies = {"USD", "EUR", "INR", "GBP", "JPY"};

        // Base Currency Selection
        add(new JLabel("Base Currency:"));
        baseCurrency = new JComboBox<>(currencies);
        add(baseCurrency);

        // Target Currency Selection
        add(new JLabel("Target Currency:"));
        targetCurrency = new JComboBox<>(currencies);
        add(targetCurrency);

        // Amount Input
        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        // Convert Button
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
        add(convertButton);

        // Result Display
        resultLabel = new JLabel("Converted Amount: ");
        add(resultLabel);
    }

    // Fetch exchange rates and convert currency
    private void convertCurrency() {
        String base = (String) baseCurrency.getSelectedItem();
        String target = (String) targetCurrency.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());

        // Fetch exchange rate from the API (Mocked here)
        double exchangeRate = fetchExchangeRate(base, target);
        double convertedAmount = amount * exchangeRate;

        // Display result
        resultLabel.setText("Converted Amount: " + String.format("%.2f", convertedAmount) + " " + target);
    }

    // Method to fetch exchange rate
    private double fetchExchangeRate(String base, String target) {
        try {
            // URL for the exchange rate API (Mocked for this example)
            String urlStr = "https://api.exchangerate-api.com/v4/latest/" + base;

            // Create the request
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Close connections
            in.close();
            conn.disconnect();

            // Parse the JSON response (Mocked here for simplicity)
            Map<String, Double> mockRates = new HashMap<>();
            mockRates.put("USD", 1.0);
            mockRates.put("EUR", 0.85);
            mockRates.put("INR", 74.0);
            mockRates.put("GBP", 0.75);
            mockRates.put("JPY", 110.0);

            return mockRates.get(target);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0; // Default exchange rate if API call fails
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
