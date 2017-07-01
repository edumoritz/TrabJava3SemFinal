package planodecontas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UtilTree<T> implements UtilConta<T>{
	
	private UniNode<T> raiz;
	
	public UtilTree(UniNode<T> noRaiz) {
		this.raiz = noRaiz;
	}

	public UniNode<T> getRaiz() {
		return raiz;
	}

	public void AllConsole() {
		StringBuilder sb = new StringBuilder();

		if (raiz == null) {
			System.out.println("Arvore vazia!");
		} else {
			sb.append(raiz.getConteudo().toString() + "\n");
		}

		sb.append(searchLeaf(raiz, chamaId()));

		System.out.println(sb.toString());
		
	}

	public String searchSon(UniNode<T> node, Object i) {
		StringBuilder sb = new StringBuilder();
		if (node.isLeaf() == false) {
			for (UniNode<T> filho : node.getFilhos()) {
				if (filho.isLeaf() == true) {
					sb.append(i + ".");
				}
				sb.append(chamaId() + "." + filho.getConteudo().toString()
						+ "\n");
			}
		}

		return sb.toString();
	}

	public String searchLeaf(UniNode<T> node, Object i) {
		StringBuilder sb = new StringBuilder();
		if (node.isLeaf() == false) {
			for (UniNode<T> no : node.getFilhos()) {
				if(no.isLeaf() == true){
					sb.append("\t");
				}
				sb.append("\t" + chamaId() + "." + no.getConteudo().toString()
						+ "\n");
				sb.append(searchLeaf(no, i));
			}
			
		}
		return sb.toString();
	}
	
	private Object chamaId() {
		Method metodoId = null;
		Object id = null;
		try {
			metodoId = raiz.getConteudo().getClass().getMethod("getId");
			id = metodoId.invoke(raiz.getConteudo());
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		return id;
	}
	

}
