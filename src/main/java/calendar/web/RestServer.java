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
import calendar.futharks.Rune;
import calendar.RunicCalendar;
import calendar.symbols.RunicDay;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
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
    router.get("/calendar/day").handler(this::handleGetDay);
    router.get("/calendar/month").handler(this::handleGetMonth);
    router.get("/calendar/moon").handler(this::handleGetMoon);
    router.get("/calendar/year").handler(this::handleGetYear);

    vertx.createHttpServer().requestHandler(router).listen(8080);
  }

  private void handleGetCalendar(RoutingContext routingContext) {
    handleGetDay(routingContext);
  }

  private void handleGetDay(RoutingContext routingContext) {

    final DateDO dateDO = getDate(routingContext.request());
    if ( !dateDO.isValid ) {
      routingContext.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end(dateDO.errorMsg);
      return;
    }

    RunicCalendar rc = new RunicCalendar(dateDO.year, Futharks.OLD_ENGLISH);
    final List<RunicDay> calendar = rc.getCalendar(dateDO.mmonth);

    final Rune sundayRune = rc.getSundayRune(dateDO.mmonth);
    final RunicDay runicDay = calendar.get(dateDO.day-1);
    final Rune moon = rc.getMoonRune();

    JsonObject reply = new JsonObject()
            .put("day", dateDO.day)
            .put("month", dateDO.mmonth.getValue())
            .put("year", dateDO.year)
            .put("runicDay", JsonConverter.toJson(runicDay))
            .put("sunday", JsonConverter.toJson(sundayRune))
            .put("moon", JsonConverter.toJson(moon));

    routingContext.response().putHeader("content-type", "application/json").end(reply.encode());
  }

  private void handleGetMonth(RoutingContext routingContext) {

    DateDO dateDO = getDate(routingContext.request());
    if ( !dateDO.isValid ) {
      routingContext.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end(dateDO.errorMsg);
      return;
    }

    RunicCalendar rc = new RunicCalendar(dateDO.year, Futharks.OLD_ENGLISH);
    final List<RunicDay> calendar = rc.getCalendar(dateDO.mmonth);
    final Rune moonRune = rc.getMoonRune();
    final List<Rune> sundayRunes = rc.getSundayRunes();

    JsonObject reply = new JsonObject()
            .put("day", dateDO.day)
            .put("month", dateDO.mmonth.getValue())
            .put("year", dateDO.year)
            .put("runicMonth", JsonConverter.daysToJson(calendar))
            .put("sundays", JsonConverter.runesToJson(sundayRunes))
            .put("moon", JsonConverter.toJson(moonRune));

    routingContext.response().putHeader("content-type", "application/json").end(reply.encode());
  }

  private void handleGetYear(RoutingContext routingContext) {

    DateDO dateDO = getDate(routingContext.request());
    if ( !dateDO.isValid ) {
      routingContext.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end(dateDO.errorMsg);
      return;
    }

    RunicCalendar rc = new RunicCalendar(dateDO.year, Futharks.OLD_ENGLISH);

    final List<Rune> sundayRunes = rc.getSundayRunes();
    final Rune moonRune = rc.getMoonRune();

    JsonObject reply = new JsonObject()
            .put("day", dateDO.day)
            .put("month", dateDO.mmonth.getValue())
            .put("year", dateDO.year)
            .put("moon", JsonConverter.toJson(moonRune))
            .put("sundays", JsonConverter.runesToJson(sundayRunes));

    routingContext.response().putHeader("content-type", "application/json").end(reply.encode());
  }

  private void handleGetMoon(RoutingContext routingContext) {

    DateDO dateDO = getDate(routingContext.request());
    if ( !dateDO.isValid ) {
      routingContext.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end(dateDO.errorMsg);
      return;
    }

    RunicCalendar rc = new RunicCalendar(dateDO.year, Futharks.OLD_ENGLISH);

    final Rune moonRune = rc.getMoonRune();


    JsonObject reply = new JsonObject()
            .put("day", dateDO.day)
            .put("month", dateDO.mmonth.getValue())
            .put("year", dateDO.year)
            .put("moon", JsonConverter.toJson(moonRune))
            .put("futhark", JsonConverter.runesToJson(Arrays.asList(Futharks.OLD_ENGLISH)));

    routingContext.response().putHeader("content-type", "application/json").end(reply.encode());
  }

  private DateDO getDate(HttpServerRequest request) {

    DateDO dateDO = new DateDO();

    LocalDate now = LocalDate.now();

    final String year = request.getParam("year", "" + now.getYear());
    final String month = request.getParam("month", "" + now.getMonth().getValue());
    final String day = request.getParam("day", "" + now.getDayOfMonth());

    dateDO.year = Integer.parseInt(year);
    dateDO.month = Integer.parseInt(month);
    if ( dateDO.month < 1 || dateDO.month > 12 ) {
      dateDO.isValid = false;
      dateDO.errorMsg = "Invalid month: " + dateDO.month;
      return dateDO;
    }
    dateDO.mmonth = Month.of(dateDO.month);

    dateDO.day = Integer.parseInt(day);
    if ( dateDO.day < 1 || dateDO.day > dateDO.mmonth.length(false) ) {
      dateDO.errorMsg = "Invalid day: " + dateDO.day;
      dateDO.isValid = false;
      return dateDO;
    }

    dateDO.isValid = true;
    return dateDO;
  }

  private static class DateDO {
    int day;
    int month;
    int year;

    Month mmonth;

    boolean isValid;
    String errorMsg;

  }

}