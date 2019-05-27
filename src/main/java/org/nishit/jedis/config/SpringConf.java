package org.nishit.jedis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.nishit.jedis.model.Programmer;
import org.nishit.jedis.receiver.RedisReciever;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.Executors;

@Configuration
public class SpringConf {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.jedis.pool.max-total}")
    private int pool_max_total;
    @Value("${redis.jedis.pool.max-idle}")
    private int pool_max_idle;
    @Value("${redis.jedis.pool.min-idle}")
    private int min_idle;
    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder JedisPoolingClientConfigurationBuilder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
                .builder();
        GenericObjectPoolConfig GenericObjectPoolConfig = new GenericObjectPoolConfig();
        GenericObjectPoolConfig.setMaxTotal(pool_max_total);
        GenericObjectPoolConfig.setMaxIdle(pool_max_idle);
        GenericObjectPoolConfig.setMinIdle(min_idle);
        return JedisPoolingClientConfigurationBuilder.poolConfig(GenericObjectPoolConfig).build();
        // https://commons.apache.org/proper/commons-pool/apidocs/org/apache/commons/pool2/impl/GenericObjectPool.html
    }

    @Bean
    public JedisConnectionFactory getJedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        if (!StringUtils.isEmpty(password)) {
            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        }
        redisStandaloneConfiguration.setPort(port);
        return new JedisConnectionFactory(redisStandaloneConfiguration, getJedisClientConfiguration());
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // for serializing redis key.We can see this in cli-monitor
   	    redisTemplate.setKeySerializer(new StringRedisSerializer());
//   	 redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer()));
        return redisTemplate;
    }

    @Bean
    @Qualifier("lisOperations")
    public ListOperations<String,Programmer> listOperations(RedisTemplate<String,Programmer> redisTemplate)
    {
        return redisTemplate.opsForList();
    }

      @Bean
    @Qualifier("setOperations")
    public SetOperations<String,Programmer> setOperations(RedisTemplate<String,Programmer> redisTemplate)
    {
        return redisTemplate.opsForSet();
    }

    @Bean
    @Qualifier("hashOperations")
    public HashOperations<String,Integer,Programmer> hashOperationsOperations(RedisTemplate<String,Object> redisTemplate)
    {
        return redisTemplate.opsForHash();
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic(UUID.randomUUID().toString());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(getJedisConnectionFactory());
        container.addMessageListener(new MessageListenerAdapter(new RedisReciever()), topic());
        container.setTaskExecutor(Executors.newFixedThreadPool(4));
        return container;
    }


}
