package br.casa.ativador;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.casa.dao.UtilSql;
import br.casa.pojo.Produto;


public class LeitorUrl {
	
	public List<Produto> lerProdutos(String strUrl) throws Exception {
		List<Produto> lista = new ArrayList<>();
		URL url = new URL(strUrl);
		URLConnection urlCon = url.openConnection();
		
		try(InputStream is = urlCon.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr)){
			
			String linha;
			while((linha = in.readLine()) != null){
				if(linha.matches("[0-9]+.*")){
					Produto p = lerProduto(linha);
					lista.add(p);
				}
			}
		}
		return lista;
	}
	
	private Pattern pattern = Pattern.compile("([0-9]+)(.*)US\\$ (.*)");
	
	public Produto lerProduto(String linha) {
		UtilSql us = new UtilSql();
		Matcher mat = pattern.matcher(linha);
		
		Produto produto = new Produto();
		if(mat.matches()){
			
			String strId = mat.group(1).trim();
			produto.setId(Long.parseLong(strId));
			
			String desc = mat.group(2).trim();
			produto.setDescricao(desc);
			
			String strValorOriginal = mat.group(3).trim();
			String strValorSemponto = strValorOriginal.replaceAll("\\.", "");
			String strValorIngles = strValorSemponto.replaceAll(",", ".");
			produto.setValorDolar(new BigDecimal(strValorIngles));
			us.insertSql(produto);
		} else {
			throw new RuntimeException("Linha inválida: "+linha);
		}
		return produto;
	}

}
