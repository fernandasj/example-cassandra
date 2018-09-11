package io.github.fernandasj.database;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 *
 * @author fernanda
 */
public class CassandraConnection {
    
    private Cluster cluster;
    private Session session;
    
    public CassandraConnection(){
        cluster = Cluster.builder()
                .addContactPoint("0.0.0.0").withPort(9042)
                .build();
        
        session = cluster.connect("pedidos");
        
        
        try{
//            String key = "CREATE KEYSPACE pedidos "
//                    + "WITH replication = {'class': 'SimpleStrategy', "
//                    + "'replication_factor':1};";

            String pedido = "CREATE TABLE IF NOT EXISTS pedidos.pedido("
                    + "codigo int PRIMARY KEY,"
                    + "itens text,"
                    + "dataHora text);";
            session.execute(pedido);
            
            String produto = "CREATE TABLE IF NOT EXISTS pedidos.produto("
                    + "codigo int PRIMARY KEY,"
                    + "descricao text,"
                    + "preco float);";
            session.execute(produto);
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public void endConnection(){
        cluster.close();
        session.close();
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
}
