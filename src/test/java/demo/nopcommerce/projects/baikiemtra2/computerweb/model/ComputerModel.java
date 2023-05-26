package demo.nopcommerce.projects.baikiemtra2.computerweb.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComputerModel {
    private String computerName;
    private String introduced;
    private String discontinued;
    private String company;


}
