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
@Table(name = "defenseWeapon")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "DefenseWeapon.findAll", query = "SELECT d FROM DefenseWeapon d"),
    @NamedQuery(name = "DefenseWeapon.findById", query = "SELECT d FROM DefenseWeapon d WHERE d.id = :id"),
    @NamedQuery(name = "DefenseWeapon.findByName", query = "SELECT d FROM DefenseWeapon d WHERE d.name = :name"),
    @NamedQuery(name = "DefenseWeapon.findByDefenseValue", query = "SELECT d FROM DefenseWeapon d WHERE d.defenseValue = :defenseValue"),
    @NamedQuery(name = "DefenseWeapon.findByRangeValue", query = "SELECT d FROM DefenseWeapon d WHERE d.rangeValue = :rangeValue"),
    @NamedQuery(name = "DefenseWeapon.findByDescription", query = "SELECT d FROM DefenseWeapon d WHERE d.description = :description")
  })
public class DefenseWeapon implements Serializable
  {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Size(max = 40)
    @Column(name = "name")
    private String name;
    @Column(name = "defense_value")
    private Integer defenseValue;
    @Column(name = "range_value")
    private Integer rangeValue;
    @Size(max = 200)
    @Column(name = "description")
    private String description;

    public DefenseWeapon()
      {
      }

    public DefenseWeapon(String id)
      {
        this.id = id;
      }

    public String getId()
      {
        return id;
      }

    public void setId(String id)
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

    public Integer getDefenseValue()
      {
        return defenseValue;
      }

    public void setDefenseValue(Integer defenseValue)
      {
        this.defenseValue = defenseValue;
      }

    public Integer getRangeValue()
      {
        return rangeValue;
      }

    public void setRangeValue(Integer rangeValue)
      {
        this.rangeValue = rangeValue;
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
        if (!(object instanceof DefenseWeapon))
          {
            return false;
          }
        DefenseWeapon other = (DefenseWeapon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "entities.DefenseWeapon[ id=" + id + " ]";
      }
    
  }
