package kz.homecredit.templateparser.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Table(name = "TBL_TEMPLATE")

@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID")
    @SequenceGenerator(name = "ID", sequenceName = "SEQ_ID", allocationSize = 1)
    @Column(name = "ID")
    private long id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String description;
    @Column(name = "ID_ROUTE")
    private long route;
}