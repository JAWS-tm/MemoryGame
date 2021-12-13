package utils;
/**
 * Class qui gère quelques exceptions afin de ne pas faire crash le jeu en cas de probleme 
 *
 */
public class AppException extends Exception{
	/**
	 * Liste des différents types d'erreurs
	 */
    public enum Type {
        GLOBAL_ERROR,
        VIEW_LOADING_FAILED

    }

    private Type errorType = Type.GLOBAL_ERROR;
    private String errorInfo = "";

    /**
     * Constructeur de {@link AppException}
     * @param type le type de l'erreur (voir {@link Type})
     */
    public AppException(Type type){
        this.errorType = type;
    }

    /**
     * Constructeur de {@link AppException}
     * @param type le type de l'erreur (voir {@link Type})
     * @param info description de l'erreur
     */
    public AppException(Type type, String info){
        this(type);
        this.errorInfo = info;
    }

    public Type getErrorType() {
        return errorType;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
