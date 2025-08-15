package com.lcdev25.idp.authnservice;

import com.lcdev25.idp.authnservice.usecase.AuthServiceGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class GrpcServerRunner {

  @Value("${grpc.server.port}")
  private int grpcPort;

  @Autowired private AuthServiceGrpcImpl authServiceGrpc;

  private Server server;

  @PostConstruct
  public void startServer() throws IOException {
    log.info("Starting grpc server on port {}", grpcPort);
    ServerBuilder.forPort(grpcPort).addService(authServiceGrpc).build().start();
    log.info("grpc server started");
  }

  @PreDestroy
  public void stopServer() {
    if (server != null) {
      log.info("Shutting down grpc server");
      server.shutdown();
      log.info("grpc server shutdown");
    }
  }
}
