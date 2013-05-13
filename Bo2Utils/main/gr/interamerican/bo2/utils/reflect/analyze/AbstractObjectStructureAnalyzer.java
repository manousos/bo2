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
package gr.interamerican.bo2.utils.reflect.analyze;

import gr.interamerican.bo2.utils.ReflectionUtils;
import gr.interamerican.bo2.utils.StringUtils;
import gr.interamerican.bo2.utils.VariableDefinitionFactory;
import gr.interamerican.bo2.utils.adapters.AnyOperation;
import gr.interamerican.bo2.utils.beans.Tree;
import gr.interamerican.bo2.utils.reflect.beans.VariableDefinition;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class that extracts the structure of an object to a tree.
 */
public abstract class AbstractObjectStructureAnalyzer 
implements AnyOperation<Object, Tree<VariableDefinition<?>>> {
	
	/**
	 * Logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(AbstractObjectStructureAnalyzer.class);
	
	/**
	 * If this is true, duplication checks are performed with the 
	 * <code>preExistingNodes</code> Map and duplicate entries are
	 * ignored.
	 * If this is false, duplicate entries in the structure are
	 * allowed. Use this setting with caution, as it may cause a
	 * StackOverFlowException.
	 */
	protected boolean ignoreDuplicates = true;
	
	/**
	 * Set with the types that are not further analyzed.
	 */
	Set<Class<?>> leafTypes = new HashSet<Class<?>>();
	
	/**
	 * Nodes already created.
	 */
	Map<Object, Tree<VariableDefinition<?>>> preExistingNodes = 
		new HashMap<Object, Tree<VariableDefinition<?>>>();
	
	/**
	 * Creates a new AbstractTreeCreator object. 
	 *
	 */
	public AbstractObjectStructureAnalyzer() {
		super();
		leafTypes.add(int.class);
		leafTypes.add(byte.class);
		leafTypes.add(char.class);
		leafTypes.add(long.class);
		leafTypes.add(double.class);
		leafTypes.add(float.class);
		leafTypes.add(boolean.class);
		leafTypes.add(Integer.class);
		leafTypes.add(Byte.class);
		leafTypes.add(Character.class);
		leafTypes.add(Long.class);
		leafTypes.add(Double.class);
		leafTypes.add(Float.class);
		leafTypes.add(Boolean.class);		
		leafTypes.add(Calendar.class);		
		leafTypes.add(String.class);
		leafTypes.add(Number.class);
		leafTypes.add(Date.class);
		leafTypes.add(java.sql.Date.class);
		leafTypes.add(Calendar.class);
		leafTypes.add(XMLGregorianCalendar.class);
		leafTypes.add(Time.class);
		leafTypes.add(Timestamp.class);
		leafTypes.add(Enum.class);
	}
	
	public Tree<VariableDefinition<?>> execute(Object a) {
		VariableDefinition<?> root = createVariableDefinition(a);
		Tree<VariableDefinition<?>> tree = createRootNode(root);
		preExistingNodes.clear();		
		if (!isLeaf(root.getType())) {
			if(ignoreDuplicates) {
				preExistingNodes.put(a, tree);
			}
			addNodes(tree);		
		} 
		return tree;
	}
	
	/**
	 * Defines which structural elements of a class should be included
	 * in the tree structure.
	 * 
	 * @param object
	 *        Object to analyze.
	 * 
	 * @return Returns a list containing the structural elements of the
	 *         class that should be included.
	 */
	protected abstract List<VariableDefinition<?>> whichFieldsToInclude(Object object);
	
	/**
	 * Variable definition creation for objects found in the object structure.
	 * Sub-classes may override this to create more tailored VariableDefinition
	 * sub-types.
	 * 
	 * @param object
	 *        Object to create a VariableDefinition for.
	 *        
	 * @return VariableDefinition.
	 */
	protected VariableDefinition<?> createVariableDefinition(Object object) {
		return VariableDefinitionFactory.asVariableDefinition(object);
	}
	
	/**
	 * Creates a node from a VariableDefinition.
	 * 
	 * @param vd
	 *        Root element.
	 *        
	 * @return Returns the node.
	 */
	private static Tree<VariableDefinition<?>> createRootNode(VariableDefinition<?> vd) {
		return new Tree<VariableDefinition<?>>(vd,vd.getName());
	}
		
	/**
	 * Adds the nodes of an element.
	 * @param tree
	 */	
	private void addNodes(Tree<VariableDefinition<?>> tree) {
		VariableDefinition<?> root = tree.getRootElement();
		Class<?> clazz = root.getType();
		if (ReflectionUtils.isCollection(clazz)) {
			addNodesOfCollection(tree);
		} else if (ReflectionUtils.isArray(clazz)) {
			addNodesOfArray(tree);
		} else if (!isLeaf(clazz)) {
			addNodesOfSingleElement(tree);
		}
	}
	
	
	/**
	 * Adds the nodes to a single element.
	 * 
	 * @param tree
	 *        Single element.
	 */	
	private void addNodesOfSingleElement(Tree<VariableDefinition<?>> tree) {		
		VariableDefinition<?> root = tree.getRootElement();
		List<VariableDefinition<?>> fields = whichFieldsToInclude(root.getValue());
		for (VariableDefinition<?> field : fields) {						
			try {
				addFieldNode(field, tree);
			} catch (RuntimeException re) {
				@SuppressWarnings("nls")
				String msg = StringUtils.concat(						
					"Exception while creating node for field:", field.getName(),
					"\nException: ", re.toString(),
					"\nTree structure so far\n", tree.toString());				
				logger.error(msg);
			}
		}
	}
	
	/**
	 * Adds a node to a parent node.
	 * 
	 * @param branch
	 *        Parent node.
	 * @param element 
	 *        Child node.
	 */
	private void addNodeOfCollectionOrArray(Tree<VariableDefinition<?>> branch, Object element) {
		boolean isPreexisting = preExistingNodes.containsKey(element);
		if (!isPreexisting) {
			VariableDefinition<?> vd = createVariableDefinition(element);
			Tree<VariableDefinition<?>> node = createRootNode(vd);
			if (element!=null && (!isLeaf(vd.getType()))) {
				if(ignoreDuplicates) {
					preExistingNodes.put(element, node);
				}
			}
			addNodes(node);
			branch.add(node);						
		}
	}
	
	/**
	 * Adds the nodes of an array.
	 * 
	 * @param tree  
	 */
	private void addNodesOfArray(Tree<VariableDefinition<?>> tree) {
		VariableDefinition<?> branch = tree.getRootElement();
		Object owner = branch.getValue();
		if (owner!=null) {
			Object[] array = (Object[]) owner;
			for (Object object : array) {
				addNodeOfCollectionOrArray(tree, object);
			}	
		}
	}
	
	/**
	 * Adds the nodes of a collection.
	 * 
	 * @param tree
	 */	
	private void addNodesOfCollection(Tree<VariableDefinition<?>> tree) {
		VariableDefinition<?> branch = tree.getRootElement();
		Object owner = branch.getValue();
		if (owner!=null) {
			Collection<?> collection = (Collection<?>) owner;
			for (Object object : collection) {
				addNodeOfCollectionOrArray(tree, object);
			}	
		}
	}
	
	/**
	 * Indicates if the instances of a class are described as leafs in the tree 
	 * or if they form branches.
	 * 
	 * @param clazz
	 *        Class being checked.
	 * 
	 * @return Returns true if the instances of the class are described as leafs.
	 */
	private boolean isLeaf(Class<?> clazz) {
		if(clazz==null) {
			return true;
		}
		for (Class<?> leafType : leafTypes) {
			if (leafType.isAssignableFrom(clazz)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds the node of a specific field.
	 * 
	 * @param vd
	 *        Definition of the field to add.
	 * @param tree
	 *        Owner tree of the new node.
	 */	
	private void addFieldNode
	(VariableDefinition<?> vd, Tree<VariableDefinition<?>> tree) {
		boolean isPreexisting = preExistingNodes.containsKey(vd.getValue());			
		if (!isPreexisting) {
			Tree<VariableDefinition<?>> node = new Tree<VariableDefinition<?>>(vd, vd.getName());
			if (!isLeaf(vd.getType()) && vd.getValue()!=null) {
				if(ignoreDuplicates) {
					preExistingNodes.put(vd.getValue(), node);
				}
				addNodes(node);
			}
			tree.add(node);				
		}
	}
	

}
