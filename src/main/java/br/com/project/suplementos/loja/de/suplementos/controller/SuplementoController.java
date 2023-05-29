package br.com.project.suplementos.loja.de.suplementos.controller;

import br.com.project.suplementos.loja.de.suplementos.model.Produto;
import br.com.project.suplementos.loja.de.suplementos.service.SuplementoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SuplementoController {
    SuplementoService service;
    private List<Produto> produtosCompra = new ArrayList<>();
    public SuplementoController(SuplementoService service){
        this.service = service;
    }
    @GetMapping({"/", "home"})
    public String paginaInicial(Model model, HttpServletResponse response){
        List<Produto> produtos = service.getItensNaoDeletados();
        model.addAttribute("produtos",produtos);
        int tamanhoItensCarrinho = service.tamanhoCarrinho(produtosCompra);
        model.addAttribute("tamanhoItensCarrinho", tamanhoItensCarrinho);
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime expires = now.plusDays(1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String cookieValue = now.format(formatter);
//        Cookie visitaCookie = new Cookie("visita", cookieValue);
//        visitaCookie.setMaxAge((int) Duration.between(now, expires).getSeconds());
//        visitaCookie.setPath("/");
//        response.addCookie(visitaCookie);
        return "index";
    }

    @GetMapping("/ofertas")
    public String paginaOfertas(Model model, HttpServletResponse response){
        List<Produto> produtos = service.listarOfertas();
        model.addAttribute("produtos",produtos);
        return "index";
    }

    @PostMapping("/adicionarCarrinho/{id}")
    public String addItemCarrinho(@PathVariable (name = "id") String id, Model model){
        Optional<Produto> p = service.buscarPeloId(id);
        if(p.isPresent()){
            produtosCompra.add(new Produto(p.get()));
            model.addAttribute("listaCarrinho", produtosCompra);
            for (Produto pr: produtosCompra) {
                System.out.println(pr);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/chamarCarrinho")
    public String verCarrinho(Model model){
        model.addAttribute("itensCarrinho", produtosCompra);
        int tamanhoItensCarrinho = service.tamanhoCarrinho(produtosCompra);
        model.addAttribute("tamanhoItensCarrinho", tamanhoItensCarrinho);
        return "carrinhoDeCompras";
    }

    @GetMapping("/admin")
    public String todosProdutos(Model model){
        List<Produto> produtos = service.getItensNaoDeletados();
        model.addAttribute("produtos",produtos);
        return "adminPage";
    }
}
