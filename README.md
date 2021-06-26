# **Currency Converter**
Essa API é responsável por realizar a conversão de moedas através de parâmetros recebidos pelo usuário e buscando as taxas de conversão de um serviço online de terceiros.

Tem como objetivo demonstrar e validar a escolha da tecnologia, bem como evidenciar boas praticas de programação.

ㅤ

## > Funcionalidade Conversor de Moedas <

Para realizar uma conversão de moedas é necessário enviar um HTTP GET para a seguinte URI:
```sh
/converter/user/{userId}/from/{currencyFrom}/to/{currencyTo}/amount/{amount}
```

ONDE:

Deve-se obrigatoriamente informar quatro parâmetros. São eles:
- {userId} - Identificador do usuário - Integer - Exemplo: 1
- {currencyFrom} - Moeda de origem - String - Exemplo: BRL
- {currencyTo} - Moeda de destino - String - Exemplo: USD
- {amount} - Valor que deseja converter - Float - Exemplo: 10 ou 10.5

ㅤ

Exemplo de end-point completo:
```sh
/converter/user/1/from/BRL/to/USD/amount/10
```

ㅤ

## > Funcionalidade Histórico de Consultas por Usuário <
Para realizar a consulta do histórico por usuário é necessário enviar um HTTP GET para a seguinte URI:
```sh
/user/history/{userId}
```
ONDE:

Deve-se obrigatoriamente informar o parâmetro:

- Identificador do usuário: {userId} - Integer - Exemplo: 1

ㅤ

Exemplo de end-point completo:
```sh
/user/history/1
```

ㅤ

## > Tecnologias Utilizadas <

- Java 11
- Spring Framework
- WEBFLUX
- Maven
- Lombok
- Swagger
- H2 (embedded)

ㅤ

## > Como Executar o Projeto <

Pré-requisitos:
1. Certifique-se de possuir o Maven instalado na máquina;
2. Certifique-se que a porta 8080 esteja disponível.

ㅤ

- Executando via IDE IntelliJ:


Baixe este repositório e importe-o como um Maven Project.

Abra a classe Application e execute o comando Run (um ícone verde próximo ao início da classe).

ㅤ

- Executando via linha de comando:


Baixe este repositório e no terminal navegue até o nível do arquivo **pom.xml** e execute o seguinte comando:


**mvn clean package**


Após o término do processo, será criada uma pasta “target”. Acesse ela e execute:


**java -jar Currency-Converter-Challenge-1.0.0.jar**

ㅤ

Em ambos os casos, após alguns segundos o projeto estará disponível na porta 8080.

ㅤ

Com a aplicação rodando na sua máquina, também é possível consultar a documentação viva.

- Acesse http://localhost:8080/api-docs e confira a documentação descritiva em formato JSON.
- Acesse http://localhost:8080/api-docs.html e confira a documentação interativa.

ㅤ

## > Camadas do Projeto <
O projeto foi dividido entre classes da seguinte ordem:

- Controller:
  Classes responsáveis por fazer a interligação da aplicação com as chamadas REST.

- Service:
  Classes responsáveis por gerir as regras de negócio.

- Repository:
  Classes que contém o envelope de persistência.

- Model:
  Classes que representam os objetos persistentes.

- DTO:
  Classes que representam objetos funcionais, que não são persistíveis.

ㅤ

## > Agradecimentos <
Agradeço por ter participado desse processo, foi muito agradável o processo de construção dessa API, espero que gostem também. ;)

Acessem meu Medium em https://megalexandre.medium.com/

Alexandre Queiroz

ㅤ

