package jesus;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter {

    private JFrame frame;
    private JTextField textFieldAmount;
    private JComboBox<String> comboBoxFrom;
    private JComboBox<String> comboBoxTo;
    private JLabel lblResult;

    private final String API_KEY = "715a38d166f6f90983041c27";
    private final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    private JsonObject exchangeRatesObj;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CurrencyConverter window = new CurrencyConverter();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CurrencyConverter() {
        initialize();
        fetchExchangeRates();
    }

    private void initialize() {
    	
		JFrame frmConversorDe = new JFrame();
		frmConversorDe.setTitle(":: Conversor de Moedas ::");
		frmConversorDe.setBounds(100, 100, 400, 300);
		frmConversorDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversorDe.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 384, 261);
		frmConversorDe.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblAmount = new JLabel("Valor:");
		lblAmount.setBounds(30, 30, 70, 20);
		panel.add(lblAmount);

		textFieldAmount = new JTextField();
		textFieldAmount.setText("1");
		textFieldAmount.setBounds(100, 30, 120, 20);
		panel.add(textFieldAmount);
		textFieldAmount.setColumns(10);

		JLabel lblFrom = new JLabel("De:");
		lblFrom.setBounds(30, 70, 70, 20);
		panel.add(lblFrom);

		comboBoxFrom = new JComboBox<>();
		comboBoxFrom.setBounds(100, 70, 120, 20);
		comboBoxFrom.addItem("Reais");
		comboBoxFrom.addItem("Dólar");
		comboBoxFrom.addItem("Euro");
		comboBoxFrom.addItem("Libras Esterlinas");
		comboBoxFrom.addItem("Peso Argentino");
		comboBoxFrom.addItem("Peso Chileno");
		panel.add(comboBoxFrom);

		JLabel lblTo = new JLabel("Para:");
		lblTo.setBounds(30, 110, 70, 20);
		panel.add(lblTo);

		comboBoxTo = new JComboBox<>();
		comboBoxTo.setBounds(100, 110, 120, 20);
		comboBoxTo.addItem("Reais");
		comboBoxTo.addItem("Dólar");
		comboBoxTo.addItem("Euro");
		comboBoxTo.addItem("Libras Esterlinas");
		comboBoxTo.addItem("Peso Argentino");
		comboBoxTo.addItem("Peso Chileno");
		panel.add(comboBoxTo);

		JButton btnConvert = new JButton("Converter");
		btnConvert.setBounds(30, 150, 190, 30);
		panel.add(btnConvert);

		lblResult = new JLabel("");
		lblResult.setBounds(30, 200, 300, 20);
		panel.add(lblResult);

		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertCurrency();
			}
		});
	}
    
    private void fetchExchangeRates() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonParser parser = new JsonParser();
            JsonObject jsonResponse = parser.parse(response.toString()).getAsJsonObject();

            String result = jsonResponse.get("result").getAsString();
            if (result.equals("success")) {
                exchangeRatesObj = jsonResponse.getAsJsonObject("conversion_rates");
            } else {
                lblResult.setText("Erro ao obter as taxas de câmbio.");
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            lblResult.setText("Erro ao obter as taxas de câmbio.");
        }
    }

	private void convertCurrency() {
		String fromCurrency = comboBoxFrom.getSelectedItem().toString();
		String toCurrency = comboBoxTo.getSelectedItem().toString();

		double amount = 0.0;
		try {
			amount = Double.parseDouble(textFieldAmount.getText());
		} catch (NumberFormatException ex) {
			lblResult.setText("Valor inválido.");
			return;
		}

		int fromIndex = getCurrencyIndex(fromCurrency);
		int toIndex = getCurrencyIndex(toCurrency);

		double[] exchangeRates = null;
		double convertedAmount = amount * exchangeRates[toIndex] / exchangeRates[fromIndex];
		lblResult.setText(
				String.format("%.2f %s equivalem a %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
	}

	private int getCurrencyIndex(String currency) {
		switch (currency) {
		case "Reais":
			return 0;
		case "Dólar": 
			return 1;
		case "Euro":
			return 2;
		case "Libras Esterlinas":
			return 3;
		case "Peso Argentino":
			return 4;
		case "Peso Chileno":
			return 5;
		default:
			return -1;
		}
	}
}
