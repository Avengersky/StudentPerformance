package mano2.studentperformance;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class NN {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.matches("student","$2a$10$u36sFejsvOX4RoXmht15JO1inr.8eWyI0u.YXj10JGdXUR6peN2Yu"));
    }
}
