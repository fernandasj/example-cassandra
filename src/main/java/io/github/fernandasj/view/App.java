package io.github.fernandasj.view;

import io.github.fernandasj.dao.PedidoDao;
import io.github.fernandasj.model.ItemPedido;
import io.github.fernandasj.model.Pedido;
import io.github.fernandasj.model.Produto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class App {
    public static void main(String[] args) {
        
        PedidoDao dao = new PedidoDao();
        
        Produto p1 = new Produto(1, "Arroz", 2.5f);
//        Produto p2 = new Produto(2, "feijão", 2.5f);
        
        System.out.println(dao.salvarProduto(p1));
//        System.out.println(dao.salvarProduto(p2));
        
        ItemPedido item = new ItemPedido(p1, 2);
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(item);
//        
        Pedido pedido = new Pedido(1, itens, LocalDateTime.now());
//        
        System.out.println(dao.salvarPedido(pedido));
        
//        dao.buscarPedido(1);
//          System.out.println(dao.excluirPedido(1));
    }
}
