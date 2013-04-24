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
package gr.interamerican.bo2.jsqlparser.op;

import gr.interamerican.bo2.JsqlParser;
import gr.interamerican.bo2.jsqlparser.visitors.RebuildQueryWithOutUselessJoins;
import gr.interamerican.bo2.utils.sql.exceptions.SqlParseException;

import java.io.StringReader;
import java.util.Set;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.JSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.Select;

/**
 * Removes useless joins from an SQL statement.
 */
public class RemoveUselessJoinsFromSql {
	
	/**
	 * Singleton.
	 */
	public static final RemoveUselessJoinsFromSql INSTANCE = new RemoveUselessJoinsFromSql();

	/**
	 * Removes useless joins from an SQL statement.
	 * 
	 * @param sql
	 * @return SQL with no useless joins.
	 * @throws SqlParseException
	 */
	public String remove(String sql) throws SqlParseException  {
		Set<Join> joins = UselessJoinsFinder.INSTANCE.find(sql);
		if (joins.size() == 0) {// nothing to remove
			JsqlParser.getLogger().debug("No Joins where removed"); //$NON-NLS-1$
			return sql;
		} else {
			JsqlParser.getLogger().debug("Removing " + joins.size() + " joins"); //$NON-NLS-1$ //$NON-NLS-2$
			JsqlParser.getLogger().debug("Original Query " + sql); //$NON-NLS-1$
			if (JsqlParser.getLogger().isTraceEnabled()) {
				for (Join j : joins) {
					JsqlParser.getLogger().trace("Removing " + j.toString()); //$NON-NLS-1$
				}
			}
		}
		RebuildQueryWithOutUselessJoins rebuild = new RebuildQueryWithOutUselessJoins();
		rebuild.setUselessJoins(joins);
		JSqlParser parser = new CCJSqlParserManager();
		Statement st;
		try {
			st = parser.parse(new StringReader(sql));
		} catch (JSQLParserException e) {
			throw new SqlParseException(e);
		}
		if (!(st instanceof Select)) {
			throw new SqlParseException("Statement type not supported"); //$NON-NLS-1$
		}
		Select plain = (Select) st;
		plain.accept(rebuild);
		String rebuiltSql = rebuild.getResult();
		JsqlParser.getLogger().debug("Rebuilt Query " + rebuiltSql); //$NON-NLS-1$
		return rebuiltSql;
	}
	
	/**
	 * Hidden. 
	 */
	private RemoveUselessJoinsFromSql() {/*empty*/}
	
}
