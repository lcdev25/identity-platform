package com.lcdev25.idp.authnservice.usecase;

import com.lcdev25.idp.grpc.AuthServiceGrpc;
import com.lcdev25.idp.grpc.ValidateTokenRequest;
import com.lcdev25.idp.grpc.ValidateTokenResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceGrpcImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Override
    public void validateToken(ValidateTokenRequest request, StreamObserver<ValidateTokenResponse> responseObserver) {
        super.validateToken(request, responseObserver);
    }
}
