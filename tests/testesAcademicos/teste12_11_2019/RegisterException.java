package testesAcademicos.teste12_11_2019;


/**
 * Created by msousa on 9/24/2019.
 */
public class RegisterException extends Exception {

    public RegisterException(String msg) {
        super( msg );
    }

    public RegisterException( ) {
        super( "Erro nas datas dos registos" );
    }
}
