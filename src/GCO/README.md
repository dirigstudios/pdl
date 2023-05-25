## Runtime

el esquema de runtime de nuestro compilador es el siguiente

![EE](https://github.com/dirigstudios/pdl/blob/master/src/GCO/imagenes/EE.png)

donde cada zona de código será un fichero independiente, que se juntarán después siguiendo ese orden

## GCO

para generar código objeto, cada sentencia en la que se haga un GCI.emite(), habrá después su correspondiente acción para GCO

aún queda por ver cómo:
    - pasar la info de la TS progresivamente (cada vez que se genere) al fichero de Datos Estáticos
    - toda la info de la pila de los RA
        - EM (DR)
        - Parámetros
        - Variable Local y Datos Temporales
    - implementar el resto de instrucciones de CI a CO
        - asignación :)
        - suma :)
        - 