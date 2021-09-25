/* *********************************************************************
 * ECE351 
 * Department of Electrical and Computer Engineering 
 * University of Waterloo 
 * Term: Fall 2021 (1219)
 *
 * The base version of this file is the intellectual property of the
 * University of Waterloo. Redistribution is prohibited.
 *
 * By pushing changes to this file I affirm that I am the author of
 * all changes. I affirm that I have complied with the course
 * collaboration policy and have not plagiarized my work. 
 *
 * I understand that redistributing this file might expose me to
 * disciplinary action under UW Policy 71. I understand that Policy 71
 * allows for retroactive modification of my final grade in a course.
 * For example, if I post my solutions to these labs on GitHub after I
 * finish ECE351, and a future student plagiarizes them, then I too
 * could be found guilty of plagiarism. Consequently, my final grade
 * in ECE351 could be retroactively lowered. This might require that I
 * repeat ECE351, which in turn might delay my graduation.
 *
 * https://uwaterloo.ca/secretariat-general-counsel/policies-procedures-guidelines/policy-71
 * 
 * ********************************************************************/

package ece351.w.svg;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.parboiled.common.ImmutableList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * A simple immutable data structure that just holds a list of pins and a list of lines.
 * To be constructed from SVG.
 * 
 * You do not need to edit this file.
 * 
 * @author drayside
 *
 */
public final class PinsLines {

	final ImmutableList<Pin> pins;
	final ImmutableList<Line> segments;

	PinsLines(final ImmutableList<Pin> pins, final ImmutableList<Line> segments) {
		this.pins = pins;
		this.segments = segments;
	}
	
	static PinsLines fromSVG(final URI uri, final boolean parseDOM) throws Exception {
		final List<Pin> pins = new ArrayList<Pin>();
		final List<Line> segments = new ArrayList<Line>();
		try {
			final Document doc;
			if (parseDOM) {
				final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				// configure the factory so it does not fetch the SVG DTD from w3.org
				factory.setValidating(false);
				factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
				// parse
				doc = factory.newDocumentBuilder().parse(uri.toString());
			} else {
		    	SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
		    	doc = f.createDocument(uri.toString());
			}
		    
		    for (Node node = doc.getDocumentElement().getFirstChild(); 
		    		node != null; 
		    		node = node.getNextSibling()) {
		    	if (node.getNodeType() == Node.ELEMENT_NODE) {
		    		final Pin p = Pin.fromSVG(node);
		    		if (p != null) {
		    			pins.add(p);
		    		}
		    		final Line line = Line.fromSVG(node);
		    		if (line != null) {
						// ignore dots (i.e., lines of zero length)
						if (!(line.x1 == line.x2 && line.y1 == line.y2)) {
							// it's not a dot, so keep it
							segments.add(line);
						}
		    		}
		    	}
		    }
		} catch (IOException e) {
		    throw e;
		} catch (Exception e) {
			throw e;
		}
		return new PinsLines(ImmutableList.copyOf(pins), ImmutableList.copyOf(segments));
	}
	
	/**
	 * If we override equals() then we must override hashCode().
	 */
	@Override
	public int hashCode() {
		int hash = 31;
		hash = hash * 13 + pins.hashCode();
		hash = hash * 13 + segments.hashCode();
		return hash;
	}

	/**
	 * WSVG objects are immutable, and so may be Liskov substitutable.
	 */
	@Override
	public boolean equals(final Object obj) {
		// basics
		if (obj == null) return false;
		if (!obj.getClass().equals(this.getClass())) return false;
		final PinsLines that = (PinsLines) obj;

		// compare pins and lines with List.equals()
		if (!this.pins.equals(that.pins)) return false;
		if (!this.segments.equals(that.segments)) return false;
		
		// no significant differences, return true
		return true;
	}

}
