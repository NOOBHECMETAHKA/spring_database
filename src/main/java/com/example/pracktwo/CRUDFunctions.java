package com.example.pracktwo;

import com.example.pracktwo.models.*;
import com.example.pracktwo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;

public class CRUDFunctions {

    private BookRepository bookRepository;
    private CatRepository catRepository;

    private HumanRepository humanRepository;
    private PencilRepository pencilRepository;

    private UserSystemRepository userSystemRepository;

    public CRUDFunctions(BookRepository rep){
        this.bookRepository = rep;
    }

    public CRUDFunctions(CatRepository rep){
        this.catRepository = rep;
    }

    public CRUDFunctions(HumanRepository rep){
        this.humanRepository = rep;
    }

    public CRUDFunctions(PencilRepository rep){
        this.pencilRepository = rep;
    }

    public CRUDFunctions(UserSystemRepository rep){
        this.userSystemRepository = rep;
    }


    public enum actionDataObject{
        INSERTORUPDATE,
        DELETE
    }

    private void insertOrUpdateObject(Object object){
        if(object instanceof Book){
            bookRepository.save((Book)object);
        }
        else if(object instanceof Cat){
            catRepository.save((Cat)object);
        } else if(object instanceof Human){
            humanRepository.save((Human)object);
        } else if (object instanceof Pencil) {
            pencilRepository.save((Pencil)object);
        } else if (object instanceof UserSystem) {
            userSystemRepository.save((UserSystem)object);
        }
    }
    private void delete(Object object){
        if(object instanceof Book){
            bookRepository.delete((Book)object);
        }
        else if(object instanceof Cat){
            catRepository.delete((Cat)object);
        } else if(object instanceof Human){
            humanRepository.delete((Human)object);
        } else if (object instanceof  Pencil) {
            pencilRepository.delete((Pencil)object);
        } else if (object instanceof UserSystem) {
            userSystemRepository.delete((UserSystem)object);
        }
    }

    public void manipulation(Object object,  actionDataObject status){
        switch (status){
            case DELETE -> delete(object);
            case INSERTORUPDATE -> insertOrUpdateObject(object);
        }
    }
}
