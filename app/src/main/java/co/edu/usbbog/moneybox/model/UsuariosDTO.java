package co.edu.usbbog.moneybox.model;

public class UsuariosDTO {

    private String id;
    private String nombre;
    //    private String telefono;
//    private String email;
    private String usuario;
//    private String clave;


    public UsuariosDTO(String id, String nombre, String usuario) {
        this.id = id;
        this.nombre = nombre;
//        this.telefono = telefono;
//        this.email = email;
        this.usuario = usuario;
//        this.clave = clave;
    }

    public String getId() {
        return id;
    }

    public String setId(String id) {
        this.id = id;
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String setNombre(String nombre) {
        this.nombre = nombre;
        return nombre;
    }

    //
//    public String getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
    public String getUsuario() {
        return usuario;
    }

    public String setUsuario(String usuario) {
        this.usuario = usuario;
        return usuario;
    }
//
//    public String getClave() {
//        return clave;
//    }
//
//    public void setClave(String clave) {
//        this.clave = clave;
//    }

    @Override
    public String toString() {
        return "UsuariosDTO{" +
                "id=" + id;
    }

    public UsuariosDTO() {
    }
}
