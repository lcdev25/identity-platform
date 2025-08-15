package com.lcdev25.idp.authzservice.usecase;

import com.lcdev25.idp.grpc.AuthServiceGrpc;
import com.lcdev25.idp.grpc.ValidateTokenRequest;
import com.lcdev25.idp.grpc.ValidateTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

  @Autowired private AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;

  public void checkPermissions(String token) {
    try {
      ValidateTokenRequest request = ValidateTokenRequest.newBuilder().setToken(token).build();
      System.out.println("gRPC Client: Sending validation request...");
      ValidateTokenResponse response = authServiceBlockingStub.validateToken(request);
      System.out.println("gRPC Client: Response received -> " + response.getIsValid());
    } catch (Exception e) {
      System.err.println("RPC to authn-service failed: " + e.getMessage());
    }
  }
}
