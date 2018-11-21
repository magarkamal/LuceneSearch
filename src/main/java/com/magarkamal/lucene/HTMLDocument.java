package com.magarkamal.lucene;

import java.io.StringReader;

import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexableField;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.ParserException;

public class HTMLDocument {

	public static Document Document(String url) {
		// create empty document
		Document doc = new Document();
		String title = new String();
		String summary = new String();

		// Add the url as a field named "path". Use a field that is
		// indexed (i.e. searchable), but don't tokenize the field into words.
		doc.add(new StringField("path", url, StringField.Store.YES));
		

	    // Add the tag-stripped contents as a Reader-valued Text field so it will
	    // get tokenized and indexed.
	    StringBean sb = new StringBean ();
	    sb.setLinks (false);
	    sb.setURL (url);
	    
	    StringReader sr = new StringReader(sb.getStrings ());
	        
	    doc.add((IndexableField) new TextField("contents", sr));

	    Parser bParser;
	    NodeFilter bFilter;

	            try
	            {
	                bParser = new Parser ();
	                bFilter = new TagNameFilter ("TITLE");
	                bParser.setResource (url);
	                title = bParser.parse( bFilter).asString();
	       
	            }
	            catch (ParserException e)
	            {
	                e.printStackTrace ();
	            }
	    
	            try
	            {
	      
	                bParser = new Parser ();
	                bFilter = new TagNameFilter ("BODY");
	                bParser.setResource (url);
	                try
	                {
	                summary = bParser.parse( bFilter).asString().substring(0, 200);
	                }
	                catch(StringIndexOutOfBoundsException e)
	                {
	                   summary = "";
	                }
	                
	            }
	            catch (ParserException e)
	            {
	                e.printStackTrace ();
	            }

	    // Add the title as a field that it can be searched and that is stored.Fi
	    doc.add(new StringField("title", title, Field.Store.YES));
	    doc.add(new StringField("summary",summary, Field.Store.YES));
	    
		return doc;
	}
}
