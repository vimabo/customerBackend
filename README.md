# CustomerBackend
Proyecto en Maven + Spring Boot + JPA para la administración de clientes.

# Instrucciones para construir el proyecto
a. El proyecto funciona con JDK versión 11.

b. clonar el proyecto y abrirlo en el ide de desarrollo.

c. Compilación: mvn instalación limpia.

d. Metodo GET para listar todos los customer en BD.

http://localhost:8080/customers/listaCustomer

e. Metodo GET para obtener un customer en BD.

http://localhost:8080/customers/{id}

d. Metodo POST para crear un customer en BD.

http://localhost:8080/customers/createCustomer

REQUEST:
{
   "firstName":"Victor Manuel",
   "lastName":"Bocanegra Rodriguez",
   "company":"CMC"
}

d. Metodo POST para actualizar un customer en BD.

http://localhost:8080/customers/updateCustomer

REQUEST:
{
    "id": 4,
   "firstName":"Victor Manuel",
   "lastName":"Bocanegra Rodriguez",
   "company":"CMC"
}

e. Metodo DELETE para borrar un customer en BD.

http://localhost:8080/customers/deleteCustomer/{id}


# SQL
resources/
data.sql = Inserta los primeros customers en Base de datos H2.

Consola H2: http://localhost:8080/h2-console/

URL de JDCB: jdbc:h2:mem:brújula

Usuario: brujula
Contraseña: brujula

Es Necesario que este proyecto este desplegado para el correcto funcionamiento de el 
proyecto Fronted.
