package ru.digitalleague.core.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "application.rabbit.enable", havingValue = "true", matchIfMissing = true)
public class RabbitMqConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitAdmin ampqAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    /**
     * Очередь для приема заказов.
     */
    @Bean
    public Queue myQueue1() {
        return new Queue("order");
    }

    /**
     * Очередь для приема результатов поездки.
     */
    @Bean
    public Queue myQueue2() {
        return new Queue("trip-result");
    }

    @Bean
    public Queue myQueue3() {
        return new Queue("moscow_queue");
    }

    @Bean
    public Queue myQueue4() {
        return new Queue("spb_queue");
    }

    @Bean
    public Queue myQueue5() {
        return new Queue("sar_queue");
    }

    @Bean
    public Queue myQueue6() {
        return new Queue("rost_queue");
    }

    @Bean
    public Queue myQueue7() {
        return new Queue("tag_queue");
    }

    @Bean
    public Queue myQueue8() {
        return new Queue("cher_queue");
    }

    @Bean
    public Queue myQueue9() {
        return new Queue("bar_queue");
    }

    @Bean
    public Queue myQueue10() {
        return new Queue("nef_queue");
    }

    @Bean
    public Queue myQueue11() {
        return new Queue("kem_queue");
    }

    @Bean
    public Queue myQueue12() {
        return new Queue("mag_queue");
    }

}
