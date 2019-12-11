package kz.homecredit.templateparser.service;

import kz.homecredit.templateparser.entity.Parameter;
import kz.homecredit.templateparser.repository.ParameterReposirtory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {
    private final ParameterReposirtory parameterReposirtory;

    public ParameterService(ParameterReposirtory parameterReposirtory) {
        this.parameterReposirtory = parameterReposirtory;
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

    public List<Parameter> getParameters(){
        return parameterReposirtory.findAll();
    }

    public Parameter getParameterByCode(String code){
        return parameterReposirtory.findByCode(code);
    }

    public Parameter addParameter(Parameter parameter){
        return parameterReposirtory.save(parameter);
    }

    public Parameter setParameter(Parameter parameter){
        return parameterReposirtory.save(parameter);
    }

    public void deleterParameter(long id){
        parameterReposirtory.deleteById(id);
    }
}
