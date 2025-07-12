package com.dex.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dex.dao.ConfigurationDao;
import com.dex.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService
{
    @Autowired
    private ConfigurationDao configDao;
    private Map<String, String> configurations;
    private Map<Integer, String> stateMap;
    private Map<String, Map<Integer, String>> referenceValue;
    private Map<Integer, String> referenceKeyValue;
    
    public ConfigurationServiceImpl() {
        referenceKeyValue = new HashMap<Integer, String>();
    }
    
    @PostConstruct
    private void initialize() {
        configurations = configDao.getConfigurations();
        stateMap = configDao.getStateListMap();
        referenceValue = configDao.getReferenceValueMap();
    }
    
    @Override
    public String getConfiguration(String parameter) {
        return configurations.get(parameter);
    }
    
    @Override
    public Map<Integer, String> getStateMap() {
        return stateMap;
    }
    
    @Override
    public Map<String, Map<Integer, String>> getReferenceValueMap() {
        return referenceValue;
    }
    
    @Override
    public String getReferenceValue(int parameter) {
        for (Map.Entry<String, Map<Integer, String>> outerMap : referenceValue.entrySet()) {
            referenceKeyValue = outerMap.getValue();
        }
        return referenceKeyValue.get(parameter);
    }
}
