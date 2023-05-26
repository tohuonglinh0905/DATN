package demo.nopcommerce.projects.demo.model;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomFiledModel {
    private String fieldName;
    private String screen;
    private String type;
    private String select;

}
