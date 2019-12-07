# desafio-peixeurbano

## docker
foi utilizado um docker contendo o banco de dados mysql, portanto para iniciar o docker é necessário ter o mesmo instalado na máquina.
para startar é necessário rodar o comando `sudo docker-compose up -d`

## tests
para rodar os testes de integração e unitários basta rodar o comando `mvn test`
obs: foi utilizado um banco de dados mysql embedded para rodar os testes de integração. 
no ubuntu foi necessária a instalação do pacote libaio1 para o banco de dados embedded funcionar. 

## aplicação
para rodar a aplicação basta rodar os comandos
`mvn package`
`mvn install`
`mvn spring-boot:run`
