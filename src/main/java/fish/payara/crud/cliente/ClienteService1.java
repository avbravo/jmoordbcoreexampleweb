/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.cliente;

/**
 *
 * @author avbravo
 */


import fish.payara.model.Cliente;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


import java.util.List;

/**
 * Servicio que simula la capa de acceso a datos (Repository/DAO) para la entidad Cliente.
 * En un entorno real, estos métodos interactuarían con una base de datos.
 */
public class ClienteService1 {

    // Simulación de la base de datos en memoria
    private final List<Cliente> mockData = new ArrayList<>();
    // Generador de IDs únicos
    private final AtomicLong idGenerator = new AtomicLong(18L); 

    public ClienteService1() {
        // Inicializar con datos de ejemplo
        mockData.add(new Cliente(1L, "Ana Gómez", 1200.00, 45.63332));
        mockData.add(new Cliente(2L, "María Pérez", 350.00, 90.624));
        mockData.add(new Cliente(3L, "Carlos Ruiz", 850.50, 75.12345));
        mockData.add(new Cliente(4L, "Laura Torres", 210.99, 105.78901));
        mockData.add(new Cliente(5L, "Javier Soto", 1500.00, 30.55555));
        mockData.add(new Cliente(6L, "Elena López", 925.75, 68.98765));
        mockData.add(new Cliente(7L, "Pedro Reyes", 480.00, 15.43210));
        mockData.add(new Cliente(8L, "Sofía Diaz", 675.30, 99.88776));
        mockData.add(new Cliente(9L, "Miguel Mora", 180.25, 40.10203));
        mockData.add(new Cliente(10L, "Carmen Salas", 1010.10, 82.50607));
        mockData.add(new Cliente(11L, "David Castro", 330.40, 55.91827));
        mockData.add(new Cliente(12L, "Paula Marín", 1750.80, 22.33445));
        mockData.add(new Cliente(13L, "Ricardo Gil", 512.90, 77.00112));
        mockData.add(new Cliente(14L, "Andrea Fdez", 99.99, 115.66778));
        mockData.add(new Cliente(15L, "Juan Blanco", 2000.00, 5.05050));
        mockData.add(new Cliente(16L, "Isabel Vega", 640.70, 88.99001));
        mockData.add(new Cliente(17L, "Fernando Mota", 130.00, 35.75382));
    }

    /**
     * Retorna todos los registros de clientes.
     */
    public List<Cliente> findAll() {
        return mockData;
    }
    
    /**
     * Busca un cliente por su ID.
     */
    public Optional<Cliente> findById(Long id) {
        return mockData.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Simula la operación de Guardar (Crear o Actualizar).
     * @param cliente El objeto Cliente a guardar.
     * @return El Cliente guardado con ID asignado si es nuevo.
     */
    public Cliente save(Cliente cliente) {
        if (cliente.getId() == 0 || findById(cliente.getId()).isEmpty()) {
            // ⭐ Crear nuevo: Asignar un nuevo ID
            Long newId = idGenerator.getAndIncrement();
            Cliente newCliente = new Cliente(newId, cliente.getNombre(), cliente.getLatitude(), cliente.getLongitude());
            mockData.add(newCliente);
            return newCliente;
        } else {
            // ⭐ Actualizar existente: Sobreescribir el objeto
            int index = -1;
            for (int i = 0; i < mockData.size(); i++) {
                if (mockData.get(i).getId().equals(cliente.getId())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                mockData.set(index, cliente);
                return cliente;
            }
        }
        return cliente; // Retorna el objeto original si falla la actualización
    }

    /**
     * Simula la eliminación de un cliente por ID.
     * @param id El ID del cliente a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean delete(Long id) {
        return mockData.removeIf(c -> c.getId().equals(id));
    }
    
    /**
     * Simula la búsqueda de clientes por nombre (para la funcionalidad de búsqueda).
     * @param query El texto a buscar en el campo nombre.
     * @return Lista de clientes que coinciden.
     */
    public List<Cliente> findByNombre(String query) {
        if (query == null || query.trim().isEmpty()) {
            return findAll();
        }
        String lowerCaseQuery = query.toLowerCase();
        return mockData.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(lowerCaseQuery))
                .toList();
    }
    
    // NOTA: En un entorno de producción, aquí agregarías métodos para:
    // - findPaginated(int page, int size)
    // - countRecords()
}