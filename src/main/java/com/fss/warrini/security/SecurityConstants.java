package com.fss.warrini.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.security.*;
import java.util.Base64;
import java.util.Random;

public class SecurityConstants {

    public static final int EXPIRATION_DURATION = 1000 * 3600 * 24 * 7;
    public static final String JWT_SECRET ;

    static {
        byte[] array = new byte[256];
        new SecureRandom().nextBytes(array);
        JWT_SECRET = Base64.getEncoder().encodeToString(array);
    }

}
