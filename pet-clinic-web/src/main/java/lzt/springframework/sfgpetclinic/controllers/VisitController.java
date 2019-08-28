package lzt.springframework.sfgpetclinic.controllers;

import lzt.springframework.sfgpetclinic.model.Pet;
import lzt.springframework.sfgpetclinic.model.Visit;
import lzt.springframework.sfgpetclinic.services.PetService;
import lzt.springframework.sfgpetclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class VisitController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private PetService petService;

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

//    @ModelAttribute("visit")
//    public Visit loadPetWithVisit(@PathVariable Long petId, Model model){
//        Pet pet = petService.findById(petId);
//        Visit visit = new Visit();
//        visit.setPet(pet);
//        model.addAttribute("pet", pet);
//        return visit;
//    }

    @GetMapping("/owners/{id}/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long id, @PathVariable Long petId, Model model){
        Pet pet = petService.findById(petId);
        Visit visit = new Visit();
        visit.setPet(pet);
        model.addAttribute("pet", pet);
        model.addAttribute("visit", visit);
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{id}/pets/{petId}/visits/new")
    public String processNewVisitForm(@PathVariable Long id, @PathVariable Long petId, @Valid Visit visit, BindingResult result){
        Pet pet = petService.findById(petId);
        visit.setPet(pet);
        visitService.save(visit);
        return "redirect:/owners/{id}";
    }

}
