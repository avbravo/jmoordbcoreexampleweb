// --- Función de utilería para aplicar la configuración global del tema ---
function applyChartTheme(isDarkMode) {
    const textColor = isDarkMode ? '#FFFFFF' : '#212529';
    const gridColor = isDarkMode ? 'rgba(255, 255, 255, 0.15)' : 'rgba(0, 0, 0, 0.1)';

    // ⭐ 1. Establecer el color de la fuente globalmente
    Chart.defaults.color = textColor; 
    
    // ⭐ 2. Establecer el color de la fuente para el tooltip (etiquetas que aparecen al pasar el mouse)
    Chart.defaults.plugins.tooltip.titleColor = textColor;
    Chart.defaults.plugins.tooltip.bodyColor = textColor;
    
    // ⭐ 3. Establecer los colores de la cuadrícula globalmente
    Chart.defaults.scale.grid.color = gridColor;
    Chart.defaults.scale.ticks.color = textColor;
}

/**
 * Inicializa un gráfico de Chart.js
 * ... (Parámetros sin cambios) ...
 */
function createChart(canvasId, type, label, labels, data) {
    const ctx = document.getElementById(canvasId);
    
    // Llamar a la función de tema antes de crear el gráfico
    const isDarkMode = document.body.classList.contains('dark-mode');
    applyChartTheme(isDarkMode); // Aplicar tema al momento de creación

    const primaryColor = isDarkMode ? '#4dc9f7' : '#36a2eb';
    const secondaryColor = isDarkMode ? '#ff6384' : '#ff9f40';
    
    if (ctx) {
        new Chart(ctx, {
            type: type,
            data: {
                labels: labels,
                datasets: [{
                    label: label,
                    data: data,
                    backgroundColor: primaryColor,
                    borderColor: primaryColor,
                    borderWidth: 1,
                    hoverBackgroundColor: type === 'bar' ? secondaryColor : primaryColor,
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                // Ya no necesitamos definir 'scales' ni 'plugins.legend' porque se usan los defaults globales.
            }
        });
    }
}