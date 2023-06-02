package br.com.project.suplementos.loja.de.suplementos.controller;

import br.com.project.suplementos.loja.de.suplementos.model.Produto;
import br.com.project.suplementos.loja.de.suplementos.service.FileStorageService;
import br.com.project.suplementos.loja.de.suplementos.service.SuplementoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SuplementoController {
    SuplementoService service;
    FileStorageService fileService;
    private List<Produto> produtosCompra = new ArrayList<>();
    public SuplementoController(SuplementoService service, FileStorageService fileService){
        this.service = service;
        this.fileService = fileService;
    }
    @GetMapping({"/", "home", "/user"})
    public String paginaInicial(Model model, HttpServletResponse response){
        List<Produto> produtos = service.getItensNaoDeletados();
        model.addAttribute("produtos",produtos);
        int tamanhoItensCarrinho = service.tamanhoCarrinho(produtosCompra);
        model.addAttribute("tamanhoItensCarrinho", tamanhoItensCarrinho);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String dataHora = dateFormat.format(new Date());
        Cookie c = new Cookie("visita", dataHora);
        c.setMaxAge(86400);
        response.addCookie(c);

        return "index";
    }

    @GetMapping("/ofertas")
    public String paginaOfertas(Model model){
        List<Produto> produtos = service.listarOfertas();
        model.addAttribute("produtos",produtos);
        return "index";
    }

    @PostMapping("/user/adicionarCarrinho/{id}")
    public String addItemCarrinho(@PathVariable (name = "id") Integer id, Model model){
        Produto p = service.buscarPeloId(id);
            produtosCompra.add(p);
            model.addAttribute("listaCarrinho", produtosCompra);
        return "redirect:/";
    }

    @GetMapping("/user/chamarCarrinho")
    public String verCarrinho(Model model){
        model.addAttribute("itensCarrinho", produtosCompra);
        int tamanhoItensCarrinho = service.tamanhoCarrinho(produtosCompra);
        model.addAttribute("tamanhoItensCarrinho", tamanhoItensCarrinho);
        return "carrinhoDeCompras";
    }

    @PostMapping ("/user/finalizarCompra")
    public String finalizarCompra(Model model, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("tamanhoItensCarrinho", 0);
        produtosCompra.clear();
        return "redirect:/";
    }

}
