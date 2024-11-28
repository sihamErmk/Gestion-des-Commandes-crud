package org.example.atelier1.controller;


import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.atelier1.dao.ClientRepository;
import org.example.atelier1.entities.Client;
import org.example.atelier1.service.ClientService;

import java.io.IOException;
import java.util.List;




@WebServlet(name = "client", value = "/client/*")
public class ClientServlet extends HttpServlet {

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
                    this.listClientsAction(req, resp);
                    break;
                case "add":
                    this.addClientAction(req, resp);
                    break;
                case "edit":
                    this.editClientAction(req, resp);
                    break;
                case "delete":
                    this.deleteClientAction(req, resp);
                    break;
                case "save":
                    this.saveClientAction(req, resp);
                    break;
                case "update":
                    this.updateClientAction(req, resp);
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
        resp.sendRedirect(req.getContextPath() + "/client/list");
    }

    private void listClientsAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientService.getAllClients();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/WEB-INF/clients.jsp").forward(req, resp);
    }

    private void addClientAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/add-client.jsp").forward(req, resp);
    }

    private void editClientAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Client client = clientService.getClientById(id);
            req.setAttribute("client", client);
            req.getRequestDispatcher("/WEB-INF/edit-client.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Client ID is missing");
        }
    }

    private void deleteClientAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            clientService.deleteClient(id);
            resp.sendRedirect(req.getContextPath() + "/client/list");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Client ID is missing");
        }
    }

    private void saveClientAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String adresse = req.getParameter("adresse");
        String email = req.getParameter("email");

        if (nom != null && adresse != null && email != null) {
            Client client = new Client(null, nom, adresse, email);
            clientService.saveClient(client);
            resp.sendRedirect(req.getContextPath() + "/client/list");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }

    private void updateClientAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            String nom = req.getParameter("nom");
            String adresse = req.getParameter("adresse");
            String email = req.getParameter("email");

            if (nom != null && adresse != null && email != null) {
                Client client = new Client(id, nom, adresse, email);
                clientService.updateClient(client);
                resp.sendRedirect(req.getContextPath() + "/client/list");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Client ID is missing");
        }
    }
}
