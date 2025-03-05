package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUser {
    public DaoUser() {
    }

    public Utilisateur getUtilisateur(String email, String password) throws ServletException {
        Utilisateur utilisateur = null;

        try {
            Connection conn = DBConnection.getConnection();

            Utilisateur var8;
            try {
                String sql = "SELECT id, nom_utilisateur, email,mot_de_passe,role,cv,date_creation FROM utilisateurs WHERE email = ? AND mot_de_passe = SHA2(?, 256)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                try {
                    stmt.setString(1, email);
                    stmt.setString(2, password);
                    ResultSet rs = stmt.executeQuery();

                    try {
                        if (rs.next()) {
                            utilisateur = new Utilisateur(rs.getInt("id"), rs.getString("nom_utilisateur"), rs.getString("mot_de_passe"), rs.getString("email"), rs.getString("cv"), rs.getString("role"), rs.getTimestamp("date_creation"));
                        }

                        var8 = utilisateur;
                    } catch (Throwable var13) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var12) {
                                var13.addSuppressed(var12);
                            }
                        }

                        throw var13;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var14) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var11) {
                            var14.addSuppressed(var11);
                        }
                    }

                    throw var14;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var15) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var10) {
                        var15.addSuppressed(var10);
                    }
                }

                throw var15;
            }

            if (conn != null) {
                conn.close();
            }

            return var8;
        } catch (SQLException var16) {
            SQLException e = var16;
            throw new ServletException("Erreur de connexion à la base de données.", e);
        } catch (ClassNotFoundException var17) {
            ClassNotFoundException e = var17;
            throw new RuntimeException(e);
        }
    }

    public boolean checkemail(String email) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();

            boolean var18;
            try {
                boolean exist = false;
                String checkEmailSql = "SELECT id FROM utilisateurs WHERE email = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkEmailSql);

                try {
                    checkStmt.setString(1, email);

                    try {
                        ResultSet rs = checkStmt.executeQuery();

                        try {
                            if (rs.next()) {
                                exist = true;
                            }
                        } catch (Throwable var12) {
                            if (rs != null) {
                                try {
                                    rs.close();
                                } catch (Throwable var11) {
                                    var12.addSuppressed(var11);
                                }
                            }

                            throw var12;
                        }

                        if (rs != null) {
                            rs.close();
                        }
                    } catch (SQLException var13) {
                        SQLException e = var13;
                        throw new RuntimeException(e);
                    }
                } catch (Throwable var14) {
                    if (checkStmt != null) {
                        try {
                            checkStmt.close();
                        } catch (Throwable var10) {
                            var14.addSuppressed(var10);
                        }
                    }

                    throw var14;
                }

                if (checkStmt != null) {
                    checkStmt.close();
                }

                var18 = exist;
            } catch (Throwable var15) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var9) {
                        var15.addSuppressed(var9);
                    }
                }

                throw var15;
            }

            if (conn != null) {
                conn.close();
            }

            return var18;
        } catch (ClassNotFoundException var16) {
            ClassNotFoundException e = var16;
            throw new RuntimeException(e);
        }
    }

    public void insertuser(String username, String email, String password, String role) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO utilisateurs (nom_utilisateur, email, mot_de_passe, role) VALUES (?, ?, SHA2(?, 256), ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            try {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, role);
                stmt.executeUpdate();
            } catch (Throwable var11) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var10) {
                        var11.addSuppressed(var10);
                    }
                }

                throw var11;
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException var12) {
            SQLException e = var12;
            throw new RuntimeException(e);
        }
    }
}
