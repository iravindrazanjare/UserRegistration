package com.dex.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.dex.dao.ConfigurationDao;
import com.dex.queries.ConfigurationQueries;


@Repository
public class ConfigurationDaoImpl implements ConfigurationDao
{
    @Autowired
    private JdbcTemplate jdbctemplate;
    
    @Override
    public Map<String, String> getConfigurations() { 
    	
    return (Map<String, String>)jdbctemplate.query(ConfigurationQueries.FETCH_CONFIGURATIONS, new ResultSetExtractor<Map<String, String>>() {
		@Override
		public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
			 Map<String, String> resMap = new HashMap<String, String>();
	            while (rs.next()) {
	                resMap.put(rs.getString(1), rs.getString(2));
	            }
	            return resMap;
		}
    });}
    
	@Override
    public Map<Integer, String> getStateListMap() {
    	 return (Map<Integer, String>)jdbctemplate.query(ConfigurationQueries.FETCH_STATES_LIST, new ResultSetExtractor<Map<Integer, String>>() {
             public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                 Map<Integer, String> stateMap = new HashMap<Integer, String>();
                 while (rs.next()) {
                     stateMap.put(rs.getInt(1), rs.getString(2));
                 }
                 return stateMap;
             }
         });
    }
    
    @Override
    public Map<String, Map<Integer, String>> getReferenceValueMap() {
    	Map<String, Map<Integer, String>> keyMap = new LinkedHashMap<String, Map<Integer, String>>();
        List<String> refName = (List<String>)jdbctemplate.query(ConfigurationQueries.FETCH_REFERENCE_VALUE_MASTER, new ResultSetExtractor<List<String>>() {
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<String> refName = new ArrayList<String>();
                while (rs.next()) {
                    refName.add(rs.getString("dex_reference_name"));
                }
                return refName;
            }
        });
        for (String name : refName) {
            @SuppressWarnings("deprecation")
			Map<Integer, String> refMap = (Map<Integer, String>)jdbctemplate.query(ConfigurationQueries.GET_KEY_VALUE_MASTER, new Object[] { name }, new ResultSetExtractor<Map<Integer, String>>() {
                public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    Map<Integer, String> refMap = new LinkedHashMap<Integer, String>();
                    while (rs.next()) {
                        refMap.put(rs.getInt("dex_reference_pk"), rs.getString("dex_reference_value"));
                    }
                    return refMap;
                }
            });
            keyMap.put(name, refMap);
        }
        return keyMap;
    }
}
