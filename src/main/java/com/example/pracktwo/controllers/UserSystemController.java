package com.example.pracktwo.controllers;

import com.example.pracktwo.CRUDFunctions;
import com.example.pracktwo.models.UserSystem;
import com.example.pracktwo.repo.UserSystemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class UserSystemController {

    @Autowired
    private UserSystemRepository userSystemRepository;

    @GetMapping("/usersSystem")
    String books(Model model, @ModelAttribute("userSystem") UserSystem userSystem){
        List<UserSystem> usersSystem = userSystemRepository.findAll();

        //Filter
        if(userSystem.getEmail() != null){
            usersSystem = usersSystem.stream().filter(x -> Objects.equals(x.getEmail(), userSystem.getEmail())).toList();
        }

        if(userSystem.getEmail() != null){
            usersSystem = usersSystem.stream().filter(x -> Objects.equals(x.getName(), userSystem.getName())).toList();
        }
        //Filter

        model.addAttribute("title", "Пользователи");
        model.addAttribute("usersSystem", usersSystem);
        model.addAttribute("userSystem", new UserSystem());
        return "pages/userSystem/index";
    }

    @GetMapping("usersSystem/create")
    String create(Model model){
        model.addAttribute("title", "Создание пользователя");
        model.addAttribute("userSystem", new UserSystem());
        return "redirect:/usersSystem";
    }

    @PostMapping("usersSystem/store")
    String store(Model model, @Valid @ModelAttribute("userSystem") UserSystem userSystem){
        CRUDFunctions crud = new CRUDFunctions(userSystemRepository);
        crud.manipulation(userSystem, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/usersSystem";
    }

    @GetMapping("usersSystem/edit/{id}")
    String edit(Model model, @PathVariable("id") long id){
        UserSystem userSystem = userSystemRepository.findById(id).orElse(null);
        if(userSystem == null){
            return "redirect:/usersSystem";
        }
        model.addAttribute("userSystemPARAMS", userSystem);
        model.addAttribute("title", "Создание пользователя");
        return "pages/userSystem/update";
    }

    @PostMapping("userSystem/update/{id}")
    String update(Model model, @PathVariable("id") long id, @Valid @ModelAttribute("userSystem") UserSystem userSystem){
        userSystem.setId(id);
        CRUDFunctions crud = new CRUDFunctions(userSystemRepository);
        crud.manipulation(userSystem, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/usersSystem";
    }

    @PostMapping("usersSystem/delete/{id}")
    String delete(Model model, @PathVariable("id") long id){
        UserSystem userSystem = userSystemRepository.findById(id).orElse(null);
        if(userSystem == null){
            return "redirect:/usersSystem";
        }
        CRUDFunctions crud = new CRUDFunctions(userSystemRepository);
        crud.manipulation(userSystem, CRUDFunctions.actionDataObject.DELETE);
        return "redirect:/usersSystem";
    }

}
