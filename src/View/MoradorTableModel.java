/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import br.com.cotemig.condominio.Morador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Informatica - ASSEMP
 */
public class MoradorTableModel extends AbstractTableModel{
     
    private List<Morador> dados;
    private String[] colunas = {"Nome" , "CPF" ,"Situação","Apartamento"};
    
     
    public MoradorTableModel(){
        dados = new ArrayList<Morador>();
    }
    
     public MoradorTableModel(List<Morador> lista){
        dados = lista;
        this.fireTableDataChanged();
    }
     
    public void addRow(Morador morador){
        this.dados.add(morador);
        this.fireTableDataChanged();
    }
 
    public String getColumnName(int num){
        return this.colunas[num];
    }
 
    @Override
    public int getRowCount() {
        return dados.size();
    }
 
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
 
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: return dados.get(linha).getNome();
            case 1: return dados.get(linha).getCpf();
            case 2: return dados.get(linha).getSituacao();
            case 3: return dados.get(linha).getAp();
        }   
        return null;
    }
    

}
