/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.service;

import entities.Login;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author junxin
 */

@Path("entities.login")
public class LoginFacadeREST extends AbstractFacade<Login> {
    @PersistenceContext(unitName = "RestAppPU")
    
    @Context
    private UriInfo context;
    private EntityManager em;

    public LoginFacadeREST() {
        super(Login.class);
        em = Persistence
                .createEntityManagerFactory("RestAppPU")
                .createEntityManager();
    }
    
    @GET
    @Path ("time")
    @Produces("text/html")
    public String getHtml() {
        //TODO return proper representation object
        return new Date().toString().toUpperCase();
    }

    
    @GET
    @Path ("users")
    @Produces({"application/xml", "application/json"})
    public List<Login> getUsers() {
        //TODO return proper representation object
        if (em != null)
            return em.createQuery("SELECT L FROM Login L").getResultList();
        else {
            throw new WebApplicationException(Response.status(400)
                    .entity("Null entity manager")
                    .build());
        }
    }
    
    @GET
    @Path ("password")
    @Produces({"application/xml", "application/json"})
    public String getPassword() {
        //TODO return proper representation object
        List<Login> users=getUsers();
        String s="";
        for(Login l:users){
            s+=l.getPassword()+"<br>";
        }
        return s;
    }

    /**
     * PUT method for updating or creating an instance of ServiceProvider
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Login entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Login entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Login find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Login> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Login> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
