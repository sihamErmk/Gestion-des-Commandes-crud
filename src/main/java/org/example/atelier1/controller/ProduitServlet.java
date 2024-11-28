package org.example.atelier1.controller;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.atelier1.entities.Produit;
import org.example.atelier1.service.ProduitService;

import java.io.IOException;
import java.util.List;



@WebServlet(name = "produit", value = "/produit/*")
public class ProduitServlet extends HttpServlet {

    @Inject
    private ProduitService produitService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        if (action == null || action.equals("/")) {
            action = "default";
        } else {
            action = action.substring(1);
        }

        try {
            switch (action) {
                case "list":
                    listProduitsAction(req, resp);
                    break;
                case "add":
                    addProduitAction(req, resp);
                    break;
                case "edit":
                    editProduitAction(req, resp);
                    break;
                case "delete":
                    deleteProduitAction(req, resp);
                    break;
                case "save":
                    saveProduitAction(req, resp);
                    break;
                case "update":
                    updateProduitAction(req, resp);
                    break;
                default:
                    defaultAction(req, resp);
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void defaultAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/produit/list");
    }

    private void listProduitsAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produit> produits = produitService.getAllProduits();
        req.setAttribute("produits", produits);
        req.getRequestDispatcher("/WEB-INF/produits.jsp").forward(req, resp);
    }

    private void addProduitAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/add-produit.jsp").forward(req, resp);
    }

    private void editProduitAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Produit produit = produitService.getProduitById(id);
            req.setAttribute("produit", produit);
            req.getRequestDispatcher("/WEB-INF/edit-produit.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Produit ID is missing");
        }
    }

    private void deleteProduitAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            produitService.deleteProduit(id);
            resp.sendRedirect(req.getContextPath() + "/produit/list");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Produit ID is missing");
        }
    }

    private void saveProduitAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prixParam = req.getParameter("prix");
        System.out.println("Nom: " + nom + ", Prix: " + prixParam);

        if (nom != null && prixParam != null) {
            double prix = Double.parseDouble(prixParam);
            Produit produit = new Produit(null, nom, prix);

            System.out.println(produit);
            produitService.saveProduit(produit);
            resp.sendRedirect(req.getContextPath() + "/produit/list");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }

    private void updateProduitAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            String nom = req.getParameter("nom");
            String prixParam = req.getParameter("prix");

            if (nom != null && prixParam != null) {
                double prix = Double.parseDouble(prixParam);
                Produit produit = new Produit(id, nom, prix);
                produitService.updateProduit(produit);
                resp.sendRedirect(req.getContextPath() + "/produit/list");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Produit ID is missing");
        }
    }
}
