package org.descartes;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class ProjetAJDCloudServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String nom = req.getParameter("nom");
		
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("personne");
		entity.setProperty("nom", nom);
		dataStore.put(entity);
		
		Query query = new Query("personne");
		PreparedQuery pq = dataStore.prepare(query);
		
		Iterable<Entity> it = pq.asIterable();
		
		try{
			getServletContext().getRequestDispatcher("/listePersonnes.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
