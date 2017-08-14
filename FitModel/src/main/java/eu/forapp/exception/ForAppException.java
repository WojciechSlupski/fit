package eu.forapp.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Wojtek
 */
public class ForAppException extends Exception {
	private static final long serialVersionUID = 1L;
	private final List<ContextException> context = new ArrayList<>();

    public ForAppException(String message){
        super(message);
    }

    public ForAppException(String message, ContextException contextException){
        super(message);
        context.add(contextException);
    }
    
    public ForAppException(Exception exception, String message){
        super(message, exception);
    }
    
    public ForAppException(Exception exception, String message, ContextException contextException){
        super(message, exception);
        context.add(contextException);
    }

    public ForAppException(Exception exception, String message, List<ContextException> contextExceptions){
        super(message, exception);
        context.addAll(contextExceptions);
    }
    
	public List<ContextException> getContext() {
		return context;
	}
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getMessage());
        sb.append(" [");
        if(getCause() != null){
            sb.append(getCause().getMessage());
        }
        //sb.append(" ");
        //sb.append(this.getClass().getSimpleName());
        sb.append("]");
        return sb.toString();
    }
}
