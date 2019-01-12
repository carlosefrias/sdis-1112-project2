/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import entities.UserAvatar;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pedro
 */
@Stateless
@Path("entities.useravatar")
public class UserAvatarFacadeREST extends AbstractFacade<UserAvatar>
  {
    @PersistenceContext(unitName = "AndroidGamePU")
    private EntityManager em;

    public UserAvatarFacadeREST()
      {
        super(UserAvatar.class);
      }

    @POST
    @Override
    @Consumes(
      {
        "application/xml", "application/json"
      })
    public void create(UserAvatar entity)
      {
        super.create(entity);
      }

    @POST
    @Path("update_userAvatar")
    @Produces(
      {
        "application/xml", "application/json"
      })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void edit(@FormParam("id") int id,
                @FormParam("location_id") int location_id,
                @Context HttpServletResponse servletResponse)
      {
       UserAvatar u = find(id);
       u.setLocationId(location_id);
       super.edit(u);
      }


    @GET
    @Path("{id}")
    @Produces(
      {
        "application/xml", "application/json"
      })
    public UserAvatar find(@PathParam("id") Integer id)
      {
        return super.find(id);
      }

    @GET
    @Override
    @Produces(
      {
        "application/xml", "application/json"
      })
    public List<UserAvatar> findAll()
      {
        return super.findAll();
      }
                
    
    @GET
    @Path("{from}/{to}")
    @Produces(
      {
        "application/xml", "application/json"
      })
    public List<UserAvatar> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to)
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
    
    
@POST
@Path("new_status")
@Produces(
      {
        "application/xml", "application/json"
      })
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public void newStatus(
		@FormParam("user_id") int user_id,
		@FormParam("idattackweapon") int attack_id,
                @FormParam("iddefenseweapon") int defense_id,
                @FormParam("avatar_id") int avatar_id,
                @FormParam("location_id") int location_id,
		@Context HttpServletResponse servletResponse
) throws IOException {
	UserAvatar c = new UserAvatar();
        c.setUserId(user_id);
        c.setLocationId(location_id);
        c.setIdattackweapon(attack_id);
        c.setIddefenseweapon(defense_id);
        c.setAvatarId(avatar_id);
	super.create(c);
		
	
	//servletResponse.sendRedirect("../pages/new_contact.html");
}
    

    @java.lang.Override
    protected EntityManager getEntityManager()
      {
        return em;
      }
    
  }
