package kz.homecredit.templateparser.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Table(name = "TBL_TEMPLATE_PARAM")

@Entity
public class TemplateParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID")
    @SequenceGenerator(name = "ID", sequenceName = "SEQ_ID", allocationSize = 1)
    @Column(name = "ID")
    private long id;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "ID_ORDER")
    private long order;
    @Column(name = "ID_TEMPLATE")
    private long templateId;
    @Column(name = "ID_PARAMETER")
    private long parameterId;
    @Column(name = "ID_LANGUAGE")
    private long languageId;
}
