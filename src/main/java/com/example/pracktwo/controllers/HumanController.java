package com.example.pracktwo.controllers;

import com.example.pracktwo.CRUDFunctions;
import com.example.pracktwo.models.Book;
import com.example.pracktwo.models.Cat;
import com.example.pracktwo.models.Human;
import com.example.pracktwo.repo.CatRepository;
import com.example.pracktwo.repo.HumanRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class HumanController {

    @Autowired
    private HumanRepository humanRepository;

    @GetMapping("/humans")
    String books(Model model, @ModelAttribute("human") Human human){
        List<Human> humans = humanRepository.findAll();

        //Filter
        if(human.getFirstName() != null){
            humans = humans.stream().filter(x -> Objects.equals(x.getFirstName(), human.getFirstName())).toList();
        }

        if(human.getLastName() != null){
            humans = humans.stream().filter(x -> Objects.equals(x.getLastName(), human.getLastName())).toList();
        }

        if(human.getWeight() != 0){
            humans = humans.stream().filter(x -> Objects.equals(x.getWeight(), human.getWeight())).toList();
        }

        if(human.getDateBirth() != null){
            humans = humans.stream().filter(x -> Objects.equals(x.getDateBirth(), human.getDateBirth())).toList();
        }
        //Filter
        
        model.addAttribute("title", "Персональная информация");
        model.addAttribute("humans", humans);
        model.addAttribute("human", new Human());
        return "pages/human/index";
    }

    @GetMapping("humans/create")
    String create(Model model){
        model.addAttribute("title", "Создание личных данных");
        model.addAttribute("cat", new Human());
        return "pages/human/add";
    }

    @PostMapping("book/store")
    String store(Model model, @Valid @ModelAttribute("human") Human human){
        CRUDFunctions crud = new CRUDFunctions(humanRepository);
        crud.manipulation(human, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/humans";
    }

    @GetMapping("human/edit/{id}")
    String edit(Model model, @PathVariable("id") long id){
        Human human = humanRepository.findById(id).orElse(null);
        if(human == null){
            return "redirect:/humans";
        }
        model.addAttribute("bookPARAMS", human);
        model.addAttribute("title", "Создание персональных данных");
        return "pages/human/update";
    }

    @PostMapping("human/update/{id}")
    String update(Model model, @PathVariable("id") long id, @Valid @ModelAttribute("human") Human human){
        human.setId(id);
        CRUDFunctions crud = new CRUDFunctions(humanRepository);
        crud.manipulation(human, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/humans";
    }

    @PostMapping("human/delete/{id}")
    String delete(Model model, @PathVariable("id") long id){
        Human human = humanRepository.findById(id).orElse(null);
        if(human == null){
            return "redirect:/humans";
        }
        CRUDFunctions crud = new CRUDFunctions(humanRepository);
        crud.manipulation(human, CRUDFunctions.actionDataObject.DELETE);
        return "redirect:/humans";
    }
}
