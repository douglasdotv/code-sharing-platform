## code-sharing-platform

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/code-sharing-platform/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/code-sharing-platform/blob/master/README.pt-br.md)

### Sobre
Esta é uma aplicação web que permite que usuários compartilhem trechos de código entre si.

**Exemplo 1:**
![Screenshot 1](./screenshots/codesharingplatform1.jpg)
![Screenshot 2](./screenshots/codesharingplatform2.jpg)

**Exemplo 2:**
![Screenshot 3](./screenshots/codesharingplatform3.jpg)

O usuário pode escrever um código, definir uma data de expiração e/ou um número máximo de visualizações para ele, e então compartilhar o link do trecho de código com outras pessoas.

Tanto a restrição de tempo quanto a de visualizações são opcionais.
Se elas forem definidas, o trecho de código será privado e estará disponível até que uma restrição seja atingida — o código é excluído do banco de dados em seguida.
Por outro lado, se nenhuma delas for definida, o trecho de código será público e estará disponível indefinitamente.
(Valores negativos, nulos e o número 0 nos campos `time` e `views` são interpretados como uma ausência de restrição. Valores positivos são interpretados como uma restrição).

Endpoints disponíveis:

* `GET /code/new` - Cria uma nova página de trecho de código
* `POST /api/code/new` - Salva um novo trecho de código
* `GET /code/{id}` - Acessa uma página de trecho de código
* `GET /api/code/{id}` - Obtém um trecho de código em formato JSON
* `GET /code/latest` - Mostra os dez trechos de códigos públicos (trechos de código sem restrição de tempo ou visualizações) mais recentes
* `GET /api/code/latest` - Obtém os dez trechos de códigos públicos (trechos de código sem restrição de tempo ou visualizações) mais recentes em formato JSON

### Etapas do projeto
<details open="open">
  <summary>Etapas</summary>
  <ol>
  <li><a href="https://hyperskill.org/projects/130/stages/692/implement">Show the code!</a></li>
  <li><a href="https://hyperskill.org/projects/130/stages/693/implement">POST updates</a></li>
  <li><a href="https://hyperskill.org/projects/130/stages/694/implement">Snippets feed</a></li>
  <li><a href="https://hyperskill.org/projects/130/stages/695/implement">Work With the database</a></li>
  <li><a href="https://hyperskill.org/projects/130/stages/696/implement">Super secret snippets</a></li>
  </ol>
</details>

### Ferramentas utilizadas
* Java 17
* Gradle
* Spring Boot 2.5.6
* Spring MVC
* Spring Data JPA
* H2 Database
* Thymeleaf
* highlight.js
* Bootstrap

### Objetivos de aprendizagem
* Como criar uma aplicação web usando Spring Boot e Spring MVC
* Como interagir com o banco de dados usando Spring Data JPA
* Como criar páginas web com Thymeleaf
* Como usar o highlight.js para destacar a sintaxe de código
* Como usar o Bootstrap para estilizar páginas web

### Como rodar o projeto

1. Certifique-se de ter o JDK (Java Development Kit) e o JRE (Java Runtime Environment) instalados no seu computador e de estar conectado à Internet.

2. Abra o terminal e clone o repositório:
```
git clone https://github.com/douglasdotv/code-sharing-platform.git
```

3. Navegue até a pasta do projeto:
```
cd code-sharing-platform
```

4. Gere a build e rode a aplicação:
```
./gradlew build
./gradlew bootRun
```

5. Acesse os endpoints da aplicação através do navegador ou de uma ferramenta como Postman ou Insomnia.
   (Nota: o projeto está configurado para rodar na porta 8889 por padrão. Exemplo: http://localhost:8889/code/new.)

### Contato
Em caso de dúvidas ou sugestões, sinta-se à vontade para entrar em contato comigo através do [LinkedIn](https://www.linkedin.com/in/douglasdotv/) ou via e-mail (douglas16722@gmail.com).