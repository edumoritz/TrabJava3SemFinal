package br.casa.planodecontas;

import java.util.ArrayList;
import java.util.List;

public class UniNodeImpl<T> implements UniNode<T> {
	
	
	private UniNode<T> pai;
	private List<UniNode<T>> filhos;
	private T conteudo;
	
	public UniNodeImpl(T t){
		this.conteudo = t;
	}
	
	public boolean isLeaf() {
		return this.filhos == null;
	}

	public T getConteudo() {
		return this.conteudo;
	}

	public UniNode<T> getPai() {
		return pai;
	}
	
	public void setPai(UniNode<T> pai) {
		this.pai = pai;
	}

	public List<UniNode<T>> getFilhos() {
		return filhos;
	}

	public void addFilho(UniNode<T> node) {
		if(this.filhos == null){
			this.filhos = new ArrayList<>();
		}
		this.filhos.add(node);
		node.setPai(this);
	}
}
