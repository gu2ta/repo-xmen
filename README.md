# repo-xmen
## ML - Reclutamiento de Xmen's

API en Google App Engine: https://api-xmen-2019.appspot.com/

### Invocaciones validas

#### [/mutant](https://api-xmen-2019.appspot.com/mutant)
* Metodo: POST (No idempotente)
* Objetivo: Validar la matriz ingresada por body, la cual si es valida se procederá a insertarla en la Base de Datos, previamente cargandola por cache en memoria. Si es Mutante devolverá HTTP 200-OK, caso contrario 403-Forbidden.
* Premisas: La matriz ingresada por body tendrá que ser cuadrada, y contener las letras de los String A, C, G y T las cuales representa cada base nitrogenada del ADN. Es mutante, si encuentras más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.
* Ejemplo de Body de Entrada:

{ "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }


#### [/stats](https://api-xmen-2019.appspot.com/stats)
* Metodo: GET (Idempotente)
* Objetivo: Devolver la cantidad de humanos y cantidad de mutantes, como tambien el ratio entre estas cantidades.
* Ejemplo de Body de Salida:
{ "count_mutant_dna":40, "count_human_dna":100, "ratio":0.4}


#### [/swagger-ui.html](https://api-xmen-2019.appspot.com/swagger-ui.html)
* Objetivo: Documentación Online API Rest


### Instrucciones en Eclipse / Pruebas Locales
* Base de Datos: Para poder utilizar la API de manera local se deberá montar la misma, ejecutando el script "DB_xmen_human.sql" ubicado en el repo (repo-xmen/api-xmen/DB_xmen_human.sql), tener en cuenta que dentro del proyecto de la API, se debera configurar el database.properties ubicado en la carpeta "src/main/resources", especialmente los atributos url_development, username_development y password_development para que funcione en MySQL. A tener en cuenta, estos 3 atributos estan encriptados y podrá ser de ayuda el test "EncrypterTest".
* Iniciar API: Ejecutar la clase ar.com.ml.xmen.Application para iniciar de manera local el servidor Tomcat, embebido en Spring Boot.
* Tests: Ejecutar con JUnit el Test Suite ar.com.ml.xmen.ApplicationTestSuite para procesar todos los Test de la API.
* Rest Docs Offline: Para visualizar el documento Spring Rest Docs se deberá compilar por Maven el proyecto api-xmen, con la ejecucion de tests, luego en la carpeta "target\generated-docs" se podrá encontrar el documento con el nombre "xmen-api-guide.html"
