/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
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
@Path("entities.user")
public class UserFacadeREST extends AbstractFacade<User>
  {
    @PersistenceContext(unitName = "AndroidGamePU")
    private EntityManager em;

    public UserFacadeREST()
      {
        super(User.class);
      }

    @POST
    @Override
    @Consumes(
      {
        "application/xml", "application/json"
      })
    public void create(User entity)
      {
        super.create(entity);
      }

    @PUT
    @Override
    @Consumes(
      {
        "application/xml", "application/json"
      })
    public void edit(User entity)
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
    public User find(@PathParam("id") Integer id)
      {
        return super.find(id);
      }
    
    
    @POST
@Produces(
      {
        "application/xml", "application/json"
      })
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public void newPlayer(
		@FormParam("id") int id,
		@FormParam("name") String name,
                @FormParam("userName") String userName,
                @FormParam("email") String email,
		@Context HttpServletResponse servletResponse
) throws IOException {
	User c = new User(id);
        c.setName(name);
        c.setUserName(userName);
        c.setEmail(email);
	super.create(c);
		
	
	//servletResponse.sendRedirect("../pages/new_contact.html");
}
    
    @POST
    @Path("update_userPoints")
    @Produces(
      {
        "application/xml", "application/json"
      })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void editPoints(@FormParam("id") int id,
                @FormParam("points_1") int points1,
                @FormParam("id2") int id2,
                @FormParam("points_2") int points2,
                @Context HttpServletResponse servletResponse)
      {
       User u1 = find(id);
       User u2 = find(id2);
       u1.setPoints(points1);
       super.edit(u1);
       u2.setPoints(points2);
       super.edit(u2);
               
           }
    
    
    @GET
@Path("name")
@Produces(
      {
        "application/xml", "application/json"
      })
public User getByUserName ( @QueryParam("userName") String userName) 
  {
  
  CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> from = criteriaQuery.from(User.class);
    Predicate condition = criteriaBuilder.equal(from.get("userName"), userName);
    criteriaQuery.where(condition);
        TypedQuery<User> query =  em.createQuery(criteriaQuery);

    return  query.getSingleResult();
}

    
    @GET
@Path("login")
@Produces(
      {
        "application/xml", "application/json"
      })    
public User getByUserNameAndPassword ( @QueryParam("userName") String userName,
                                       @QueryParam("password") String password) 
  {
  
  CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> from = criteriaQuery.from(User.class);
    Predicate condition = criteriaBuilder.equal(from.get("userName"), userName);
    Predicate condition2 = criteriaBuilder.equal(from.get("password"), password);
    criteriaQuery.where(condition, condition2);
        TypedQuery<User> query =  em.createQuery(criteriaQuery);

    return  query.getSingleResult();
}    
    
    @GET
    @Override
    @Produces(
      {
        "application/xml", "application/json"
      })
    public List<User> findAll()
      {
        return super.findAll();
      }
    
    

    @GET
    @Path("{from}/{to}")
    @Produces(
      {
        "application/xml", "application/json"
      })
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to)
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
