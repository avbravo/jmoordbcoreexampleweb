/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.dashboard;

/**
 *
 * @author avbravo
 */
import com.jmoordb.core.ui.menu.MenuLink;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuService {

    /**
     * Construye la estructura completa del menú lateral (SideBar) basada en el
     * rol y el controlador actual.
     *
     * @param currentController Nombre del controlador actual (ej.
     * "DashboardController").
     * @param username Nombre del usuario (puede usarse para personalizar,
     * aunque no se usa aquí).
     * @param rol Rol del usuario logeado (ej. "ADMIN", "USER", "GUEST").
     * @return Mapa de secciones y listas de enlaces del menú.
     */
    public static Map<String, List<MenuLink>> getSidebarSections(
            String currentController,
            String username,
            String rol) {

        // Usamos LinkedHashMap para mantener el orden de inserción de las secciones.
        Map<String, List<MenuLink>> sections = new LinkedHashMap<>();

        // =========================================================
        // A. SECCIÓN: NAVEGACIÓN PRINCIPAL
        // =========================================================
        List<MenuLink> mainLinks = new ArrayList<>();

        // 1. Dashboard (Disponible para todos)
        boolean isDashboardActive = currentController.equals("DashboardController");
        mainLinks.add(new MenuLink("Dashboard", "/dashboard", isDashboardActive, "fas fa-tachometer-alt"));

        // 2. Perfil (Disponible para todos)
        boolean isProfileActive = currentController.equals("ProfileController");
        mainLinks.add(new MenuLink("Perfil", "/profile", isProfileActive, "fas fa-user-circle"));

        sections.put("MAIN NAVIGATION", mainLinks);

        // =========================================================
        // B. SECCIÓN: GESTIÓN (Solo visible para ADMIN)
        // =========================================================
        if (rol.equals("ADMIN")) {
            List<MenuLink> adminLinks = new ArrayList<>();
            // 1. Usuarios
            boolean isUsersActive = currentController.equals("UsersController");
            adminLinks.add(new MenuLink("Usuarios", "/admin/users", isUsersActive, "fas fa-users-cog"));

            // 2. Configuración
            boolean isSettingsActive = currentController.equals("SettingsController");
            adminLinks.add(new MenuLink("Configuración", "/admin/settings", isSettingsActive, "fas fa-cogs"));
            
            

            sections.put("MANAGEMENT", adminLinks);
        }

        // =========================================================
        // C. SECCIÓN: REPORTES (Disponible para ADMIN y USER)
        // =========================================================
        if (rol.equals("ADMIN") || rol.equals("USER")) {
            List<MenuLink> reportLinks = new ArrayList<>();

            // 1. Ventas
            boolean isSalesActive = currentController.equals("SalesReportsController");
            reportLinks.add(new MenuLink("Ventas", "/reports/sales", isSalesActive, "fas fa-chart-line"));

            // 2. Inventario
            boolean isInventoryActive = currentController.equals("InventoryReportsController");
            reportLinks.add(new MenuLink("Inventario", "/reports/inventory", isInventoryActive, "fas fa-warehouse"));
            // 3. Clientes
            
            boolean isClienteActive = currentController.equals("ClienteController");
            reportLinks.add(new MenuLink("Cliente", "/clientes", isClienteActive, "fas fa-solid fa-person"));
            
            // 4. ModalTest
            
              boolean isModalDashboardActive = currentController.equals("ModalDashboardController");
            reportLinks.add(new MenuLink("Modal", "/modal", isModalDashboardActive, "fas fa-solid fa-microscope"));

            
              boolean isModalSimpleActive = currentController.equals("ModalSimpleController");
            reportLinks.add(new MenuLink("Modal Simple", "/modal-simple", isModalSimpleActive, "fas fa-solid fa-microscope"));

            
            boolean isModalTestActive = currentController.equals("ModalController");
            reportLinks.add(new MenuLink("Modal Varios", "/modal-test", isInventoryActive, "fas fa-solid fa-microscope"));
            
            
            
          

            
            sections.put("REPORTES", reportLinks);
        }

        return sections;
    }
}
