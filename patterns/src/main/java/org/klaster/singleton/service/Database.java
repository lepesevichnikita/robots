/*
 * Database
 *
 * practice
 *
 * 14:28
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.singleton.service;

public class Database {

  private static Database database;

  private Database() {
  }

  public static Database getInstance() {
    synchronized (Database.class) {
      if (database == null) {
        database = new Database();
      }
    }
    return database;
  }
}
