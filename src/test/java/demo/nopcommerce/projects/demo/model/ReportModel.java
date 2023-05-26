package demo.nopcommerce.projects.demo.model;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportModel {
    private String reportName;
    private String selectionCriteria;
    private String include;
    private String displayFieldGroup;
    private String displayField;

}
