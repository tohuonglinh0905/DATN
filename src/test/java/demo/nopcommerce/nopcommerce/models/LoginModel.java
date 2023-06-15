package demo.nopcommerce.nopcommerce.models;

import lombok.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter



public class LoginModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String company;
}
