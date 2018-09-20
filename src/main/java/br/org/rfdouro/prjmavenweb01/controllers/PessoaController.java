/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.prjmavenweb01.controllers;

import br.org.rfdouro.prjmavenweb01.models.Pessoa;
import br.org.rfdouro.prjmavenweb01.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author romulo.douro
 */
@ManagedBean(name = "PessoasBean")
@SessionScoped
public class PessoaController implements Serializable {

 private Pessoa pessoa = new Pessoa();

 public Pessoa getPessoa() {
  return pessoa;
 }

 public void setPessoa(Pessoa pessoa) {
  this.pessoa = pessoa;
 }

 private EntityManagerFactory getEMF() {
  FacesContext facesContext = FacesContext.getCurrentInstance();
  HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
  Object o = session.getAttribute("EMF");
  if (o != null) {
   return (EntityManagerFactory) o;
  }
  return null;
 }

 public void adicionaPessoa() {
  //EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
  EntityManagerFactory emf = this.getEMF();
  if (emf != null) {
   EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
   entityManager.getTransaction().begin();
   entityManager.persist(this.pessoa);
   entityManager.getTransaction().commit();
   this.pessoa = new Pessoa();
   entityManager.clear();
   entityManager.close();
  }
 }

 public void excluiPessoa(long id){
  EntityManagerFactory emf = this.getEMF();
  if (emf != null) {
   EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
   entityManager.getTransaction().begin();
   Pessoa p = entityManager.find(Pessoa.class, id);
   entityManager.remove(p);
   entityManager.getTransaction().commit();
   this.pessoa = new Pessoa();
   entityManager.clear();
   entityManager.close();
  }
 }

 public List<Pessoa> getPessoas() {
  EntityManagerFactory emf = this.getEMF();
  List<Pessoa> l = null;
  if (emf != null) {
   EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
   entityManager.getTransaction().begin();
   l = entityManager.createQuery("select p from Pessoa p", Pessoa.class).getResultList();
   entityManager.clear();
   entityManager.close();
  }
  return l;
 }
}
