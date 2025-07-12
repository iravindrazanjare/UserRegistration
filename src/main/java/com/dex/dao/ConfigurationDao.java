package com.dex.dao;

import java.util.Map;

public interface ConfigurationDao
{
    Map<String, String> getConfigurations();
    
    Map<Integer, String> getStateListMap();
    
    Map<String, Map<Integer, String>> getReferenceValueMap();
}
