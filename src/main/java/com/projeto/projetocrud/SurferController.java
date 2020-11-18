package com.projeto.projetocrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SurferController {

    SurferService service;

    @Autowired
    public void setService(SurferService service){
        this.service = service;
    }

    @RequestMapping("/")
    public String getHome(Model model,HttpServletResponse response, @CookieValue(value = "last", defaultValue = "last") String last) {
        List<Surfer> surferList = service.findAll();
        model.addAttribute("surferList",surferList);

        model.addAttribute("last",last);
        Date d = new Date();
        DateFormat dateF = new SimpleDateFormat("HH:mm:ss_dd-MM-yyyy");
        String date = dateF.format(d);
        Cookie c = new Cookie("last", date);

        response.addCookie(c);
        return "home";
    }
    @RequestMapping("/cadastrar")
    public String getCadastrar(Model model) {
        Surfer s = new Surfer();
        model.addAttribute("newSurfer", s);

        return "cadastrar";
    }
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String doSalvar(@ModelAttribute(name="newSurfer") @Valid Surfer s, Errors errors, RedirectAttributes r) {
        if(errors.hasErrors()){
            return "cadastrar";
        }else {
            r.addFlashAttribute("success",true);
            service.save(s);
            return "redirect:/";
        }

    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String doUpdate(@ModelAttribute(name="surfer") @Valid Surfer s, Errors errors, RedirectAttributes r) {
        if(errors.hasErrors()){
            return "editar";
        }else {
            r.addFlashAttribute("update",true);
            service.save(s);
            return "redirect:/";
        }

    }
    @RequestMapping("/editar/{id}")
    public ModelAndView getEditar (@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editar");
        Surfer s = service.getById(id);
        modelAndView.addObject("surfer",s);

        return modelAndView;
    }
    @RequestMapping("/deletar/{id}")
    public String doDelete(@PathVariable(name="id") Long id,RedirectAttributes r) {
        r.addFlashAttribute("delete",true);
        service.delete(id);
        return "redirect:/";
    }

}