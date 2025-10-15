/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.crud.form;

/**
 *
 * @author avbravo
 */
import fish.payara.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ClienteService {

    private final List<Cliente> mockData = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(18L);

    public ClienteService() {
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

    public List<Cliente> findAll() {
        return mockData;
    }

    public long count() {
        return mockData.size();
    }

    public Optional<Cliente> findById(Long id) {
        return mockData.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Cliente save(Cliente cliente) {
        if (cliente.getId() == 0L) {
            Long newId = idGenerator.getAndIncrement();
            cliente.setId(newId);
            this.mockData.add(cliente);
            return cliente;
        } else {
            return cliente;
        }
    }

    // El resto de métodos CRUD (save, delete, findByNombre) se omiten por espacio, 
    // pero se asume su existencia como en la respuesta anterior.
}
