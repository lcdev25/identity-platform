package com.lcdev25.idp.authzservice;

import com.lcdev25.idp.grpc.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

  @Value("${grpc.client.authn-service.address}")
  private String authnServiceAddress;

  @Bean
  public ManagedChannel managedChannel() {
    return ManagedChannelBuilder.forTarget(authnServiceAddress).usePlaintext().build();
  }

  @Bean
  public AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub(
      ManagedChannel managedChannel) {
    return AuthServiceGrpc.newBlockingStub(managedChannel);
  }
}
