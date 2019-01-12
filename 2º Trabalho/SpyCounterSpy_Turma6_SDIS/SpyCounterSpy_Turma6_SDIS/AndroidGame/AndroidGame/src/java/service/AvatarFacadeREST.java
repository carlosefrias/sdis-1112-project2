/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Avatar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

/**
 *
 * @author Pedro
 */
@Stateless
@Path("entities.avatar")
public class AvatarFacadeREST extends AbstractFacade<Avatar>
  {
    @PersistenceContext(unitName = "AndroidGamePU")
    private EntityManager em;

    public AvatarFacadeREST()
      {
        super(Avatar.class);
      }

    @POST
    @Override
    @Consumes(
      {
        "application/xml", "application/json"
      })
    public void create(Avatar entity)
      {
        super.create(entity);
      }

    @PUT
    @Override
    @Consumes(
      {
        "application/xml", "application/json"
      })
    public void edit(Avatar entity)
      {
        super.edit(entity);
      }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id)
      {
        super.remove(super.find(id));
      }

    @GET
    @Path("{id}")
    @Produces(
      {
        "application/xml", "application/json"
      })
    public Avatar find(@PathParam("id") Integer id)
      {
        return super.find(id);
      }

    @GET
    @Override
    @Produces(
      {
        "application/xml", "application/json"
      })
    public List<Avatar> findAll()
      {
        return super.findAll();
      }

    @GET
    @Path("{from}/{to}")
    @Produces(
      {
        "application/xml", "application/json"
      })
    public List<Avatar> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to)
      {
        return super.findRange(new int[]{from, to});
      }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST()
      {
        return String.valueOf(super.count());
      }

    @java.lang.Override
    protected EntityManager getEntityManager()
      {
        return em;
      }
    
  }
