package testeConversor;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConversorDeMoedas {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            // Substitua "YOUR-API-KEY" pela chave da sua API do exchangerate-api.com
            String apiKey = "715a38d166f6f90983041c27";

            // Setting URL com a sua chave da API
            String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/BRL";

            // Making Request
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Accessing object
            String req_result = jsonobj.get("result").getAsString();

            // Mostra o resultado da API (apenas para verificar)
            System.out.println("Resultado da API: " + req_result);

         // Parse do JSON recebido para um objeto JsonObject usando o Gson
            Gson gson = new Gson();
            Object response;
			ExchangeRateData exchangeRateData = gson.fromJson(response.toString(), ExchangeRateData.class);

            double taxaDolar = exchangeRateData.rates.USD;
            double taxaEuro = exchangeRateData.rates.EUR;
            double taxaLibraEsterlina = exchangeRateData.rates.GBP;
            double taxaPesoArgentino = exchangeRateData.rates.ARS;
            double taxaPesoChileno = exchangeRateData.rates.CLP;

            

            

            

            // Pergunta ao usuário qual moeda ele possui e o valor financeiro
            System.out.println("Qual moeda você possui?");
            System.out.println("1 - Reais\n2 - Dólar\n3 - Euro\n4 - Libras Esterlinas\n5 - Peso Argentino\n6 - Peso Chileno");
            int moedaOrigem = input.nextInt();

            System.out.println("Informe o valor financeiro:");
            double valorOrigem = input.nextDouble();

            // Realiza a conversão para Reais (BRL) a partir da moeda de origem selecionada
            double valorEmReais = 0.0;

            switch (moedaOrigem) {
                case 1:
                    valorEmReais = valorOrigem; // Já é em Reais
                    break;
                case 2:
                    valorEmReais = valorOrigem / taxaDolar;
                    break;
                case 3:
                    valorEmReais = valorOrigem / taxaEuro;
                    break;
                case 4:
                    valorEmReais = valorOrigem / taxaLibraEsterlina;
                    break;
                case 5:
                    valorEmReais = valorOrigem / taxaPesoArgentino;
                    break;
                case 6:
                    valorEmReais = valorOrigem / taxaPesoChileno;
                    break;
                default:
                    System.out.println("Moeda de origem inválida.");
                    input.close();
                    return; // Encerra o programa caso a moeda de origem seja inválida
            }

            // Oferece opções de conversão a partir de Reais (BRL)
            System.out.println("Para qual moeda você deseja converter?");
            System.out.println("1 - Dólar\n2 - Euro\n3 - Libras Esterlinas\n4 - Peso Argentino\n5 - Peso Chileno");
            int moedaDestino = input.nextInt();

            // Realiza a conversão a partir de Reais (BRL) para a moeda de destino desejada
            double resultado = 0.0;

            switch (moedaDestino) {
                case 1:
                    resultado = valorEmReais * taxaDolar;
                    break;
                case 2:
                    resultado = valorEmReais * taxaEuro;
                    break;
                case 3:
                    resultado = valorEmReais * taxaLibraEsterlina;
                    break;
                case 4:
                    resultado = valorEmReais * taxaPesoArgentino;
                    break;
                case 5:
                    resultado = valorEmReais * taxaPesoChileno;
                    break;
                default:
                    System.out.println("Moeda de destino inválida.");
                    break;
            }

            // Exibindo o resultado da conversão
            System.out.println("O valor convertido é: " + resultado);

            input.close();

        } catch (Exception e) {
            System.out.println("Erro ao obter as taxas de câmbio da API.");
            e.printStackTrace();
        }
    }
    
 // Classe auxiliar para representar os dados das taxas de câmbio
    static class ExchangeRateData {
        Rates rates;
    }

    // Classe auxiliar para representar as taxas de câmbio
    static class Rates {
        double USD;
        double EUR;
        double GBP;
        double ARS;
        double CLP;
        // Adicione outras moedas conforme necessário
    }
}