package DAO;

import Entity.User;

import java.sql.*;

public class UserDAO {
    private Connection conexion;

    public UserDAO() {
        String url = "jdbc:postgresql://localhost:5432/Clients";
        String usuario = "postgres";
        String contrasena = "Dargnel";
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crear(User user) {
        String sql = "INSERT INTO users(nombre, apellido, edad) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setInt(3, user.getEdad());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User leer(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {

                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                int edad = resultado.getInt("edad");
                return new User(id, nombre, apellido, edad);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
             e.printStackTrace();

        }
        return null;
    }

    public void actualizar(User user) {
        String sql = "UPDATE users SET nombre = ?, apellido = ?, edad = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setInt(3, user.getEdad());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrar(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}