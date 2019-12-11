package kz.homecredit.templateparser.model;

import kz.homecredit.templateparser.entity.Parameter;
import kz.homecredit.templateparser.entity.Template;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Parser {
    private List<Parameter> parameterList;
    private List<Template> templateList;
}
