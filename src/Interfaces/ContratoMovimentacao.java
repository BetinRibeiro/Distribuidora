package Interfaces;

public interface ContratoMovimentacao {

	void carregaItenAnterrior();
	
	void carregaItemPosterior();
	
	void liberaAlteracoes();
	
	void liberaNovoCadastro();
	
	void voltaParaPaginaAnterior();
	
	void inserirProdutoNaLista();
	
	void alterarQuantidadeProdutoLista();
	
	void removeProdutoDaLista();
	
	void finalizaInsercaoDeProdutos();
	
	void cancelaMovimentacaoAtual();
	
	
	
	void finalizarMovimento();

}
