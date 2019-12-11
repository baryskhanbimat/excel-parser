package kz.homecredit.templateparser.repository;

import kz.homecredit.templateparser.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    public Language findByCode(String code);
}
