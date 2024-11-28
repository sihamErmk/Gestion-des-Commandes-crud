package org.example.atelier1.controller;


import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.atelier1.entities.Client;
import org.example.atelier1.entities.Commande;
import org.example.atelier1.service.ClientService;
import org.example.atelier1.service.CommandeService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
@WebServlet(name = "commandes", value = "/commandes/*")
public class CommandeServlet extends HttpServlet {

    @Inject
    private CommandeService commandeService;

    @Inject
    private ClientService clientService;

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
                    this.listCommandesAction(req, resp);
                    break;
                case "add":
                    this.addCommandeAction(req, resp);
                    break;
                case "edit":
                    this.editCommandeAction(req, resp);
                    break;
                case "delete":
                    this.deleteCommandeAction(req, resp);
                    break;
                case "save":
                    this.saveCommandeAction(req, resp);
                    break;
                case "update":
                    this.updateCommandeAction(req, resp);
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
        resp.sendRedirect(req.getContextPath() + "/commandes/list");
    }

    private void listCommandesAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Commande> commandes = commandeService.getAllCommandes();
        req.setAttribute("commandes", commandes);
        req.getRequestDispatcher("/WEB-INF/commandes.jsp").forward(req, resp);
    }

    private void addCommandeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientService.getAllClients();
        System.out.println("Clients: " + clients); // Debug: Check if clients are being fetched
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/WEB-INF/add-commande.jsp").forward(req, resp);
    }

    private void editCommandeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Commande commande = commandeService.getCommandeById(id);

            if (commande != null) {
                // Fetch all clients for the dropdown list
                List<Client> clients = clientService.getAllClients();
                System.out.println("Clients: " + clients);

                // Set the commande and clients as request attributes
                req.setAttribute("commande", commande);
                req.setAttribute("clients", clients);

                // Forward to the edit form
                req.getRequestDispatcher("/WEB-INF/edit-commande.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Commande not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Commande ID is missing");
        }
    }


    private void deleteCommandeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            commandeService.deleteCommande(id);
            resp.sendRedirect(req.getContextPath() + "/commandes/list");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Commande ID is missing");
        }
    }

    private void saveCommandeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date dateCommande = Date.valueOf(req.getParameter("dateCommande"));
        Long clientId = Long.valueOf(req.getParameter("clientId"));

        Client client = new Client();
        client.setId(clientId);

        Commande commande = new Commande();
        commande.setDateCommande(dateCommande);
        commande.setClient(client);

        commandeService.saveCommande(commande);
        resp.sendRedirect(req.getContextPath() + "/commandes/list");
    }

    private void updateCommandeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Date dateCommande = Date.valueOf(req.getParameter("dateCommande"));
            Long clientId = Long.valueOf(req.getParameter("clientId"));

            Client client = new Client();
            client.setId(clientId);

            Commande commande = new Commande();
            commande.setId(id);
            commande.setDateCommande(dateCommande);
            commande.setClient(client);

            commandeService.updateCommande(commande);
            resp.sendRedirect(req.getContextPath() + "/commandes/list");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Commande ID is missing");
        }
    }
}
