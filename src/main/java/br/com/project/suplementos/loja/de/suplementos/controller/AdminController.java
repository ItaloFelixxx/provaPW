package br.com.project.suplementos.loja.de.suplementos.controller;

import br.com.project.suplementos.loja.de.suplementos.model.Produto;
import br.com.project.suplementos.loja.de.suplementos.service.FileStorageService;
import br.com.project.suplementos.loja.de.suplementos.service.SuplementoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {
    SuplementoService service;
    FileStorageService fileService;
    private List<Produto> produtosCompra = new ArrayList<>();
    public AdminController(SuplementoService service, FileStorageService fileService){
        this.service = service;
        this.fileService = fileService;
    }

    @GetMapping("/admin")
    public String paginaAdmin(Model model){
        List<Produto> produtos = service.getItensNaoDeletados();
        model.addAttribute("produtos",produtos);
        return "adminPage";
    }

    @PostMapping("/admin/excluir/{id}")
    public String softDeleteProduto(@PathVariable Integer id) {
        Produto p = service.buscarPeloId(id);
        p.setDeleted(new Date());
        service.update(p);
        return "redirect:/admin";
    }

    @PostMapping("/admin/editar")
    public String editar(@ModelAttribute Produto p, @RequestParam("file") MultipartFile imagem){
        Date d = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
        String dataUpload = formato.format(d);

        dataUpload = dataUpload.replaceAll("/", "_");
        dataUpload = dataUpload.replaceAll(":", "_");
        p.setImageUri(dataUpload + imagem.getOriginalFilename());

        this.fileService.save(imagem, dataUpload);
        service.update(p);
        return "redirect:/admin";
    }

    @GetMapping("/admin/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model){
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

    @GetMapping("admin/cadastro")
    public String cadastro(){
        return "cadastrarItem";
    }

    @PostMapping("/admin/cadastro")
    public String cadastro(@ModelAttribute Produto p, @RequestParam("file") MultipartFile imagem,@RequestParam(name = "inSale", required = false) Boolean promocao){
        p.setInSale(promocao);
        p.setImageUri(imagem.getOriginalFilename());

        Date d = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
        String dataUpload = formato.format(d);

        dataUpload = dataUpload.replaceAll("/", "_");
        dataUpload = dataUpload.replaceAll(":", "_");
        p.setImageUri(dataUpload + imagem.getOriginalFilename());

        this.fileService.save(imagem, dataUpload);
        this.service.create(p);

        return "redirect:/admin";
    }
}
