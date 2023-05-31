package br.com.project.suplementos.loja.de.suplementos.controller;

import br.com.project.suplementos.loja.de.suplementos.model.Produto;
import br.com.project.suplementos.loja.de.suplementos.service.FileStorageService;
import br.com.project.suplementos.loja.de.suplementos.service.SuplementoService;
import br.com.project.suplementos.loja.de.suplementos.util.UploadUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String paginaOfertas(Model model){
        List<Produto> produtos = service.listarOfertas();
        model.addAttribute("produtos",produtos);
        return "index";
    }

    @PostMapping("/adicionarCarrinho/{id}")
    public String addItemCarrinho(@PathVariable (name = "id") String id, Model model){
        Produto p = service.buscarPeloId(id);
            produtosCompra.add(p);
            model.addAttribute("listaCarrinho", produtosCompra);
        return "redirect:/";
    }

    @GetMapping("/chamarCarrinho")
    public String verCarrinho(Model model){
        model.addAttribute("itensCarrinho", produtosCompra);
        int tamanhoItensCarrinho = service.tamanhoCarrinho(produtosCompra);
        model.addAttribute("tamanhoItensCarrinho", tamanhoItensCarrinho);
        return "carrinhoDeCompras";
    }

    //ROTAS ADMIN

    @GetMapping("/admin")
    public String todosProdutos(Model model){
        List<Produto> produtos = service.getItensNaoDeletados();
        model.addAttribute("produtos",produtos);
        return "adminPage";
    }

    @PostMapping("/admin/excluir/{id}")
    public String softDeleteProduto(@PathVariable String id) {
        Produto p = service.buscarPeloId(id);
        p.setDeleted(new Date());
        service.update(p);
        return "redirect:/admin";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Produto p){
        p.setDeleted(p.getDeleted());
        service.update(p);
        return "redirect:/admin";
    }

    @GetMapping("/admin/editar/{id}")
    public String editar(@PathVariable("id") String id, Model model){
        Produto p = service.buscarPeloId(id);
        model.addAttribute("produto",p);
        return "editar";
    }

    @GetMapping("/all")
    public String listAll(Model model){
        List<Produto> produtos = service.listarTodos();
        model.addAttribute("produtos",produtos);
        return "allProducts";
    }

    @GetMapping("/cadastrarItem")
    public String cadastro(){
        return "cadastrarItem";
    }

    @PostMapping("/admin/cadastro")
    public String cadastro(@ModelAttribute Produto p, @RequestParam("file") MultipartFile imagem,@RequestParam(name = "inSale", required = false) Boolean promocao){
        p.setInSale(promocao);
        p.setImageUri(imagem.getOriginalFilename());
        this.fileService.save(imagem);
        this.service.create(p);
        return "adminPage";
    }
}
