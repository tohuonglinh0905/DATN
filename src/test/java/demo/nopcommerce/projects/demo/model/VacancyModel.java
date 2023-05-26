package demo.nopcommerce.projects.demo.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VacancyModel {
    private String vacancyName;
    private String jobTitle;
    private String description;
    private String hiringManager;;
    private String numOfPosition;
    private String active;
    private String publishInRSSFeed;


}
