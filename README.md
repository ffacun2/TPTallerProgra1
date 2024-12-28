# Testing con JUnit
Este proyecto consiste en evaluar un sistema de gestion de viajes mediante pruebas de testeo para determinar los escenarios y casos de usos de cada metodo.
Se han implementado diferentes tipos de prueba, incluyendo unitarias, de integracion, mocks y prueba de vista, para garantizar la calidad y fiabilidad del sistema.

### Sistema de Gestion de Viajes

El sistema consiste en gestionar parte de una empresa de transporte de pasajeros. La empresa cuenta con una flota de vehiculos de diferentes
caracteristicas (Auto/Combi/Moto), un conjunto de choferes con categoria (Permanente/Temporario) que manejan cualquier tipo de vehiculo y un conjunto de clientes
registrados con los cuales opera. Estos actores y recursos son dinamicos en el tiempo, o sea, pueden aumentar.

El sistema tendra dos tipos de usuario:
1. Un administrador
2. Varios Clientes

### Funcionalidades del Administrador
- Alta de choferes y vehiculos.
- Solicitud de listados de:<br>
    - Clientes<br>
    - Choferes<br>
    - Vehiculos<br>
    - Viajes<br>
- Calculo del total de dinero necesario para pagar los salarios.
- Solicitud de listado de viajes de un Chofer particular.
- Solicutud de puntaje y sueldo de un Chofer particular.
- Creacion de Viaje, asignando un Chofer disponible y un Vehiculo pertinente a un Pedido generado por un Cliente.

### Funcionalidades del Cliente
- Registro de un nuevo Cliente. El sistema debe notificar en caso de error, ya que no se puede repetir nombre de usuario.- Completar formulario de solicitud de Viaje. El sistema evaluara la solicitud e informara al usuario sobre:<br>
    - Aceptacion del Pedido.<br>
    - Rechazo por falta de Vehiculo pertinente.<br>
- Pagar el Viaje y calificar el Chofer.
- Visualizacion de historial de los propios Viajes.

### Testing Realizados
- <b>Pruebas Unitarias</b><br>
   Validacion de las funcionalidades principales de las entidades del sistema, como la creacion de usuario, asignacion de viajes y calculo de salarios.<br>
- <b>Pruebas de integracion</b><br>
   Verificacion del funcionamiento correcto entre modulos, como la interaccion entre Clientes, Choferes y Vehiculos en la asignacion de viajes.<br>
- <b>Pruebas Mocks</b><br>
   Simulacion de dependencias, utilizando la herramienta como Mockito, para aislar componentes durante las pruebas.<br>
- <b>Pruebas de Vista</b><br>
   Automatizacion de interacciones con la interfaz grafica del sistema, utilizando la libreria Robots de Java.

### Herramientas y Librerias Utilizadas
- Lenguaje Java
- JUnit 4
- Mockito
- java.awt.Robot
- Maven

   
