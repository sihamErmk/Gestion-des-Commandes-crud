package org.example.atelier1.controller;


import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.atelier1.entities.Commande;
import org.example.atelier1.entities.LigneDeCommande;
import org.example.atelier1.entities.Produit;
import org.example.atelier1.service.CommandeService;
import org.example.atelier1.service.LigneDeCommandeService;
import org.example.atelier1.service.ProduitService;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "ligneDeCommande", value = "/ligneDeCommande/*")
public class LigneDeCommandeServlet extends HttpServlet {

    @Inject
    private LigneDeCommandeService ligneDeCommandeService;

    @Inject
    private CommandeService commandeService;

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
                    this.listLignesDeCommandeAction(req, resp);
                    break;
                case "add":
                    this.addLigneDeCommandeFormAction(req, resp);
                    break;
                case "edit":
                    this.editLigneDeCommandeFormAction(req, resp);
                    break;
                case "delete":
                    this.deleteLigneDeCommandeAction(req, resp);
                    break;
                case  "save":
                    this.saveLigneDeCommandeAction(req, resp);
                    break;

                default:
                    this.defaultAction(req, resp);
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
        resp.sendRedirect(req.getContextPath() + "/ligneDeCommande/list");
    }

    private void listLignesDeCommandeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LigneDeCommande> lignesDeCommande = ligneDeCommandeService.getAllLignesDeCommande();
        req.setAttribute("lignesDeCommande", lignesDeCommande);
        req.getRequestDispatcher("/WEB-INF/lignes-de-commande.jsp").forward(req, resp);
    }

    private void addLigneDeCommandeFormAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Commande> commandes = commandeService.getAllCommandes();
        List<Produit> produits = produitService.getAllProduits();
        System.out.println(commandes);
        System.out.println(produits);
        req.setAttribute("commandes", commandes);
        req.setAttribute("produits", produits);
        req.getRequestDispatcher("/WEB-INF/add-ligne-de-commande.jsp").forward(req, resp);
    }

    private void editLigneDeCommandeFormAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            LigneDeCommande ligneDeCommande = ligneDeCommandeService.getLigneDeCommandeById(id);

            if (ligneDeCommande != null) {
                List<Commande> commandes = commandeService.getAllCommandes();
                List<Produit> produits = produitService.getAllProduits();

                req.setAttribute("ligneDeCommande", ligneDeCommande);
                req.setAttribute("commandes", commandes);
                req.setAttribute("produits", produits);

                req.getRequestDispatcher("/WEB-INF/edit-ligne-de-commande.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ligne de commande not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ID");
        }
    }



    private void saveLigneDeCommandeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long commandeId = Long.valueOf(req.getParameter("commandeId"));
            Long produitId = Long.valueOf(req.getParameter("produitId"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));

            // Fetch Commande and Produit
            Commande commande = commandeService.getCommandeById(commandeId);
            Produit produit = produitService.getProduitById(produitId);

            // Create new LigneDeCommande
            LigneDeCommande ligneDeCommande = new LigneDeCommande();
            ligneDeCommande.setCommande(commande);
            ligneDeCommande.setProduit(produit);
            ligneDeCommande.setQuantite(quantite);

            // Save it
            ligneDeCommandeService.saveLigneDeCommande(ligneDeCommande);

            // Redirect to list
            resp.sendRedirect(req.getContextPath() + "/ligneDeCommande/list");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to save Ligne De Commande: " + e.getMessage());
        }
    }

    private void deleteLigneDeCommandeAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            ligneDeCommandeService.deleteLigneDeCommande(id);
            resp.sendRedirect(req.getContextPath() + "/ligneDeCommande/list");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ID");
        }
    }
}
