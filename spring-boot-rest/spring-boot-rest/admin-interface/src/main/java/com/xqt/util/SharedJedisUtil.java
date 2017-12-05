package com.xqt.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * redis 支持分片连接池 工具类
 * 
 * @author Andy
 *	
 */
public class SharedJedisUtil {

	private static final Logger logger = LogManager.getLogger(SharedJedisUtil.class);
	// 读取配置文件
	private static PropertiesUtil UTIL = null;
	static {
		UTIL = new PropertiesUtil("jedis.properties");
	}

	// session默认存在时间：120分钟
	private static final Integer SESSION_DEFAULT_EXPIRE = Integer.valueOf(UTIL
			.readProperty("redis.default.expire"));

	private static final String KEY_PREFIX = "KEY_";
	// 连接池
	//private static JedisPool jedisPool = null;
	private static ShardedJedisPool jedisPool;
	private static List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
	private static String hosts;
	private static int port;
	private static int maxIdle;
	private static int maxTotal;
	private static int maxWaitMillis;
	private static int timeBetweenEvictionRunsMillis;
	private static int minEvictableIdleTimeMillis;
	private static Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");


	static {
		
		//Redis服务器列表
		hosts = UTIL.readProperty("redis.hosts");
		port = Integer.parseInt(UTIL.readProperty("redis.port"));
		maxIdle = Integer.parseInt(UTIL.readProperty("redis.MaxIdle"));
		maxTotal = Integer.parseInt(UTIL.readProperty("redis.pool.maxTotal"));
		maxWaitMillis = Integer.parseInt(UTIL.readProperty("redis.MaxWait"));
		timeBetweenEvictionRunsMillis = Integer.parseInt(UTIL.readProperty("redis.pool.timeBetweenEvictionRunsMillis"));
		minEvictableIdleTimeMillis = Integer.parseInt(UTIL.readProperty("redis.pool.minEvictableIdleTimeMillis"));
		
		if(StringUtils.isNotBlank(hosts)){
			String[] _hosts = hosts.split(",");
			for(String host : _hosts){
				String _host = host.trim();
				if(isIP(_host)){
					shards.add(new JedisShardInfo(_host, port));
				}
			}
		}
		if(!shards.isEmpty()){			
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(maxIdle);
			config.setMaxTotal(maxTotal);
			config.setMaxWaitMillis(maxWaitMillis);
			config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
			jedisPool = new ShardedJedisPool(config, shards);
		}
	}

	private static Boolean isIP(String ip){
		 Matcher matcher = pattern.matcher(ip);
		 return matcher.matches();
	}
	
	/**
	 * 向缓存中设置对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(String key, Object value) {
		ShardedJedis jedis  = null;
		try {

			jedis = jedisPool.getResource();
			jedis.set(KEY_PREFIX + key, SerializeUtil.serialize(value));
			jedis.expire(KEY_PREFIX + key, SESSION_DEFAULT_EXPIRE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 向缓存中设置对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean setex(String key, int expire, Object value) {
		ShardedJedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(KEY_PREFIX + key, SerializeUtil.serialize(value));
			jedis.expire(KEY_PREFIX + key, expire);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 删除缓存中得对象，根据key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean del(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(KEY_PREFIX + key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}


	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Object value = SerializeUtil.unserialize(jedis.get(KEY_PREFIX + key));
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T getObject(String key, Class<T> clazz) {
		ShardedJedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			T value = SerializeUtil.unserializeObject(
					jedis.get(KEY_PREFIX + key), clazz);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static <T> List<T> getList(String key, Class<T> clazz) {
		ShardedJedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			List<T> value = SerializeUtil.unserializeList(jedis.get(KEY_PREFIX + key), clazz);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	public static Long expire(String key, int seconds) {
		if (seconds < 0) {
			return null;
		}
		ShardedJedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.expire(KEY_PREFIX + key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		} finally {
			jedisPool.returnResource(jedis);
		}
		return null;
	}

}
