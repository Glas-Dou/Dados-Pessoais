# dados-pessoais

> **Observação — Spring Initializr Customizado**
> Este projeto foi gerado pelo **TADS Spring Initializr customizado**, uma ferramenta
> didática para a disciplina de Desenvolvimento de Sistemas Web no curso TADS do Senac.
> A estrutura gerada é equivalente à do [Spring Initializr oficial](https://start.spring.io),
> porém inclui algumas dependências adicionais não presentes no gerador
> original (ex: Springdoc OpenAPI/Swagger, Thymeleaf Layout Dialect, WebJars).

## Dependências selecionadas

- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools) — Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
- [Spring Web](https://docs.spring.io/spring-framework/reference/web/webmvc.html) — Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- [Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html) — Bean Validation with Hibernate Validator.

## Como executar

Este projeto foi criado para ser aberto diretamente na IDE de sua preferência
(IntelliJ IDEA, Eclipse, STS, VS Code com extensão Java, etc.). Importe-o como um
projeto Maven existente e aguarde o download das dependências.

### Pré-requisitos

- Java 21+
- Maven 3.9+ (instalado localmente **ou** use o Maven Wrapper após gerá-lo — veja abaixo)

### Gerando os arquivos do Maven Wrapper

O arquivo ZIP gerado por esta ferramenta **não inclui** os scripts `mvnw` / `mvnw.cmd`.
Para gerá-los, execute o comando abaixo uma vez na raiz do projeto (requer Maven instalado):

```bash
mvn wrapper:wrapper
```

Isso criará os arquivos `mvnw`, `mvnw.cmd` e o diretório `.mvn/wrapper/`.

### Executar com Maven Wrapper

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Executar com Maven local

```bash
mvn spring-boot:run
```

### Empacotar

```bash
./mvnw clean package
java -jar target/dados-pessoais-0.0.1-SNAPSHOT.jar
```

## Referências

- [Documentação do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Guias do Spring](https://spring.io/guides)
- [Spring Initializr oficial](https://start.spring.io)
- [Como gerar os arquivos do Maven Wrapper](https://mkyong.com/maven/how-to-generate-maven-wrapper-files-mvnw-and-mvnw-cmd/)
- [Dicas e Tutoriais Spring](https://github.com/ftsuda-senac/dicas-desenvolvimento/blob/main/Dicas-e-Tutoriais-Spring.md)
