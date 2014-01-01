package com.andrewkravets.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by Andrew Kravets.
 * Date: 1/1/14.
 * Url: andrewkravets.com
 */
public class Main {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:from/?noop=true").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("Найден и загружен файл: "
                                + exchange.getIn().getHeader("CamelFileName"));
                    }
                }).to("file:to/");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}
