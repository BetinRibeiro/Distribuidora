import TelasMovimentacao.MovimentacaoCompra;



public class Principal {
    public static void main(String args[]) {  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MovimentacaoCompra login =new MovimentacaoCompra();
                login.setVisible(true);
                login.setLocationRelativeTo(null); 
                
            }
        });
    }
 
}