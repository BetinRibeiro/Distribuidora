package ClassesBin;

public class Item {
	private int id;
	private String descricao;
	private String fornecedor;
	private String local;
	private float estoqueMin;
	private float quantidade;
	private float custo;
	public Item(String descricao, String fornecedor, String local,
			float estoqueMin, float quantidade, float preco) {
		super();
		this.descricao = descricao;
		this.fornecedor = fornecedor;
		this.local = local;
		this.estoqueMin = estoqueMin;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	private float preco;
	private float desconto;

	public Item(String descricao, String fornecedor, String local,
			float estoqueMin, float preco) {
		super();
		this.descricao = descricao;
		this.fornecedor = fornecedor;
		this.local = local;
		this.estoqueMin = estoqueMin;
		this.preco = preco;
	}

	public Item(String descricao, String fornecedor, String local,
			float estoqueMin, float quantidade, float custo, float preco,
			float desconto) {
		super();
		this.descricao = descricao;
		this.fornecedor = fornecedor;
		this.local = local;
		this.estoqueMin = estoqueMin;
		this.quantidade = quantidade;
		this.custo = custo;
		this.preco = preco;
		this.desconto = desconto;
	}

	public Item(int id, String descricao, String fornecedor, String local,
			float estoqueMin, float quantidade, float custo, float preco,
			float desconto) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.fornecedor = fornecedor;
		this.local = local;
		this.estoqueMin = estoqueMin;
		this.quantidade = quantidade;
		this.custo = custo;
		this.preco = preco;
		this.desconto = desconto;
	}

	// construtor para ser utilizado no carrinho de compra
	public Item(int id, String descricao, float quantidade, float preco) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public float getEstoqueMin() {
		return estoqueMin;
	}

	public void setEstoqueMin(float estoqueMin) {
		this.estoqueMin = estoqueMin;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quant) {
		this.quantidade = quant;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public float getDesconto() {
		return desconto;
	}

	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}

}
