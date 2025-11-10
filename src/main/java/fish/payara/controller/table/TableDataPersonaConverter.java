/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.controller.table;

import fish.payara.model.Persona;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author avbravo
 */
public class TableDataPersonaConverter {

    /**
     * Convierte una lista de objetos Persona al formato Map<ID, List<Valores>>
     * requerido por TableEditable, para su edición. * El orden de los valores
     * añadidos (producto, precio, cantidad) debe coincidir con el orden de los
     * encabezados ('Persona', 'Precio', 'Cantidad').
     */
    public static Map<String, List<String>> convert(List<Persona> personas) {
        Map<String, List<String>> dataMap = new HashMap<>();

        for (Persona c : personas) {
            List<String> values = new ArrayList<>();

            // Los valores se añaden en el orden deseado para la tabla:
            values.add(c.getNombre());
            values.add(String.format("%.2f", c.getLatitude())); 
            values.add(String.format("%.5f", c.getLongitude()));

            // La clave del Map es el ID del producto (usado como primera columna de la fila)
            dataMap.put(c.getId().toString(), values);
        }
        return dataMap;
    }
}
