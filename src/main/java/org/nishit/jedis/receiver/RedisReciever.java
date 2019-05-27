package org.nishit.jedis.receiver;

import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RedisReciever implements MessageListener {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RedisReciever.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // TODO Auto-generated method stub
        LOGGER.info("Received data - " + message.toString() + " from Topic - " + new String(pattern));
    }
}
