package io.github.fernandasj.model;

/**
 *
 * @author fernanda
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int codigo;
    private List<ItemPedido> itens;
    private LocalDateTime dataHora;

    public Pedido() {
        itens = new ArrayList<>();
        dataHora = LocalDateTime.now();
    }

    public Pedido(int codigo, List<ItemPedido> itens, LocalDateTime dataHora) {
        this.codigo = codigo;
        this.itens = itens;
        this.dataHora = dataHora;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "codigo=" + codigo +
                ", itens=" + itens +
                ", dataHora=" + dataHora +
                '}';
    }
}
