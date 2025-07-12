package com.dex.service;

import java.util.Map;

public interface ConfigurationService {
	 String getConfiguration(String parameter);
	    
	    Map<Integer, String> getStateMap();
	    
	    Map<String, Map<Integer, String>> getReferenceValueMap();
	    
	    String getReferenceValue(int parameter);
}
