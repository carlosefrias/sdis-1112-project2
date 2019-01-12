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
@Table(name = "attackWeapon")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "AttackWeapon.findAll", query = "SELECT a FROM AttackWeapon a"),
    @NamedQuery(name = "AttackWeapon.findById", query = "SELECT a FROM AttackWeapon a WHERE a.id = :id"),
    @NamedQuery(name = "AttackWeapon.findByName", query = "SELECT a FROM AttackWeapon a WHERE a.name = :name"),
    @NamedQuery(name = "AttackWeapon.findByAttackValue", query = "SELECT a FROM AttackWeapon a WHERE a.attackValue = :attackValue"),
    @NamedQuery(name = "AttackWeapon.findByRangeValue", query = "SELECT a FROM AttackWeapon a WHERE a.rangeValue = :rangeValue"),
    @NamedQuery(name = "AttackWeapon.findByDescription", query = "SELECT a FROM AttackWeapon a WHERE a.description = :description")
  })
public class AttackWeapon implements Serializable
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
    @Column(name = "attack_value")
    private Integer attackValue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "range_value")
    private Double rangeValue;
    @Size(max = 200)
    @Column(name = "description")
    private String description;

    public AttackWeapon()
      {
      }

    public AttackWeapon(String id)
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

    public Integer getAttackValue()
      {
        return attackValue;
      }

    public void setAttackValue(Integer attackValue)
      {
        this.attackValue = attackValue;
      }

    public Double getRangeValue()
      {
        return rangeValue;
      }

    public void setRangeValue(Double rangeValue)
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
        if (!(object instanceof AttackWeapon))
          {
            return false;
          }
        AttackWeapon other = (AttackWeapon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "entities.AttackWeapon[ id=" + id + " ]";
      }
    
  }
