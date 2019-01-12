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
@Table(name = "avatar")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "Avatar.findAll", query = "SELECT a FROM Avatar a"),
    @NamedQuery(name = "Avatar.findById", query = "SELECT a FROM Avatar a WHERE a.id = :id"),
    @NamedQuery(name = "Avatar.findByName", query = "SELECT a FROM Avatar a WHERE a.name = :name"),
    @NamedQuery(name = "Avatar.findByDescription", query = "SELECT a FROM Avatar a WHERE a.description = :description")
  })
public class Avatar implements Serializable
  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "name")
    private String name;
    @Size(max = 30)
    @Column(name = "description")
    private String description;

    public Avatar()
      {
      }

    public Avatar(Integer id)
      {
        this.id = id;
      }

    public Integer getId()
      {
        return id;
      }

    public void setId(Integer id)
      {
        this.id = id;
      }

    public String getName()
      {
        return name;
      }

    public void setName(String name)
      {
        this.name = name;
      }

    public String getDescription()
      {
        return description;
      }

    public void setDescription(String description)
      {
        this.description = description;
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
        if (!(object instanceof Avatar))
          {
            return false;
          }
        Avatar other = (Avatar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "entities.Avatar[ id=" + id + " ]";
      }
    
  }
