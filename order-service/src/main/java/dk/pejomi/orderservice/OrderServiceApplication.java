package dk.pejomi.orderservice;

import dk.pejomi.orderservice.service.impl.OrderPaymentImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
