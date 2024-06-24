package prog.microservice.orderms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prog.microservice.orderms.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {
}
