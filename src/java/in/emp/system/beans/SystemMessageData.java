package in.emp.system.beans;

/**
 * Data object for SystemMessage.
 *
 * @author	MDA
 * 
 */
public class SystemMessageData
   extends java.lang.Object
   implements java.io.Serializable
{
   static final long serialVersionUID = -1269042769923235681L;

   protected java.lang.Long sysMessageId;
   protected java.lang.String sysMessageCd;
   protected java.lang.String sysMessage;
   protected java.lang.String sysMessageSeverity;
   protected java.lang.String statusCd;
   protected java.lang.Long createdBy;
   protected java.util.Date createdDt;
   protected java.lang.Long updatedBy;
   protected java.util.Date updatedDt;

   public SystemMessageData()
   {
   }

   public SystemMessageData( java.lang.Long sysMessageId,java.lang.String sysMessageCd,java.lang.String sysMessage,java.lang.String sysMessageSeverity,java.lang.String statusCd,java.lang.Long createdBy,java.util.Date createdDt,java.lang.Long updatedBy,java.util.Date updatedDt )
   {
      this.sysMessageId = sysMessageId;
      this.sysMessageCd = sysMessageCd;
      this.sysMessage = sysMessage;
      this.sysMessageSeverity = sysMessageSeverity;
      this.statusCd = statusCd;
      this.createdBy = createdBy;
      this.createdDt = createdDt;
      this.updatedBy = updatedBy;
      this.updatedDt = updatedDt;
   }

   public SystemMessageData( SystemMessageData otherData )
   {
      this.sysMessageId = otherData.sysMessageId;
      this.sysMessageCd = otherData.sysMessageCd;
      this.sysMessage = otherData.sysMessage;
      this.sysMessageSeverity = otherData.sysMessageSeverity;
      this.statusCd = otherData.statusCd;
      this.createdBy = otherData.createdBy;
      this.createdDt = otherData.createdDt;
      this.updatedBy = otherData.updatedBy;
      this.updatedDt = otherData.updatedDt;

   }

   public java.lang.Long getSysMessageId()
   {
      return this.sysMessageId;
   }
   public void setSysMessageId( java.lang.Long sysMessageId )
   {
      this.sysMessageId = sysMessageId;
   }

   public java.lang.String getSysMessageCd()
   {
      return this.sysMessageCd;
   }
   public void setSysMessageCd( java.lang.String sysMessageCd )
   {
      this.sysMessageCd = sysMessageCd;
   }

   public java.lang.String getSysMessage()
   {
      return this.sysMessage;
   }
   public void setSysMessage( java.lang.String sysMessage )
   {
      this.sysMessage = sysMessage;
   }

   public java.lang.String getSysMessageSeverity()
   {
      return this.sysMessageSeverity;
   }
   public void setSysMessageSeverity( java.lang.String sysMessageSeverity )
   {
      this.sysMessageSeverity = sysMessageSeverity;
   }

   public java.lang.String getStatusCd()
   {
      return this.statusCd;
   }
   public void setStatusCd( java.lang.String statusCd )
   {
      this.statusCd = statusCd;
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

      str.append("sysMessageId=" + sysMessageId + " " + "sysMessageCd=" + sysMessageCd + " " + "sysMessage=" + sysMessage + " " + "sysMessageSeverity=" + sysMessageSeverity + " " + "statusCd=" + statusCd + " " + "createdBy=" + createdBy + " " + "createdDt=" + createdDt + " " + "updatedBy=" + updatedBy + " " + "updatedDt=" + updatedDt);
      str.append('}');

      return(str.toString());
   }

   public boolean equals( Object pOther )
   {
      if( pOther instanceof SystemMessageData )
      {
         SystemMessageData lTest = (SystemMessageData) pOther;
         boolean lEquals = true;

         if( this.sysMessageId == null )
         {
            lEquals = lEquals && ( lTest.sysMessageId == null );
         }
         else
         {
            lEquals = lEquals && this.sysMessageId.equals( lTest.sysMessageId );
         }
         if( this.sysMessageCd == null )
         {
            lEquals = lEquals && ( lTest.sysMessageCd == null );
         }
         else
         {
            lEquals = lEquals && this.sysMessageCd.equals( lTest.sysMessageCd );
         }
         if( this.sysMessage == null )
         {
            lEquals = lEquals && ( lTest.sysMessage == null );
         }
         else
         {
            lEquals = lEquals && this.sysMessage.equals( lTest.sysMessage );
         }
         if( this.sysMessageSeverity == null )
         {
            lEquals = lEquals && ( lTest.sysMessageSeverity == null );
         }
         else
         {
            lEquals = lEquals && this.sysMessageSeverity.equals( lTest.sysMessageSeverity );
         }
         if( this.statusCd == null )
         {
            lEquals = lEquals && ( lTest.statusCd == null );
         }
         else
         {
            lEquals = lEquals && this.statusCd.equals( lTest.statusCd );
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
      result = 37*result + ((this.sysMessageId != null) ? this.sysMessageId.hashCode() : 0);
      result = 37*result + ((this.sysMessageCd != null) ? this.sysMessageCd.hashCode() : 0);
      result = 37*result + ((this.sysMessage != null) ? this.sysMessage.hashCode() : 0);
      result = 37*result + ((this.sysMessageSeverity != null) ? this.sysMessageSeverity.hashCode() : 0);
      result = 37*result + ((this.statusCd != null) ? this.statusCd.hashCode() : 0);
      result = 37*result + ((this.createdBy != null) ? this.createdBy.hashCode() : 0);
      result = 37*result + ((this.createdDt != null) ? this.createdDt.hashCode() : 0);
      result = 37*result + ((this.updatedBy != null) ? this.updatedBy.hashCode() : 0);
      result = 37*result + ((this.updatedDt != null) ? this.updatedDt.hashCode() : 0);
      return result;
      }

}
