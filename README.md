# Parcial 2
Crear una solución web que explore dos algoritmos de busqueda: búsqueda lineal y búsqueda binaria. El programa debe estar desplegado en tres maquinas virtuales de Ec2 de AWS.

Creé el codigo base para el funcionamiento del proyecto junto a los algoritmos de busqueda. (me faltó terminar el de busqueda binaria)
![image](https://github.com/user-attachments/assets/5ae3fd80-8c43-4a60-8cf5-61e3e3a7bf01)

Creé las 3 instancias Ec2 (Una para la busqueda lineal, otra para la busqueda binaria y otra para el proxy)
![image](https://github.com/user-attachments/assets/c47c7c7f-71cd-4aab-9ded-3ca65c616b70)
Creé la función lambda para la busqueda lineal utilizando el jar del proyecto y modifique el evento json para su funcionamiento (me faltó configurar el string por lo que no funciona correctamente)
![image](https://github.com/user-attachments/assets/36c42b83-db06-4c8a-9309-0abd14b212a3)
![image](https://github.com/user-attachments/assets/049af50a-5989-4bd0-878e-292821a80823)
![image](https://github.com/user-attachments/assets/547cd8bc-40f5-4db8-8a23-2d4ff18da4fc)
![image](https://github.com/user-attachments/assets/9837fbd6-6b0d-4572-8653-534d4985ac14)

Creé la función lambda para la busqueda binaria utilizando el jar del proyecto y modifique el evento json para su funcionamiento (me faltó configurar el string por lo que no funciona correctamente)
![image](https://github.com/user-attachments/assets/8c76e236-4622-4ca2-8a76-239151922f3f)
![image](https://github.com/user-attachments/assets/4f6a889f-1a28-419f-b5c5-b529fdbea15b)

Entré al link usando las ips de las instancias y el puerto congigurado pero como lo anterior no me funcionaba tampoco esto.
![image](https://github.com/user-attachments/assets/a97e4b8a-87ca-42a1-95dd-d340ac5bc1f3)
![image](https://github.com/user-attachments/assets/52a87466-0ce6-48a5-b798-523f4821683a)

No alcancé a configurar el proxy.

Para correrlo en Ec2 toca configurar una función lambda usando el jar ejecutable del proyecto, esta función debe tener un rol asignado y modificar el json para su correcto funcionamiento asignandole un string, si todo sale correctamente, se puede revisar su funcionamiento usando la ip de la instancia Ec2 y el puerto configurado junto con la información en tipo json para que devuelva una respuesta.



