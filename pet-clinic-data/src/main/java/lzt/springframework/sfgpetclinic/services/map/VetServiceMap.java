package lzt.springframework.sfgpetclinic.services.map;

import lzt.springframework.sfgpetclinic.model.Speciality;
import lzt.springframework.sfgpetclinic.model.Vet;
import lzt.springframework.sfgpetclinic.services.SpecialityService;
import lzt.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractServiceMap<Vet, Long> implements VetService {

    @Autowired
    private SpecialityService specialityService;

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if (object.getSpecialities().size() > 0) {
            for (Speciality speciality:object.getSpecialities()){
                if (speciality.getId() == null){
                    Speciality savedSpeciality = specialityService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            }
        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
