import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private final String URL = "jdbc:postgresql://localhost:5433/postgres";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuario (codigo, login, senha, sexo) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getCodigo());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, String.valueOf(usuario.getSexo()));
            stmt.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT codigo, login, senha, sexo FROM Usuario";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCodigo(rs.getInt("codigo"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setSexo(rs.getString("sexo").charAt(0));
                lista.add(usuario);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE Usuario SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, String.valueOf(usuario.getSexo()));
            stmt.setInt(4, usuario.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int codigo) {
        String sql = "DELETE FROM Usuario WHERE codigo = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
