/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fish.payara.controller.formulario;

/**
 *
 * @author avbravo
 */
// Usando tipos de datos apropiados (String para nhrc y foto IDs)
// Importaciones de Jakarta EE:
import jakarta.json.bind.annotation.JsonbProperty;
import java.util.ArrayList;
import java.util.List;


public class AnalisisViewModel {


   @JsonbProperty("nhrc")
    private String nhrc;
    
   @JsonbProperty("numeromuestra")
    private String numeromuestra;
    
   @JsonbProperty("fechaRegistro")
    private String fechaRegistroString; // <-- Cambiado a String

   @JsonbProperty("edad")
    private int edad;
  

   @JsonbProperty("motivo")
    private String motivo;

   @JsonbProperty("otroMotivo")
    private String otroMotivo;
    
   @JsonbProperty("diagnostico")
    private String diagnostico;
    
   @JsonbProperty("pcritsStatus")
    private List<String> pcritsStatus = new ArrayList<>();
    
   @JsonbProperty("ctPcrPositiva")
    private String ctPcrPositiva;
    
   @JsonbProperty("gramEtiquetado")
    private List<String> gramEtiquetado = new ArrayList<>();
    
   @JsonbProperty("leucocitosPresencia")
    private String leucocitosPresencia;
   @JsonbProperty("otroLeucocitoValor")
    private String otroLeucocitoValor;
   @JsonbProperty("levadurasPresencia")
    private String levadurasPresencia;
   @JsonbProperty("otroLevaduraValor")
    private String otroLevaduraValor;
    
   @JsonbProperty("epitelialesPresencia")
    private String epitelialesPresencia;
    
   @JsonbProperty("otroEpitelialValor")
    private String otroEpitelialValor;
   @JsonbProperty("nugentScore")
    private String nugentScore;
   @JsonbProperty("resultadoCultivo")
    private List<String> resultadoCultivo = new ArrayList<>();
    
    
@JsonbProperty("imagen11Cultivo")
private String imagen11Cultivo;


    
@JsonbProperty("recuentoHongos")
private String recuentoHongos;
    
@JsonbProperty("calidadTincion")
private String calidadTincion;

@JsonbProperty("cultivoOrinaResultado")
private String cultivoOrinaResultado;

@JsonbProperty("discrepancia")
private boolean discrepancia;
//@JsonbProperty("discrepancia")
//private String discrepancia;
    
    
    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 1 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId1")
    private String fileId1;

   @JsonbProperty("fileRemoteId1")
    private String fileRemoteId1;

   @JsonbProperty("originalFileName1")
    private String originalFileName1;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 2 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId2")
    private String fileId2;

   @JsonbProperty("fileRemoteId2")
    private String fileRemoteId2;

   @JsonbProperty("originalFileName2")
    private String originalFileName2;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 3 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId3")
    private String fileId3;

   @JsonbProperty("fileRemoteId3")
    private String fileRemoteId3;

   @JsonbProperty("originalFileName3")
    private String originalFileName3;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 4 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId4")
    private String fileId4;

   @JsonbProperty("fileRemoteId4")
    private String fileRemoteId4;

   @JsonbProperty("originalFileName4")
    private String originalFileName4;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 5 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId5")
    private String fileId5;

   @JsonbProperty("fileRemoteId5")
    private String fileRemoteId5;

   @JsonbProperty("originalFileName5")
    private String originalFileName5;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 6 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId6")
    private String fileId6;

   @JsonbProperty("fileRemoteId6")
    private String fileRemoteId6;

   @JsonbProperty("originalFileName6")
    private String originalFileName6;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 7 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId7")
    private String fileId7;

   @JsonbProperty("fileRemoteId7")
    private String fileRemoteId7;

   @JsonbProperty("originalFileName7")
    private String originalFileName7;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 8 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId8")
    private String fileId8;

   @JsonbProperty("fileRemoteId8")
    private String fileRemoteId8;

   @JsonbProperty("originalFileName8")
    private String originalFileName8;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 9 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId9")
    private String fileId9;

   @JsonbProperty("fileRemoteId9")
    private String fileRemoteId9;

   @JsonbProperty("originalFileName9")
    private String originalFileName9;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 10 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId10")
    private String fileId10;

   @JsonbProperty("fileRemoteId10")
    private String fileRemoteId10;

   @JsonbProperty("originalFileName10")
    private String originalFileName10;

    // -----------------------------------------------------------------
    // CAMPOS DE IMAGEN 11 (Coinciden con name="...")
    // -----------------------------------------------------------------
   @JsonbProperty("fileId11")
    private String fileId11;

   @JsonbProperty("fileRemoteId11")
    private String fileRemoteId11;

   @JsonbProperty("originalFileName11")
    private String originalFileName11;

    // ***************************************************************
    // 1. CONSTRUCTOR POR DEFECTO (NECESARIO para JAX-RS/Payara)
    // ***************************************************************
    public AnalisisViewModel() {
        // Constructor por defecto
    }

    // ***************************************************************
    // 2. GETTERS Y SETTERS (NECESARIOS para inyección por @BeanParam)
    // ***************************************************************
    // Getters y Setters para nhrc
    public String getNhrc() {
        return nhrc;
    }

    public void setNhrc(String nhrc) {
        this.nhrc = nhrc;
    }

    

//    // Getters y Setters para edad (Ahora Integer)
//    public Integer getEdad() { return edad; }
//    public void setEdad(Integer edad) { this.edad = edad; }
    // Getters y Setters para Imagen 1
    public String getFileId1() {
        return fileId1;
    }

    public void setFileId1(String fileId1) {
        this.fileId1 = fileId1;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFileRemoteId1() {
        return fileRemoteId1;
    }

    public void setFileRemoteId1(String fileRemoteId1) {
        this.fileRemoteId1 = fileRemoteId1;
    }

    public String getOriginalFileName1() {
        return originalFileName1;
    }

    public void setOriginalFileName1(String originalFileName1) {
        this.originalFileName1 = originalFileName1;
    }

    // Getters y Setters para Imagen 2
    public String getFileId2() {
        return fileId2;
    }

    public void setFileId2(String fileId2) {
        this.fileId2 = fileId2;
    }

    public String getFileRemoteId2() {
        return fileRemoteId2;
    }

    public void setFileRemoteId2(String fileRemoteId2) {
        this.fileRemoteId2 = fileRemoteId2;
    }

    public String getOriginalFileName2() {
        return originalFileName2;
    }

    public void setOriginalFileName2(String originalFileName2) {
        this.originalFileName2 = originalFileName2;
    }

    public String getFileId3() {
        return fileId3;
    }

    public void setFileId3(String fileId3) {
        this.fileId3 = fileId3;
    }

    public String getFileRemoteId3() {
        return fileRemoteId3;
    }

    public void setFileRemoteId3(String fileRemoteId3) {
        this.fileRemoteId3 = fileRemoteId3;
    }

    public String getOriginalFileName3() {
        return originalFileName3;
    }

    public void setOriginalFileName3(String originalFileName3) {
        this.originalFileName3 = originalFileName3;
    }

    public String getFileId4() {
        return fileId4;
    }

    public void setFileId4(String fileId4) {
        this.fileId4 = fileId4;
    }

    public String getFileRemoteId4() {
        return fileRemoteId4;
    }

    public void setFileRemoteId4(String fileRemoteId4) {
        this.fileRemoteId4 = fileRemoteId4;
    }

    public String getOriginalFileName4() {
        return originalFileName4;
    }

    public void setOriginalFileName4(String originalFileName4) {
        this.originalFileName4 = originalFileName4;
    }

    public String getFileId5() {
        return fileId5;
    }

    public void setFileId5(String fileId5) {
        this.fileId5 = fileId5;
    }

    public String getFileRemoteId5() {
        return fileRemoteId5;
    }

    public void setFileRemoteId5(String fileRemoteId5) {
        this.fileRemoteId5 = fileRemoteId5;
    }

    public String getOriginalFileName5() {
        return originalFileName5;
    }

    public void setOriginalFileName5(String originalFileName5) {
        this.originalFileName5 = originalFileName5;
    }

    public String getFileId6() {
        return fileId6;
    }

    public void setFileId6(String fileId6) {
        this.fileId6 = fileId6;
    }

    public String getFileRemoteId6() {
        return fileRemoteId6;
    }

    public void setFileRemoteId6(String fileRemoteId6) {
        this.fileRemoteId6 = fileRemoteId6;
    }

    public String getOriginalFileName6() {
        return originalFileName6;
    }

    public void setOriginalFileName6(String originalFileName6) {
        this.originalFileName6 = originalFileName6;
    }

    public String getFileId7() {
        return fileId7;
    }

    public void setFileId7(String fileId7) {
        this.fileId7 = fileId7;
    }

    public String getFileRemoteId7() {
        return fileRemoteId7;
    }

    public void setFileRemoteId7(String fileRemoteId7) {
        this.fileRemoteId7 = fileRemoteId7;
    }

    public String getOriginalFileName7() {
        return originalFileName7;
    }

    public void setOriginalFileName7(String originalFileName7) {
        this.originalFileName7 = originalFileName7;
    }

    public String getFileId8() {
        return fileId8;
    }

    public void setFileId8(String fileId8) {
        this.fileId8 = fileId8;
    }

    public String getFileRemoteId8() {
        return fileRemoteId8;
    }

    public void setFileRemoteId8(String fileRemoteId8) {
        this.fileRemoteId8 = fileRemoteId8;
    }

    public String getOriginalFileName8() {
        return originalFileName8;
    }

    public void setOriginalFileName8(String originalFileName8) {
        this.originalFileName8 = originalFileName8;
    }

    public String getFileId9() {
        return fileId9;
    }

    public void setFileId9(String fileId9) {
        this.fileId9 = fileId9;
    }

    public String getFileRemoteId9() {
        return fileRemoteId9;
    }

    public void setFileRemoteId9(String fileRemoteId9) {
        this.fileRemoteId9 = fileRemoteId9;
    }

    public String getOriginalFileName9() {
        return originalFileName9;
    }

    public void setOriginalFileName9(String originalFileName9) {
        this.originalFileName9 = originalFileName9;
    }

    public String getFileId10() {
        return fileId10;
    }

    public void setFileId10(String fileId10) {
        this.fileId10 = fileId10;
    }

    public String getFileRemoteId10() {
        return fileRemoteId10;
    }

    public void setFileRemoteId10(String fileRemoteId10) {
        this.fileRemoteId10 = fileRemoteId10;
    }

    public String getOriginalFileName10() {
        return originalFileName10;
    }

    public void setOriginalFileName10(String originalFileName10) {
        this.originalFileName10 = originalFileName10;
    }

    public String getFileId11() {
        return fileId11;
    }

    public void setFileId11(String fileId11) {
        this.fileId11 = fileId11;
    }

    public String getFileRemoteId11() {
        return fileRemoteId11;
    }

    public void setFileRemoteId11(String fileRemoteId11) {
        this.fileRemoteId11 = fileRemoteId11;
    }

    public String getOriginalFileName11() {
        return originalFileName11;
    }

    public void setOriginalFileName11(String originalFileName11) {
        this.originalFileName11 = originalFileName11;
    }

    public String getNumeromuestra() {
        return numeromuestra;
    }

    public void setNumeromuestra(String numeromuestra) {
        this.numeromuestra = numeromuestra;
    }
 
    // <editor-fold defaultstate="collapsed" desc="set/get">
    
    
       public String getFechaRegistroString() {
        return fechaRegistroString;
    }

    public void setFechaRegistroString(String fechaRegistroString) {
        this.fechaRegistroString = fechaRegistroString;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
    
    
// </editor-fold>

    public String getOtroMotivo() {
        return otroMotivo;
    }

    public void setOtroMotivo(String otroMotivo) {
        this.otroMotivo = otroMotivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<String> getPcritsStatus() {
        return pcritsStatus;
    }

    public void setPcritsStatus(List<String> pcritsStatus) {
        this.pcritsStatus = pcritsStatus;
    }

    public String getCtPcrPositiva() {
        return ctPcrPositiva;
    }

    public void setCtPcrPositiva(String ctPcrPositiva) {
        this.ctPcrPositiva = ctPcrPositiva;
    }

    public List<String> getGramEtiquetado() {
        return gramEtiquetado;
    }

    public void setGramEtiquetado(List<String> gramEtiquetado) {
        this.gramEtiquetado = gramEtiquetado;
    }

    public String getLeucocitosPresencia() {
        return leucocitosPresencia;
    }

    public void setLeucocitosPresencia(String leucocitosPresencia) {
        this.leucocitosPresencia = leucocitosPresencia;
    }

    public String getOtroLeucocitoValor() {
        return otroLeucocitoValor;
    }

    public void setOtroLeucocitoValor(String otroLeucocitoValor) {
        this.otroLeucocitoValor = otroLeucocitoValor;
    }

    public String getLevadurasPresencia() {
        return levadurasPresencia;
    }

    public void setLevadurasPresencia(String levadurasPresencia) {
        this.levadurasPresencia = levadurasPresencia;
    }

    public String getOtroLevaduraValor() {
        return otroLevaduraValor;
    }

    public void setOtroLevaduraValor(String otroLevaduraValor) {
        this.otroLevaduraValor = otroLevaduraValor;
    }

    public String getEpitelialesPresencia() {
        return epitelialesPresencia;
    }

    public void setEpitelialesPresencia(String epitelialesPresencia) {
        this.epitelialesPresencia = epitelialesPresencia;
    }

    public String getOtroEpitelialValor() {
        return otroEpitelialValor;
    }

    public void setOtroEpitelialValor(String otroEpitelialValor) {
        this.otroEpitelialValor = otroEpitelialValor;
    }

    public String getNugentScore() {
        return nugentScore;
    }

    public void setNugentScore(String nugentScore) {
        this.nugentScore = nugentScore;
    }

    public List<String> getResultadoCultivo() {
        return resultadoCultivo;
    }

    public void setResultadoCultivo(List<String> resultadoCultivo) {
        this.resultadoCultivo = resultadoCultivo;
    }

    public String getImagen11Cultivo() {
        return imagen11Cultivo;
    }

    public void setImagen11Cultivo(String imagen11Cultivo) {
        this.imagen11Cultivo = imagen11Cultivo;
    }

    

    public String getRecuentoHongos() {
        return recuentoHongos;
    }

    public void setRecuentoHongos(String recuentoHongos) {
        this.recuentoHongos = recuentoHongos;
    }

    public String getCalidadTincion() {
        return calidadTincion;
    }

    public void setCalidadTincion(String calidadTincion) {
        this.calidadTincion = calidadTincion;
    }

    public String getCultivoOrinaResultado() {
        return cultivoOrinaResultado;
    }

    public void setCultivoOrinaResultado(String cultivoOrinaResultado) {
        this.cultivoOrinaResultado = cultivoOrinaResultado;
    }

    public boolean getDiscrepancia() {
        return discrepancia;
    }

    public void setDiscrepancia(boolean discrepancia) {
        this.discrepancia = discrepancia;
    }

// public boolean isImagen11CultivoMarcada() {
//        // Comprueba si el String no es null y si es el valor esperado ("si")
//        return "si".equals(this.imagen11Cultivo);
//    }
    
    
}
