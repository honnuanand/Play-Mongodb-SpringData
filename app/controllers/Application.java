package controllers;

import models.Person;
import mongoproxy.MongoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * The main set of web services.
 */

public class Application extends Controller {

    /**
     * The purpose of this method is the initial index.
     * Need to move this into some more structured methods.
     *
     * @return a Result ( play.mvc.Result )
     */
    public Result index() {
        try {
            ApplicationContext ctx =
                    new AnnotationConfigApplicationContext(MongoConfiguration.class);
            MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

            //Lets add  some people into the Database
            mongoOperation.save(new Person("ABCDEF", "password123"));
            mongoOperation.save(new Person("ABCDEJ", "password123"));
            mongoOperation.save(new Person("ABCDEK", "password123"));
        } catch (BeansException e) {
            return internalServerError(e.toString());
        } 

        //return ok(views.html.index.render("Need  to Change the index page to "));
        return ok("The Data was  inserted.");


    }
}
