package com.example.ListaUsuarios;
import com.example.ListaUsuarios.model.Product;
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
@RequestMapping("/products")
public class ProductController{
    private List<Product> listProduct = new ArrayList<Product>();
    private Long nextId = 2L;

    public ProductController(){
        listProduct.add(new Product(1L, "Notebook", "Notebook Dell 16GB RAM", 4500.00));
    }

    @GetMapping("/index")
    public String index(ModelMap model){
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("size", listProduct.size());
        return "products/index";
    }

    @GetMapping("/new")
    public String novo(){
        return "products/new";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String description, @RequestParam Double price) {
        listProduct.add(new Product(nextId++, name, description, price));
        return "redirect:/products/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap model){
        for (Product product : listProduct) {
            if (product.getId().equals(id)) {
                model.addAttribute("product", product);
                return "products/edit";
            }
        }
        return "redirect:/products/index";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @RequestParam String description, @RequestParam Double price) {
        for (Product product : listProduct) {
            if (product.getId().equals(id)) {
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                break;
            }
        }
        return "redirect:/products/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        listProduct.removeIf(product -> product.getId().equals(id));
        return "redirect:/products/index";
    }
}
