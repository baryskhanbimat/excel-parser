package kz.homecredit.templateparser.service;


import kz.homecredit.templateparser.entity.Language;
import kz.homecredit.templateparser.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    public Language getLanguageByCode(String code) {
        return languageRepository.findByCode(code);
    }

    public Language addLanguage(Language language){
        return languageRepository.save(language);
    }

    public Language editLanguage(Language language){
        return languageRepository.save(language);
    }

    public void deleteLanguage(long id){
        languageRepository.deleteById(id);
    }
}

