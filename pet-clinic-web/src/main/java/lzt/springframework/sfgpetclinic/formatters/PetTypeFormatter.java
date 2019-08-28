//package lzt.springframework.sfgpetclinic.formatters;
//
//import lzt.springframework.sfgpetclinic.model.PetType;
//import lzt.springframework.sfgpetclinic.services.PetTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.util.Collection;
//import java.util.Locale;
//
//@Component
//public class PetTypeFormatter implements Formatter<PetType> {
//
//    @Autowired
//    private PetTypeService petTypeService;
//
//    @Override
//    public PetType parse(String s, Locale locale) throws ParseException {
//        Collection<PetType> petTypes = petTypeService.findAll();
//        for(PetType petType : petTypes){
//            if (petType.getName().equals(s)){
//                return petType;
//            }
//        }
//        throw new ParseException("type not found: " + s, 0);
//    }
//
//    @Override
//    public String print(PetType petType, Locale locale) {
//        return petType.getName();
//    }
//}
