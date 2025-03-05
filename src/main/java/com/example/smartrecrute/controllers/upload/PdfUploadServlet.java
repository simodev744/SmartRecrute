
package com.example.smartrecrute.controllers.upload;

import com.example.smartrecrute.daos.DaoCandidat;
import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet({"/candidat/uploadPdf"})
@MultipartConfig
public class PdfUploadServlet extends HttpServlet {
    private DaoCandidat daoCandidat;
    private final String UPLOAD_DIRECTORY = "uploads";

    public PdfUploadServlet() {
    }

    public void init() throws ServletException {
        try {
            this.daoCandidat = new DaoCandidat();
        } catch (ClassNotFoundException | SQLException var2) {
            Exception e = var2;
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        Part filePart = request.getPart("pdfFile");
        String fileName = this.getFileName(filePart);
        Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
        if (fileName != null && !fileName.isEmpty()) {
            String var10000 = this.getServletContext().getRealPath("");
            String uploadPath = var10000 + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            Path filePath = Paths.get(uploadPath + File.separator + fileName);
            InputStream inputStream = filePart.getInputStream();

            try {
                Files.copy(inputStream, filePath, new CopyOption[0]);
            } catch (Throwable var13) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable var12) {
                        var13.addSuppressed(var12);
                    }
                }

                throw var13;
            }

            if (inputStream != null) {
                inputStream.close();
            }

            this.daoCandidat.updateProfile("uploads/" + fileName, utilisateur.getId());
            response.sendRedirect("/candidat?action=profile");
        } else {
            response.getWriter().println("No file uploaded.");
        }

    }

    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        String[] var3 = header.split(";");
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String cd = var3[var5];
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf(61) + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
}
