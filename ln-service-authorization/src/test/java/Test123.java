import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test123 {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hexPassword = DigestUtil.md5Hex("test123456" + "l7a1f8");
        System.out.println(hexPassword);
        String encodeUserPassword = passwordEncoder.encode(hexPassword);
        System.out.println(encodeUserPassword);
        //ee91d8e56d5f641d7b7360ac200c6898
        //$2a$10$8.gwrkBeCB2pVsyQI6/zdurdbfj2NJZkA5t0mZZupYmsS/iINaXMa
        boolean matches = passwordEncoder.matches(hexPassword, "$2a$10$uCR4UPMMJ5CLyXXzogBYtOBj5H/vaOIy.gAJalPlQ9grwUH4GKRXa");
        System.out.println(matches);
    }
}
