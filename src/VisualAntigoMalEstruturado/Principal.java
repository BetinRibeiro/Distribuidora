package VisualAntigoMalEstruturado;

import TelasCadastros.CadastroProduto;
import TelasMovimentacao.MovimentacaoCompra;
import TelasMovimentacao.MovimentacaoVenda;
import EstruturaVisual.Movimentacao;

public class Principal {
    public static void main(String args[]) {  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	MovimentacaoVenda login =new MovimentacaoVenda();
                login.setVisible(true);
                login.setLocationRelativeTo(null); 
                
            }
        });
    }
 
}