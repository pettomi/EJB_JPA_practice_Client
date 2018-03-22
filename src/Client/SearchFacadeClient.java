package Client;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.company.SearchFacade;






/**
 * Servlet implementation class SearchFacadeClient
 */
@WebServlet(name = "SearchFacadeClient", urlPatterns = { "/SearchFacadeClient" })
public class SearchFacadeClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	@Resource(lookup="java:global/RemoteClient-0.0.1-SNAPSHOT/SearchFacadeBean!com.company.SearchFacade")
	SearchFacade searchFacade;



	/**
	 * @throws NamingException 
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchFacadeClient() throws NamingException {
		super();
	//	doLookup();
		
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	 	//searchFacade.createCustomer("Andi");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet SearchFacadeClient</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1> Starting Search Facade test ... </h1>");

			out.println("<h1>SearchFacade Lookup</h1>");
			out.println("<h1>Searching Customers</h1>");
			List<String> customerList = searchFacade.customerSearch("Andi");
			out.println("<h1>Printing Customer list</h1>");
			for (String customer : (List<String>) customerList) {
				out.println("<h1>" + customer + "</h1>");
			}
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	@Override
	 public String getServletInfo() {
	 return "Short description";
	 }

}
