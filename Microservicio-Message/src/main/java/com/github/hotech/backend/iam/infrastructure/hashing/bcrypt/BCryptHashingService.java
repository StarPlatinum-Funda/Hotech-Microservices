package com.github.hotech.backend.iam.infrastructure.hashing.bcrypt;

import com.github.hotech.backend.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService  extends HashingService, PasswordEncoder {

}