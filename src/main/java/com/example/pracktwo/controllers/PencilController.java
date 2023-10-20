package com.example.pracktwo.controllers;

import com.example.pracktwo.CRUDFunctions;
import com.example.pracktwo.models.Book;
import com.example.pracktwo.models.Pencil;
import com.example.pracktwo.models.UserSystem;
import com.example.pracktwo.repo.PencilRepository;
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
public class PencilController {

    @Autowired
    private PencilRepository pencilRepository;

    @GetMapping("/pencils")
    String books(Model model, @ModelAttribute("pencil") Pencil pencil) {
        List<Pencil> pencils = pencilRepository.findAll();

        //Filter
        if(pencil.getName() != null){
            pencils = pencils.stream().filter(x -> Objects.equals(x.getName(), pencil.getName())).toList();
        }

        if(pencil.getColor() != null){
            pencils = pencils.stream().filter(x -> Objects.equals(x.getColor(), pencil.getColor())).toList();
        }

        if(pencil.getPrice() != 0){
            pencils = pencils.stream().filter(x -> Objects.equals(x.getPrice(), pencil.getPrice())).toList();
        }
        //Filter

        model.addAttribute("title", "Карандаши");
        model.addAttribute("pencils", pencils);
        model.addAttribute("pencil", new Pencil());
        return "pages/pencil/index";
    }

    @GetMapping("pencils/create")
    String create(Model model){
        model.addAttribute("title", "Создание карандаша");
        model.addAttribute("book", new Pencil());
        return "pages/pencil/add";
    }

    @PostMapping("pencils/store")
    String store(Model model, @Valid @ModelAttribute("pencil") Pencil pencil){
        CRUDFunctions crud = new CRUDFunctions(pencilRepository);
        crud.manipulation(pencil, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/pencils";
    }

    @GetMapping("pencils/edit/{id}")
    String edit(Model model, @PathVariable("id") long id){
        Pencil pencil = pencilRepository.findById(id).orElse(null);
        if(pencil == null){
            return "redirect:/pencils";
        }
        model.addAttribute("pencilPARAMS", pencil);
        model.addAttribute("title", "Создание карандаша");
        return "pages/pencil/update";
    }

    @PostMapping("pencils/update/{id}")
    String update(Model model, @PathVariable("id") long id, @Valid @ModelAttribute("pencil") Pencil pencil){
        pencil.setId(id);
        CRUDFunctions crud = new CRUDFunctions(pencilRepository);
        crud.manipulation(pencil, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/pencil";
    }

    @PostMapping("pencils/delete/{id}")
    String delete(Model model, @PathVariable("id") long id){
        Pencil pencil = pencilRepository.findById(id).orElse(null);
        if(pencil == null){
            return "redirect:/pencils";
        }
        CRUDFunctions crud = new CRUDFunctions(pencilRepository);
        crud.manipulation(pencil, CRUDFunctions.actionDataObject.DELETE);
        return "redirect:/pencils";
    }
}
