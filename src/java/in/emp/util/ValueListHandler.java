package in.emp.util;

import java.util.*;
import java.lang.Math;
import java.io.Serializable;

public class ValueListHandler implements ValueListIterator, java.io.Serializable
{

	protected List list;
	protected ListIterator listIterator;
	protected double totalSize;

	public ValueListHandler() {  }

	public void setList(List list) throws IteratorException 
	{
		this.list = list;
		if(list != null)
			listIterator =  list.listIterator();
		else
			throw new IteratorException("List empty");
	}

	public Collection getList() throws IteratorException
	{
		return list;
	}
    
	public int getSize() throws IteratorException
	{
		int size = 0;
		
		if (list != null)
			size = list.size();
		else
			throw new IteratorException(); //No Data

		return size;
	}
    
	public Object getCurrentElement() throws IteratorException 
	{

		Object obj = null;
		// Will not advance iterator
		if (list != null)
		{
		  int currIndex = listIterator.nextIndex();
		  obj = list.get(currIndex);
		}
		else
		  throw new IteratorException();
		return obj;
	}
    
	public List getPreviousElements(int pageSize) throws IteratorException 
	{
		
		Object object = null;
		LinkedList linkedlist = new LinkedList();
		if (listIterator != null)
		{
			int currentIndex = listIterator.nextIndex() - 1;
			int modVal = currentIndex % pageSize;
			int temp = pageSize - modVal;
			int temp1 = currentIndex - pageSize;
			int lastVal = temp + temp1;
			int firstVal = lastVal - pageSize;

			while (firstVal != lastVal)
			{
				object = list.get(firstVal);
				linkedlist.add(object);
				firstVal ++;
			}

			//set listIterators index to last value
			int i = 0;
			resetIndex();
			while(i != lastVal)
			{
				listIterator.next();
				i++;
			}
			
		}// end if
		else
			throw new IteratorException(); // No data

		return linkedlist;
	}
    
	public List getNextElements(int count) throws IteratorException 
	{
		int i = 0;
		Object object = null;
		LinkedList list = new LinkedList();
		
		if(listIterator != null)
		{
			while(  listIterator.hasNext() )
			{
				if(i < count)
				{
					object = listIterator.next();
					list.add(object);
				}
				else
					break;

				i++;
			}
				
		} // end if
		else
			throw new IteratorException(); // No data

		return list;
	}

	public List getSameElements(int pageSize, int firstRow, int lastRow) throws IteratorException 
	{
		Object object = null;
		LinkedList linkedlist = new LinkedList();

		firstRow = lastRow - pageSize;
		
		//-- this situation occurs in the last page.
		if(lastRow % pageSize > 0)
		{
			int temp = lastRow / pageSize;
			firstRow = temp * pageSize;
			lastRow = getSize();
		}

		//-- this situation occurs when there is only one record in the page.
		if(firstRow == lastRow)
		{
			firstRow = lastRow - pageSize;
		}

		//-- just to ensure that the lastRow is always less than or equal to the size of our list.
		if(lastRow > getSize())
		{
			lastRow = getSize();
		}

		//System.out.println("ValueList Handler :: get Same Elements :: last Row" + lastRow);
		//System.out.println("ValueList Handler :: get Same Elements :: firstRow" + firstRow);
		if (listIterator != null)
		{
			while (firstRow != lastRow)
			{
				object = list.get(firstRow);
				linkedlist.add(object);
				firstRow ++;
			}

			//set listIterators index to last value
			int i = 0;
			resetIndex();
			while(i != lastRow)
			{
				listIterator.next();
				i++;
			}
		}// end if
		else
			throw new IteratorException(); // No data

		return linkedlist;
	}

	public List getSameElements(int pageSize, int firstRow, int lastRow,int cachePageSize) throws IteratorException 
	{
		Object object = null;
		LinkedList linkedlist = new LinkedList();

		int pageNo = lastRow/pageSize;
		int mod = pageNo%cachePageSize;

		if(mod == 0)
			mod = cachePageSize;

		lastRow = mod*pageSize;
		firstRow = mod*pageSize;
		
		// reducing the value of lastrow and first row on the basis of cache page size
		/*if (lastRow > pageSize*cachePageSize)
		{
			lastRow = ((lastRow/pageSize) % cachePageSize)*pageSize;
			firstRow = ((firstRow/pageSize) % cachePageSize)*pageSize;
		}
	
	/*	if (lastRow % cachePageSize == 0)
		{
			lastRow = cachePageSize*pageSize;
		}

		if (firstRow % cachePageSize == 0)
		{
			firstRow = cachePageSize*pageSize;
		}
		
		if (lastRow % cachePageSize == 0)
		{
			lastRow = Math.abs(lastRow/cachePageSize - pageSize)*pageSize;
		}*/

	
		firstRow = lastRow - pageSize;
		
		//-- this situation occurs in the last page.
		if(lastRow % pageSize > 0)
		{
			int temp = lastRow / pageSize;
			firstRow = temp * pageSize;
			lastRow = getSize();
		}

		//-- this situation occurs when there is only one record in the page.
		if(firstRow == lastRow)
		{
			firstRow = lastRow - pageSize;
		}

		//-- just to ensure that the lastRow is always less than or equal to the size of our list.
		if(lastRow > getSize())
		{
			lastRow = getSize();
		}

		//System.out.println("ValueList Handler :: get Same Elements :: last Row" + lastRow);
		//System.out.println("ValueList Handler :: get Same Elements :: firstRow" + firstRow);
		if (listIterator != null)
		{
			while (firstRow != lastRow)
			{
				object = list.get(firstRow);
				linkedlist.add(object);
				firstRow ++;
			}

			//set listIterators index to last value
			int i = 0;
			resetIndex();
			while(i != lastRow)
			{
				listIterator.next();
				i++;
			}
		}// end if
		else
			throw new IteratorException(); // No data

		return linkedlist;
	}

	public void resetIndex() throws IteratorException
	{
		if(listIterator != null)
		{
		  listIterator = list.listIterator();
		}
		else
		  throw new IteratorException(); // No data
	}

	public boolean hasNext() throws IteratorException
	{
		if(listIterator != null)
		{
			return listIterator.hasNext();
		}
		
		return false;
	}

	public boolean hasPrevious(int pageSize) throws IteratorException
	{
		if(listIterator != null)
		{
			if( ( (listIterator.nextIndex()) - pageSize ) > 0 )
			return true;
		}
				
			return false;
	}

	public int getFirstRow(int pageSize, int lastRow) throws IteratorException 
	{
		int firstRow = 0;
		if (listIterator != null)
		{
			firstRow = (lastRow - pageSize) + 1;
			if(lastRow % pageSize > 0)
			{
				int temp = lastRow / pageSize;
				firstRow = (temp * pageSize) + 1;
			}
		}// end if
		else
			throw new IteratorException(); // No data

		return firstRow;
	}

	public int getLastRow() throws IteratorException 
	{
		int lastRow = 0;
		if (listIterator != null)
		{
			lastRow = listIterator.nextIndex();
		}// end if
		else
			throw new IteratorException(); // No data

		return lastRow;
	}

	public void setTotalSize(double totalSize)
	{
		this.totalSize = totalSize;
	}

	public double getTotalSize()
	{
		return totalSize;
	}
}//end class