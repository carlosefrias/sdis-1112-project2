/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "user_avatar")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "UserAvatar.findAll", query = "SELECT u FROM UserAvatar u"),
    @NamedQuery(name = "UserAvatar.findById", query = "SELECT u FROM UserAvatar u WHERE u.id = :id"),
    @NamedQuery(name = "UserAvatar.findByUserId", query = "SELECT u FROM UserAvatar u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserAvatar.findByIdattackweapon", query = "SELECT u FROM UserAvatar u WHERE u.idattackweapon = :idattackweapon"),
    @NamedQuery(name = "UserAvatar.findByIddefenseweapon", query = "SELECT u FROM UserAvatar u WHERE u.iddefenseweapon = :iddefenseweapon"),
    @NamedQuery(name = "UserAvatar.findByName", query = "SELECT u FROM UserAvatar u WHERE u.name = :name"),
    @NamedQuery(name = "UserAvatar.findByAvatarId", query = "SELECT u FROM UserAvatar u WHERE u.avatarId = :avatarId")
  })
public class UserAvatar implements Serializable
  {
    @Column(name = "location_id")
    private Integer locationId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idattackweapon")
    private int idattackweapon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddefenseweapon")
    private int iddefenseweapon;
    @Size(max = 40)
    @Column(name = "name")
    private String name;
    @Column(name = "avatar_id")
    private Integer avatarId;

    public UserAvatar()
      {
      }

    public UserAvatar(Integer id)
      {
        this.id = id;
      }

    public UserAvatar(Integer id, int idattackweapon, int iddefenseweapon)
      {
        this.id = id;
        this.idattackweapon = idattackweapon;
        this.iddefenseweapon = iddefenseweapon;
      }

    public Integer getId()
      {
        return id;
      }

    public void setId(Integer id)
      {
        this.id = id;
      }

    public Integer getUserId()
      {
        return userId;
      }

    public void setUserId(Integer userId)
      {
        this.userId = userId;
      }

    public int getIdattackweapon()
      {
        return idattackweapon;
      }

    public void setIdattackweapon(int idattackweapon)
      {
        this.idattackweapon = idattackweapon;
      }

    public int getIddefenseweapon()
      {
        return iddefenseweapon;
      }

    public void setIddefenseweapon(int iddefenseweapon)
      {
        this.iddefenseweapon = iddefenseweapon;
      }

    public String getName()
      {
        return name;
      }

    public void setName(String name)
      {
        this.name = name;
      }

    public Integer getAvatarId()
      {
        return avatarId;
      }

    public void setAvatarId(Integer avatarId)
      {
        this.avatarId = avatarId;
      }

    @Override
    public int hashCode()
      {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
      }

    @Override
    public boolean equals(Object object)
      {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAvatar))
          {
            return false;
          }
        UserAvatar other = (UserAvatar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "entities.UserAvatar[ id=" + id + " ]";
      }

    public Integer getLocationId()
      {
        return locationId;
      }

    public void setLocationId(Integer locationId)
      {
        this.locationId = locationId;
      }
    
  }
