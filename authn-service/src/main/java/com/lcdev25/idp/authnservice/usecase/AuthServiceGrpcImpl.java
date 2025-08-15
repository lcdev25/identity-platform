package com.lcdev25.idp.authnservice.usecase;

import com.lcdev25.idp.grpc.AuthServiceGrpc;
import com.lcdev25.idp.grpc.ValidateTokenRequest;
import com.lcdev25.idp.grpc.ValidateTokenResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceGrpcImpl extends AuthServiceGrpc.AuthServiceImplBase {

  @Override
  public void validateToken(
      ValidateTokenRequest request, StreamObserver<ValidateTokenResponse> responseObserver) {
        System.out.println("Invoking GRPC server request for validating token");
        ValidateTokenResponse response = ValidateTokenResponse.newBuilder()
                .setIsValid(true)
                .setUserId("user-from-standard-grpc")
                .addRoles("ADMIN")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
