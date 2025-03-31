import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        String keyString = "mitulsudani188"; // Your input key
        byte[] keyBytes = keyString.getBytes();

        // Ensure the key length is valid (at least 32 bytes for HS256)
        byte[] validKeyBytes = new byte[32];
        System.arraycopy(keyBytes, 0, validKeyBytes, 0, Math.min(keyBytes.length, 32));

        SecretKey secretKey = Keys.hmacShaKeyFor(validKeyBytes);
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        System.out.println("Generated Secret Key: " + encodedKey);
    }
}
