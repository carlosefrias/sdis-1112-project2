/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "location_log")
@XmlRootElement
@NamedQueries(
  {
    @NamedQuery(name = "LocationLog.findAll", query = "SELECT l FROM LocationLog l"),
    @NamedQuery(name = "LocationLog.findById", query = "SELECT l FROM LocationLog l WHERE l.id = :id"),
    @NamedQuery(name = "LocationLog.findByUserId", query = "SELECT l FROM LocationLog l WHERE l.userId = :userId"),
    @NamedQuery(name = "LocationLog.findByLocationId", query = "SELECT l FROM LocationLog l WHERE l.locationId = :locationId"),
    @NamedQuery(name = "LocationLog.findByCurTimestamp", query = "SELECT l FROM LocationLog l WHERE l.curTimestamp = :curTimestamp")
  })
public class LocationLog implements Serializable
  {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "cur_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date curTimestamp;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 10)
    @Column(name = "location_id")
    private String locationId;

    public LocationLog()
      {
      }

    public LocationLog(String id)
      {
        this.id = id;
      }

    public LocationLog(String id, Date curTimestamp)
      {
        this.id = id;
        this.curTimestamp = curTimestamp;
      }

    public String getId()
      {
        return id;
      }

    public void setId(String id)
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

    public String getLocationId()
      {
        return locationId;
      }

    public void setLocationId(String locationId)
      {
        this.locationId = locationId;
      }

    public Date getCurTimestamp()
      {
        return curTimestamp;
      }

    public void setCurTimestamp(Date curTimestamp)
      {
        this.curTimestamp = curTimestamp;
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
        if (!(object instanceof LocationLog))
          {
            return false;
          }
        LocationLog other = (LocationLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
          {
            return false;
          }
        return true;
      }

    @Override
    public String toString()
      {
        return "entities.LocationLog[ id=" + id + " ]";
      }

  /*  public Date getCurTimestamp()
      {
        return curTimestamp;
      }

    public void setCurTimestamp(Date curTimestamp)
      {
        this.curTimestamp = curTimestamp;
      }*/
//
//    public Date getCurTimestamp()
//      {
//        return curTimestamp;
//      }
//
//    public void setCurTimestamp(Date curTimestamp)
//      {
//        this.curTimestamp = curTimestamp;
//      }
    
  }
