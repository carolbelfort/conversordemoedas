import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;

public class TestePrincipal {

    private ApiClient apiClient;
    private Map<String, Double> taxas;
    private Scanner scanner;

    public TestePrincipal() {
        this.apiClient = new ApiClient();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        exibirBoasVindas();

        if (!carregarTaxas()) {
            System.err.println("❌ Não foi possível iniciar o conversor. Verifique sua conexão.");
            return;
        }

        executarMenuPrincipal();

        System.out.println("\n✓ Obrigado por usar o Conversor de Moedas!");
        System.out.println("✓ Até logo!\n");
        scanner.close();
    }

    private void exibirBoasVindas() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║                                           ║");
        System.out.println("║      CONVERSOR DE MOEDAS - v2.0          ║");
        System.out.println("║      Bem-vindo(a)!                       ║");
        System.out.println("║                                           ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }

    private boolean carregarTaxas() {
        System.out.println("\n⏳ Buscando taxas de câmbio atualizadas...");

        String json = apiClient.buscarTaxas("USD");

        if (json == null) {
            return false;
        }

        Gson gson = new Gson();
        ExchangeResponse response = gson.fromJson(json, ExchangeResponse.class);

        if (response.isSuccess()) {
            this.taxas = response.getConversion_rates();
            System.out.println("✓ Taxas obtidas com sucesso!");
            return true;
        }

        return false;
    }

    private void executarMenuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            exibirMenuPrincipal();

            int opcao = lerOpcaoMenu();

            if (opcao == 8) {
                continuar = false;
                continue;
            }

            if (opcao == 7) {
                realizarConversaoPersonalizada();
            } else if (opcao >= 1 && opcao <= 6) {
                realizarConversaoPredefinida(opcao);
            } else {
                System.out.println("\n❌ Opção inválida! Escolha um número de 1 a 8.");
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║          MENU DE CONVERSÃO DE MOEDAS              ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  1) Dólar (USD)          ==>  Real Brasileiro (BRL) ║");
        System.out.println("║  2) Real Brasileiro (BRL) ==>  Dólar (USD)          ║");
        System.out.println("║  3) Dólar (USD)          ==>  Euro (EUR)            ║");
        System.out.println("║  4) Euro (EUR)           ==>  Dólar (USD)           ║");
        System.out.println("║  5) Dólar (USD)          ==>  Peso Argentino (ARS)  ║");
        System.out.println("║  6) Peso Argentino (ARS) ==>  Dólar (USD)           ║");
        System.out.println("║  7) Conversão Personalizada (Escolher moedas)       ║");
        System.out.println("║  8) Sair do programa                                ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print("\nEscolha uma opção: ");
    }

    private int lerOpcaoMenu() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void realizarConversaoPredefinida(int opcao) {
        String moedaOrigem = "";
        String moedaDestino = "";

        switch (opcao) {
            case 1: moedaOrigem = "USD"; moedaDestino = "BRL"; break;
            case 2: moedaOrigem = "BRL"; moedaDestino = "USD"; break;
            case 3: moedaOrigem = "USD"; moedaDestino = "EUR"; break;
            case 4: moedaOrigem = "EUR"; moedaDestino = "USD"; break;
            case 5: moedaOrigem = "USD"; moedaDestino = "ARS"; break;
            case 6: moedaOrigem = "ARS"; moedaDestino = "USD"; break;
        }

        double valor = solicitarValor();
        double resultado = converterMoeda(valor, moedaOrigem, moedaDestino);
        exibirResultado(valor, moedaOrigem, resultado, moedaDestino);
    }

    private void realizarConversaoPersonalizada() {
        exibirMoedasDisponiveis();

        System.out.print("Escolha a moeda de ORIGEM (1-10): ");
        String moedaOrigem = obterMoedaPorOpcao(lerOpcaoMenu());

        if (moedaOrigem == null || !taxas.containsKey(moedaOrigem)) {
            System.out.println("❌ Moeda inválida!");
            return;
        }

        System.out.print("Escolha a moeda de DESTINO (1-10): ");
        String moedaDestino = obterMoedaPorOpcao(lerOpcaoMenu());

        if (moedaDestino == null || !taxas.containsKey(moedaDestino)) {
            System.out.println("❌ Moeda inválida!");
            return;
        }

        double valor = solicitarValor();
        double resultado = converterMoeda(valor, moedaOrigem, moedaDestino);
        exibirResultado(valor, moedaOrigem, resultado, moedaDestino);
    }

    private void exibirMoedasDisponiveis() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     MOEDAS DISPONÍVEIS             ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  1. USD - Dólar Americano          ║");
        System.out.println("║  2. BRL - Real Brasileiro          ║");
        System.out.println("║  3. EUR - Euro                     ║");
        System.out.println("║  4. GBP - Libra Esterlina          ║");
        System.out.println("║  5. JPY - Iene Japonês             ║");
        System.out.println("║  6. ARS - Peso Argentino           ║");
        System.out.println("║  7. CAD - Dólar Canadense          ║");
        System.out.println("║  8. AUD - Dólar Australiano        ║");
        System.out.println("║  9. CNY - Yuan Chinês              ║");
        System.out.println("║ 10. INR - Rúpia Indiana            ║");
        System.out.println("╚════════════════════════════════════╝\n");
    }

    private String obterMoedaPorOpcao(int opcao) {
        switch (opcao) {
            case 1: return "USD";
            case 2: return "BRL";
            case 3: return "EUR";
            case 4: return "GBP";
            case 5: return "JPY";
            case 6: return "ARS";
            case 7: return "CAD";
            case 8: return "AUD";
            case 9: return "CNY";
            case 10: return "INR";
            default: return null;
        }
    }

    private double solicitarValor() {
        while (true) {
            System.out.print("\nDigite o valor a ser convertido: ");
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor <= 0) {
                    System.out.println("❌ Por favor, digite um valor positivo!");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("❌ Valor inválido! Digite um número válido.");
            }
        }
    }

    private double converterMoeda(double valor, String moedaOrigem, String moedaDestino) {
        double valorEmUSD = valor;

        if (!moedaOrigem.equals("USD")) {
            double taxaOrigem = taxas.get(moedaOrigem);
            valorEmUSD = valor / taxaOrigem;
        }

        if (moedaDestino.equals("USD")) {
            return valorEmUSD;
        } else {
            double taxaDestino = taxas.get(moedaDestino);
            return valorEmUSD * taxaDestino;
        }
    }

    private void exibirResultado(double valor, String moedaOrigem, double valorConvertido, String moedaDestino) {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║         RESULTADO DA CONVERSÃO             ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.printf("║  %.2f %s  =  %.2f %s%n", valor, moedaOrigem, valorConvertido, moedaDestino);
        System.out.println("╚════════════════════════════════════════════╝");
    }
}