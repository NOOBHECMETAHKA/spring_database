package com.example.pracktwo.controllers;

import com.example.pracktwo.CRUDFunctions;
import com.example.pracktwo.models.Book;
import com.example.pracktwo.models.Cat;
import com.example.pracktwo.repo.BookRepository;
import com.example.pracktwo.repo.CatRepository;
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
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @GetMapping("/cats")
    String books(Model model, @ModelAttribute("cat") Cat cat){
        List<Cat> cats = catRepository.findAll();

        //Filter
        if(cat.getName() != null){
            cats = cats.stream().filter(x -> Objects.equals(x.getName(), cat.getName())).toList();
        }

        if(cat.getLike() != 0){
            cats = cats.stream().filter(x -> Objects.equals(x.getLike(), cat.getLike())).toList();
        }

        if(cat.getView() != 0){
            cats = cats.stream().filter(x -> Objects.equals(x.getView(), cat.getView())).toList();
        }
        //Filter

        model.addAttribute("title", "Книги");
        model.addAttribute("cats", cats);
        model.addAttribute("cat", new Cat());
        return "pages/cat/index";
    }

    @GetMapping("cats/create")
    String create(Model model){
        model.addAttribute("title", "Создание котика");
        model.addAttribute("cat", new Cat());
        return "pages/cat/add";
    }

    @PostMapping("cats/store")
    String store(Model model, @Valid @ModelAttribute("cats") Cat cat){
        CRUDFunctions crud = new CRUDFunctions(catRepository);
        crud.manipulation(cat, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/cats";
    }

    @GetMapping("cats/edit/{id}")
    String edit(Model model, @PathVariable("id") long id){
        Cat cat = catRepository.findById(id).orElse(null);
        if(cat == null){
            return "redirect:/cats";
        }
        model.addAttribute("catPARAMS", cat);
        model.addAttribute("title", "Создание кота");
        return "pages/cat/update";
    }

    @PostMapping("cats/update/{id}")
    String update(Model model, @PathVariable("id") long id, @Valid @ModelAttribute("cat") Cat cat){
        cat.setId(id);
        CRUDFunctions crud = new CRUDFunctions(catRepository);
        crud.manipulation(cat, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/cats";
    }

    @PostMapping("cat/delete/{id}")
    String delete(Model model, @PathVariable("id") long id){
        Cat cat = catRepository.findById(id).orElse(null);
        if(cat == null){
            return "redirect:/cats";
        }
        CRUDFunctions crud = new CRUDFunctions(catRepository);
        crud.manipulation(cat, CRUDFunctions.actionDataObject.DELETE);
        return "redirect:/cats";
    }
}
