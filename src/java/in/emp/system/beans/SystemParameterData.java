package in.emp.system.beans;

/**
 * Data object for SystemParameter.
 *
 * @author	MDA
 * 
 */
public class SystemParameterData
   extends java.lang.Object
   implements java.io.Serializable
{
   static final long serialVersionUID = 3106716898337570234L;

   protected java.lang.Long parameterId;
   protected java.lang.String parameterName;
   protected java.lang.String parameterValue;
   protected java.lang.Long createdBy;
   protected java.util.Date createdDt;
   protected java.lang.String statusCd;
   protected java.lang.Long updatedBy;
   protected java.util.Date updatedDt;

   public SystemParameterData()
   {
   }

   public SystemParameterData( java.lang.Long parameterId,java.lang.String parameterName,java.lang.String parameterValue,java.lang.Long createdBy,java.util.Date createdDt,java.lang.String statusCd,java.lang.Long updatedBy,java.util.Date updatedDt )
   {
      this.parameterId = parameterId;
      this.parameterName = parameterName;
      this.parameterValue = parameterValue;
      this.createdBy = createdBy;
      this.createdDt = createdDt;
      this.statusCd = statusCd;
      this.updatedBy = updatedBy;
      this.updatedDt = updatedDt;
   }

   public SystemParameterData( SystemParameterData otherData )
   {
      this.parameterId = otherData.parameterId;
      this.parameterName = otherData.parameterName;
      this.parameterValue = otherData.parameterValue;
      this.createdBy = otherData.createdBy;
      this.createdDt = otherData.createdDt;
      this.statusCd = otherData.statusCd;
      this.updatedBy = otherData.updatedBy;
      this.updatedDt = otherData.updatedDt;

   }

   public java.lang.Long getParameterId()
   {
      return this.parameterId;
   }
   public void setParameterId( java.lang.Long parameterId )
   {
      this.parameterId = parameterId;
   }

   public java.lang.String getParameterName()
   {
      return this.parameterName;
   }
   public void setParameterName( java.lang.String parameterName )
   {
      this.parameterName = parameterName;
   }

   public java.lang.String getParameterValue()
   {
      return this.parameterValue;
   }
   public void setParameterValue( java.lang.String parameterValue )
   {
      this.parameterValue = parameterValue;
   }

   public java.lang.Long getCreatedBy()
   {
      return this.createdBy;
   }
   public void setCreatedBy( java.lang.Long createdBy )
   {
      this.createdBy = createdBy;
   }

   public java.util.Date getCreatedDt()
   {
      return this.createdDt;
   }
   public void setCreatedDt( java.util.Date createdDt )
   {
      this.createdDt = createdDt;
   }

   public java.lang.String getStatusCd()
   {
      return this.statusCd;
   }
   public void setStatusCd( java.lang.String statusCd )
   {
      this.statusCd = statusCd;
   }

   public java.lang.Long getUpdatedBy()
   {
      return this.updatedBy;
   }
   public void setUpdatedBy( java.lang.Long updatedBy )
   {
      this.updatedBy = updatedBy;
   }

   public java.util.Date getUpdatedDt()
   {
      return this.updatedDt;
   }
   public void setUpdatedDt( java.util.Date updatedDt )
   {
      this.updatedDt = updatedDt;
   }

   public String toString()
   {
      StringBuffer str = new StringBuffer("{");

      str.append("parameterId=" + parameterId + " " + "parameterName=" + parameterName + " " + "parameterValue=" + parameterValue + " " + "createdBy=" + createdBy + " " + "createdDt=" + createdDt + " " + "statusCd=" + statusCd + " " + "updatedBy=" + updatedBy + " " + "updatedDt=" + updatedDt);
      str.append('}');

      return(str.toString());
   }

   public boolean equals( Object pOther )
   {
      if( pOther instanceof SystemParameterData )
      {
         SystemParameterData lTest = (SystemParameterData) pOther;
         boolean lEquals = true;

         if( this.parameterId == null )
         {
            lEquals = lEquals && ( lTest.parameterId == null );
         }
         else
         {
            lEquals = lEquals && this.parameterId.equals( lTest.parameterId );
         }
         if( this.parameterName == null )
         {
            lEquals = lEquals && ( lTest.parameterName == null );
         }
         else
         {
            lEquals = lEquals && this.parameterName.equals( lTest.parameterName );
         }
         if( this.parameterValue == null )
         {
            lEquals = lEquals && ( lTest.parameterValue == null );
         }
         else
         {
            lEquals = lEquals && this.parameterValue.equals( lTest.parameterValue );
         }
         if( this.createdBy == null )
         {
            lEquals = lEquals && ( lTest.createdBy == null );
         }
         else
         {
            lEquals = lEquals && this.createdBy.equals( lTest.createdBy );
         }
         if( this.createdDt == null )
         {
            lEquals = lEquals && ( lTest.createdDt == null );
         }
         else
         {
            lEquals = lEquals && this.createdDt.equals( lTest.createdDt );
         }
         if( this.statusCd == null )
         {
            lEquals = lEquals && ( lTest.statusCd == null );
         }
         else
         {
            lEquals = lEquals && this.statusCd.equals( lTest.statusCd );
         }
         if( this.updatedBy == null )
         {
            lEquals = lEquals && ( lTest.updatedBy == null );
         }
         else
         {
            lEquals = lEquals && this.updatedBy.equals( lTest.updatedBy );
         }
         if( this.updatedDt == null )
         {
            lEquals = lEquals && ( lTest.updatedDt == null );
         }
         else
         {
            lEquals = lEquals && this.updatedDt.equals( lTest.updatedDt );
         }

         return lEquals;
      }
      else
      {
         return false;
      }
   }

   public int hashCode()
   {
      int result = 17;
      result = 37*result + ((this.parameterId != null) ? this.parameterId.hashCode() : 0);
      result = 37*result + ((this.parameterName != null) ? this.parameterName.hashCode() : 0);
      result = 37*result + ((this.parameterValue != null) ? this.parameterValue.hashCode() : 0);
      result = 37*result + ((this.createdBy != null) ? this.createdBy.hashCode() : 0);
      result = 37*result + ((this.createdDt != null) ? this.createdDt.hashCode() : 0);
      result = 37*result + ((this.statusCd != null) ? this.statusCd.hashCode() : 0);
      result = 37*result + ((this.updatedBy != null) ? this.updatedBy.hashCode() : 0);
      result = 37*result + ((this.updatedDt != null) ? this.updatedDt.hashCode() : 0);
      return result;
      }

}
