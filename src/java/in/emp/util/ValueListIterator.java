package in.emp.util;

import java.util.List;
import java.io.Serializable;

public interface ValueListIterator extends java.io.Serializable
{
	public void setList(List list) throws IteratorException; 

	public int getSize() throws IteratorException;

	public Object getCurrentElement() throws IteratorException;

	public List getPreviousElements(int count) throws IteratorException;

	public List getNextElements(int count) throws IteratorException;

	public List getSameElements(int pageSize, int firstRow, int lastRow) throws IteratorException;

	public List getSameElements(int pageSize, int firstRow, int lastRow,int cachePageSize) throws IteratorException;

	public void resetIndex() throws IteratorException;

	public boolean hasNext() throws IteratorException;

	public boolean hasPrevious(int count) throws IteratorException;

	public int getFirstRow(int pageSize, int lastRow) throws IteratorException; 

	public int getLastRow() throws IteratorException; 

	public void setTotalSize(double totalSize); 

	public double getTotalSize(); 


  // other common methods as required
}