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
@Table(name = "location")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findById", query = "SELECT l FROM Location l WHERE l.id = :id"),
    @NamedQuery(name = "Location.findByLatitude", query = "SELECT l FROM Location l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "Location.findByLongitude", query = "SELECT l FROM Location l WHERE l.longitude = :longitude")
  })
public class Location implements Serializable
  {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private int id;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;

    public Location()
      {
      }

    public Location(int id)
      {
        this.id = id;
      }

    public int getId()
      {
        return id;
      }

    public void setId(int id)
      {
        this.id = id;
      }

    public String getLatitude()
      {
        return latitude;
      }

    public void setLatitude(String latitude)
      {
        this.latitude = latitude;
      }

    public String getLongitude()
      {
        return longitude;
      }

    public void setLongitude(String longitude)
      {
        this.longitude = longitude;
      }

    @Override
    public String toString()
      {
        return "entities.Location[ id=" + id + " ]";
      }
    
  }
