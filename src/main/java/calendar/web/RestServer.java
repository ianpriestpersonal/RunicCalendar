/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package calendar.web;

import calendar.futharks.Futharks;
import calendar.play.RunicCalendar;
import calendar.play.RunicDay;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

import java.time.LocalDate;
import java.util.List;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class RestServer extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", RestServer.class.getName());
  }

  @Override
  public void start() {

    Router router = Router.router(vertx);

    router.route().handler(BodyHandler.create());
    router.get("/calendar").handler(this::handleGetCalendar);

    vertx.createHttpServer().requestHandler(router).listen(8080);
  }

  private void handleGetCalendar(RoutingContext routingContext) {

    HttpServerResponse response = routingContext.response();

    LocalDate now = LocalDate.now();

    RunicCalendar rc = new RunicCalendar(now.getYear(), Futharks.OLD_ENGLISH);
    final List<RunicDay> calendar = rc.getCalendar(now.getMonth());
    response.putHeader("content-type", "application/json").end(JsonConverter.daysToJson(calendar).encode());
  }

}