package Excepciones;

public class ExceptionInConvertion extends RuntimeException{
    private String mensaje;


    public ExceptionInConvertion(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return this.mensaje;
    }
}
