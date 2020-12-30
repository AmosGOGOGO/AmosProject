package com.ai.rti.ic.grp.ci.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ai.rti.ic.grp.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:redis_config.properties")
public class RedisConfig {

	@Value("${redis.ip}")
	private  String host;
	
	@Value("${redis.password}")
	private  String passwd;
	
	@Value("${redis.maxIdle}")
	private  int maxIdle;
	
	@Value("${redis.maxTotal}")
	private  int maxTotal;
	
	@Value("${redis.maxWaitMillis}")
	private  int maxWaitMillis;
	
	@Value("${redis.testOnBorrow}")
	private  boolean testOnBorrow;
	
	@Value("${redis.timeOut}")
	private  int timeOut;

	@Value("${redis.maxAttempts}")
	private  int maxAttempts;
	
	
	@Bean
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxIdle(maxIdle);
		config.setMaxTotal(maxTotal);
		config.setMaxWaitMillis(maxWaitMillis);
		config.setTestOnBorrow(testOnBorrow);
		return config;
	}
	
//	@Bean
//	public JedisPool getJedisPool() {
//		JedisPoolConfig config=getRedisConfig();
//		config.setMaxIdle(maxIdle);
//		config.setMaxTotal(maxTotal);
//		config.setBlockWhenExhausted(blockWhenExhausted);
//		config.setTestOnBorrow(testOnBorrow);
//		config.setMaxWaitMillis(maxWaitMillis);
//		JedisPool jedisPool=new JedisPool(config,host,port,timeout,passwd);
//		return jedisPool;
//
//	}
	
	@Bean
	public JedisCluster getJedisCluster() {
		Set<HostAndPort> set = new HashSet<>();
		if(StringUtil.isNotEmpty(host)) {
			JSONArray list = JSONArray.parseArray(host);
			
			for(Object o : list) {
				JSONObject json = (JSONObject) o;
				set.add(new HostAndPort(json.getString("host"), json.getInteger("port")));
			}
		}
		
		JedisCluster jedisCluster = new JedisCluster(set, timeOut, timeOut, maxAttempts, passwd,  getRedisConfig());
		
		return jedisCluster;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
