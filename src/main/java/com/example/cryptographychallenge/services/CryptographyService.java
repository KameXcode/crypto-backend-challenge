package com.example.cryptographychallenge.services;
import org.apache.commons.codec.binary.Hex;
import com.example.cryptographychallenge.Dtos.UserDTO;
import com.example.cryptographychallenge.entites.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

@Service
public class CryptographyService {

    @Autowired
    private UserEntity userEntity;

    Random intAleatorio = new Random();

    Long valorAleatorio = intAleatorio.nextLong(9999);
    public UserEntity cryptographyUser(UserEntity userEntity){
        userEntity.setSalt(valorAleatorio);
        byte[] hashedBytes = hashCreditandDocument(userEntity, "creditCard");
        String hashedString = Hex.encodeHexString(hashedBytes);
        byte[] hashedDocumentBytes = hashCreditandDocument(userEntity, "userDocument");
        String hashedDocumentString = Hex.encodeHexString(hashedBytes);
        userEntity.setCreditCardToken(hashedString);
        userEntity.setUserDocument(hashedDocumentString);
        return userEntity;
    }
    public static byte[] hashCreditandDocument( UserEntity userEntity, String data) {

        Long userSalt = userEntity.getSalt();
        String str = Long.toString(userSalt);
        final char[] creditCard = userEntity.getCreditCardToken().toCharArray();
        final char[] userDocument = userEntity.getUserDocument().toCharArray();
        final byte[] salt = str.getBytes();
        final int iterations = 1000;
        final int keyLength = 512;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            if(data.equals("creditCard")) {
                PBEKeySpec spec = new PBEKeySpec(creditCard, salt, iterations, keyLength );
                SecretKey key = skf.generateSecret( spec );
                byte[] res = key.getEncoded( );
                return res;
            }
            else{
                PBEKeySpec spec = new PBEKeySpec(userDocument, salt, iterations, keyLength );
                SecretKey key = skf.generateSecret( spec );
                byte[] res = key.getEncoded( );
                return res;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

}
