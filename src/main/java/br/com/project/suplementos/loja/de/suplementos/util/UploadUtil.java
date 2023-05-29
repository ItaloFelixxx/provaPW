package br.com.project.suplementos.loja.de.suplementos.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.io.File;

public class UploadUtil {

    public static boolean fazerUploadImagem(MultipartFile imagem){

        boolean sucessUpload = false;

        if(!imagem.isEmpty()){
            String nomeArquivo = imagem.getOriginalFilename();
            try{

                //criando diretorio para armazenar o arquivo
                String postaUploadImagem = "C:\\Users\\Rafael\\Downloads\\loja de suplementos\\loja-de-suplementos\\src\\main\\resources\\static\\imagens\\img-upload";
                File dir = new File(postaUploadImagem);
                if((!dir.exists())){
                    dir.mkdirs();
                }

                // criando arquivo no diretorio
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream((serverFile)));

                stream.write(imagem.getBytes());
                stream.close();

                sucessUpload = true;

            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }else{
            System.out.println("Arquivo vazio!!");
        }

        return sucessUpload;
    }
}
