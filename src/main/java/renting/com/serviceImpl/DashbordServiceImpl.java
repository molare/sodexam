package renting.com.serviceImpl;

import org.springframework.stereotype.Service;
import renting.com.service.DashbordService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olivier on 20/12/2019.
 */
@Service("dashbordService")
public class DashbordServiceImpl implements DashbordService {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Object> firstYearPayRollChart() {

        String sql = "";
        int numberYear =13;

        for(int i=1 ; i < numberYear; i++){

            if(i == numberYear-1){
                sql += "SELECT COALESCE(SUM(lb.payroll_amount),0)\n" +
                        "FROM customer l, customer_enter lb, product b, enter ent \n" +
                        "WHERE lb.customer_id = l.id \n" +
                        "AND lb.enter_id= ent.id \n" +
                        "AND ent.product_id= b.id \n" +
                        "AND EXTRACT(YEAR from l.created_date) = (EXTRACT(YEAR from NOW()))\n" +
                        "AND EXTRACT(MONTH FROM l.created_date) ="+i;
            }else{
                sql += "SELECT COALESCE(SUM(lb.payroll_amount),0)\n" +
                        "FROM customer l, customer_enter lb, product b, enter ent \n" +
                        "WHERE lb.customer_id = l.id \n" +
                        "AND lb.enter_id= ent.id \n" +
                        "AND ent.product_id= b.id \n" +
                        "AND EXTRACT(YEAR from l.created_date) = (EXTRACT(YEAR from NOW()))\n" +
                        "AND EXTRACT(MONTH FROM l.created_date) ="+i+" UNION ALL\n";
            }
        }
        Query query =em.createNativeQuery(sql);
        List<Object> results =query.getResultList();
        return results;
    }

    @Override
    public List<Object> secondYearPayRollChart() {
        String sql = "";
        int numberYear =13;

        for(int i=1 ; i < numberYear; i++){

            if(i == numberYear-1){
                sql += "SELECT COALESCE(SUM(lb.payroll_amount),0)\n" +
                        "FROM customer l, customer_enter lb, product b, enter ent \n" +
                        "WHERE lb.customer_id = l.id \n" +
                        "AND lb.enter_id= ent.id \n" +
                        "AND ent.product_id= b.id \n" +
                        "AND EXTRACT(YEAR from l.created_date) = (EXTRACT(YEAR from NOW())-1)\n" +
                        "AND EXTRACT(MONTH FROM l.created_date) ="+i;
            }else{
                sql += "SELECT COALESCE(SUM(lb.payroll_amount),0)\n" +
                        "FROM customer l, customer_enter lb, product b, enter ent \n" +
                        "WHERE lb.customer_id = l.id \n" +
                        "AND lb.enter_id= ent.id \n" +
                        "AND ent.product_id= b.id \n" +
                        "AND EXTRACT(YEAR from l.created_date) = (EXTRACT(YEAR from NOW())-1)\n" +
                        "AND EXTRACT(MONTH FROM l.created_date) ="+i+" UNION ALL\n";
            }
        }
        Query query =em.createNativeQuery(sql);
        List<Object> results =query.getResultList();
        return results;
    }

    @Override
    public List<Object> threeYearPayRollChart() {
        String sql = "";
        int numberYear =13;

        for(int i=1 ; i < numberYear; i++){

            if(i == numberYear-1){
                sql += "SELECT COALESCE(SUM(lb.payroll_amount),0)\n" +
                        "FROM customer l, customer_enter lb, product b, enter ent \n" +
                        "WHERE lb.customer_id = l.id \n" +
                        "AND lb.enter_id= ent.id \n" +
                        "AND ent.product_id= b.id \n" +
                        "AND EXTRACT(YEAR from l.created_date) = (EXTRACT(YEAR from NOW())-2)\n" +
                        "AND EXTRACT(MONTH FROM l.created_date) ="+i;
            }else{
                sql += "SELECT COALESCE(SUM(lb.payroll_amount),0)\n" +
                        "FROM customer l, customer_enter lb, product b, enter ent \n" +
                        "WHERE lb.customer_id = l.id \n" +
                        "AND lb.enter_id= ent.id \n" +
                        "AND ent.product_id= b.id \n" +
                        "AND EXTRACT(YEAR from l.created_date) = (EXTRACT(YEAR from NOW())-2)\n" +
                        "AND EXTRACT(MONTH FROM l.created_date) ="+i+" UNION ALL\n";
            }
        }
        Query query =em.createNativeQuery(sql);
        List<Object> results =query.getResultList();
        return results;
    }
}
