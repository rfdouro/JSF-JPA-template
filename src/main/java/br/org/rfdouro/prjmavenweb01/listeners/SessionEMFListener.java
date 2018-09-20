/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.prjmavenweb01.listeners;

import br.org.rfdouro.prjmavenweb01.util.JPAUtil;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author romulo.douro
 */
public class SessionEMFListener implements HttpSessionListener {

 private static int totalActiveSessions;

 public static int getTotalActiveSession() {
  return totalActiveSessions;
 }

 @Override
 public void sessionCreated(HttpSessionEvent arg0) {
  EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
  arg0.getSession().setAttribute("EMF", emf);
 }

 @Override
 public void sessionDestroyed(HttpSessionEvent arg0) {
  Object e = arg0.getSession().getAttribute("EMF");
  if (e != null) {
   EntityManagerFactory emf = (EntityManagerFactory) e;
   emf.close();
   emf = null;
   arg0.getSession().setAttribute("EMF", emf);
  }
 }
}
