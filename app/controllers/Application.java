package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import models.*;

public class Application extends Controller {
    
    public static String newsGlobal= "Tech Meme";
    public static String global= "http://www.techmeme.com/feed.xml";

    public static Result index(String news, String source) {
	/* Supply any number of RSS feeds and trying to randomize it across news websites
	   Replace with say 
			
		http://www.techmeme.com/feed.xml (default)
		http://www.hindu.com/rss/nushdline.xml
		http://timesofindia.feedsportal.com/c/33039/f/533916/index.rss
		http://feeds.bbci.co.uk/news/rss.xml

	  on click of a button
	*/
	RSSFeedParser parser = new RSSFeedParser(news);
    	Feed feed = parser.readFeed();
    	System.out.println(feed);
    	for (FeedMessage message : feed.getMessages()) {
      		System.out.println(message);
    	}
        return ok(index.render(feed, source));
    }
    /*
       Randomize on refresh
    */
    public static Result refresh() {
     
       return redirect(routes.Application.index(global, newsGlobal));
    }

    public static Result techmeme() {
     
       newsGlobal = "Tech Meme";
       global = "http://www.techmeme.com/feed.xml";
       return redirect(routes.Application.index(global, "Tech Meme"));
    }

    public static Result hindu() {
       
       newsGlobal = "Hindu News Update";
       global = "http://www.hindu.com/rss/nushdline.xml";
       return redirect(routes.Application.index(global, "Hindu News Update"));
    }

    public static Result toi() {
       
       newsGlobal = "Times of India";
       global = "http://timesofindia.feedsportal.com/c/33039/f/533916/index.rss";
       return redirect(routes.Application.index(global, "Times of India"));
    }

    public static Result bbc() {
	
       newsGlobal = "BBC Top Stories";
       global = "http://feeds.bbci.co.uk/news/rss.xml";
       return redirect(routes.Application.index(global, "BBC Top Stories"));
    }
}

