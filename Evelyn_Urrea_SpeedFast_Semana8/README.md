# SpeedFast — Semana 8 (CRUD con JDBC y Swing)

Proyecto Java 21 + Swing + MySQL con CRUD para repartidores, pedidos y entregas. Los DAO usan `PreparedStatement`, `ResultSet` y cierre automático de recursos.

## Preparación rápida

1. En MySQL Workbench, abre y ejecuta `sql/speedfast_db.sql`.
2. Copia `config.properties.example` como `config.properties` y escribe tu clave local de MySQL. Ese archivo está excluido de Git.
3. Abre esta carpeta desde IntelliJ IDEA como proyecto Maven y espera que cargue MySQL Connector/J.
4. Ejecuta `cl.speedfast.Main`.

Consola Maven: `mvn clean compile exec:java`.

## Uso

- En cada pestaña, completa los campos y pulsa **Guardar** para crear. Selecciona una fila para editarla y vuelve a pulsar **Guardar**; **Eliminar** borra la fila seleccionada; **Limpiar** deja el formulario nuevo.
- Pedidos permite filtrar por estado y tipo.
- Entregas obtiene pedidos y repartidores desde la base de datos para escogerlos en combos. Ingresa la fecha y hora como `AAAA-MM-DD HH:MM`.
- La app muestra confirmaciones y errores con ventanas de mensaje y vuelve a cargar las tablas después de cada operación.

El modelo usa las tablas `repartidores`, `pedidos` y `entregas` en `speedfast_db`. Si ya tienes un esquema previo con nombres o columnas diferentes, ajusta primero `sql/speedfast_db.sql` y los DAO para que coincidan.
