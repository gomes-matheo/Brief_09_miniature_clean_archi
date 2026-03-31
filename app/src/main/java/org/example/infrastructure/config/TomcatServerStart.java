package org.example.infrastructure.config;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.example.controller.FeedServlet;
import org.example.controller.LikeServlet;
import org.example.controller.LoginServlet;
import org.example.controller.LogoutServlet;
import org.example.controller.PostDetailServlet;
import org.example.controller.PostServlet;
import org.example.controller.RegisterServlet;
import org.example.infrastructure.services.IWebServerStart;


public class TomcatServerStart implements IWebServerStart {

    private static final int TOMCAT_PORT = 8080;
    private static final String WEBAPP_DIR = "src/main/webapp";
    private static final String CLASSES_DIR = "build/classes/java/main";
    private static final String WEBAPP_MOUNT = "/WEB-INF/classes";

    private Tomcat tomcat;

    private Context ctx = null;

    private TomcatServerStart(Tomcat tomcat) {
        this.tomcat = tomcat;
    }

    /**
     * Create a new Tomcat server
     * @return the created tomcat server
     */
    public static TomcatServerStart create() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(TOMCAT_PORT);
        tomcat.getConnector();

        return new TomcatServerStart(tomcat);
    }

    /**
     * Create the webapp from the specified Tomcat server
     * @param tomcat : tomcat server
     * @return the context of this Tomcat server
     */
    public TomcatServerStart createWebapp() {

        Context ctx = tomcat.addWebapp("", new File(WEBAPP_DIR).getAbsolutePath());
        WebResourceRoot resources = new StandardRoot(ctx);

        resources.addPreResources(new DirResourceSet(
                resources,
                WEBAPP_MOUNT,
                new File(CLASSES_DIR).getAbsolutePath(),
                "/"));
        ctx.setResources(resources);
        
        this.ctx = ctx;

        return this;
    }

    /**
     * Create the servlet mappings from the specified context of the Tomcat server
     * @param ctx : context from specified Tomcat server
     */
    public TomcatServerStart mapServlets() {
        Tomcat.addServlet(ctx, "feedServlet", new FeedServlet());
        ctx.addServletMappingDecoded("/feed", "feedServlet");

        Tomcat.addServlet(ctx, "loginServlet", new LoginServlet());
        ctx.addServletMappingDecoded("/login", "loginServlet");

        Tomcat.addServlet(ctx, "registerServlet", new RegisterServlet());
        ctx.addServletMappingDecoded("/register", "registerServlet");

        Tomcat.addServlet(ctx, "logoutServlet", new LogoutServlet());
        ctx.addServletMappingDecoded("/logout", "logoutServlet");

        Tomcat.addServlet(ctx, "postServlet", new PostServlet());
        ctx.addServletMappingDecoded("/post", "postServlet");

        Tomcat.addServlet(ctx, "postDetailServlet", new PostDetailServlet());
        ctx.addServletMappingDecoded("/post-detail", "postDetailServlet");

        Tomcat.addServlet(ctx, "likeServlet", new LikeServlet());
        ctx.addServletMappingDecoded("/like", "likeServlet");

        return this;
    }

    /**
     * Starts the Tomcat server and freezes the program.
     * Since it is freezing the program, it should be the LAST instruction of where it is used !
     * @param tomcat : The tomcat server to start
     */
    public void build() {
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Miniature started on http://localhost:8080");
        tomcat.getServer().await();
    }
}