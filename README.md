commit "Ajuste ProdutoDAO"

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultSet;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto){
        conn = new ConectaDAO().connectDB();

        try {
            String query = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(query);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new ConectaDAO().connectDB();

        try {
            String query = "SELECT * FROM produtos";
            prep = conn.prepareStatement(query);
            resultSet = prep.executeQuery();

            while(resultSet.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        }

        return listagem;
    }
}

public class ProdutosDTO {
    private Integer id;
    private String nome;
    private Integer valor;
    private String status;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

commit "Ajuste ConectaDAO"

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectaDAO {

    public Connection connectDB(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=");
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }

        return conn;
    }
}


}
