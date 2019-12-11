package kz.homecredit.templateparser.repository;

import kz.homecredit.templateparser.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    public Template findByCode(String code);
}
