package adapter;

import domain.Produto;
import externo.AtacadoBrasilApi;
import repository.CatalogoProdutos;

import java.util.ArrayList;
import java.util.List;

public class AtacadoBrasilAdapter implements CatalogoProdutos {
    private final AtacadoBrasilApi api;

    public AtacadoBrasilAdapter(AtacadoBrasilApi api) {
        this.api = api;
    }

    @Override
    public List<Produto> getProdutos() {
        List<String[]> dadosExternos = api.buscarProdutosAtacado();
        List<Produto> listaFormatada = new ArrayList<>();

        for (String[] item : dadosExternos) {
            String codigo = item[0];
            String nome = item[1];
            double preco = Double.parseDouble(item[2]);
            int estoque = Integer.parseInt(item[3]);

            boolean disponivel = estoque > 0;

            listaFormatada.add(new Produto(
                    codigo,
                    nome,
                    preco,
                    estoque,
                    "Atacado Brasil",
                    disponivel
            ));
        }
        return listaFormatada;
    }
}