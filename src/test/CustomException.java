package test;

public class CustomException extends Throwable{
    private static final long serialVersionUID = 700L;

    public CustomException(String mesaje){
        super(mesaje);
    }
}
