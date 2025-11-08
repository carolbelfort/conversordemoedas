# 💱 Conversor de Moedas

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge)

Um conversor de moedas em tempo real desenvolvido em Java, utilizando a API ExchangeRate para obter taxas de câmbio atualizadas.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido como parte de um desafio de programação, com o objetivo de criar uma aplicação funcional que permite aos usuários converter valores entre diferentes moedas de forma simples e intuitiva através do console.

### ✨ Funcionalidades

- ✅ Conversão entre múltiplas moedas em tempo real
- ✅ Menu interativo com opções numeradas
- ✅ 6 conversões rápidas predefinidas (USD↔BRL, USD↔EUR, USD↔ARS)
- ✅ Modo de conversão personalizada entre 10 moedas diferentes
- ✅ Validação robusta de entradas do usuário
- ✅ Tratamento de erros e exceções
- ✅ Interface visual clara e organizada no console
- ✅ Taxas de câmbio atualizadas automaticamente via API

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **HttpClient** - Cliente HTTP nativo do Java para requisições à API
- **Gson 2.10.1** - Biblioteca para parsing de JSON
- **ExchangeRate API** - API gratuita para obter taxas de câmbio

## 📁 Estrutura do Projeto

```
conversordemoedas/
│
├── src/
│   ├── Main.java                  # Classe principal que inicia o programa
│   ├── TestePrincipal.java        # Lógica de conversão e interface do menu
│   ├── ApiClient.java             # Cliente para comunicação com a API
│   └── ExchangeResponse.java      # Modelo de dados da resposta da API
│
├── lib/
│   └── gson-2.10.1.jar           # Biblioteca Gson
│
└── README.md
```

## 🔧 Instalação e Configuração

### Pré-requisitos

- Java JDK 21 ou superior
- Biblioteca Gson 2.10.1
- Conexão com a internet (para acessar a API)

### Passos para Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/conversor-de-moedas.git
cd conversor-de-moedas
```

2. **Configure a biblioteca Gson**
   - Baixe o Gson 2.10.1 em: https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
   - Adicione o arquivo `.jar` ao classpath do seu projeto

3. **Obtenha sua chave da API**
   - Acesse: https://www.exchangerate-api.com/
   - Cadastre-se gratuitamente
   - Copie sua chave de API
   - Substitua no arquivo `ApiClient.java`:
   ```java
   private final String API_KEY = "SUA_CHAVE_AQUI";
   ```

4. **Compile o projeto**
```bash
javac -cp ".:lib/gson-2.10.1.jar" *.java
```

5. **Execute o programa**
```bash
java -cp ".:lib/gson-2.10.1.jar" Main
```

> **Nota para Windows**: Use `;` ao invés de `:` no classpath
> ```bash
> javac -cp ".;lib/gson-2.10.1.jar" *.java
> java -cp ".;lib/gson-2.10.1.jar" Main
> ```

## 💻 Como Usar

### Menu Principal

Ao executar o programa, você verá o menu principal:

```
╔════════════════════════════════════════════════════╗
║          MENU DE CONVERSÃO DE MOEDAS              ║
╠════════════════════════════════════════════════════╣
║  1) Dólar (USD)          ==>  Real Brasileiro (BRL) ║
║  2) Real Brasileiro (BRL) ==>  Dólar (USD)          ║
║  3) Dólar (USD)          ==>  Euro (EUR)            ║
║  4) Euro (EUR)           ==>  Dólar (USD)           ║
║  5) Dólar (USD)          ==>  Peso Argentino (ARS)  ║
║  6) Peso Argentino (ARS) ==>  Dólar (USD)           ║
║  7) Conversão Personalizada (Escolher moedas)       ║
║  8) Sair do programa                                ║
╚════════════════════════════════════════════════════╝
```

### Moedas Disponíveis

- 🇺🇸 USD - Dólar Americano
- 🇧🇷 BRL - Real Brasileiro
- 🇪🇺 EUR - Euro
- 🇬🇧 GBP - Libra Esterlina
- 🇯🇵 JPY - Iene Japonês
- 🇦🇷 ARS - Peso Argentino
- 🇨🇦 CAD - Dólar Canadense
- 🇦🇺 AUD - Dólar Australiano
- 🇨🇳 CNY - Yuan Chinês
- 🇮🇳 INR - Rúpia Indiana

### Exemplo de Uso

```
Escolha uma opção: 1
Digite o valor a ser convertido: 100

╔════════════════════════════════════════════╗
║         RESULTADO DA CONVERSÃO             ║
╠════════════════════════════════════════════╣
║  100.00 USD  =  491.50 BRL
╚════════════════════════════════════════════╝
```

## 🏗️ Arquitetura do Projeto

### Classes Principais

#### `Main.java`
Ponto de entrada do programa. Instancia e inicia o conversor.

#### `TestePrincipal.java`
Gerencia toda a lógica do programa:
- Interface com o usuário (menu)
- Loop de execução principal
- Lógica de conversão de moedas
- Validações de entrada

#### `ApiClient.java`
Responsável pela comunicação com a API:
- Realiza requisições HTTP
- Gerencia autenticação (API Key)
- Trata erros de conexão

#### `ExchangeResponse.java`
Modelo de dados que representa a resposta da API:
- Mapeia o JSON retornado
- Fornece métodos de acesso aos dados

## 🔐 Segurança

- **Nunca compartilhe sua chave de API publicamente**
- A chave da API tem limite de 1.500 requisições gratuitas por mês
- Para uso em produção, considere usar variáveis de ambiente para armazenar a chave

## 🧪 Validações Implementadas

O programa inclui validações robustas:

- ✅ Verificação de entrada numérica
- ✅ Validação de valores positivos
- ✅ Verificação de moedas válidas
- ✅ Tratamento de erros de conexão
- ✅ Validação de resposta da API
- ✅ Tratamento de exceções genéricas

## 📊 Tratamento de Erros

O programa trata diversos cenários de erro:

- Conexão com a API falha
- Resposta HTTP diferente de 200
- Entrada de dados inválida
- Valores negativos ou zero
- Moedas não disponíveis
- Formato de número inválido

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Fazer um Fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abrir um Pull Request

## 📝 Melhorias Futuras

- [ ] Adicionar mais moedas
- [ ] Implementar histórico de conversões
- [ ] Criar interface gráfica (GUI)
- [ ] Adicionar gráficos de variação cambial
- [ ] Salvar conversões favoritas
- [ ] Modo offline com cache de taxas

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

Desenvolvido por **[Ana Carolina]**

