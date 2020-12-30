package com.ai.rti.ic.grp.ci.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ai.rti.ic.grp.ci.config.RedisConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;
import redis.clients.util.JedisClusterCRC16;

@Component
public class RedisUtil {
	private static Log log = LogFactory.getLog(RedisUtil.class);

	@Autowired(required = false)
	private JedisCluster jedisCluster;

	@Autowired
	private RedisConfig config;
	
	private static TreeMap<Long, String> slotHostMap;
	private static Map<String, JedisPool> nodeMap;

	@PostConstruct
	private void initSlotCluster() {
		nodeMap = this.jedisCluster.getClusterNodes();
		String anyHost = nodeMap.keySet().iterator().next();
		slotHostMap = getSlotHostMap(anyHost, config.getPasswd());
	}

	public Set<String> sunion(String... keys) {
		return this.jedisCluster.sunion(keys);
	}

	public Set<String> sinter(String... keys) {
		return this.jedisCluster.sinter(keys);
	}

	public Long sinterstore(String dstkey, String... keys) {
		return this.jedisCluster.sinterstore(dstkey, keys);
	}

	public Long delKey(String key) {
		Long result = Long.valueOf(0L);
		Boolean exsist = this.jedisCluster.exists(key);
		if (exsist.booleanValue()) {
			result = this.jedisCluster.del(key);
		}
		return result;
	}

	public Long hdelKey(String key, String[] field) {
		return this.jedisCluster.hdel(key, field);
	}

	public Long hdelkey(String key, String field) {
		return this.jedisCluster.hdel(key, new String[] { field });
	}

	public Long expire(String key, int expire) {
		return this.jedisCluster.expire(key, expire);
	}

	public Boolean exists(String key) {
		return this.jedisCluster.exists(key);
	}

	public Boolean hexists(String key, String field) {
		return this.jedisCluster.hexists(key, field);
	}

	public List<String> sort(String key) {
		return this.jedisCluster.sort(key);
	}

	public long makeId(String key) {
		long id = this.jedisCluster.incr(key).longValue();
		if (id + 75807L >= Long.MAX_VALUE) {
			this.jedisCluster.getSet(key, "0");
		}
		return id;
	}

	public String setString(String key, String value) {
		return this.jedisCluster.set(key, value);
	}

	public String setString(String key, String value, int expire) {
		return this.jedisCluster.setex(key, expire, value);
	}

	public Long setStringIfNotExists(String key, String value) {
		return this.jedisCluster.setnx(key, value);
	}

	public String getString(String key) {
		return this.jedisCluster.get(key);
	}

	public Long hashSet(String key, String field, String value) {
		return this.jedisCluster.hset(key, field, value);
	}

	public Long hlen(String key) {
		return this.jedisCluster.hlen(key);
	}

	public Long hashSetByPipeline(String key, Map<String, String> data) {
		JedisCluster jedisCluster = getJedisCluster();
		Assert.notNull(jedisCluster);

		if (data != null && !data.isEmpty()) {
			jedisCluster.hmset(key, data);
		}

		return Long.valueOf(data.size());
	}

	public Jedis getJedisByKey(String key) {
		int slot = JedisClusterCRC16.getSlot(key);

		System.out.println(slot);

		Map.Entry<Long, String> entry = slotHostMap.lowerEntry(Long.valueOf(slot));

		Jedis jedis = ((JedisPool) nodeMap.get(entry.getValue())).getResource();
		return jedis;
	}

	private static TreeMap<Long, String> getSlotHostMap(String anyHostAndPortStr, String serverAuth) {
		log.info("serverAuth:" + serverAuth);
		TreeMap<Long, String> tree = new TreeMap<>();
		String[] parts = anyHostAndPortStr.split(":");
		HostAndPort anyHostAndPort = new HostAndPort(parts[0], Integer.parseInt(parts[1]));
		try {
			Jedis jedis = new Jedis(anyHostAndPort.getHost(), anyHostAndPort.getPort());
			if (serverAuth != null && !"".equals(serverAuth)) {
				jedis.auth(serverAuth);
			}
			List<Object> list = jedis.clusterSlots();
			for (Object object : list) {
				List<Object> list1 = (List<Object>) object;
				List<Object> master = (List<Object>) list1.get(2);
				String hostAndPort = new String((byte[]) master.get(0)) + ":" + master.get(1);
				tree.put((Long) list1.get(0), hostAndPort);
				tree.put((Long) list1.get(1), hostAndPort);
			}
			jedis.close();
		} catch (Exception e) {
			log.error("", e);
		}
		return tree;
	}

	public String hashGet(String key, String field) {
		return this.jedisCluster.hget(key, field);
	}

	public String hashMultipleSet(String key, Map<String, String> hash) {
		return this.jedisCluster.hmset(key, hash);
	}

	public List<String> hashMultipleGet(String key, String... fields) {
		return this.jedisCluster.hmget(key, fields);
	}

	public Map<String, String> hashGetAll(String key) {
		return this.jedisCluster.hgetAll(key);
	}

	public Long listPushTail(String key, String... values) {
		return this.jedisCluster.rpush(key, values);
	}

	public Long listPushHead(String key, String... values) {
		return this.jedisCluster.lpush(key, values);
	}

	public List<String> listGetAll(String key) {
		return this.jedisCluster.lrange(key, 0L, -1L);
	}

	public List<String> listRange(String key, long beginIndex, long endIndex) {
		return this.jedisCluster.lrange(key, beginIndex, endIndex - 1L);
	}

	public Long lrem(String key, String value) {
		return this.jedisCluster.lrem(key, 0L, value);
	}

	public Long lrem(String key, int count, String value) {
		return this.jedisCluster.lrem(key, count, value);
	}

	public Long addWithSet(String key, String... members) {
		return this.jedisCluster.sadd(key, members);
	}

	public Long srem(String srckey, String... member) {
		return this.jedisCluster.srem(srckey, member);
	}

	public String getSrandmember(String key) {
		return this.jedisCluster.srandmember(key);
	}

	public boolean sismember(String key, String value) {
		return this.jedisCluster.sismember(key, value).booleanValue();
	}

	public Long scard(String key) {
		return this.jedisCluster.scard(key);
	}

	public Set<String> smembers(String key) {
		return this.jedisCluster.smembers(key);
	}

	public Long addWithSortedSet(String key, double score, String member) {
		return this.jedisCluster.zadd(key, score, member);
	}

	public Long delSortedSet(String key, String members) {
		return this.jedisCluster.zrem(key, new String[] { members });
	}

	public Long addWithSortedSet(String key, Map<String, Double> scoreMembers) {
		return this.jedisCluster.zadd(key, scoreMembers);
	}

	public Set<Tuple> revrangeByScore(String key) {
		return this.jedisCluster.zrevrangeWithScores(key, 0L, -1L);
	}

	public Set<String> revrangeByScoreWithSortedSet(String key, double max, double min) {
		return this.jedisCluster.zrevrangeByScore(key, max, min);
	}

	public JedisCluster getJedisCluster() {
		return this.jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

}
