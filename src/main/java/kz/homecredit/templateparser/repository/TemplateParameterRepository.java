package kz.homecredit.templateparser.repository;

import kz.homecredit.templateparser.entity.TemplateParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateParameterRepository extends JpaRepository<TemplateParameter, Long> {
    public TemplateParameter findById(long id);
}
