package com.magarkamal.lucene;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

public class WebCrawler {
	private static IndexWriter writer; // new index being built
	private static ArrayList<String> indexed;
	private static String beginDomain;
	
	
	public static void indexLink(String link) throws Exception {
			
			String index = "C:\\Users\\magarkamal\\Desktop\\index";
		 	beginDomain = Domain(link);
	        System.out.println(beginDomain);
	        
	        FSDirectory dir = FSDirectory.open(Paths.get(index));
			writer = new IndexWriter(dir, new IndexWriterConfig(new StandardAnalyzer()) );
			
			
	        indexed = new ArrayList<String>();
	        
	        indexDocs(link);
	        
	        System.out.println("Optimizing...");
	        writer.forceMerge(100000);
	       /* writer.optimize();*/
	        writer.close();
	}

	 

	private static void indexDocs(String url) throws Exception {

	        //index page
	        Document doc = HTMLDocument.Document(url);
	        System.out.println("adding " + doc.get("path"));
	        try {
	            indexed.add(doc.get("path"));
	            writer.addDocument(doc);		  // add docs unconditionally
	            //TODO: only add html docs
	            //and create other doc types
	            

	            //get all links on the page then index them
	            LinkParser lp = new LinkParser(url);
	            URL[] links = lp.ExtractLinks();

	            for (URL l : links) {
	                //make sure the url hasnt already been indexed
	                //make sure the url contains the home domain
	                //ignore urls with a querystrings by excluding "?" 
	                if ((!indexed.contains(l.toURI().toString())) && (l.toURI().toString().contains(beginDomain)) && (!l.toURI().toString().contains("?"))) {
	                    //don't index zip files
	                    if (!l.toURI().toString().endsWith(".zip"))
	                    {
	                    System.out.print(l.toURI().toString());
	                    indexDocs(l.toURI().toString());
	                    }
	                }
	            }

	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	    }
	
	private static String Domain(String url)
	 {
	     int firstDot = url.indexOf(".");
	     int lastDot =  url.lastIndexOf(".");
	     return url.substring(firstDot+1,lastDot);
	 }

}
