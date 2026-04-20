
🚀 Ejecución rápida
Si ya tienes el entorno de Java configurado, puedes ejecutar la aplicación directamente desde la terminal con el siguiente comando:

PowerShell
java -jar out/artifacts/TodoApp_jar/TodoApp.jar
📂 Estructura del Proyecto
La organización de carpetas del proyecto es la siguiente:

src/: Contiene el código fuente original.

jg/: Paquete principal donde reside la lógica de la aplicación.

META-INF/: Archivos de configuración del manifiesto para el empaquetado.

out/: Directorio de salida generado tras la compilación.

artifacts/: Contiene el ejecutable .jar final.

production/: Clases compiladas (.class) y dependencias del diseñador de UI de IntelliJ.

.idea/: Archivos de configuración interna del IDE (IntelliJ).

🛠️ Requisitos
Java Runtime Environment (JRE): Versión 8 o superior (se recomienda la versión compatible con la que fue compilado el proyecto).

IntelliJ IDEA: (Opcional) Para realizar modificaciones en el diseño de la interfaz .form.

🏗️ Compilación y Empaquetado
Si deseas realizar cambios y volver a generar el archivo ejecutable en IntelliJ:

Ve a File > Project Structure > Artifacts.

Asegúrate de que el artefacto TodoApp_jar esté configurado correctamente.

Usa la opción Build > Build Artifacts... y selecciona Build.

El nuevo archivo aparecerá en la ruta out/artifacts/TodoApp_jar/.

📝 Notas adicionales
El proyecto hace uso de las librerías de IntelliJ UI Designer Core, las cuales están incluidas en la carpeta de producción para garantizar que los componentes visuales se rendericen correctamente.
