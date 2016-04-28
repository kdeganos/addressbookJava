import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/contacts/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/contact-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/contacts", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String firstName = request.queryParams("firstName");
      String lastName = request.queryParams("lastName");
      String month = request.queryParams("month");
      Contact newContact = new Contact(firstName, lastName, month);

      model.put("template", "templates/contact-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/contacts", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("contacts", Contact.all());
      model.put("template", "templates/contacts.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/contacts/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Contact contact = Contact.find(Integer.parseInt(request.params(":id")));
      model.put("contact", contact);
      model.put("template", "templates/contact.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/contacts/:id/phones/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Contact contact = Contact.find(Integer.parseInt(request.params(":id")));
      model.put("contact", contact);
      model.put("template", "templates/contact-phones-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/phones", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Contact contact = Contact.find(Integer.parseInt(request.queryParams("contactId")));

      String areaCode = request.queryParams("areaCode");
      String number = request.queryParams("number");
      String type = request.queryParams("type");

      Phone newPhone = new Phone(areaCode, number, type);

      contact.addPhone(newPhone);

      model.put("contact", contact);
      model.put("template", "templates/contact-phones-success.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/contacts/:id/emails/new", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Contact contact = Contact.find(Integer.parseInt(request.params(":id")));
        model.put("contact", contact);
        model.put("template", "templates/contact-emails-form.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
  }


}
