public class conectaDAO {

    public Connection connectDB(){
        Connection conn = null;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=");

        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }

}
