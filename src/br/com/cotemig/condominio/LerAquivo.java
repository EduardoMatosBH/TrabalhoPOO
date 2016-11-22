/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cotemig.condominio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Informatica - ASSEMP
 */
public class LerAquivo {

    private File arquivo;

    public static String AbrirArquivo() {
        JFileChooser select = new JFileChooser();
        int result = select.showOpenDialog(null);
        String caminho = null;
        FileReader arq;
        if (result == JFileChooser.APPROVE_OPTION) {
            caminho = select.getSelectedFile().getAbsolutePath();
        }
        return caminho;
    }

    public static ArrayList<String> LerArquivo() {
            String caminho = AbrirArquivo();
            ArrayList <String> listaPg = new ArrayList<>();
        try {
            FileReader arq = new FileReader(caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            //File arq = new File(caminho);
            String linha = lerArq.readLine();   // lê a primeira linha
            listaPg.add(linha);
             // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            while (linha != null) {
                System.out.printf("%s\n", linha);

                linha = lerArq.readLine();      // lê da segunda até a última linha
                listaPg.add(linha);
                arq.close();
            }

        }

  
    catch (IOException e

    
        ) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                e.getMessage());
    }
        return listaPg;
}
}

