package com.github.hotech.iam.infrastructure.hashing.bcrypt;

import com.github.hotech.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService  extends HashingService, PasswordEncoder {

}
