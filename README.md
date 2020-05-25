## Iniciando
- Desenvolvido com Java 14
- Alterar o arquivo application.yml, mudar o ambiente de test para dev. 
- Usar test somente quando executar os testes unitários e de integração.
- Executar o comando **gradle bootRun** para iniciar a aplicação
- Usuários com perfíl Admin podem realizar operações POST
- 96% classes, 72% lines covered
- Banco de dados em memória H2
- Spring Security e JWT

## Efetuar Login
- http://ip:port/login
- Usuário Perfíl ADMIN:
```
{
	"login": "uadm",
	"password": "admin"
}
```
- Usuário Perfíl USER:
```
{
	"login": "uexp",
	"password": "root"
}
```
Após efetuar o login na aplicação, utilizar o Bearer Token para autenticar. Encontra-se no header.

## Documentação dos Frameworks Utilizados
- https://spring.io/projects/spring-data-jpa#overview
- https://spring.io/projects/spring-security#overview
- https://jwt.io/
- https://projectlombok.org/
- https://gradle.org/
- https://swagger.io/tools/swagger-ui/

## Ferramentas Utilizadas:
- https://www.jetbrains.com/pt-br/idea/
- https://www.postman.com/
- https://github.com/aegerm/proof-ais-themovie/actions
- https://www.microsoft.com/pt-br/windows/

## Notebook de Desenvolvimento
- Dell Latitude 3400
- Memória RAM 8GB
- SSD 250GB
- Processador Intel Core I7-8565U
