/*******************************************************************************
 * Copyright (c) 2013 INTERAMERICAN PROPERTY AND CASUALTY INSURANCE COMPANY S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/copyleft/lesser.html
 * 
 * This library is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 ******************************************************************************/
package gr.interamerican.bo2.utils.doc;


/**
 * Utilities.
 */
public class DocumentUtils {

	/**
	 * This is a utility class having only static methods.
	 */
	private DocumentUtils() {/*empty*/}
	
	/**
	 * This method adds a row to the end of the documentTable if needed.
	 * That means that if and only if the rows are shown at the template are used we add a new one.
	 * 
	 * @param documentTable
	 * @param rowPointer
	 * @throws DocumentEngineException
	 */
	public static void appendRowIfNeeded(DocumentTable documentTable, int rowPointer) throws DocumentEngineException{
		if(documentTable.getRowCount()==rowPointer){
			documentTable.appendRow();
		}
	}
	
	

	
	/**
	 * ������� ��� ������� �� �� columnIndex ���������� �� ����� ��� ������ table ��� �������, ������ 
	 * �� columnIndex �� ����� ���������� table.maxColumn 
	 * �� � ������� ���� ��������� ������ ��� �� columnIndex ���� ������ DocumentEngineException().
	 * @param table o ������� ��� ������������.
	 * @param tableName �� ����� ��� ������, ��� error message.
	 * @param columnIndex � �������� ������� ������ ��� ������� �� ���� � �������.
	 * @throws DocumentEngineException
	 */
	public static void validateColumnCount(DocumentTable table, String tableName,int columnIndex) throws DocumentEngineException{
		if(columnIndex >= table.getColumnCount()){
			String message = "Column index exceeds the number of columns of table "  + tableName; //$NON-NLS-1$ 
			throw new DocumentEngineException(message);
		}
	}
	
	/**
	 * ����� append �� null-safe ����� ��� BusinessDocuments ��� ���������� �� ����������.
	 * <br>�� ��� �� ��� documents ����� null ���� ��� ����� ������ ��� ���������� null
	 * <br>�� ����� ��� �� ��� documents not-null ���� ����� append �� extraDoc ��� ����� 
	 * ��� baseDoc ��� ���������� �� extraDoc.
	 * <br>�� ����� �� baseDoc ����� null ���� �� extraDoc ��� ���� ���������� �� extraDoc.
	 * @param baseDoc �� ������ document ��� ����� �� ������� append.
	 * @param extraDoc �� document �� ����� �� ���� ��� ����� ��� �������.
	 * @param addPageBreak �� ����� true ����� ��� pageBreak ������ ��� ��� �������.
	 * @return resultDoc �� ���������� ��� append
	 * @throws DocumentEngineException
	 */
	public static BusinessDocument safeAppend
	(BusinessDocument baseDoc, BusinessDocument extraDoc, boolean addPageBreak) 
	throws DocumentEngineException{
		if(baseDoc!= null && addPageBreak && extraDoc != null){
			baseDoc.pageBreak();
		}		
		BusinessDocument resultDoc = baseDoc;
		if(baseDoc!= null && extraDoc!=null ){
			baseDoc.append(extraDoc);
			resultDoc = baseDoc;
		}
		else if(baseDoc == null && extraDoc!=null ){
			resultDoc = extraDoc;
		}		
		return resultDoc;
	}
	
	
}
