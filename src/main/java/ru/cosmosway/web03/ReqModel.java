package ru.cosmosway.web03;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class ReqModel {
    @PersistenceContext
    private EntityManager em;

    private Req req = new Req();

    @Transactional
    public void addReq() {
        if (req == null) req = new Req();
        em.getTransaction().begin();
        em.persist(req);
        em.getTransaction().commit();
    }
    @Transactional
    public void addEntryWithParameters() {
        if (req == null) req = new Req();
        Map<String, String> paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        req.setX(Double.parseDouble(paramMap.get("x")));
        req.setY(Double.parseDouble(paramMap.get("y")));
        req.setR(Double.parseDouble(paramMap.get("r")));

        em.persist(req);
        req = new Req();
    }

    public List<Req> getReqs() {
        TypedQuery<Req> query = em.createQuery("SELECT r FROM Req r", Req.class);
        return query.getResultList();
    }

    public void deleteReq(Req req) {
        Req toBeDeleted = em.find(Req.class, req.getId());
        if (toBeDeleted != null) {
            em.remove(toBeDeleted);
        }
    }

    public Req getReq() {
        return req;
    }

    public void setReq(Req req) {
        this.req = req;
    }
}

