package kz.homecredit.templateparser.service;

import kz.homecredit.templateparser.entity.TemplateParameter;
import kz.homecredit.templateparser.repository.TemplateParameterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateParameterService {
    private final TemplateParameterRepository templateParameterRepository;

    public TemplateParameterService(TemplateParameterRepository templateParameterRepository) {
        this.templateParameterRepository = templateParameterRepository;
    }

    /*
    1. add* can be applied for INSERT operations: e.g. addUser(User user).
    2. get* can be applied for SELECT operations: e.g. getUser(Long userId).
    3. set* can be applied for UPDATE operations: e.g. setUsername(String username).
    4. delete* can be applied for DELETE operations: e.g. like deleteUser(Long userId).
    Although I'm not sure how useful is the physical delete. Personally, we should consider setting a flag that
    indicates that the row is not going to be used, rather than performing a physical delete.
    5. is* can be applied on a method that check something: e.g. isUsernameAvailable(String username).
    */

    public List<TemplateParameter> getTemplateParameters(){
        return templateParameterRepository.findAll();
    }

    public TemplateParameter getTemplateParameterById(long id){
        return templateParameterRepository.findById(id);
    }

    public TemplateParameter addTemplateParameter(TemplateParameter templateParameter){
        return templateParameterRepository.save(templateParameter);
    }

    public TemplateParameter setTemplateParameter(TemplateParameter templateParameter){
        return templateParameterRepository.save(templateParameter);
    }

    public void deleteTemplateParameter(long id){
        templateParameterRepository.deleteById(id);
    }
}
