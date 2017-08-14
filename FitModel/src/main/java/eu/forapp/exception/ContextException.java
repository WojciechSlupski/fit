package eu.forapp.exception;

import java.util.Date;

public class ContextException {
	private String key;
	private String value;
	
	public ContextException(String key, String value){
		if(key == null)
			this.key = "";
		else
			this.key = key;
		this.value = value;
	}
	
	public static ContextException get(String key, int value){
		Integer intValue = value;
		return new ContextException( key, intValue.toString()); 
	}

	public static ContextException get(String key, Integer value){
		return new ContextException( key, value.toString()); 
	}

	public static ContextException get(String key, Date value){
		return new ContextException( key, value.toString()); 
	}

	public static ContextException get(String key, boolean value){
		Boolean booleanValue = value;
		return new ContextException( key, booleanValue.toString()); 
	}
	
	public static ContextException get(Exception key){
		return new ContextException( key.toString(), key.getMessage()); 
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
