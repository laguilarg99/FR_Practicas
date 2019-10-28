1. Descripción
2. Diagrama de estados Descripción del funcionamiento
3. Descripcion de los mensajes que intercambian cliente-servidor (parametros del mensaje y descripcion)
4. Capturas de pantalla del modelo cliente servidor mostrando el funcionamiento

TCP
servidor							Client

ServerSocket(port_numb,timeout)		Socket(host,port_numb)
accept()							
Output Stream()						OutputStream()
InputStream()						InputStream()
Close()								Close()

UDP
Server							Client
DatagramSocket(port)			DatagramSocket()
DatagramPacket					DatagramSocket()
Receive()						Send()
close()							close()

javac -d . ProcesadorYodafy.java
javac YodafyServidorIterativo.java

##Especificación del protocolo IMAP##
1. El protocolo usa TCP para permitir a un cliente acceder y manipular mensajes de correo electronico en un servidor.
2. Las interacciones consisten en
   1. Establecimento de la conexion cliente/servidor
   2. Respuesta inicial positiva del servidor
   3. Interacciones cliente/servidor
      1. comandos del cliente
      2. datos del servidor
      3. Respuesta del servidor a completitud de operaciones
   4. todas las interacciones estan en formato de lineas(strings que acaban en CRLF)
   5. Cada comando empieza con una operacion, cada comando del cliente tiene un prefijo alfanumerico(A0001 A0002) denominado tag
   6. Cada cliente sigue una sintaxis de comandos especificada en el RFC3501 
