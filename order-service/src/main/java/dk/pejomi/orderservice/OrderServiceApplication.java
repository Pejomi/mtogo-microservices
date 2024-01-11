package dk.pejomi.orderservice;

import dk.pejomi.orderservice.service.OrderService;
import dk.pejomi.orderservice.service.impl.OrderPaymentImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(8999)
                .addService(new OrderPaymentImpl())
                .build();

        server.start();
        System.out.println("Server started at port: " + server.getPort());
        SpringApplication.run(OrderServiceApplication.class, args);
        server.awaitTermination();
    }

}
