/*
 * Copyright (c)2011, DIADEM Team
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the DIADEM team nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DIADEM Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/**
 * Package containing supporting classes, derived from the OXPath model (which itself extends the XPath model).
 * This subpackage includes classes and interface relating to the OXPath language. 
 */
package uk.ac.ox.comlab.diadem.oxpath.model.language;

import uk.ac.ox.comlab.diadem.oxpath.model.OXPathContextNode;
import uk.ac.ox.comlab.diadem.oxpath.model.OXPathNodeList;
import uk.ac.ox.comlab.diadem.oxpath.model.OXPathType;
import uk.ac.ox.comlab.diadem.oxpath.utils.OXPathException;

/**
 * implementation of selector predicates
 * @author AndrewJSel
 *
 */
public class SelectorPredicate implements OXPathPredicate {

	/**
	 * basic constructor
	 * @param select the selector to be used
	 * @param aName the attribute name
	 */
	public SelectorPredicate(Selector select, String aName) {
		this.selector = select;
		this.attributeName = new OXPathType(aName);
	}
	
	/**
	 * evaluates input by the operation and returns the result; call this method when no <tt>position()</tt> or <tt>last()</tt> node tests occur inside subsequent predicates (or this one) in this list.
	 * @param contextNode context node
	 * @return value of expression
	 * @throws OXPathException in case of exception in nested calls
	 */
	public OXPathType evaluateIterative(OXPathContextNode contextNode) throws OXPathException {
		return this.selector.evaluateIterative(contextNode, this.attributeName);
	}
	
	/**
	 * evaluates input by the operation and returns the result; call this method when <tt>position()</tt> or <tt>last()</tt> node tests occur inside subsequent predicates (or this one) in this list.
	 * @param contextSet context set
	 * @return value of expression
	 * @throws OXPathException in case of exception in nested calls
	 */
	public OXPathType evaluateSet(OXPathNodeList<OXPathContextNode> contextSet) throws OXPathException {
		return this.selector.evaluateSet(contextSet, this.attributeName);
	}
	
	/**
	 * returns the type of the predicate
	 * @return the type of the predicate
	 */
	public OXPathPredicateTypes getType() {
		return this.selector.getType();
	}
	
	/**
	 * returns a {@code String} representation of the selector predicate
	 * @return a {@code String} representation of the selector predicate
	 */
	public String getValue() {
		return selector.getValue() + this.attributeName;
	}
	
	/**
	 * instance field for the selector
	 */
	private final Selector selector;
	
	/**
	 * instance field for the attribute name
	 */
	private final OXPathType attributeName;

}
