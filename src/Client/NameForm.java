package Client;



import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;

import org.apache.wicket.model.Model;

import com.company.SearchFacade;


public class NameForm extends Form{
	
	private String name;
	private TextField<String> nameField;
	private RepeatingView resultNames;
	private ArrayList<String> names;
//	private ListView<String> resultNames;
	
	SearchFacade searchFacade;
private TextField<String> newnameField;


	public NameForm(String id){
		super(id);
		names=new ArrayList<String>();
		nameField=new TextField<String>("name", Model.of(""));
		newnameField=new TextField<String>("newname", Model.of(""));
		add(nameField);
		add(newnameField);
		resultNames=new RepeatingView("resultNames");
		add(resultNames);
		try {
			InitialContext ctx = new InitialContext();
	        searchFacade = (SearchFacade ) ctx.lookup
	        		("java:global/RemoteClient-0.0.1-SNAPSHOT/SearchFacadeBean!com.company.SearchFacade");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void onSubmit() {
		name=(String)nameField.getDefaultModelObject();
		System.out.println(name);
		try {
			names=(ArrayList<String>) searchFacade.customerSearch(name);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		String newname=(String)newnameField.getDefaultModelObject();
		if(newname!=null) {
			searchFacade.createCustomer(newname);
		}
		
		System.out.println("names: "+names);
		System.out.println(searchFacade.toString());
		resultNames.removeAll();
		names.stream().forEach((String i)->resultNames.add(new Label(resultNames.newChildId(), i)));
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		/*resultNames=(new ListView<String>("resultNames", names) {

			@Override
			protected void populateItem(ListItem<String> item) {
				String tmpname=(String)item.getDefaultModelObject();
				names.forEach((String s)-> item.add(new Label(s,s)));
				System.out.println("hello"+names);
			}
			
		});*/
	//	add(resultNames);
	}


}
