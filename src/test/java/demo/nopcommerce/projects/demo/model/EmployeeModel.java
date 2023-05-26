package demo.nopcommerce.projects.demo.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EmployeeModel {
    private String firstName;
    private String middleName;
    private String lastName;
    private String img;
    private String id;
    private String nickname;
    private String otherID;
    private String licenseNumber;
    private String licenseExpiryDate;
    private String numberSSN;
    private String numberSIN;
    private String nationality;
    private String maritalStatus;
    private String dateOfBirth;
    private String gender;
    private String militaryService;
    private String smoker;

}
