package kz.homecredit.templateparser.repository;

import kz.homecredit.templateparser.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterReposirtory extends JpaRepository<Parameter, Long> {
    public Parameter findByCode(String code);
}
