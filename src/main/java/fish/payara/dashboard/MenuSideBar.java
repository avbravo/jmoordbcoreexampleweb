/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.dashboard;

/**
 *
 * @author avbravo
 */
import com.jmoordb.core.ui.icon.FontAwesomeIcon;
import com.jmoordb.core.ui.jettra.JettraView;
import com.jmoordb.core.ui.menu.MenuLink;
import com.jmoordb.core.ui.menu.MenuUIUtil;
import com.jmoordb.core.ui.model.WebModelSession;
import fish.payara.controller.ClienteController;
import fish.payara.controller.DashboardController;
import fish.payara.view.FetchView;
import fish.payara.view.ImageView;
import fish.payara.view.ModalView;
import fish.payara.view.ProfileView;
import fish.payara.view.VentasView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuSideBar extends MenuUIUtil{

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
     * https://flowbite.com/icons/
     */
    

        
    public static Map<String, List<MenuLink>> getSidebarSections(
            String currentController,
             WebModelSession webModelSession) {
        
        
        
     var   rol = webModelSession.getUserRol().toUpperCase();
        // Usamos LinkedHashMap para mantener el orden de inserción de las secciones.
        Map<String, List<MenuLink>> sections = new LinkedHashMap<>();

        // =========================================================
        // A. SECCIÓN: NAVEGACIÓN PRINCIPAL
        // =========================================================
        List<MenuLink> mainLinks = new ArrayList<>();

        // 1. Dashboard (Disponible para todos)
      
        mainLinks.add(new MenuLink("Dashboard", "/api/dashboard", isActive(currentController,DashboardController.class), FontAwesomeIcon.FA_S_TACHOMETER_ALT));

        // 2. Perfil (Disponible para todos)
       
 
        mainLinks.add(new MenuLink("Profile View", "/api/profile-view", isActive(currentController,ProfileView.class),  FontAwesomeIcon.FA_S_USER_CIRCLE));

        sections.put("MAIN NAVIGATION", mainLinks);

        // =========================================================
        // B. SECCIÓN: GESTIÓN (Solo visible para ADMIN)
        // =========================================================
        if (rol.equals("ADMIN") || rol.equals("SUPERVISOR")) {
            List<MenuLink> adminLinks = new ArrayList<>();
            // 1. Usuarios
          
            adminLinks.add(new MenuLink("Usuarios", "/admin/users", isActive(currentController,ClienteController.class), FontAwesomeIcon.FA_S_USERS_COG));

            // 2. Configuración

            adminLinks.add(new MenuLink("Configuración", "/admin/settings", isActive(currentController,ClienteController.class),FontAwesomeIcon.FA_S_USERS_COG));
         //   adminLinks.add(new MenuLink("Configuración", "/admin/settings", isActive(currentController,ClienteController.class),FlowbiteIconSvg.ADD_COLUMN_AFTER_OUTLINE));


            sections.put("MANAGEMENT", adminLinks);
        }

        // =========================================================
        // C. SECCIÓN: REPORTES (Disponible para ADMIN y USER)
        // =========================================================
        if (rol.equals("ADMIN") || rol.equals("USER") || rol.equals("SUPERVISOR")) {
            List<MenuLink> reportLinks = new ArrayList<>();

            // 1. Ventas
       
//            reportLinks.add(new MenuLink("Ventas", "/reports/sales", isActive(currentController,ClienteController.class), "fas fa-chart-line"));
       
             reportLinks.add(new MenuLink("Ventas View", "/api/ventas-view", isActive(currentController,VentasView.class),  FontAwesomeIcon.FA_S_CHART_LINE));
            // 2. Inventario

//            reportLinks.add(new MenuLink("Inventario", "/reports/inventory",  isActive(currentController,ClienteController.class), "fas fa-warehouse"));
            reportLinks.add(new MenuLink("Inventario", "/reports/inventory",  isActive(currentController,ClienteController.class), FontAwesomeIcon.FA_S_WAREHOUSE));
            // 3. Clientes

         
            reportLinks.add(new MenuLink("Cliente", "/clientes",isActive(currentController,ClienteController.class),FontAwesomeIcon.FA_S_PERSON));

        
     
  

            reportLinks.add(new MenuLink("JettraView", "/api/jettra-view",isActive(currentController,JettraView.class), FontAwesomeIcon.FA_S_PLUG));

            reportLinks.add(new MenuLink("Fetch View", "/api/fetch-view", isActive(currentController,FetchView.class), FontAwesomeIcon.FA_S_PLUG));
            
            

            reportLinks.add(new MenuLink("Modal View", "/api/modal-view", isActive(currentController,ModalView.class), FontAwesomeIcon.FA_S_WINDOW_RESTORE));
         

            reportLinks.add(new MenuLink("Image View", "/api/image-view", isActive(currentController,ImageView.class),  FontAwesomeIcon.FA_S_IMAGE));
            
           
            
 

            sections.put("REPORTES", reportLinks);
        }

        return sections;
    }
}
