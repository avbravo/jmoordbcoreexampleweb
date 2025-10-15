/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.graph;

/**
 *
 * @author avbravo
 */


import com.jmoordb.core.ui.Tag;
import com.jmoordb.core.ui.WebComponent;
import fish.payara.model.Producto;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSalesChart implements WebComponent {
    private final List<Producto> productList;
    private final String chartId = "salesChartCanvas";

    public ProductSalesChart(List<Producto> productList) {
        this.productList = productList;
    }

    @Override
    public String render() {
        // 1. Extraer datos para Chart.js
        String labels = productList.stream()
                .map(p -> "'" + p.getProducto() + "'")
                .collect(Collectors.joining(","));

        String data = productList.stream()
                .map(p -> p.getCantidad().toString())
                .collect(Collectors.joining(","));

        // 2. Generar el contenedor HTML para la gráfica
        Tag chartHtml = new Tag("div").withClass("chart-container")
                .withChild(new Tag("canvas").withAttribute("id", chartId));

        // 3. Generar el script de inicialización
        // Se envuelve en DOMContentLoaded para asegurar que el canvas exista
        String script = new StringBuilder()
                .append("<script>")
                .append("document.addEventListener('DOMContentLoaded', function() {")
                .append("  createChart('").append(chartId).append("', ")
                .append("              'bar', ") // Tipo de gráfica
                .append("              'Unidades Vendidas', ")
                .append("              [").append(labels).append("], ")
                .append("              [").append(data).append("]);")
                .append("});")
                .append("</script>")
                .toString();

        return chartHtml.render() + script;
    }
}