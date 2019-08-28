package lzt.springframework.sfgpetclinic.controllers;

import lzt.springframework.sfgpetclinic.model.Owner;
import lzt.springframework.sfgpetclinic.model.Pet;
import lzt.springframework.sfgpetclinic.model.PetType;
import lzt.springframework.sfgpetclinic.services.OwnerService;
import lzt.springframework.sfgpetclinic.services.PetService;
import lzt.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/owners/{id}")
@Controller
public class PetController {

    @Autowired
    private PetService petService;
    @Autowired
    private PetTypeService petTypeService;
    @Autowired
    private OwnerService ownerService;

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @ModelAttribute("petType")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        owner.getPets().add(pet);
        pet.setOwner(owner);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model, Owner owner){
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String  processUpdateForm(@Valid Pet pet, BindingResult result, Model model, Owner owner){
        pet.setOwner(owner);
        if (result.hasErrors()){
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/remove")
    public String removePet(Owner owner, @PathVariable Long petId){
        petService.deleteById(petId);
        return "redirect:/owners/" + owner.getId();
    }
}
