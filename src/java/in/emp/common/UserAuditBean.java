package in.emp.common;

import java.sql.Date;

/**
 * This public Class use for accumulating User Audit information
 */

public class UserAuditBean implements java.io.Serializable
{	
	private long createdBy;
	private Date createdDt;
	private long updatedBy;
	private Date updatedDt;
	private String strOrderByColumnName;
	private String strSortOrder;
	private String status;
	
	/**
	 * Class constructor. Use to set default values.
	*/
	public UserAuditBean()
	{
		createdBy = -1;
		updatedBy = -1;		
	}

	/**
	 * Public API to set created by
	 * @param createdBy, created by
	 * @return void
	 */
	public void setCreatedBy(long createdBy)
	{
		this.createdBy = createdBy;
	}
	
	/**
	 * Public API to get created by
	 * @param void
	 * @return long createdBy, created by
	 */
	public long getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Public API to set created on
	 * @param createdDt, created on
	 * @return void
	 */
	public void setCreatedDt(Date createdDt)
	{
		this.createdDt = createdDt;
	}
	/**
	 * Public API to get created on date
	 * @param void
	 * @return Date createdDt, created on
	 */
	public Date getCreatedDt()
	{
		return createdDt;
	}

	/**
	 * Public API to set last modified by
	 * @param updatedBy, last modified by
	 * @return void
	 */
	public void setUpdatedBy(long updatedBy)
	{
		this.updatedBy = updatedBy;
	}
	
	/**
	 * Public API to get last modified by
	 * @param void
	 * @return long updatedBy, last modified by
	 */
	public long getUpdatedBy()
	{
		return updatedBy;
	}	

	/**
	 * Public API to set last modified on
	 * @param updatedDt, last modified on
	 * @return void
	 */
	public void setUpdatedDt(Date updatedDt)
	{
		this.updatedDt = updatedDt;
	}
	
	/**
	 * Public API to get last modified on
	 * @param void
	 * @return Date updatedDt, last modified on
	 */
	public Date getUpdatedDt()
	{
		return updatedDt;
	}	
		
	/**
	 * Public API to set order by column name
	 * @param strOrderByColumnName, order by column name
	 * @return void
	 */
	public void setOrderByColumnName(String strOrderByColumnName)
	{
		this.strOrderByColumnName = strOrderByColumnName;
	}
	
	/**
	 * Public API to get order by column name
	 * @param void
	 * @return String strOrderByColumnName, order by column name
	 */
	public String getOrderByColumnName()
	{
		return strOrderByColumnName;
	}
	
	/**
	 * Public API to set sort order
	 * @param strSortOrder, sort order
	 * @return void
	 */
	public void setSortOrder(String strSortOrder)
	{
		this.strSortOrder = strSortOrder;
	}
	
	/**
	 * Public API to get sort order
	 * @param void
	 * @return String strSortOrder, sort order
	 */
	public String getSortOrder()
	{
		return strSortOrder;
	}	

	/**
	 * Public API to set status
	 * @param statusCd, status
	 * @return void
	 */   
	public void setStatus(String status) 
	{
	this.status = status;
	}

	/**
	 * Public API to get status
	 * @param void
	 * @return String status, status
	 */
	public String getStatus()
	{
	return this.status;
	}
}