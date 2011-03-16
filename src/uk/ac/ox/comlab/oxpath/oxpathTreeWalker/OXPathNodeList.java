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
 * 
 */
package uk.ac.ox.comlab.oxpath.oxpathTreeWalker;

import static uk.ac.ox.comlab.oxpath.oxpathTreeWalker.OXPathType.OXPathTypes.NODESET;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.ac.ox.comlab.oxpath.BadDataException;
import uk.ac.ox.comlab.oxpath.XPathHelper;
import uk.ac.ox.comlab.oxpath.benchmark.BenchFactory;
import uk.ac.ox.comlab.oxpath.benchmark.BenchMarker;

import com.gargoylesoftware.htmlunit.html.DomNode;

/**
 * 
 * Extends <tt>DomNode</tt> instead of <tt>Node</tt> because we will use <tt>DomNode</tt> specific methods like <tt>getByXPath()</tt>.  Implemented as
 * contracted by Java library interfaces except where noted.  In particular, for efficiency, lists only contain each element at most one time (set-based
 * paradigm)
 * @author AndrewJSel
 *
 */
public class OXPathNodeList<T extends OXPathDomNode> implements NodeList, List<T>, Comparator<T> {
//        , Collection<T>, Iterable<T> {

	public OXPathNodeList() {}
	
	/* (non-Javadoc)
	 * @see org.w3c.dom.NodeList#getLength()
	 */
	@Override
	public int getLength() {
		return nodelist.size();
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.NodeList#item(int)
	 */
	@Override
	public Node item(int index) {
		return nodelist.get(index).getNode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
//	@Override
//	public Iterator<T> iterator() {
//		return new Iterator;
//	}

	/**
	 * Adopts set paradigm; only adds an object if it isn't present already
	 */
	@Override
	public boolean add(T arg0) {
		return  (!nodelist.contains(arg0)) ? nodelist.add(arg0) : false;
	}

	@Override
	public void add(int arg0, T arg1) {
		if (!nodelist.contains(arg1)) nodelist.add(arg0,arg1);
	}

	/**
	 * Returns <true> if any value is added because it's not already present in the list; false otherwise
	 */
	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		boolean rv = false;
		for (T iArg : arg0) {
			if (!this.nodelist.contains(iArg)) {
				rv=nodelist.add(iArg);				
			}
		}
		return rv;
	}

	/**
	 * Returns <true> if any value is added because it's not already present in the list; false otherwise
	 */
	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		boolean rv = false;
		int count = arg0;
		for (T iArg : arg1) {
			if (!nodelist.contains(iArg)) {
				nodelist.add(count,iArg);
				rv=true;
				count++;
			}
		}
		return rv;
	}
	
	/**
	 * Adds list elements only if they are compatible
	 * @param arg0 list to add to data structure
	 */
	@SuppressWarnings("unchecked")
	public void addList(List<?> arg0) {
		for (Object iArg : arg0) {
			if (iArg instanceof OXPathDomNode) {
				this.add((T) iArg);
			}
		}
	}
	
	@Override
	public void clear() {
		nodelist.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return nodelist.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return nodelist.containsAll(arg0);
	}

	@Override
	public T get(int arg0) {
		return nodelist.get(arg0);
	}

	@Override
	public int indexOf(Object arg0) {
		return nodelist.indexOf(arg0);
	}

	@Override
	public boolean isEmpty() {
		return nodelist.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return nodelist.iterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return nodelist.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<T> listIterator() {
		return nodelist.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		return nodelist.listIterator(arg0);
	}

	@Override
	public boolean remove(Object arg0) {
		return nodelist.remove(arg0);
	}

	@Override
	public T remove(int arg0) {
		return nodelist.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return nodelist.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return nodelist.retainAll(arg0);
	}

	@Override
	public T set(int arg0, T arg1) {
		return nodelist.set(arg0, arg1);
	}

	@Override
	public int size() {
		return nodelist.size();
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		return nodelist.subList(arg0,arg1);
	}

	@Override
	public Object[] toArray() {
		return nodelist.toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] arg0) {
		return nodelist.toArray(arg0);
	}
	
	/**
	 * Calls HtmlUnit's getByXPath method over the entire context and returns an (emulated) sorted set
	 * @param path XPath to pass to HtmlUnit 
	 * @throws BadDataException in case of bad data
	 */
	public OXPathNodeList<T> getByXPath(String path) throws BadDataException {
		return this.getByXPath(path, true);
	}
	
	/**
	 * Calls HtmlUnit's getByXPath method over the entire context and returns an (emulated) sorted set.  Used for a backwards axis.
	 * @param path XPath to pass to HtmlUnit 
	 * @throws BadDataException in case of bad data
	 */
	public OXPathNodeList<T> getByXPathBackwardsAxis(String path) throws BadDataException {
		return this.getByXPath(path, false);
	}
	
	/**
	 * Calls HtmlUnit's getByXPath method over the entire context and returns an (emulated) sorted set
	 * @param path XPath to pass to HtmlUnit
	 * @param forward <tt>true</tt> if sort by document order (because of forward axis) or <tt>false</tt> if sort by reverse document order (because of inverse axis)
	 * @throws BadDataException in case of bad data
	 */
	public OXPathNodeList<T> getByXPath(String path, boolean forward) throws BadDataException {
		OXPathNodeList<T> result = new OXPathNodeList<T>();
		BenchMarker xalanOverhead = BenchFactory.newBenchMarker("xalanOverhead");
		xalanOverhead.start();
		for(T iCC : this.nodelist) {
			DomNode iC = iCC.getNode();
			//since we are passing in XPath, no extraction are encountered, so parent and current are the same
			OXPathType iResult = new OXPathType(iC.getByXPath(path), iCC.getParent(), iCC.getLast());
			if (iResult.isType().equals(NODESET)) {
				result.addList(iResult.nodeList());
			}
//			else {
//				xalanOverhead.finish();
//				return result;
//			}
		}
		xalanOverhead.finish();
		if (forward) Collections.sort(result, this);
		else Collections.sort(result, Collections.reverseOrder(this));
		return result;
	}
	
	/**
	 * Uses XPathHelper to compare and sort nodes
	 * @param o1 first OXPathDomNode to compare
	 * @param o2 second OXPathDomNode to compare
	 * @return standard Java Comparator convention
	 */
	@Override
	public int compare(T o1, T o2) {
		Node n1 = o1.getNode();
		Node n2 = o2.getNode();
		return new XPathHelper().compareDocumentOrder(n1, n2);
	}

	
	/**
	 * structure holding state data
	 */
	private ArrayList<T> nodelist = new ArrayList<T>();
	
}