package com.xqt.util;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @author andy
 */
@Component
public class JedisUtil {

	private static final Logger logger = LogManager.getLogger(JedisUtil.class);

	@Autowired
	private JedisPool getJedisPool;

	// session默认存在时间：120分钟
	private static final Integer SESSION_DEFAULT_EXPIRE = 7200;

	private static final String KEY_PREFIX = "KEY_";
	/**
	 * 连接池
	 */
	private static JedisPool jedisPool = null;

	@PostConstruct
	public void init() {
		jedisPool = getJedisPool;
	}


	/**
	 * 获取jedis实例
	 * @return
	 */
	public static Jedis getJedis(){
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}

	/**
	 * Description: 返回资源
	 * @author andy
	 */
	public static void returnResource(Jedis jedis) {
		if (null != jedis) {
			jedis.close();
		}
	}

	/**
	 * 向缓存中设置对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(String key, Object value) {
		Jedis jedis = getJedis();
		try {
			jedis.set(KEY_PREFIX + key, SerializeUtil.serialize(value));
			jedis.expire(KEY_PREFIX + key, SESSION_DEFAULT_EXPIRE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			returnResource(jedis);
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
		Jedis jedis = getJedis();
		key = KEY_PREFIX + key;
		try {
			jedis.set(key, SerializeUtil.serialize(value));
			jedis.expire(key, expire);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 删除缓存中得对象，根据key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean del(String key) {
		Jedis jedis = getJedis();
		try {
			jedis.del(KEY_PREFIX + key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 删除缓存中得对象，根据key
	 * 
	 * @return
	 */
	public static boolean clear() {
		Jedis jedis = getJedis();
		try {
			jedis.flushDB();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Object value = SerializeUtil.unserialize(jedis.get(KEY_PREFIX + key));
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return false;
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 通过批量的key 获取批量的value
	 * @param keys
	 * @return
	 */
	public static List<String> mget(String...keys){
		Jedis jedis = null;
		List<String> values = null;
		try {
			jedis = getJedis();
			values = jedis.mget(keys);
		} catch (Exception e) {
			if(jedis != null){
				jedis.close();
			}
			e.printStackTrace();
		} finally {
			//返还到连接池
			returnResource(jedis);
		}
		return values;
	}

	/**
	 * 批量设置 key value
	 * mset(new String[]{"key2","value1","key2","value2"})
	 * @param keysvalues
	 * @return
	 */
	public static String mset(String...keysvalues){
		Jedis jedis = null;
		String ans = null;
		try{
			jedis = getJedis();
			ans = jedis.mset(keysvalues);
		}catch (Exception e) {
			if(jedis != null){
				jedis.close();
			}
			e.printStackTrace();
		} finally {
			//返还到连接池
			returnResource(jedis);
		}
		return ans;
	}

	/**
	 * 通过key向指定的value追加值
	 * @param key
	 * @param str
	 * @return
	 */
	public static Long append(String key,String str){
		Jedis jedis = null;

		try {
			jedis = getJedis();
			return jedis.append(key, str);
		} catch (Exception e) {
			if(jedis != null){
				jedis.close();
			}
			e.printStackTrace();
			return 0L;
		} finally {
			//返还到连接池
			returnResource(jedis);
		}
	}

	/**
	 *  判断key是否存在
	 * @param key
	 * @return
	 */
	public static Boolean exists(String key){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.exists(key);
		} catch (Exception e) {
			if(jedis != null){
				jedis.close();
			}
			e.printStackTrace();
			return false;
		} finally {
			//返还到连接池
			returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T getObject(String key, Class<T> clazz) {
		Jedis jedis = getJedis();
		try {
			T value = SerializeUtil.unserializeObject(jedis.get(KEY_PREFIX + key), clazz);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return null;
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static <T> List<T> getList(String key, Class<T> clazz) {
		Jedis jedis = getJedis();
		try {
			List<T> value = SerializeUtil.unserializeList(jedis.get(KEY_PREFIX + key), clazz);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return null;
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 
	 * @function 设置过期时间
	 * @date 2014-12-4 下午08:25:51
	 * @author v_lianghua
	 * @param key
	 * @param seconds
	 * @return void
	 */
	public static Long expire(String key, int seconds) {
		if (seconds < 0) {
			return null;
		}
		Jedis jedis = jedisPool.getResource();
		try {
			return jedis.expire(KEY_PREFIX + key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	/**
	 * 
	 * @function 的获取大小
	 * @return void
	 */
	public static Long dbsize() {

		Jedis jedis = getJedis();
		try {
			return jedis.dbSize();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return null;
	}

}
