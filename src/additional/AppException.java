package additional;

public class AppException extends Exception{

    public enum Type {
        GLOBAL_ERROR,
        VIEW_LOADING_FAILED

    }
    private Type errorType = Type.GLOBAL_ERROR;
    private String errorInfo = "";

    public AppException(Type type){
        this.errorType = type;
    }

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
