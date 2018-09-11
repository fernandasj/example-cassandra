package io.github.fernandasj.dao;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import io.github.fernandasj.database.CassandraConnection;
import io.github.fernandasj.model.Pedido;
import io.github.fernandasj.model.Produto;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class PedidoDao {
    
    Cluster connection;
    Session session;
    CassandraConnection db = new CassandraConnection();
    
    public PedidoDao(){
        connection = db.getCluster();
        session = db.getSession();
        
    }
        
    public boolean salvarPedido(Pedido pedido){
        
        PreparedStatement stmt = session.prepare("INSERT INTO pedido "
                + "(codigo, itens, datahora) VALUES (?,?,?)");

        BoundStatement bound = stmt.bind(pedido.getCodigo(), pedido.getItens()+
                "" + "", pedido.getDataHora() + "");

        ResultSet rs = session.execute(bound);

        db.endConnection();
        
        return rs.wasApplied();
    }
    
    public boolean salvarProduto(Produto produto) {
        
        PreparedStatement stmt = session.prepare("INSERT INTO produto "
                + "(codigo, descricao, preco) VALUES (?,?,?)");

        BoundStatement bound = stmt.bind(produto.getCodigo(),produto.getDescricao(),produto.getPreco());

        ResultSet rs = session.execute(bound);

        db.endConnection();

        return rs.wasApplied();
    }
    
    public Pedido buscarPedido(int codigo){
        
        Statement stmt = QueryBuilder.select().all().from("pedido").where(eq("codigo",codigo));

        ResultSet rs = session.execute(stmt);

        if (rs.wasApplied()) {
            List<Row> linhas = rs.all();

            for (Row r : linhas) {
                System.out.printf("TABELA pedido\nCodigo | DataHora | Itens\n");
                System.out.printf(String.valueOf(r.getInt("codigo")) + "" +
                        "" + r.getString("datahora") + "" + r.getString("itens")+"\n");   
            }
        } else {
            System.out.println("Não há pedido com esse codigo");
        }
        db.endConnection();
        return null;
    }
    
    public List<Pedido> listarPedido(){
    
        Statement stmt = QueryBuilder.select().all().from("pedido");

        ResultSet rs = session.execute(stmt);

        List<Row> linhas = rs.all();
        if (rs.wasApplied()) {
            for(Row r : linhas){
                System.out.printf("TABELA pedido\nCodigo | DataHora | Itens\n");
                System.out.printf(String.valueOf(r.getInt("codigo"))+"        " +
                        ""+r.getString("datahora")+"   "+r.getString("itens"));
            }
        }else{
            System.out.println("Não há pedidos");
        }
        
        
        db.endConnection();
        
        return null;
    }
    
    public boolean excluirPedido(int codigo) {
    
        Statement stmt = QueryBuilder.delete().from("pedido").where(eq("codigo",codigo));
        session.execute(stmt);

        ResultSet rs = session.execute(stmt);

        db.endConnection();
        
        return rs.wasApplied();
    }
}
