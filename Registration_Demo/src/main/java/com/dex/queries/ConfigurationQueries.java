package com.dex.queries;

public class ConfigurationQueries {
	 public static String FETCH_CONFIGURATIONS;
	    public static String FETCH_STATES_LIST;
	    public static String FETCH_REFERENCE_VALUE_MASTER;
	    public static String GET_KEY_VALUE_MASTER;
	    
	    static {
	        ConfigurationQueries.FETCH_CONFIGURATIONS = "select dex_param_key, dex_param_value from dex_config_param";
	        ConfigurationQueries.FETCH_STATES_LIST = "select dex_state_pk,dex_state_name from dex_state_master where dex_active_status = 'A'";
	        ConfigurationQueries.FETCH_REFERENCE_VALUE_MASTER = "select dex_reference_name,dex_reference_value from dex_reference_value_master where dex_active_status = 'A'";
	        ConfigurationQueries.GET_KEY_VALUE_MASTER = "select dex_reference_pk,dex_reference_value from dex_reference_value_master where dex_reference_name = ?";
	    }
}
