package controllers;

import models.Person;
import mongoproxy.MongoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;


public class Application extends Controller {


    public  Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result testDataInsert() {

        try {
            ApplicationContext ctx =
                    new AnnotationConfigApplicationContext(MongoConfiguration.class);
            MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

            mongoOperation.save(new Person("ABCDEF", "password123"));
            mongoOperation.save(new Person("ABCDEJ", "password123"));
            mongoOperation.save(new Person("ABCDEK", "password123"));
        } catch (BeansException e) {
            internalServerError(e.toString());
        }
        return ok("The Data is Created");
    }

}