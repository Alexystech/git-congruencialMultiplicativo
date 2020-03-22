package test;

public class ValidacionesException {
    public ValidacionesException(){}

    public void validarValor(int valor) throws CustomException{
        if (valor < 1){
            throw new CustomException("el valor no puede ser negativo o neutro");
        }
        if (valor % 2 == 0) {
            throw new CustomException("la semilla debe ser impar");
        }
    }
}
