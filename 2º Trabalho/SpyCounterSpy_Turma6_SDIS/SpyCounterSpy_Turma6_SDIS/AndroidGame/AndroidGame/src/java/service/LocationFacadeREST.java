/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entities.Location;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pedro
 */
@Stateless
@Path("entities.location")
public class LocationFacadeREST extends AbstractFacade<Location>
  {
    @PersistenceContext(unitName = "AndroidGamePU")
    private EntityManager em;

    public LocationFacadeREST()
      {
        super(Location.class);
      }

    @POST
    @Override
    @Consumes(
      {
        "application/xml", "application/json"
      })
    public void create(Location entity)
      {
        super.create(entity);
      }
    
@POST
@Path("new_local")
@Produces(
      {
        "application/xml", "application/json"
      })
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public void newLocation(
		@FormParam("latitude") String latitude,
                @FormParam("longitude") String longitude,
		@Context HttpServletResponse servletResponse
) throws IOException {
	Location l = new Location();
        l.setLatitude(latitude);
        l.setLongitude(longitude);
	super.create(l);
		
	
	//servletResponse.sendRedirect("../pages/new_contact.html");
}

    @PUT
  //  @Override
    @Path("update_local")
    @Consumes(
      {
        "application/xml", "application/json"
      })
    public void edit(
                @FormParam("id") int id,
                @FormParam("latitude") String latitude,
                @FormParam("longitude") String longitude,
		@Context HttpServletResponse servletResponse
        ) throws IOException
      {
        Location l = find(id);
        l.setLatitude(latitude);
        l.setLongitude(longitude);
        super.edit(l);
      }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id)
      {
        super.remove(super.find(id));
      }

    @GET
    @Path("{id}")
    @Produces(
      {
        "application/xml", "application/json"
      })
    public Location find(@PathParam("id") int id)
      {
        return super.find(id);
      }

    @GET
    @Override
    @Produces(
      {
        "application/xml", "application/json"
      })
    public List<Location> findAll()
      {
        return super.findAll();
      }

    @GET
    @Path("{from}/{to}")
    @Produces(
      {
        "application/xml", "application/json"
      })
    public List<Location> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to)
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
