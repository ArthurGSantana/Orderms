package prog.microservice.orderms.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String ORDER_CREATED_QUEUE = "order-created";
}