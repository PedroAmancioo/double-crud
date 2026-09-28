package com.example.ListaUsuarios;
import com.example.ListaUsuarios.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController{
    private List<User> listUser = new ArrayList<User>();
    private Long nextId = 2L;

    public UserController(){
        listUser.add(new User(1L, "cleiton", "@cleitindograu", "119"));
    }

    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("listUser", listUser);
        model.addAttribute("size", listUser.size());
        return "users/index";
    }

    @GetMapping("/new")
    public String novo(){
        return "users/new";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String arroba, @RequestParam String cpf) {
        listUser.add(new User(nextId++, name, arroba, cpf));
        return "redirect:/users/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap model){
        for (User user : listUser) {
            if (user.getId().equals(id)) {
                model.addAttribute("user", user);
                return "users/edit";
            }
        }
        return "redirect:/users/index";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @RequestParam String arroba, @RequestParam String cpf) {
        for (User user : listUser) {
            if (user.getId().equals(id)) {
                user.setName(name);
                user.setArroba(arroba);
                user.setCpf(cpf);
                break;
            }
        }
        return "redirect:/users/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        listUser.removeIf(user -> user.getId().equals(id));
        return "redirect:/users/index";
    }
}
