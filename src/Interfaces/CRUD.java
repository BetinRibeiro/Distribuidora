package Interfaces;

import java.util.List;

public interface CRUD {
	void criar(Object ob) ; 
	public List<Object> buscar() ; 
	void atualizar(Object ob); 
	public void deletar(Object ob); 

}
