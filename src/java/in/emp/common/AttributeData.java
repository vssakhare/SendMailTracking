package in.emp.common;

/**
 * Data object for update Attribute.
 *
 * @author MDA
 *
 */
public class AttributeData extends java.lang.Object implements java.io.Serializable
{
	private Object columnValue;
	private String columnType;

	/*
	*Constructor for AttributeData
	*/
	public AttributeData(Object columnValue,String columnType)
	{
		this.columnValue = columnValue;	
		this.columnType = columnType;	
	}

	/**
	 * Returns columnValue
	 * @return columnValue
	 */
	public Object getColumnValue()
	{
		return columnValue;
	}
	/**
	 * Sets the columnValue
	 * @param columnValue
	 */
	public void setColumnValue(Object columnValue)
	{
		this.columnValue = columnValue;		
	}
	/**
	 * Returns The columnType
	 * @return the columnType
	 */
	public String getColumnType()
	{
		return columnType;
	}
	/**
	 * Sets the columnType
	 * @param columnType
	 */
	public void setColumnType(String columnType)
	{
		this.columnType = columnType;		
	}
}
