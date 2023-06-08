package demo.nopcommerce.nopcommerce.models;

import lombok.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter



public class LoginModel {
    private String username;
    private String password;
}
