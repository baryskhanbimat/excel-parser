package kz.homecredit.templateparser.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "TBL_PARAMETER")
@Entity
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID")
    @SequenceGenerator(name = "ID", sequenceName = "SEQ_ID", allocationSize = 1)
    @Column(name = "ID")
    private long id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String description;
    @Column(name = "ID_TYPE")
    private long type;
}
