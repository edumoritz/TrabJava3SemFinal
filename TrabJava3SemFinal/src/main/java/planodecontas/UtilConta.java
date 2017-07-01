package planodecontas;

public interface UtilConta<T> {
	
	public UniNode<T> getRaiz();
	public void AllConsole();
	public String searchSon(UniNode<T> node, Object i);
	public String searchLeaf(UniNode<T> node, Object i);
}
