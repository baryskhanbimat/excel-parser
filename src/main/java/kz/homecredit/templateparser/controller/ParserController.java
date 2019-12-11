package kz.homecredit.templateparser.controller;

import kz.homecredit.templateparser.entity.Language;
import kz.homecredit.templateparser.entity.Parameter;
import kz.homecredit.templateparser.entity.Template;
import kz.homecredit.templateparser.entity.TemplateParameter;
import kz.homecredit.templateparser.service.LanguageService;
import kz.homecredit.templateparser.service.ParameterService;
import kz.homecredit.templateparser.service.TemplateParameterService;
import kz.homecredit.templateparser.service.TemplateService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/parse")
public class ParserController {

    @Autowired
    ParameterService parameterService;

    @Autowired
    TemplateService templateService;

    @Autowired
    LanguageService languageService;

    @Autowired
    TemplateParameterService templateParameterService;

    private static final long PARAMETER_TYPE_VARIABLE = 10;
    private static final long PARAMETER_TYPE_TEXT = 13;
    private static final long DEFAULT_ROUTING = 1;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @ResponseBody
    public ResponseEntity<List<HashMap<String, Integer>>> readFile(@RequestParam("file") MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        List<HashMap<String, Integer>> list = new ArrayList<>();
        String regex = "\\%([^%]*)\\%";

        for(int i = 0; i<worksheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = worksheet.getRow(i);
            String templateCode = row.getCell(0).getStringCellValue();
            String smsText = row.getCell(1).getStringCellValue();
            String languageCode = row.getCell(2).getStringCellValue();

            HashMap<String, Integer> found = new HashMap<>();
            List<String> parameters = new ArrayList<>();
            List<String> templates = new ArrayList<>();
            List<String> languages = new ArrayList<>();

            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(smsText);
            String[] arrOfStr = smsText.split(regex);

            while (m.find()) {
                found.put(m.group(1), smsText.indexOf(m.group(1)));
                parameters.add(m.group(1));
            }

            addParameters(parameters);

            for(String s : arrOfStr){
                if(!s.isEmpty()){
                    found.put(s, smsText.indexOf(s));
                }
            }

            addTemplates(templateCode);
            addLanguages(languageCode);

            HashMap<String, Integer> sortedMapDesc = sortByValue(found, true);
            addTemplateParameters(sortedMapDesc, languageCode, templateCode, parameters);

            list.add(sortedMapDesc);
        }

        return ResponseEntity.ok(list);
    }

    private void addTemplateParameters(HashMap<String, Integer> map, String languageCode, String templateCode, List<String> parameters) {

        long order = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            order++;
            TemplateParameter templateParameter = new TemplateParameter();
            templateParameter.setLanguageId(languageService.getLanguageByCode(languageCode).getId());
            templateParameter.setTemplateId(templateService.getTemplateByCode(templateCode).getId());
            templateParameter.setOrder(order);

            if (parameters.contains(entry.getKey())) {
                templateParameter.setParameterId(parameterService.getParameterByCode(entry.getKey()).getId());
                templateParameter.setValue("");
            } else {
                templateParameter.setParameterId(PARAMETER_TYPE_TEXT);
                templateParameter.setValue(entry.getKey());
            }

            templateParameterService.addTemplateParameter(templateParameter);
        }
    }

    private void addLanguages(String lang) {
        if(languageService.getLanguageByCode(lang) == null){
            Language language = new Language();
            language.setCode(lang);
            language.setDescription(lang);

            languageService.addLanguage(language);
        }

    }

    private void addTemplates(String temp) {
        if(templateService.getTemplateByCode(temp) == null){
            Template template = new Template();
            template.setCode(temp);
            template.setDescription(temp);
            template.setRoute(DEFAULT_ROUTING);

            templateService.addTemplate(template);
        }
    }

    private void addParameters(List<String> parameters) {
        for(String param : parameters){
            if(parameterService.getParameterByCode(param) == null){
                Parameter parameter = new Parameter();
                parameter.setCode(param);
                parameter.setDescription(param);
                parameter.setType(PARAMETER_TYPE_VARIABLE);
                parameterService.addParameter(parameter);
            }
        }
    }

    private HashMap<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{code}")
    @ResponseBody
    public ResponseEntity<Parameter> getParameter(@PathVariable String code) {

        Parameter parameter = parameterService.getParameterByCode(code);

        return ResponseEntity.ok(parameter);
    }



}
